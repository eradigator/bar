package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 24.08.2017.
 */

public class MethodDao extends AbstractDao<Method> {

    private Connection connection;

    public MethodDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Method update(Method entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Method entity) {
        return false;
    }

    @Override
    public boolean create(Method entity) {
        return false;
    }

    public List<Method> getList() {

        List<Method> methods = new ArrayList<>();

        final String QUERY = "SELECT * FROM build_method ORDER BY id";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String nameRu = resultSet.getString("name_ru");
                String nameEn = resultSet.getString("name_en");
                methods.add(new Method(id, nameRu, nameEn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return methods;
    }
}
