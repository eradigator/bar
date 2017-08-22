package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.logic.DeleteUserLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(DeleteUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;

        String checkedUserName = request.getParameter("checkedName");
        System.out.println(checkedUserName);
        if (checkedUserName != null) {
            if (new DeleteUserLogic().deleteUser(checkedUserName)) {
                System.out.println(true);
                request.setAttribute("deleteUserResult", "Пользователь удален");
                log.info("Пользователь: " + checkedUserName + " удален");
            }
        } else {
            request.setAttribute("deleteUserResult", MessageManager.getProperty("message.loginerror"));
        }

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
