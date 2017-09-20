package kz.epam.javalab22.bar.command.impl.cocktail;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailManagerCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.CocktailLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;
import java.sql.Connection;

public class AddCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddCocktailCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        Connection connection = ConnectionPool.getInstance().getConnection();

        CocktailLogic cocktailLogic = new CocktailLogic(reqWrapper, connection);

        if (!cocktailLogic.checkForExistence()) {
            if (cocktailLogic.checkSelectedComponent()) {
                if (cocktailLogic.addCocktail()) {
                    String message = messageManager.getProperty(Const.PROP_COCKTAIL_ADDED);
                    reqWrapper.addAttribute(Const.ATTR_ADD_COCKTAIL_RESULT, message);
                    log.info(Const.LOG_COCKTAIL + Const.DIV_SPACE + reqWrapper.getParam(Const.PARAM_NAME) +
                            Const.DIV_SPACE + Const.LOG_HAS_BEEN_ADDED);
                } else {
                    String message = messageManager.getProperty(Const.PROP_ERROR);
                    reqWrapper.addAttribute(Const.ATTR_ERROR, message);
                }
            } else {
                String message = messageManager.getProperty(Const.PROP_NO_COMPONENT_SELECTED);
                reqWrapper.addAttribute(Const.ATTR_ERROR, message);
            }
        } else {
            String message = messageManager.getProperty(Const.PROP_COCKTAIL_EXIST);
            reqWrapper.addAttribute(Const.ATTR_ERROR, message);
        }

        ConnectionPool.getInstance().returnConnection(connection);

        return new PageCocktailManagerCommand().execute(reqWrapper);
    }

}
