package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.ComponentNameDao;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqHandler;

import java.sql.Connection;

public class ComponentLogic {

    private ReqHandler reqHandler;

    public ComponentLogic(ReqHandler reqHandler) {
        this.reqHandler = reqHandler;
    }

    public boolean addComponent() {

        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean success = false;

        String name_RU = reqHandler.getNonArrayRequestParameters().get("name_RU");
        String name_EN = reqHandler.getNonArrayRequestParameters().get("name_EN");
        int componentType = Integer.parseInt(reqHandler.getNonArrayRequestParameters().get("componentType"));
        double strength = Double.parseDouble(reqHandler.getNonArrayRequestParameters().get("strength"));
        double price = Double.parseDouble(reqHandler.getNonArrayRequestParameters().get("price"));

        ComponentName componentName = new ComponentName(name_EN, name_RU);

        if (new ComponentNameDao(connection).create(componentName)) {
            int nameId = new ComponentNameDao(connection).getId(componentName);
            Component component = new Component(nameId, componentType, strength, price);

            success = new ComponentDao(connection).insert(component);
        }

        ConnectionPool.getInstance().returnConnection(connection);
        return success;
    }

}
