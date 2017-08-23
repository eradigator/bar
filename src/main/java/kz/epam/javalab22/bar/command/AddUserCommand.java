package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.logic.AddUserLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddUserCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;
        User user;

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Role role = Role.valueOf(request.getParameter("role").toUpperCase());

        user = new User(login,password,email,role);

        if (!login.isEmpty()) {
            if (new AddUserLogic().addUser(user)) {
            request.setAttribute("addUserResult", "Пользователь добавлен");
            log.info("Пользователь: " + login + " добавлен");
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        }

        page = ConfigurationManager.getProperty(Const.PAGE_MAIN);
        return page;
    }
}
