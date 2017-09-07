package kz.epam.javalab22.bar.command.impl.user;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageUserManagerCommand;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DelUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(DelUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);

        if (new UserLogic(reqWrapper).delUser()) {
            request.setAttribute("deleteUserResult", "Пользователь удален");
            log.info("Пользователь: " + reqWrapper.getParam("checkedName") + " удален");
        } else {
            request.setAttribute("deleteUserResult", MessageManager.getProperty("message.deleteUserError"));
        }

        return new PageUserManagerCommand().execute(request);
    }
}
