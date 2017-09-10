package kz.epam.javalab22.bar.command.impl.user;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class SignUpCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        String page = ConfigurationManager.getProperty(Const.PARAM_LOGIN);

        String login = reqWrapper.getParam(Const.PARAM_LOGIN);
        String password = reqWrapper.getParam(Const.PARAM_PASSWORD);
        String email = reqWrapper.getParam(Const.PARAM_EMAIL);

        User user = new User(login, password, email);

        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean success = new UserDao(connection).create(user);
        ConnectionPool.getInstance().returnConnection(connection);

        if (success) {
            log.info(Const.LOG_USER + reqWrapper.getParam(Const.PARAM_LOGIN) +
                    Const.DIV_SPACE + Const.LOG_HAS_BEEN_ADDED);
            page = new PageMainCommand().execute(reqWrapper);
        } else {
            String message = MessageManager.getProperty("message.loginError");
            reqWrapper.addAttribute(Const.ATTR_ERROR, message);
        }

        return page;
    }

}
