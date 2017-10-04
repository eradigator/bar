package kz.epam.javalab22.bar.command.impl.user;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageUserManagerCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class DelUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(DelUserCommand.class);
    private ReqWrapper reqWrapper;

    @Override
    public String execute(ReqWrapper reqWrapper) {

        this.reqWrapper = reqWrapper;
        Connection connection = ConnectionPool.getInstance().getConnection();
        addMessage(new UserLogic(reqWrapper, connection).delUser());
        ConnectionPool.getInstance().returnConnection(connection);
        return new PageUserManagerCommand().execute(reqWrapper);
    }

    private void addMessage(boolean success) {
        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (success) {
            reqWrapper.addAttribute(Const.ATTR_DEL_USER_RESULT, messageManager.getProperty(Const.PROP_USER_DELETED));
            log.info(Const.LOG_USER + Const.DIV_SPACE +
                    reqWrapper.getParam(Const.PARAM_CHECKED_NAME) + Const.LOG_HAS_BEEN_DELETED);
        } else {
            reqWrapper.addAttribute(Const.ATTR_ERROR, messageManager.getProperty(Const.PROP_ERROR));
        }
    }

}
