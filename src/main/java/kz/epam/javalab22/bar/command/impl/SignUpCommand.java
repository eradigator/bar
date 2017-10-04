package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class SignUpCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        String page = ConfigurationManager.getProperty(Const.PAGE_LOGIN);
        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        String login = reqWrapper.getParam(Const.PARAM_LOGIN);
        String password = reqWrapper.getParam(Const.PARAM_PASSWORD);
        String email = reqWrapper.getParam(Const.PARAM_EMAIL);

        User user = new User(login, password, email);

        Connection connection = ConnectionPool.getInstance().getConnection();
        UserLogic userLogic = new UserLogic(reqWrapper, connection);

        if (!(userLogic.checkForExistence(user))) {
            if (new UserDao(connection).create(user)) {

                log.info(Const.LOG_USER + reqWrapper.getParam(Const.PARAM_LOGIN) +
                        Const.DIV_SPACE + Const.LOG_HAS_BEEN_ADDED);

                user = new UserDao(connection).getUser(user.getName());
                reqWrapper.addSessionAttribute(Const.ATTR_USER, user);
                page = new PageMainCommand().execute(reqWrapper);

            } else {
                String message = messageManager.getProperty(Const.PROP_LOGIN_ERROR);
                reqWrapper.addAttribute(Const.ATTR_ERROR, message);
            }
        }

        ConnectionPool.getInstance().returnConnection(connection);
        return page;
    }

}
