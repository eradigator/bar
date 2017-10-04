package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.FavoriteDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.CocktailList;
import kz.epam.javalab22.bar.entity.Favorite;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;

public class PageFavoriteCommand implements ActionCommand {

    private Connection connection;

    @Override
    public String execute(ReqWrapper reqWrapper) {

        connection = ConnectionPool.getInstance().getConnection();

        User user = reqWrapper.getUser();
        Favorite favorite = new FavoriteDao(connection).getList(user.getId());
        CocktailList cocktailList = getFavoriteCocktailList(favorite);
        fillFavorite(cocktailList, user, favorite);
        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute(Const.ATTR_COCKTAIL_LIST, cocktailList);
        reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_FAVORITE);

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }

    private CocktailList getFavoriteCocktailList(Favorite favorite) {

        CocktailList cocktailList = new CocktailList();
        for (int cocktailId : favorite.getCocktailIds()) {
            cocktailList.getCocktailList().add(new CocktailDao(connection).get(cocktailId));
        }

        return cocktailList;
    }


    private void fillFavorite(CocktailList cocktailList, User user, Favorite favorite) {

        if (null != user) {
            for (Cocktail cocktail : cocktailList.getCocktailList()) {
                if (favorite.getCocktailIds().contains(cocktail.getId())) {
                    cocktail.setFavorite(true);
                }
            }
        }
    }

}
