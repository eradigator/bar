package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.FavoriteDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.CocktailList;
import kz.epam.javalab22.bar.entity.Favorite;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class FavoriteLogic {

    private ReqWrapper reqWrapper;
    private Connection connection;

    public FavoriteLogic(ReqWrapper reqWrapper, Connection connection) {
        this.reqWrapper = reqWrapper;
        this.connection = connection;
    }

    public void addFavorite() {
        int userId = reqWrapper.getUser().getId();
        int cocktailId = Integer.parseInt(reqWrapper.getParam(Const.PARAM_ID));
        List<Integer> cocktailIds = Collections.singletonList(cocktailId);

        Favorite favorite = new Favorite(userId, cocktailIds);

        new FavoriteDao(connection).create(favorite);
    }

    public void removeFavorite() {
        int userId = reqWrapper.getUser().getId();
        int cocktailId = Integer.parseInt(reqWrapper.getParam(Const.PARAM_ID));
        List<Integer> cocktailIds = Collections.singletonList(cocktailId);

        Favorite favorite = new Favorite(userId, cocktailIds);
        new FavoriteDao(connection).delete(favorite);
    }

    public void getFavoriteCocktailList() {
        User user = reqWrapper.getUser();
        Favorite favorite = new FavoriteDao(connection).getList(user.getId());
        CocktailList cocktailList = getFavoriteCocktailList(favorite);
        fillFavorite(cocktailList, user, favorite);

        reqWrapper.addAttribute(Const.ATTR_COCKTAIL_LIST, cocktailList);
    }

    private CocktailList getFavoriteCocktailList(Favorite favorite) {
        CocktailList cocktailList = new CocktailList();

        for (int cocktailId : favorite.getCocktailIds()) {
            cocktailList.getCocktailList().add(new CocktailDao(connection).get(cocktailId));
        }

        return cocktailList;
    }

    public void fillFavorite(CocktailList cocktailList, User user, Favorite favorite) {

        if (null != user) {
            for (Cocktail cocktail : cocktailList.getCocktailList()) {
                if (favorite.getCocktailIds().contains(cocktail.getId())) {
                    cocktail.setFavorite(true);
                }
            }
        }
    }
}
