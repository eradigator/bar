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

        if (null == reqWrapper.getParam(Const.PARAM_FILTER)) {
            reqWrapper.addAttribute(Const.ATTR_FILTER_CHECKED_ID, Const.VAL_ALL);
            filteredCocktailList = new FilterCocktailList().getAll(cocktailList);
        } else {
            switch (reqWrapper.getParam(Const.PARAM_FILTER)) {
                case Const.VAL_NON_ALCO:
                    reqWrapper.addAttribute(Const.ATTR_FILTER_CHECKED_ID, Const.VAL_NON_ALCO);
                    filteredCocktailList = new FilterCocktailList().getNonAlco(cocktailList);
                    break;
                case Const.VAL_LOW:
                    reqWrapper.addAttribute(Const.ATTR_FILTER_CHECKED_ID, Const.VAL_LOW);
                    filteredCocktailList = new FilterCocktailList().getLowAlco(cocktailList);
                    break;
                case Const.VAL_MIDDLE:
                    reqWrapper.addAttribute(Const.ATTR_FILTER_CHECKED_ID, Const.VAL_MIDDLE);
                    filteredCocktailList = new FilterCocktailList().getMiddleAlco(cocktailList);
                    break;
                case Const.VAL_STRONG:
                    reqWrapper.addAttribute(Const.ATTR_FILTER_CHECKED_ID, Const.VAL_STRONG);
                    filteredCocktailList = new FilterCocktailList().getStrongAlco(cocktailList);
                    break;
                default:
                    reqWrapper.addAttribute(Const.ATTR_FILTER_CHECKED_ID, Const.VAL_ALL);
                    filteredCocktailList = new FilterCocktailList().getAll(cocktailList);
                    break;
            }
        }

        return filteredCocktailList;
    }

    private void sortCocktailList(ReqWrapper reqWrapper, CocktailList cocktailList) {
        String paramSort = reqWrapper.getParam(Const.PARAM_SORT);

        if (null == paramSort || paramSort.equals(Const.VAL_BY_NAME)) {
            reqWrapper.addAttribute(Const.ATTR_SORT_CHECKED_INDEX, Const.INDEX_0);

            switch (reqWrapper.getLocale().toString()) {
                case Const.LOC_RU_RU:
                    cocktailList.getCocktailList().sort(new SortCocktailListByNameRu());
                    break;
                case Const.LOC_EN_US:
                    cocktailList.getCocktailList().sort(new SortCocktailListByNameEn());
                    break;
            }

        } else if (paramSort.equals(Const.VAL_BY_STRENGTH)) {
            cocktailList.getCocktailList().sort(new SortCocktailListByStrength());
            reqWrapper.addAttribute(Const.ATTR_SORT_CHECKED_INDEX, Const.INDEX_1);
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

        int cocktailListIndex = Const.N_0;
        if (null != reqWrapper.getParam(Const.PARAM_COCKTAIL_LIST_INDEX)) {
            cocktailListIndex = Integer.parseInt(reqWrapper.getParam(Const.PARAM_COCKTAIL_LIST_INDEX));
        }

        return cocktailListIndex;
    }

    private UIText getUIText(Connection connection) {
        int textId = Integer.parseInt(ConfigurationManager.getProperty(Const.PROP_UI_TEXT_FOR_ALCOHOLIC_PAGE));
        return new UITextDao(connection).get(textId);
    }

}
