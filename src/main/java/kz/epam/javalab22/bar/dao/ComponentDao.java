package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vten on 25.08.2017.
 */
public class ComponentDao extends AbstractDao {

    private Connection connection;

    public ComponentDao() {
    }

    public ComponentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public boolean delete(Object entity) {
        return false;
    }

    @Override
    public boolean create(Object entity) {
        return false;
    }

    public boolean insert(Component entity) {
        final String QUERY = "INSERT INTO public.component (type_id,name_id,strength,price) VALUES (?,?,?,?)";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Boolean success = false;

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

        connectionPool.returnConnection(connection);
        return success;
    }

    public List<String> getList() {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        List<String> components = new ArrayList<>();
        String name;

        try {
            final String QUERY = "SELECT cn.ru AS ru, cn.en AS en\n" +
                    "FROM public.component c \n" +
                    "INNER JOIN public.component_name cn ON c.name_id = cn.id";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                name = resultSet.getString("ru");
                components.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return components;
    }

    public Map<Integer, String> getComponents() {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        //autocommit - false
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Map<Integer,String> components = new LinkedHashMap<>();
        String name;
        int id;

        try {
            final String QUERY = "SELECT c.id,cn.ru AS ru, cn.en AS en\n" +
                    "FROM public.component c \n" +
                    "INNER JOIN public.component_name cn ON c.name_id = cn.id";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                name = resultSet.getString("ru");
                id = resultSet.getInt("id");
                components.put(id,name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //commit
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //connectionPool.returnConnection(connection);
        return components;
    }
}
