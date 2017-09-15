package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 24.08.2017.
 */

public class MethodDao extends AbstractDao<Method> {

    private Connection connection;

    private static final String SQL_GET_LIST = "SELECT * FROM build_method ORDER BY id";

    public MethodDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Method update(Method entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Method entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Method entity) {
        throw new UnsupportedOperationException();
    }

    public List<Method> getList() {

        List<Method> methods = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_LIST);
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString(Const.COLUMN_LABEL_ID));
                String nameRu = resultSet.getString(Const.COLUMN_LABEL_NAME_RU);
                String nameEn = resultSet.getString(Const.COLUMN_LABEL_NAME_EN);
                methods.add(new Method(id, nameRu, nameEn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return methods;
    }
}
