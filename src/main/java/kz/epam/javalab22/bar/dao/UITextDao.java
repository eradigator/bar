package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.UIText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by admin on 24.08.2017.
 */

public class UITextDao extends AbstractDao<UIText> {

    private Connection connection;

    public UITextDao() {
    }

    public UITextDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UIText update(UIText entity) {
        return null;
    }

    @Override
    public boolean delete(UIText entity) {
        return false;
    }

    @Override
    public boolean create(UIText entity) {
        return false;
    }

    public UIText get(int id) {

        UIText uiText = new UIText();

        final String QUERY = "SELECT * FROM text WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                String textRu = resultSet.getString("text_ru");
                String textEn = resultSet.getString("text_en");
                uiText = new UIText(id,textRu,textEn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uiText;
    }
}
