package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentNameDao;
import kz.epam.javalab22.bar.dao.ComponentTypeDao;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class PageComponentCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);

        Connection connection = ConnectionPool.getInstance().getConnection();

        Map<Integer, String> componentTypes = new ComponentTypeDao().getComponentTypes();
        reqWrapper.addAttribute("componentTypes", componentTypes);

        List<ComponentName> componentNames = new ComponentNameDao(connection).getList();
        reqWrapper.addAttribute("componentNames", componentNames);

        ConnectionPool.getInstance().returnConnection(connection);

        return ConfigurationManager.getProperty(Const.PAGE_COMPONENT_MANAGER);
    }
}
