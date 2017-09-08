package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.LoginCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getSession().getAttribute("user") != null) {

            User user = (User) request.getSession().getAttribute("user");
            log.info(user.getName() + " вышел");
            request.getSession().invalidate();
        }
        return new PageMainCommand().execute(request);
    }
}
