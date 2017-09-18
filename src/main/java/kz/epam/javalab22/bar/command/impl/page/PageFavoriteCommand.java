package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.FavoriteDao;
import kz.epam.javalab22.bar.dao.UITextDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.Favorite;
import kz.epam.javalab22.bar.entity.UIText;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.FilterCocktailList;
import kz.epam.javalab22.bar.util.SortCocktailListByNameEn;
import kz.epam.javalab22.bar.util.SortCocktailListByNameRu;
import kz.epam.javalab22.bar.util.SortCocktailListByStrength;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PageFavoriteCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        Connection connection = ConnectionPool.getInstance().getConnection();

        User user = reqWrapper.getUser();
        Favorite favorite = new FavoriteDao(connection).getList(user.getId());

        List<Cocktail> cocktailList = new ArrayList<>();
        for (int cocktailId : favorite.getCocktailIds()) {
            cocktailList.add(new CocktailDao(connection).get(cocktailId));
        }

        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute(Const.ATTR_COCKTAIL_LIST, cocktailList);
        reqWrapper.addAttribute(Const.ATTR_CONTENT, "favorite");

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }

}
