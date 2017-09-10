package kz.epam.javalab22.bar.dao;

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

    public MixDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Mix update(Mix entity) {
        return null;
    }

    @Override
    public boolean delete(Mix entity) {
        return false;
    }

    @Override
    public boolean create(Mix entity) {
        throw new UnsupportedOperationException();
    }

    public boolean add(Mix entity, int cocktailId) {

        final String QUERY = "INSERT INTO mix (cocktail_id,component_id,amount) VALUES (?,?,?)";
        Boolean success = false;

        try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
            for (Map.Entry<Component, Integer> pair : entity.getMix().entrySet()) {
                ps.setInt(1, cocktailId);
                ps.setInt(2, pair.getKey().getId());
                ps.setDouble(3, pair.getValue());
                ps.execute();
            }

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public Mix getMix(int cocktailId) {

        Mix mix = new Mix();

        String QUERY = "SELECT m.amount, " +
                "cn.ru AS nameRu," +
                "cn.en AS nameEn " +
                "FROM mix m " +
                "INNER JOIN component c ON m.component_id = c.id " +
                "INNER JOIN component_name cn ON c.name_id = cn.id " +
                "WHERE cocktail_id = "+ cocktailId;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                int amount = Integer.parseInt(resultSet.getString("amount"));
                String nameRu = resultSet.getString("nameRu");
                String nameEn = resultSet.getString("nameEn");
                ComponentName componentName = new ComponentName(nameRu,nameEn);
                Component component = new Component();
                component.setComponentName(componentName);
                mix.getMix().put(component,amount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mix;
    }

}
