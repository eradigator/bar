package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.UIText;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * @author vten
 */

public class UITextDao extends AbstractDao<UIText> {

    private static final Logger log = Logger.getLogger(UITextDao.class);

    private Connection connection;
    private static final String SQL_GET_UI_TEXT = "SELECT * FROM text WHERE id=?";

    public UITextDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UIText update(UIText entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(UIText entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(UIText entity) {
        throw new UnsupportedOperationException();
    }

    public UIText get(int id) {

        UIText uiText = new UIText();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_UI_TEXT)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String textRu = resultSet.getString(Const.COLUMN_LABEL_TEXT_RU);
                String textEn = resultSet.getString(Const.COLUMN_LABEL_TEXT_EN);
                uiText = new UIText(id, textRu, textEn);
            }
        } catch (SQLException e) {
            log.info(Const.LOG_EXC_SQL);
        }

        return uiText;
    }
}
