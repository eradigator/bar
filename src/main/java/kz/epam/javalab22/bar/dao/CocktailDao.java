package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.Glass;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CocktailDao extends AbstractDao<Cocktail> {

    private Connection connection;

    public CocktailDao() {
    }

    public CocktailDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Cocktail update(Cocktail entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Cocktail entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Cocktail entity) {

        final String QUERY = "INSERT INTO public.cocktail(name,method,glass,image_id) " +
                "VALUES(?,?,?,?)";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        int buildMethodId = new BuildMethodDao().getId(entity.getBuildMethod());
        Boolean success = false;

        try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, buildMethodId);
            ps.setString(3, entity.getGlass().toString());
            ps.setInt(4, entity.getImage().getId());
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
    }

    public int getId(String name) {

        final String QUERY = "SELECT id FROM cocktail WHERE name='" + name + "'";
        int id = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }


    public List<Cocktail> getCocktailsList() {

        final String QUERY = "SELECT c.id,c.name,c.glass,c.image_id,b.method_name AS method " +
                "FROM cocktail c " +
                "INNER JOIN build_method b ON c.method = b.id";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        List<Cocktail> cocktailList = new ArrayList<>();

        String name;
        int id;
        Map<String, Integer> map;
        BuildMethod buildMethod;
        Glass glass;
        int imageId;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                buildMethod = BuildMethod.valueOf(resultSet.getString("method"));
                glass = Glass.valueOf(resultSet.getString("glass"));
                imageId = resultSet.getInt("image_id");
                map = new MixDao(connection).getMix(id);
                cocktailList.add(new Cocktail(name, map, buildMethod, glass, imageId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return cocktailList;
    }

    public boolean deleteByName(String name) {

        Boolean success = false;
        final String QUERY = String.format("DELETE FROM cocktail WHERE name = '%s'", name);

        try {
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(QUERY);

            if (rowsAffected > 0) {
                success = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean update(String name) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Boolean success = false;

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE cocktail SET img = '/bar/images/cocktails/name.jpg'" +
                    "WHERE NAME= '" + name + "'");
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
    }

}
