package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vten on 24.08.2017.
 */

public class ComponentNameDao extends AbstractDao<ComponentName> {
    @Override
    public ComponentName update(ComponentName entity) {
        return null;
    }

    @Override
    public boolean delete(ComponentName entity) {
        return false;
    }

    @Override
    public boolean create(ComponentName entity) {

        final String QUERY = "INSERT INTO public.component_name (en,ru) VALUES (?,?)";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Boolean success = false;

        try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
            ps.setString(1, entity.getEn());
            ps.setString(2, entity.getRu());
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
    }

    public int getId(ComponentName entity) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        int id = 0;

        try {
            String QUERY = "SELECT id FROM public.component_name " + "WHERE en = '" + entity.getEn() + "'";
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

    public List<String> getComponentNameList() {

        final String QUERY = "SELECT * FROM public.component_name ORDER BY ru ASC";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        List<String> componentNamesList = new ArrayList<>();
        String name;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                name = resultSet.getString("ru");
                componentNamesList.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return componentNamesList;
    }
}
