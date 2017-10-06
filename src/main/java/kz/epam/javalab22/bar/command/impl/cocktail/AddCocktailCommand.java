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
    private ReqWrapper reqWrapper;

    @Override
    public String execute(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
        Connection connection = ConnectionPool.getInstance().getConnection();
        CocktailLogic cocktailLogic = new CocktailLogic(reqWrapper, connection);
        addMessage(cocktailLogic.validate() && cocktailLogic.addCocktail());
        ConnectionPool.getInstance().returnConnection(connection);
        return new PageCocktailManagerCommand().execute(reqWrapper);
    }

    private void addMessage(boolean success) {
        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (success) {
            String message = messageManager.getProperty(Const.PROP_COCKTAIL_ADDED);
            reqWrapper.addAttribute(Const.ATTR_ADD_COCKTAIL_RESULT, message);
            log.info(Const.LOG_COCKTAIL + Const.DIV_SPACE + reqWrapper.getParam(Const.PARAM_NAME) +
                    Const.DIV_SPACE + Const.LOG_HAS_BEEN_ADDED);
        } else {
            if (null == reqWrapper.getAttribute(Const.ATTR_ERROR)) {
                String errorMessage = messageManager.getProperty(Const.PROP_ERROR);
                reqWrapper.addAttribute(Const.ATTR_ERROR, errorMessage);
            }
        }
    }
}


