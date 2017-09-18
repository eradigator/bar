package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.FavoriteDao;
import kz.epam.javalab22.bar.dao.UITextDao;
import kz.epam.javalab22.bar.entity.Cocktail;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageAlcoholicCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        Connection connection = ConnectionPool.getInstance().getConnection();

        List<Cocktail> cocktailList = new CocktailDao(connection).getCocktailsList();

        List<Cocktail> filteredCocktailList;

        if (null == reqWrapper.getParam("filter")) {
            reqWrapper.addAttribute("filter_checked_id", "all");
            filteredCocktailList = new FilterCocktailList().getAllAlco(cocktailList);
        } else {
            switch (reqWrapper.getParam("filter")) {
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

        String paramSort = reqWrapper.getParam("sort");

        if (null == paramSort || paramSort.equals("by_name")) {
            reqWrapper.addAttribute("sort_checked_index", 0);

            switch (reqWrapper.getLocale().toString()) {
                case "ru_RU":
                    filteredCocktailList.sort(new SortCocktailListByNameRu());
                    break;
                case "en_US":
                    filteredCocktailList.sort(new SortCocktailListByNameEn());
                    break;
            }

        } else if (paramSort.equals("by_strength")) {
            filteredCocktailList.sort(new SortCocktailListByStrength());
            reqWrapper.addAttribute("sort_checked_index", 1);
        }

        int textId = Integer.parseInt(ConfigurationManager.getProperty(Const.PROP_UI_TEXT_FOR_ALCOHOLIC_PAGE));
        UIText uiText = new UITextDao(connection).get(textId);

        //favorite
        User user = reqWrapper.getUser();
        if (null != user) {
            Favorite favorite = new FavoriteDao(connection).getList(user.getId());

            for (Cocktail cocktail : filteredCocktailList) {
                if (favorite.getCocktailIds().contains(cocktail.getId())) {
                    cocktail.setFavorite(true);
                }
            }
        }

        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute(Const.ATTR_COCKTAIL_LIST, filteredCocktailList);
        reqWrapper.addAttribute(Const.ATTR_UI_TEXT, uiText);
        reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_ALCOHOLIC);

        //page navigation
        int beginValue = 0;
        int endValue = 2;

        if (null != reqWrapper.getParam("endValue")) {
            int counter = Integer.parseInt(reqWrapper.getParam("endValue"));
            beginValue = counter + 1;
            endValue = counter + 3;
        }

        reqWrapper.addAttribute("beginValue", beginValue);
        reqWrapper.addAttribute("endValue", endValue);


        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }
}
