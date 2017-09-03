package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.io.InputStream;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vten on 24.08.2017.
 */

public class ImageDao extends AbstractDao<Image> {

    private Connection connection;

    public ImageDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Image update(Image entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Image entity) {
        return false;
    }

    @Override
    public boolean create(Image entity) {
        return false;
    }

    public boolean add(Image image) {

        final String QUERY = "INSERT INTO image (bytes) VALUES (?)";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Boolean success = false;

        try {

            PreparedStatement statement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setBinaryStream(1, image.getInputStream(), image.getInputStreamLength());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException();
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    image.setId(generatedKeys.getInt("id"));
                } else {
                    throw new SQLException("failed, no ID obtained.");
                }
            }

            statement.close();
            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
    }

    public byte[] getImage(int id) {

        byte[] bytes = null;

        final String QUERY = "SELECT bytes FROM image WHERE id =" + id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                bytes = resultSet.getBytes("bytes");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bytes;
    }

}
