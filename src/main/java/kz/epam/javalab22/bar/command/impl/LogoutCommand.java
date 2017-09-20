package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.LoginCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        if (null != reqWrapper.getUser()) {

            User user = reqWrapper.getUser();
            log.info(user.getName() + Const.DIV_SPACE + Const.LOG_LOGGED_OUT);
            reqWrapper.getRequest().getSession().removeAttribute(Const.ATTR_USER);
        }

        return new PageMainCommand().execute(reqWrapper);
    }

}
