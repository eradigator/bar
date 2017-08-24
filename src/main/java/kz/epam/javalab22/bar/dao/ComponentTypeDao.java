package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by admin on 24.08.2017.
 */

public class ComponentTypeDao extends AbstractDao {

    @Override
    public Object update(Object entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Object entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Object entity) {
        throw new UnsupportedOperationException();
    }

    public int getId(BuildMethod buildMethod) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        int id=0;

        /*String QUERY = "SELECT id FROM public.component_type " +
                "WHERE name = '" + buildMethod.toString() + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        connectionPool.returnConnection(connection);
        return id;
    }
}
