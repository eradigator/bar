package kz.epam.javalab22.bar.command.impl.component;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageComponentManagerCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.ComponentLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class DelComponentCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(DelComponentCommand.class);
    private ReqWrapper reqWrapper;

    @Override
    public String execute(ReqWrapper reqWrapper) {

        this.reqWrapper = reqWrapper;
        Connection connection = ConnectionPool.getInstance().getConnection();
        addMessage(new ComponentLogic(reqWrapper, connection).delComponent());
        ConnectionPool.getInstance().returnConnection(connection);
        return new PageComponentManagerCommand().execute(reqWrapper);
    }

    private void addMessage(boolean success) {
        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (success) {
            String message = messageManager.getProperty(Const.PROP_COMPONENT_DELETED);
            reqWrapper.addAttribute(Const.ATTR_DEL_COMPONENT_MESSAGE, message);
            log.info(Const.LOG_COMPONENT + Const.DIV_SPACE +
                    reqWrapper.getParam(Const.PARAM_COMPONENT_TO_DEL) +
                    Const.LOG_HAS_BEEN_DELETED);
        } else {
            String message = messageManager.getProperty(Const.PROP_ERROR);
            reqWrapper.addAttribute(Const.ATTR_DEL_COMPONENT_MESSAGE, message);
        }
    }

}
