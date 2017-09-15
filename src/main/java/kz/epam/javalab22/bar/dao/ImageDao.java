package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.exception.AddImageException;

import java.sql.*;

/**
 * Created by vten on 24.08.2017.
 */

public class ImageDao extends AbstractDao<Image> {

    private Connection connection;

    private static final String SQL_GET_IMAGE = "SELECT bytes FROM image WHERE id=?";
    private static final String SQL_ADD_IMAGE = "INSERT INTO image (bytes) VALUES (?)";

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

        Boolean success = false;

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_IMAGE, Statement.RETURN_GENERATED_KEYS);
            statement.setBinaryStream(Const.SQL_PARAM_INDEX_1, image.getInputStream(), image.getInputStreamLength());

            if (statement.executeUpdate() == 0) {
                throw new AddImageException();
            }

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    image.setId(resultSet.getInt("id"));
                }
            }

            statement.close();
            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public byte[] getImage(int id) {

        byte[] bytes = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_IMAGE)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bytes = resultSet.getBytes("bytes");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bytes;
    }

}
