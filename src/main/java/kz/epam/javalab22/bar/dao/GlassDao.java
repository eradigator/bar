package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Glass;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vten
 */

public class GlassDao extends AbstractDao<Glass> {

    private static final Logger log = Logger.getLogger(GlassDao.class);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Glass entity) {
        throw new UnsupportedOperationException();
    }

    public List<Glass> getList() {

        List<Glass> glasses = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_LIST)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(Const.COLUMN_LABEL_ID);
                String nameRu = resultSet.getString(Const.COLUMN_LABEL_NAME_RU);
                String nameEn = resultSet.getString(Const.COLUMN_LABEL_NAME_EN);
                glasses.add(new Glass(id, nameRu, nameEn));
            }
        } catch (SQLException e) {
            log.error(Const.LOG_EXC_SQL);
        }

        return glasses;
    }
}
