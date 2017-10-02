package kz.epam.javalab22.bar.command.impl.user;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageUserManagerCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class AddUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddUserCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        String login = reqWrapper.getParam(Const.PARAM_LOGIN);
        String password = reqWrapper.getParam(Const.PARAM_PASSWORD);
        String email = reqWrapper.getParam(Const.PARAM_EMAIL);
        Role role = Role.valueOf(reqWrapper.getParam(Const.PARAM_ROLE).toUpperCase());

        User user = new User(login, password, email, role);

        Connection connection = ConnectionPool.getInstance().getConnection();

        UserLogic userLogic = new UserLogic(reqWrapper, connection);
        if (!userLogic.checkForExistence(user)) {
            if (userLogic.addUser(user)) {
                reqWrapper.addAttribute(Const.ATTR_ADD_USER_RESULT, messageManager.getProperty(Const.PROP_USER_ADDED));
                log.info(Const.LOG_USER + Const.DIV_SPACE +
                        reqWrapper.getParam(Const.PARAM_LOGIN) + Const.LOG_HAS_BEEN_ADDED);
            } else {
                reqWrapper.addAttribute(Const.ATTR_ERROR, messageManager.getProperty(Const.PROP_ERROR));
            }
        } else {
            reqWrapper.addAttribute(Const.ATTR_ERROR, messageManager.getProperty(Const.PROP_USER_EXIST));
        }

        ConnectionPool.getInstance().returnConnection(connection);

        return new PageUserManagerCommand().execute(reqWrapper);
    }

}
