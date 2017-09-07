package kz.epam.javalab22.bar.command.impl.user;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageUserManagerCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);
        UserLogic userLogic = new UserLogic(reqWrapper);

        if (userLogic.addUser()) {
            reqWrapper.addAttribute("addUserResult", "Пользователь добавлен");
            log.info("Пользователь: " + reqWrapper.getParam(Const.PARAM_LOGIN) + " добавлен");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginError"));
        }

        return new PageUserManagerCommand().execute(request);
    }
}