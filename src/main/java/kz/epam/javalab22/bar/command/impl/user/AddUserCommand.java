package kz.epam.javalab22.bar.command.impl.user;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageUserManagerCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class AddUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddUserCommand.class);
    private ReqWrapper reqWrapper;

    @Override
    public String execute(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;

        Connection connection = ConnectionPool.getInstance().getConnection();
        UserLogic userLogic = new UserLogic(reqWrapper, connection);
        User user = userLogic.getUserFromRequest();
        addMessage(!userLogic.checkForExistence(user) && userLogic.addUser(user));
        ConnectionPool.getInstance().returnConnection(connection);

        return new PageUserManagerCommand().execute(reqWrapper);
    }

    private void addMessage(boolean success) {
        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (success) {
            String message = messageManager.getProperty(Const.PROP_USER_ADDED);
            reqWrapper.addAttribute(Const.ATTR_ADD_USER_RESULT, message);
            log.info(Const.LOG_USER + Const.DIV_SPACE +
                    reqWrapper.getParam(Const.PARAM_LOGIN) + Const.LOG_HAS_BEEN_ADDED);
        } else {
            if (null != reqWrapper.getAttribute(Const.ATTR_ERROR)) {
                String message = messageManager.getProperty(Const.PROP_ERROR);
                reqWrapper.addAttribute(Const.ATTR_ERROR, message);
            }
        }
    }


}
