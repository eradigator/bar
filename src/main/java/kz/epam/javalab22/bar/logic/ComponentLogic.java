package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.ComponentNameDao;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;

public class ComponentLogic {

    private ReqWrapper reqWrapper;

    public ComponentLogic(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
    }

    public boolean addComponent() {

        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean success = false;

        String name_RU = reqWrapper.getNonArrayRequestParameters().get("name_RU");
        String name_EN = reqWrapper.getNonArrayRequestParameters().get("name_EN");
        int componentType = Integer.parseInt(reqWrapper.getNonArrayRequestParameters().get("componentType"));
        double strength = Double.parseDouble(reqWrapper.getNonArrayRequestParameters().get("strength"));
        double price = Double.parseDouble(reqWrapper.getNonArrayRequestParameters().get("price"));

        ComponentName componentName = new ComponentName(name_RU, name_EN);

        if (new ComponentNameDao(connection).create(componentName)) {
            int nameId = new ComponentNameDao(connection).getId(componentName);
            Component component = new Component(nameId, componentType, strength, price);

            success = new ComponentDao(connection).insert(component);
        }

        ConnectionPool.getInstance().returnConnection(connection);
        return success;
    }

    public boolean delComponent() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean success = false;

        int componentId = Integer.parseInt(reqWrapper.getParam(Const.PARAM_COMPONENT_TO_DEL));

        ComponentName componentName = new ComponentName(componentId);
        ComponentNameDao componentNameDao = new ComponentNameDao(connection);

        if (componentNameDao.delete(componentName)) {
            Component component = new Component(componentId);
            ComponentDao componentDao = new ComponentDao(connection);
            if (componentDao.delete(component)) {
                success = true;
            }
        }

        ConnectionPool.getInstance().returnConnection(connection);
        return success;
    }

}
