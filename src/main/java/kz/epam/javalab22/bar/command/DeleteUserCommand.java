package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.logic.DeleteUserLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(DeleteUserCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {

        String page;

        String checkedUserName = request.getParameter("checkedName");
        System.out.println(checkedUserName);
        if (new DeleteUserLogic().deleteUser(checkedUserName)) {
            System.out.println(true);
        }

        request.setAttribute("deleteMessage", "ok");
        log.info("Пользователь: " + checkedUserName + " удален");

        page = ConfigurationManager.getProperty("path.page.main");

        /*} else {
            log.info(login + ": неудачная попытка входа");

            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }*/

        return page;
    }
}
