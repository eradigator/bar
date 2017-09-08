package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentNameDao;
import kz.epam.javalab22.bar.dao.ComponentTypeDao;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class PageComponentManagerCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(PageComponentManagerCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);
        String page = new PageMainCommand().execute(request);

        if (null != request.getSession().getAttribute("user")) {
            User user = (User) request.getSession().getAttribute("user");

            switch (user.getRole()) {
                case ADMIN:
                    Connection connection = ConnectionPool.getInstance().getConnection();

                    Map<Integer, String> componentTypes = new ComponentTypeDao().getComponentTypes();
                    reqWrapper.addAttribute("componentTypes", componentTypes);

                    List<ComponentName> componentNames = new ComponentNameDao(connection).getList();
                    reqWrapper.addAttribute("componentNames", componentNames);

                    ConnectionPool.getInstance().returnConnection(connection);

                    reqWrapper.addAttribute("content", "componentManager");
                    page = ConfigurationManager.getProperty(Const.PAGE_COMPONENT_MANAGER);
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
