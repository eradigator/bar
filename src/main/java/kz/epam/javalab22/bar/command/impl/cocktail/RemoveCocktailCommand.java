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

public class RemoveCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(RemoveCocktailCommand.class);
    private ReqWrapper reqWrapper;

    @Override
    public String execute(ReqWrapper reqWrapper) {

        this.reqWrapper = reqWrapper;
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean success = new CocktailLogic(reqWrapper, connection).deleteCocktail();
        addMessage(success);
        ConnectionPool.getInstance().returnConnection(connection);
        return new PageCocktailManagerCommand().execute(reqWrapper);
    }

    private void addMessage(boolean success) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (success) {
            String message = messageManager.getProperty(Const.PROP_COCKTAIL_DELETED);
            reqWrapper.addAttribute(Const.ATTR_DEL_COCKTAIL_RESULT, message);

            log.info(Const.LOG_COCKTAIL + Const.DIV_SPACE +
                    reqWrapper.getParam(Const.PARAM_COCKTAIL_ID_TO_DELETE) +
                    Const.DIV_SPACE + Const.LOG_HAS_BEEN_DELETED);
        } else {
            String message = messageManager.getProperty(Const.PROP_ERROR);
            reqWrapper.addAttribute(Const.ATTR_ERROR, message);
        }
    }
}
