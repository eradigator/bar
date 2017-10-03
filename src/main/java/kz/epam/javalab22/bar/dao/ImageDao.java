package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Image;
import kz.epam.javalab22.bar.exception.AddImageException;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * @author vten
 */

public class ImageDao extends AbstractDao<Image> {

    private static final Logger log = Logger.getLogger(ImageDao.class);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Image entity) {

        Boolean success = false;

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_IMAGE, Statement.RETURN_GENERATED_KEYS);
            statement.setBinaryStream(Const.SQL_PARAM_INDEX_1, entity.getInputStream(), entity.getInputStreamLength());

            if (statement.executeUpdate() == Const.N_0) {
                log.info(Const.LOG_EXC_IMG);
                throw new AddImageException();
            }

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    entity.setId(resultSet.getInt(Const.COLUMN_LABEL_ID));
                    success = true;
                }
            }
            statement.close();

        } catch (SQLException e) {
            log.error(Const.LOG_EXC_SQL);
        }

        return success;
    }

    public byte[] getImage(int id) {

        byte[] bytes = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_IMAGE)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bytes = resultSet.getBytes(Const.COLUMN_LABEL_BYTES);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(Const.LOG_EXC_SQL);
        }

        return bytes;
    }

}
