package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.logic.AddUserLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqHandler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqHandler reqHandler = new ReqHandler(request);
        AddUserLogic addUserLogic = new AddUserLogic(reqHandler);

        if (addUserLogic.addUser()) {
            reqHandler.addAttribute("addUserResult", "Пользователь добавлен");
            log.info("Пользователь: " + reqHandler.getParam(Const.PARAM_LOGIN) + " добавлен");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginError"));
        }

        return ConfigurationManager.getProperty(Const.PAGE_MAIN);
    }
}
