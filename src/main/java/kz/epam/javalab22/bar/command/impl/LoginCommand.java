package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.logic.LoginLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class LoginCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        String page = ConfigurationManager.getProperty(Const.PAGE_LOGIN);
        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        String login = reqWrapper.getParam(Const.PARAM_LOGIN);
        String pass = reqWrapper.getParam(Const.PARAM_PASSWORD);

        Connection connection = ConnectionPool.getInstance().getConnection();
        User user = new UserDao(connection).getUser(login);
        ConnectionPool.getInstance().returnConnection(connection);

        if (new LoginLogic().checkLogin(login, pass)) {
            reqWrapper.addSessionAttribute(Const.ATTR_USER, user);
            page = new PageMainCommand().execute(reqWrapper);
            log.info(login + Const.DIV_SPACE + Const.LOG_LOGGED_IN);
        } else {
            log.info(login + Const.DIV_SPACE + Const.LOG_UNSUCCESSED_LOG_IN);
            reqWrapper.addAttribute(Const.ATTR_ERROR_LOGIN_PASS_MESSAGE, messageManager.getProperty("loginError"));
        }

        return page;
    }

}
