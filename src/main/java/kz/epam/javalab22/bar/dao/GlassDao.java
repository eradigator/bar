package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.Glass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 24.08.2017.
 */

public class GlassDao extends AbstractDao<Glass> {

    private Connection connection;
    private static final String SQL_GET_LIST = "SELECT * FROM glass ORDER BY id";

    public GlassDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Glass update(Glass entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Glass entity) {
        return false;
    }

    @Override
    public boolean create(Glass entity) {
        return false;
    }

    public List<Glass> getList() {

        List<Glass> glasses = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_LIST)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String nameRu = resultSet.getString("name_ru");
                String nameEn = resultSet.getString("name_en");
                glasses.add(new Glass(id, nameRu, nameEn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return glasses;
    }
}
