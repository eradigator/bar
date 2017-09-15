package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.UITextDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.UIText;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.FilterCocktailList;

import java.sql.Connection;
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

        if (null == reqWrapper.getParam("sort")) {
            reqWrapper.addAttribute("sort_checked_index", 0);
        } else {
            switch (reqWrapper.getParam("sort")) {
                case "by_name":
                    reqWrapper.addAttribute("sort_checked_index", 0);
                    //компаратор
                    break;
                case "by_strength":
                    reqWrapper.addAttribute("sort_checked_index", 1);
                    //компаратор
                    break;
                default:
                    reqWrapper.addAttribute("sort_checked_index", 0);
                    break;

            }
        }

        int textId = Integer.parseInt(ConfigurationManager.getProperty(Const.PROP_UI_TEXT_FOR_ALCOHOLIC_PAGE));
        UIText uiText = new UITextDao(connection).get(textId);

        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute(Const.ATTR_COCKTAIL_LIST, filteredCocktailList);
        reqWrapper.addAttribute(Const.ATTR_UI_TEXT, uiText);
        reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_ALCOHOLIC);

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }

}
