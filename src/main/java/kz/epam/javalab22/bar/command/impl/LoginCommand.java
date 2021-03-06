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

public class LoginCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private ReqWrapper reqWrapper;

    @Override
    public String execute(ReqWrapper reqWrapper) {

        this.reqWrapper = reqWrapper;
        String page = ConfigurationManager.getProperty(Const.PAGE_LOGIN);

        Connection connection = ConnectionPool.getInstance().getConnection();
        String login = reqWrapper.getParam(Const.PARAM_LOGIN);
        boolean isCheckLoginSuccessful = false;

        if (new UserLogic(reqWrapper, connection).checkLogin()) {
            User user = new UserDao(connection).getUser(login);
            reqWrapper.addSessionAttribute(Const.ATTR_USER, user);
            page = new PageMainCommand().execute(reqWrapper);
            isCheckLoginSuccessful = true;
        }

        addMessage(isCheckLoginSuccessful);
        ConnectionPool.getInstance().returnConnection(connection);
        return page;
    }

    private void addMessage(boolean success) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());
        String login = reqWrapper.getParam(Const.PARAM_LOGIN);

        if (success) {
            log.info(login + Const.DIV_SPACE + Const.LOG_LOGGED_IN);
        } else {
            log.info(login + Const.DIV_SPACE + Const.LOG_UNSUCCESSED_LOG_IN);
            String message = messageManager.getProperty(Const.PROP_LOGIN_ERROR);
            reqWrapper.addAttribute(Const.ATTR_ERROR_LOGIN_PASS_MESSAGE, message);
        }
    }

}
