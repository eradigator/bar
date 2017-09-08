package kz.epam.javalab22.bar.command.impl.user;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class SignUpCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;

        ReqWrapper reqWrapper = new ReqWrapper(request);

        String login = reqWrapper.getParam(Const.PARAM_LOGIN);
        String password = reqWrapper.getParam(Const.PARAM_PASSWORD);
        String email = reqWrapper.getParam(Const.PARAM_EMAIL);

        User user = new User(login, password, email);

        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean success = new UserDao(connection).create(user);
        ConnectionPool.getInstance().returnConnection(connection);

        if (success) {
            log.info("Пользователь: " + reqWrapper.getParam(Const.PARAM_LOGIN) + " добавлен");
            page = new PageMainCommand().execute(request);
        } else {
            page = ConfigurationManager.getProperty(Const.PARAM_LOGIN);
            request.setAttribute("error", MessageManager.getProperty("message.loginError"));
        }

        return page;
    }
}
