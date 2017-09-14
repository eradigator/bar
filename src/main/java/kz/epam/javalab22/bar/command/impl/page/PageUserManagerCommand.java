package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.exception.ForbiddenPageException;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.UserCheck;
import org.apache.log4j.Logger;

import java.util.List;

public class PageUserManagerCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(PageUserManagerCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        String page = new PageMainCommand().execute(reqWrapper);

        if (new UserCheck(reqWrapper).roleIsAdminCheck()) {
            List<User> users = new UserDao().getUserList();
            reqWrapper.addAttribute(Const.ATTR_USERS, users);
            reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_USER_MANAGER);
            page = ConfigurationManager.getProperty(Const.PAGE_INDEX);
        } else {
            try {
                throw new ForbiddenPageException();
            } catch (ForbiddenPageException e) {
                e.printStackTrace();
            }
            log.info(Const.LOG_FORBIDDEN_PAGE);
        }

        return page;
    }

}
