package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.logic.LoginLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {

        String page;

        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        // проверка логина и пароля
        if (new LoginLogic().checkLogin(login, pass)) {

            request.setAttribute("user", login);
            request.getSession().setAttribute("username",login);
            request.getSession().setAttribute("isadmin",true);

            log.info(login + " залогинился");
            // определение пути к админке main.jsp
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            log.info(login + ": неудачная попытка входа");

            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
