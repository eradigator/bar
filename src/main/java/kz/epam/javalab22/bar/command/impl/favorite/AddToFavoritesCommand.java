package kz.epam.javalab22.bar.command.impl.favorite;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailsCommand;
import kz.epam.javalab22.bar.command.impl.page.PageLoginCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.logic.FavoriteLogic;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;

public class AddToFavoritesCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {
        String page = new PageCocktailsCommand().execute(reqWrapper);

        if (new UserLogic(reqWrapper).checkForUserLoggedIn()) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            new FavoriteLogic(reqWrapper, connection).addFavorite();
            ConnectionPool.getInstance().returnConnection(connection);

        } else {
            page = new PageLoginCommand().execute(reqWrapper);
        }

        return page;
    }

}
