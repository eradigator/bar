package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.FavoriteDao;
import kz.epam.javalab22.bar.dao.UITextDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.CocktailList;
import kz.epam.javalab22.bar.entity.Favorite;
import kz.epam.javalab22.bar.entity.UIText;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.FilterCocktailList;
import kz.epam.javalab22.bar.util.SortCocktailListByNameEn;
import kz.epam.javalab22.bar.util.SortCocktailListByNameRu;
import kz.epam.javalab22.bar.util.SortCocktailListByStrength;

import java.sql.Connection;

public class PageCocktailsCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        Connection connection = ConnectionPool.getInstance().getConnection();

        CocktailList cocktailList = new CocktailList(new CocktailDao(connection).getCocktailsList());
        CocktailList filteredCocktailList = filterCocktailList(reqWrapper, cocktailList);
        sortCocktailList(reqWrapper,filteredCocktailList);
        UIText uiText = getUIText(connection);
        fillFavorite(reqWrapper, connection, filteredCocktailList);

        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute(Const.ATTR_COCKTAIL_LIST, filteredCocktailList);
        reqWrapper.addAttribute(Const.ATTR_COCKTAIL_LIST_INDEX, getCocktailListIndex(reqWrapper));
        reqWrapper.addAttribute(Const.ATTR_UI_TEXT, uiText);
        reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_COCKTAILS);

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }

    private CocktailList filterCocktailList(ReqWrapper reqWrapper, CocktailList cocktailList) {

        CocktailList filteredCocktailList;

        if (null == reqWrapper.getParam("filter")) {
            reqWrapper.addAttribute("filter_checked_id", "all");
            filteredCocktailList = new FilterCocktailList().getAllAlco(cocktailList);
        } else {
            switch (reqWrapper.getParam("filter")) {
                case "nonalco":
                    reqWrapper.addAttribute("filter_checked_id", "nonalco");
                    filteredCocktailList = new FilterCocktailList().getNonAlco(cocktailList);
                    break;
                case "low":
                    reqWrapper.addAttribute("filter_checked_id", "low");
                    filteredCocktailList = new FilterCocktailList().getLowAlco(cocktailList);
                    break;
                case "middle":
                    reqWrapper.addAttribute("filter_checked_id", "middle");
                    filteredCocktailList = new FilterCocktailList().getMiddleAlco(cocktailList);
                    break;
                case "strong":
                    reqWrapper.addAttribute("filter_checked_id", "strong");
                    filteredCocktailList = new FilterCocktailList().getStrongAlco(cocktailList);
                    break;
                default:
                    reqWrapper.addAttribute("filter_checked_id", "all");
                    filteredCocktailList = new FilterCocktailList().getAllAlco(cocktailList);
                    break;
            }
        }

        return filteredCocktailList;
    }

    private void sortCocktailList(ReqWrapper reqWrapper, CocktailList cocktailList) {
        String paramSort = reqWrapper.getParam("sort");

        if (null == paramSort || paramSort.equals("by_name")) {
            reqWrapper.addAttribute("sort_checked_index", 0);

            switch (reqWrapper.getLocale().toString()) {
                case "ru_RU":
                    cocktailList.getCocktailList().sort(new SortCocktailListByNameRu());
                    break;
                case "en_US":
                    cocktailList.getCocktailList().sort(new SortCocktailListByNameEn());
                    break;
            }

        } else if (paramSort.equals("by_strength")) {
            cocktailList.getCocktailList().sort(new SortCocktailListByStrength());
            reqWrapper.addAttribute("sort_checked_index", 1);
        }
    }

    private void fillFavorite(ReqWrapper reqWrapper, Connection connection, CocktailList cocktailList) {
        User user = reqWrapper.getUser();
        if (null != user) {
            Favorite favorite = new FavoriteDao(connection).getList(user.getId());

            for (Cocktail cocktail : cocktailList.getCocktailList()) {
                if (favorite.getCocktailIds().contains(cocktail.getId())) {
                    cocktail.setFavorite(true);
                }
            }
        }
    }

    private int getCocktailListIndex(ReqWrapper reqWrapper) {

        int cocktailListIndex = 0;
        if (null != reqWrapper.getParam("cocktailListIndex")) {
            cocktailListIndex = Integer.parseInt(reqWrapper.getParam("cocktailListIndex"));
        }

        return cocktailListIndex;
    }

    private UIText getUIText(Connection connection) {
        int textId = Integer.parseInt(ConfigurationManager.getProperty(Const.PROP_UI_TEXT_FOR_ALCOHOLIC_PAGE));
        return new UITextDao(connection).get(textId);
    }

}
