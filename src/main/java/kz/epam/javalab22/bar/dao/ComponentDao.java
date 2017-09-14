package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;

import java.sql.*;

/**
 * Created by vten on 25.08.2017.
 */
public class ComponentDao extends AbstractDao<Component> {

    private Connection connection;
    private static final String SQL_DELETE = "UPDATE component " +
            "SET deleted = TRUE " +
            "WHERE id = ?";

    private static final String SQL_CREATE = "INSERT INTO component (type_id,name_id,strength,price) " +
            "VALUES (?,?,?,?)";

    private static final String SQL_GET = "SELECT id,strength,price FROM component WHERE id=?";


    public ComponentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Component update(Component entity) {
        return null;
    }

    @Override
    public boolean delete(Component entity) {

        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, entity.getId());

            if (preparedStatement.executeUpdate() > 0) {
                success = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return success;
    }

    public Component getComponent(int id) {

        double strength = 0;
        double price = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET)){
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                strength = resultSet.getDouble("strength");
                price = resultSet.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Component(id, strength, price);
    }
}
