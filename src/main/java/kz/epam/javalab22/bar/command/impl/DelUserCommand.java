package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqHandler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DelUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(DelUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqHandler reqHandler = new ReqHandler(request);

        if (new UserLogic(reqHandler).delUser()) {
            request.setAttribute("deleteUserResult", "Пользователь удален");
            log.info("Пользователь: " + reqHandler.getParam("checkedName") + " удален");
        } else {
            request.setAttribute("deleteUserResult", MessageManager.getProperty("message.deleteUserError"));
        }

        return ConfigurationManager.getProperty(Const.PAGE_USER_MANAGER);
    }
}
