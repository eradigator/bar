package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.entity.Mix;

import java.sql.*;
import java.util.Map;

/**
 * Created by vten on 24.08.2017.
 */

public class MixDao extends AbstractDao<Mix> {

    private Connection connection;
    private static final String SQL_ADD_MIX = "INSERT INTO mix (cocktail_id,component_id,amount) VALUES (?,?,?)";
    private static final String SQL_GET_MIX = "SELECT m.amount, " +
            "cn.ru AS nameRu," +
            "cn.en AS nameEn " +
            "FROM mix m " +
            "INNER JOIN component c ON m.component_id = c.id " +
            "INNER JOIN component_name cn ON c.name_id = cn.id " +
            "WHERE cocktail_id = ?";

    public MixDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Mix update(Mix entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Mix entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Mix entity) {
        throw new UnsupportedOperationException();
    }

    public boolean add(Mix entity, int cocktailId) {

        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_MIX)) {

            for (Map.Entry<Component, Integer> pair : entity.getMix().entrySet()) {
                preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, cocktailId);
                preparedStatement.setInt(Const.SQL_PARAM_INDEX_2, pair.getKey().getId());
                preparedStatement.setDouble(Const.SQL_PARAM_INDEX_3, pair.getValue());
                preparedStatement.execute();
            }

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public Mix getMix(int cocktailId) {

        Mix mix = new Mix();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_MIX)) {

            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, cocktailId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int amount = Integer.parseInt(resultSet.getString(Const.COLUMN_LABEL_AMOUNT));
                String nameRu = resultSet.getString(Const.COLUMN_LABEL_NAMERU);
                String nameEn = resultSet.getString(Const.COLUMN_LABEL_NAMEEN);

                Component component = new Component();
                component.setComponentName(new ComponentName(nameRu, nameEn));

                mix.getMix().put(component, amount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mix;
    }

}
