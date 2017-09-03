package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.LoginLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqHandler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;

        ReqHandler reqHandler = new ReqHandler(request);
        String login = reqHandler.getParam(Const.PARAM_LOGIN);
        String pass = reqHandler.getParam(Const.PARAM_PASSWORD);

        if (new LoginLogic().checkLogin(login, pass)) {

            reqHandler.addSessionAttribute("username",login);
            reqHandler.addSessionAttribute("role","admin");

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
