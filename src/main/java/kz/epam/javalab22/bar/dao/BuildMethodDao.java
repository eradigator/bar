package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.*;

/**
 * Created by admin on 24.08.2017.
 */

public class BuildMethodDao extends AbstractDao<BuildMethod> {

    @Override
    public BuildMethod update(BuildMethod entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(BuildMethod entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(BuildMethod entity) {
        throw new UnsupportedOperationException();
    }

    public int getId(BuildMethod buildMethod) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        int id = 0;

        final String QUERY = "SELECT id " +
                "FROM build_method " +
                "WHERE method_name = '" + buildMethod.toString() + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return id;
    }
}
