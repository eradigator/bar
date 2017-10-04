package kz.epam.javalab22.bar.command.impl.cocktail;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;

public class ShowCocktailCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        int cocktailId = Integer.parseInt(reqWrapper.getParam(Const.PARAM_COCKTAIL_ID));

        Connection connection = ConnectionPool.getInstance().getConnection();
        Cocktail cocktail = new CocktailDao(connection).get(cocktailId);
        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute(Const.ATTR_COCKTAIL, cocktail);
        reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_COCKTAIL);

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }

}
