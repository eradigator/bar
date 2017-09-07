package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Method;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 24.08.2017.
 */

public class MethodDao extends AbstractDao<BuildMethod> {

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

    /*public int getId(BuildMethod buildMethod) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        int id = 0;

        final String QUERY = "SELECT id " +
                "FROM build_method " +
                "WHERE name = '" + buildMethod.toString() + "'";
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

    public List<Method> getList() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        List<Method> methods = new ArrayList<>();
        int id;
        String nameRu;
        String nameEn;

        final String QUERY = "SELECT * FROM build_method ORDER BY id";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("id"));
                nameRu = resultSet.getString("name_ru");
                nameEn = resultSet.getString("name_en");
                methods.add(new Method(id, nameRu, nameEn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return methods;
    }
}
