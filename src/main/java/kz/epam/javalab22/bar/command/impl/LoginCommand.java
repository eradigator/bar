package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.LoginLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.SessionRequestContent;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        SessionRequestContent sessionRequestContent = new SessionRequestContent();
        Map<String, Object> sessionAttributes = new HashMap<>();
        String page;

        sessionRequestContent.extractValues(request);
        String login = sessionRequestContent.getNonArrayRequestParameters().get(Const.PARAM_LOGIN);
        String pass = sessionRequestContent.getNonArrayRequestParameters().get(Const.PARAM_PASSWORD);

        if (new LoginLogic().checkLogin(login, pass)) {

            sessionAttributes.put("username", login);
            sessionAttributes.put("role", "admin");

            sessionRequestContent.setSessionAttributes(sessionAttributes);
            sessionRequestContent.insertAttributes(request);

            page = ConfigurationManager.getProperty(Const.PAGE_MAIN);

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
