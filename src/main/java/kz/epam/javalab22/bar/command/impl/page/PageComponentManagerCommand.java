package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentNameDao;
import kz.epam.javalab22.bar.dao.ComponentTypeDao;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.entity.ComponentType;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.UserCheck;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class PageComponentManagerCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(PageComponentManagerCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        String page = new PageMainCommand().execute(reqWrapper);

        if (new UserCheck(reqWrapper).roleIsAdminCheck()) {

            Connection connection = ConnectionPool.getInstance().getConnection();
            List<ComponentType> componentTypes = new ComponentTypeDao(connection).getList();
            List<ComponentName> componentNames = new ComponentNameDao(connection).getList();
            ConnectionPool.getInstance().returnConnection(connection);

            reqWrapper.addAttribute(Const.ATTR_COMPONENT_TYPES, componentTypes);
            reqWrapper.addAttribute(Const.ATTR_COMPONENT_NAMES, componentNames);
            reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_COMPONENT_MANAGER);
            page = ConfigurationManager.getProperty(Const.PAGE_INDEX);

        } else {
            log.info(Const.LOG_FORBIDDEN_PAGE);
        }
        return page;
    }
}
