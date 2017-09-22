package kz.epam.javalab22.bar.command.impl.favorite;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.cocktail.PageCocktailsCommand;
import kz.epam.javalab22.bar.command.impl.page.PageLoginCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.FavoriteDao;
import kz.epam.javalab22.bar.entity.Favorite;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class AddToFavoritesCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (null == reqWrapper.getUser()) {
            String message = messageManager.getProperty(Const.PROP_REGISTRATION_NEEDED);
            reqWrapper.addAttribute(Const.ATTR_ERROR, message);
            return new PageLoginCommand().execute(reqWrapper);
        } else {
            int userId = reqWrapper.getUser().getId();
            List<Integer> cocktailIds = Collections.singletonList(Integer.parseInt(reqWrapper.getParam("id")));

            Favorite favorite = new Favorite(userId,cocktailIds);

            Connection connection = ConnectionPool.getInstance().getConnection();
            new FavoriteDao(connection).create(favorite);
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return new PageCocktailsCommand().execute(reqWrapper);
    }

}
