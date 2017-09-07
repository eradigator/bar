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

    private Connection connection;

    public ComponentNameDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ComponentName update(ComponentName entity) {
        return null;
    }

    @Override
    public boolean delete(ComponentName entity) {

        String QUERY = "UPDATE component_name " +
                "SET deleted = '1' WHERE id =" +
                "(SELECT name_id " +
                "FROM component " +
                "WHERE id =" + entity.getId() + ")";

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
    public boolean create(ComponentName entity) {

        final String QUERY = "INSERT INTO component_name (en,ru) VALUES (?,?)";

        Boolean success = false;

        try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
            ps.setString(1, entity.getNameEn());
            ps.setString(2, entity.getNameRu());
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public int getId(ComponentName entity) {

        final String QUERY = String.format("SELECT id FROM component_name WHERE en = '%s'", entity.getNameEn());
        int id = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public List<ComponentName> getList() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        List<ComponentName> componentNames = new ArrayList<>();

        try {
            final String QUERY = "SELECT c.id,cn.ru AS ru, cn.en AS en " +
                    "FROM component c " +
                    "INNER JOIN component_name cn ON c.name_id = cn.id " +
                    "WHERE c.deleted IS NOT TRUE " +
                    "ORDER BY cn.ru";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameRu = resultSet.getString("ru");
                String nameEn = resultSet.getString("en");
                componentNames.add(new ComponentName(id,nameRu,nameEn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return componentNames;
    }

}
