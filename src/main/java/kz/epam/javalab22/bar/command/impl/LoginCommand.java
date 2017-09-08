package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
import kz.epam.javalab22.bar.command.impl.page.PageUserManagerCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.logic.LoginLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class LoginCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;

        ReqWrapper reqWrapper = new ReqWrapper(request);
        String login = reqWrapper.getParam(Const.PARAM_LOGIN);
        String pass = reqWrapper.getParam(Const.PARAM_PASSWORD);

        Connection connection = ConnectionPool.getInstance().getConnection();
        User user = new UserDao(connection).getUser(login);
        ConnectionPool.getInstance().returnConnection(connection);


        if (new LoginLogic().checkLogin(login, pass)) {

            reqWrapper.addSessionAttribute("user", user);

            page = new PageMainCommand().execute(request);
            log.info(login + " залогинился");

        } else {
            log.info(login + ": неудачная попытка входа");
            String message = MessageManager.getProperty("message.loginError");
            request.setAttribute("errorLoginPassMessage", message);
            page = ConfigurationManager.getProperty(Const.PAGE_LOGIN);
        }
        return page;
    }
}
