package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.ComponentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vten on 24.08.2017.
 */

public class ComponentTypeDao extends AbstractDao {

    private Connection connection;

    private static final String SQL_GET_LIST = "SELECT * FROM component_type ORDER BY id";

    public ComponentTypeDao(Connection connection) {
        this.connection = connection;
    }

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

    public List<ComponentType> getList() {

        List<ComponentType> componentTypes = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_LIST)){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameEn = resultSet.getString("name_en");
                String nameRu = resultSet.getString("name_ru");
                componentTypes.add(new ComponentType(id,nameRu,nameEn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return componentTypes;
    }
}
