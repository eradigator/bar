package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.entity.ComponentType;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vten
 */

public class ComponentDao extends AbstractDao<Component> {

    private static final Logger log = Logger.getLogger(ComponentDao.class);
    private Connection connection;
    private static final String SQL_DELETE = "UPDATE component SET deleted = TRUE WHERE id=?";
    private static final String SQL_CREATE = "INSERT INTO component (type_id,name_id,strength,price) " +
            "VALUES (?,?,?,?)";
    private static final String SQL_GET = "SELECT id,strength,price FROM component WHERE id=?";
    private static final String SQL_GET_LIST = "SELECT c.id,c.type_id,cn.ru AS ru, cn.en AS en," +
            "ct.name_ru AS typeNameRu, ct.name_en AS typeNameEn " +
            "FROM component c " +
            "INNER JOIN component_name cn ON c.name_id = cn.id " +
            "INNER JOIN component_type ct ON c.type_id = ct.id " +
            "WHERE c.deleted IS NOT TRUE " +
            "ORDER BY cn.ru";

    public ComponentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Component update(Component entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Component entity) {

        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, entity.getId());
            if (preparedStatement.executeUpdate() > Const.N_0) {
                success = true;
            }
        } catch (SQLException e) {
            log.info(Const.LOG_EXC_SQL);
        }

        return success;
    }

    @Override
    public boolean create(Component entity) {

        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, entity.getType());
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_2, entity.getNameId());
            preparedStatement.setDouble(Const.SQL_PARAM_INDEX_3, entity.getStrength());
            preparedStatement.setDouble(Const.SQL_PARAM_INDEX_4, entity.getPrice());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            log.info(Const.LOG_EXC_SQL);
        }

        return success;
    }

    public Component getComponent(int id) {

        double strength = Const.N_0;
        double price = Const.N_0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                strength = resultSet.getDouble(Const.COLUMN_LABEL_STRENGTH);
                price = resultSet.getDouble(Const.COLUMN_LABEL_PRICE);
            }
        } catch (SQLException e) {
            log.info(Const.LOG_EXC_SQL);
        }

        return new Component(id, strength, price);
    }

    public List<Component> getList() {

        List<Component> componentList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_LIST)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(Const.COLUMN_LABEL_ID);
                String nameRu = resultSet.getString(Const.COLUMN_LABEL_RU);
                String nameEn = resultSet.getString(Const.COLUMN_LABEL_EN);
                ComponentName componentName = new ComponentName(nameRu, nameEn);

                int typeId = resultSet.getInt(Const.COLUMN_LABEL_TYPE_ID);
                String typeNameRu = resultSet.getString(Const.COLUMN_LABEL_TYPE_NAME_RU);
                String typeNameEn = resultSet.getString(Const.COLUMN_LABEL_TYPE_NAME_EN);
                ComponentType componentType = new ComponentType(typeId, typeNameRu, typeNameEn);

                componentList.add(new Component(id, componentName, componentType));
            }
        } catch (SQLException e) {
            log.info(Const.LOG_EXC_SQL);
        }

        return componentList;
    }
}
