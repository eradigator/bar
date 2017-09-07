package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.Glass;
import kz.epam.javalab22.bar.entity.Method;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 24.08.2017.
 */

public class GlassDao extends AbstractDao<Glass> {
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
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        List<Glass> glasses = new ArrayList<>();
        int id;
        String nameRu;
        String nameEn;

        final String QUERY = "SELECT * FROM glass ORDER BY id";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("id"));
                nameRu = resultSet.getString("name_ru");
                nameEn = resultSet.getString("name_en");
                glasses.add(new Glass(id, nameRu, nameEn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return glasses;
    }
}
