package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.ComponentName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ComponentNameDao extends AbstractDao<ComponentName> {

    private Connection connection;

    private static final String SQL_DELETE = "UPDATE component_name " +
            "SET deleted = TRUE WHERE id =" +
            "(SELECT name_id FROM component WHERE id = ?)";

    private static final String SQL_CREATE = "INSERT INTO component_name (en,ru) VALUES (?,?)";

    private static final String SQL_GET_LIST = "SELECT c.id,cn.ru AS ru, cn.en AS en " +
            "FROM component c " +
            "INNER JOIN component_name cn ON c.name_id = cn.id " +
            "WHERE c.deleted IS NOT TRUE " +
            "ORDER BY cn.ru";

    public ComponentNameDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ComponentName update(ComponentName entity) {
        return null;
    }

    @Override
    public boolean delete(ComponentName entity) {

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
    public boolean create(ComponentName entity) {

        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(Const.SQL_PARAM_INDEX_1, entity.getNameEn());
            preparedStatement.setString(Const.SQL_PARAM_INDEX_2, entity.getNameRu());

            if (preparedStatement.executeUpdate() > Const.N_0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt("id"));
                        success = true;
                    } else {
                        throw new SQLException("failed, no ID obtained.");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public List<ComponentName> getList() {

        List<ComponentName> componentNames = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_LIST)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameRu = resultSet.getString("ru");
                String nameEn = resultSet.getString("en");
                componentNames.add(new ComponentName(id, nameRu, nameEn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return componentNames;
    }

}
