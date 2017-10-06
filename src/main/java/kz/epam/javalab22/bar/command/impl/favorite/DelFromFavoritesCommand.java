package kz.epam.javalab22.bar.command.impl.favorite;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.FavoriteLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;

public class DelFromFavoritesCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        Connection connection = ConnectionPool.getInstance().getConnection();
        new FavoriteLogic(reqWrapper, connection).removeFavorite();
        ConnectionPool.getInstance().returnConnection(connection);

        return ConfigurationManager.getProperty(Const.PAGE_REFFERER);
    }
}
