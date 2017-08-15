package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        if (request.getSession().getAttribute("username") != null) {
            String username = request.getSession().getAttribute("username").toString();
            log.info(username + " вышел");
            request.getSession().invalidate();
        }
        return page;
    }
}
