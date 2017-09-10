package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;

import java.sql.*;

/**
 * Created by vten on 25.08.2017.
 */
public class ComponentDao extends AbstractDao<Component> {

    private Connection connection;

    public ComponentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Component update(Component entity) {
        return null;
    }

    @Override
    public boolean delete(Component entity) {
        String QUERY = "UPDATE component " +
                "SET deleted = '1' " +
                "WHERE id =" + entity.getId();

        Boolean success = false;

        try {
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(QUERY);

            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean create(Component entity) {
        return false;
    }

    public boolean insert(Component entity) {

        final String QUERY = "INSERT INTO component (type_id,name_id,strength,price) VALUES (?,?,?,?)";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Boolean success = false;

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
            ps.setInt(1, entity.getType());
            ps.setInt(2, entity.getNameId());
            ps.setDouble(3, entity.getStrength());
            ps.setDouble(4, entity.getPrice());
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
    }

    public Component getComponent(int id) {

        final String QUERY = "SELECT id,strength,price FROM component WHERE id=" + id;

        double strength = 0;
        double price = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
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
