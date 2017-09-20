package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.ComponentNameDao;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ComponentLogic {

    private ReqWrapper reqWrapper;
    private Connection connection;

    public ComponentLogic(ReqWrapper reqWrapper, Connection connection) {
        this.reqWrapper = reqWrapper;
        this.connection = connection;
    }

    public boolean addComponent() {

        boolean success = false;

        String name_RU = reqWrapper.getParam(Const.PARAM_NAME_RU);
        String name_EN = reqWrapper.getParam(Const.PARAM_NAME_EN);
        int componentType = Integer.parseInt(reqWrapper.getParam(Const.PARAM_COMPONENT_TYPE));
        double strength = Double.parseDouble(reqWrapper.getParam(Const.PARAM_STRENGTH));
        double price = Double.parseDouble(reqWrapper.getParam(Const.PARAM_PRICE));

        ComponentName componentName = new ComponentName(name_RU, name_EN);

        if (new ComponentNameDao(connection).create(componentName)) {
            int nameId = componentName.getId();
            Component component = new Component(nameId, componentType, strength, price);
            new ComponentDao(connection).create(component);
            success = true;
        }

        return success;
    }

    public boolean delComponent() {

        boolean success = false;

        int componentId = Integer.parseInt(reqWrapper.getParam(Const.PARAM_COMPONENT_TO_DEL));

        try {
            connection.setAutoCommit(false);

            ComponentName componentName = new ComponentName(componentId);
            ComponentNameDao componentNameDao = new ComponentNameDao(connection);
            Component component = new Component(componentId);
            ComponentDao componentDao = new ComponentDao(connection);

            if (componentNameDao.delete(componentName) && componentDao.delete(component)) {
                connection.commit();
                success = true;
            } else {
                connection.rollback();
            }

            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean checkForExistence() {

        boolean isComponentNameExist = false;

        String name_RU = reqWrapper.getParam(Const.PARAM_NAME_RU);
        String name_EN = reqWrapper.getParam(Const.PARAM_NAME_EN);

        ComponentName componentName = new ComponentName(name_RU, name_EN);
        List<ComponentName> componentNames = new ComponentNameDao(connection).getList();

        for (ComponentName entity : componentNames) {
            if (entity.getNameRu().equals(componentName.getNameRu()) ||
                    entity.getNameEn().equals(componentName.getNameEn())) {
                isComponentNameExist = true;
            }
        }

        return isComponentNameExist;
    }

}
