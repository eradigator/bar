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

    @Override
    public String execute(ReqWrapper reqWrapper) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        Connection connection = ConnectionPool.getInstance().getConnection();

        if (new UserLogic(reqWrapper, connection).delUser()) {
            reqWrapper.addAttribute(Const.ATTR_DEL_USER_RESULT, messageManager.getProperty(Const.PROP_USER_DELETED));
            log.info(Const.LOG_USER + Const.DIV_SPACE +
                    reqWrapper.getParam(Const.PARAM_CHECKED_NAME) + Const.LOG_HAS_BEEN_DELETED);
        } else {
            reqWrapper.addAttribute(Const.ATTR_ERROR, messageManager.getProperty(Const.PROP_ERROR));
        }

        ConnectionPool.getInstance().returnConnection(connection);

        return new PageUserManagerCommand().execute(reqWrapper);
    }

}
