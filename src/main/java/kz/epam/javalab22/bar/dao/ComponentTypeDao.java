package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vten on 24.08.2017.
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

    /*public int getId(String name) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        int id=0;

        final String QUERY = "SELECT id FROM public.component_type " +
                "WHERE name = '" + name + "'";
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
    }*/

    public Map<Integer,String> getComponentTypes() {

        final String QUERY = "SELECT * FROM public.component_type ORDER BY name ASC";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        //List<String> componentTypesList = new ArrayList<>();
        Map<Integer,String> componentTypes = new HashMap<>();
        String name;
        int id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                componentTypes.put(id,name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return componentTypes;
    }
}
