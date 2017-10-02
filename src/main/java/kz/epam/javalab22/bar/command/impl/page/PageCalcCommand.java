package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.ComponentTypeDao;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.ComponentType;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;
import java.util.List;

public class PageCalcCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        Connection connection = ConnectionPool.getInstance().getConnection();

        List<ComponentType> componentTypes = new ComponentTypeDao(connection).getList();
        List<Component> components = new ComponentDao(connection).getList();
        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute(Const.ATTR_COMPONENT_TYPES, componentTypes);
        reqWrapper.addAttribute(Const.ATTR_COMPONENTS, components);


        reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_CALCULATOR);
        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }

}
