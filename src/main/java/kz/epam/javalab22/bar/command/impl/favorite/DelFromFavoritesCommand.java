package kz.epam.javalab22.bar.command.impl.favorite;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.FavoriteDao;
import kz.epam.javalab22.bar.entity.Favorite;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class DelFromFavoritesCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        int userId = reqWrapper.getUser().getId();
        int cocktailId = Integer.parseInt(reqWrapper.getParam(Const.PARAM_ID));
        List<Integer> cocktailIds = Collections.singletonList(cocktailId);

        Favorite favorite = new Favorite(userId, cocktailIds);

        Connection connection = ConnectionPool.getInstance().getConnection();
        new FavoriteDao(connection).delete(favorite);
        ConnectionPool.getInstance().returnConnection(connection);

        return ConfigurationManager.getProperty(Const.PAGE_REFFERER);
    }
}
