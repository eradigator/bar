package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.LoginLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.SessionRequestContent;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class LoginCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        SessionRequestContent sessionRequestContent = new SessionRequestContent();
        HashMap<String, Object> sessionAttributes = new HashMap<>();
        String page;

        String login = request.getParameter(Const.PARAM_LOGIN);
        String pass = request.getParameter(Const.PARAM_PASSWORD);

        if (new LoginLogic().checkLogin(login, pass)) {

            sessionAttributes.put("username", login);
            sessionAttributes.put("role", "admin");

            sessionRequestContent.setSessionAttributes(sessionAttributes);
            sessionRequestContent.insertAttributes(request);

            page = ConfigurationManager.getProperty(Const.PAGE_MAIN);

            log.info(login + " залогинился");

        } else {
            log.info(login + ": неудачная попытка входа");
            String message = MessageManager.getProperty("message.loginerror");
            request.setAttribute("errorLoginPassMessage", message);
            page = ConfigurationManager.getProperty(Const.PAGE_LOGIN);
        }
        return page;
    }
}
