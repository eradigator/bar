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

    @Override
    public String execute(ReqWrapper reqWrapper) {

        Connection connection = ConnectionPool.getInstance().getConnection();

        User user = reqWrapper.getUser();
        Favorite favorite = new FavoriteDao(connection).getList(user.getId());

        CocktailList cocktailList = new CocktailList();
        for (int cocktailId : favorite.getCocktailIds()) {
            cocktailList.getCocktailList().add(new CocktailDao(connection).get(cocktailId));
        }

        fillFavorite(reqWrapper, connection, cocktailList);
        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute(Const.ATTR_COCKTAIL_LIST, cocktailList);
        reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_FAVORITE);

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
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

}
