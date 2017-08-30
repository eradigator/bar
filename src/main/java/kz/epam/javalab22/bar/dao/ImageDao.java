package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.Mix;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.io.InputStream;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vten on 24.08.2017.
 */

public class ImageDao extends AbstractDao {

    @Override
    public Object update(Object entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Object entity) {
        return false;
    }

    @Override
    public boolean create(Object entity) {
        return false;
    }

    public boolean add(InputStream inputStream, int length) {

        //возвращает id добавленной записи но это не точно
        final String QUERY = "INSERT INTO image (bytes) VALUES (?) RETURNING id";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Boolean success = false;

        try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
            ps.setBinaryStream(1,inputStream,length);
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
    }

}
