package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.Mix;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
            for (Map.Entry<Integer, Double> pair : entity.getMix().entrySet()) {
                ps.setInt(1, cocktailId);
                ps.setInt(2, pair.getKey());
                ps.setDouble(3, pair.getValue());
                ps.execute();
            }

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public Map<String,Integer> getMix(int cocktailId) {

        Map<String,Integer> map = new LinkedHashMap<>();

        String name;
        Integer amount;

        String QUERY = "SELECT m.amount,cn.ru AS name\n" +
                "FROM mix m \n" +
                "INNER JOIN component c ON m.component_id = c.id\n" +
                "INNER JOIN component_name cn ON c.name_id = cn.id\n" +
                "WHERE cocktail_id = "+ cocktailId;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                amount = Integer.parseInt(resultSet.getString("amount"));
                map.put(name,amount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

}
