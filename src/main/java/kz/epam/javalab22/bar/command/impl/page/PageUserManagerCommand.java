package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.user.AddUserCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageUserManagerCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(PageUserManagerCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);
        String page = new PageMainCommand().execute(request);   //main page

        if (null != request.getSession().getAttribute("user")) {
            User user = (User) request.getSession().getAttribute("user");

            switch (user.getRole()) {
                case ADMIN:
                    List<User> users = new UserDao().getUserList();
                    reqWrapper.addAttribute("users", users);
                    reqWrapper.addAttribute("content", "userManager");
                    page = ConfigurationManager.getProperty(Const.PAGE_USER_MANAGER);
                    break;
                case USER:
                    log.info(user.getName() + Const.LOG_FORBITTEN_PAGE);
                    break;
            }
        } else {
            log.info(Const.LOG_FORBITTEN_PAGE);
        }


        return page;
    }
}
