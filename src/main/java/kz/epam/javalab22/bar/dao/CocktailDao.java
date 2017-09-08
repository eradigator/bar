package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.*;
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

        final String QUERY = "INSERT INTO cocktail(name,method,glass_id,image_id) " +
                "VALUES(?,?,?,?)";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        int methodId = entity.getMethodId();
        Boolean success = false;

        try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, methodId);
            ps.setInt(3, entity.getGlass().getId());
            ps.setInt(4, entity.getImage().getId());
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
    }

    public Cocktail get(int cocktailId) {

        final String QUERY = "SELECT c.id,c.glass,c.image_id," +
                "cn.id AS cocktailNameId, " +
                "cn.name_ru AS cocktailNameNameRu, " +
                "cn.name_en AS cocktailNameNameEn, " +
                "b.id AS methodId, " +
                "b.name_ru AS methodNameRu, " +
                "b.name_en AS methodNameEn, " +
                "g.id AS glassId, " +
                "g.name_ru AS glassNameRu, " +
                "g.name_en AS glassNameEn " +
                "FROM cocktail c " +
                "INNER JOIN cocktail_name cn ON c.name_id = cn.id " +
                "INNER JOIN build_method b ON c.method = b.id " +
                "INNER JOIN glass g ON c.glass_id = g.id " +
                "AND c.deleted IS NOT TRUE " +
                "AND c.id=" + cocktailId;

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Cocktail cocktail = new Cocktail();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                CocktailName cocktailName;
                {
                    int cocktailNameId = resultSet.getInt("cocktailNameId");
                    String cocktailNameNameRu = resultSet.getString("cocktailNameNameRu");
                    String cocktailNameNameEn = resultSet.getString("cocktailNameNameEn");
                    cocktailName = new CocktailName(cocktailNameId, cocktailNameNameRu, cocktailNameNameEn);
                }
                Method method;
                {
                    int methodId = resultSet.getInt("methodId");
                    String methodNameRU = resultSet.getString("methodNameRu");
                    String methodNameEn = resultSet.getString("methodNameEn");
                    method = new Method(methodId, methodNameRU, methodNameEn);
                }
                Glass glass;
                {
                    int glassId = resultSet.getInt("glassId");
                    String glassNameRu = resultSet.getString("glassNameRu");
                    String glassNameEn = resultSet.getString("glassNameEn");
                    glass = new Glass(glassId, glassNameRu, glassNameEn);
                }

                int imageId = resultSet.getInt("image_id");
                Mix mix = new MixDao(connection).getMix(id);
                cocktail = new Cocktail(id, cocktailName, mix, method, glass, imageId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return cocktail;
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

        final String QUERY = "SELECT c.id,c.glass,c.image_id," +
                "cn.id AS cocktailNameId, " +
                "cn.name_ru AS cocktailNameNameRu, " +
                "cn.name_en AS cocktailNameNameEn, " +
                "b.id AS methodId, " +
                "b.name_ru AS methodNameRu, " +
                "b.name_en AS methodNameEn, " +
                "g.id AS glassId, " +
                "g.name_ru AS glassNameRu, " +
                "g.name_en AS glassNameEn " +
                "FROM cocktail c " +
                "INNER JOIN cocktail_name cn ON c.name_id = cn.id " +
                "INNER JOIN build_method b ON c.method = b.id " +
                "INNER JOIN glass g ON c.glass_id = g.id " +
                "WHERE strength > 0 " +
                "AND c.deleted IS NOT TRUE " +
                "ORDER BY c.name";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        List<Cocktail> cocktailList = new ArrayList<>();


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                CocktailName cocktailName;
                {
                    int cocktailNameId = resultSet.getInt("cocktailNameId");
                    String cocktailNameNameRu = resultSet.getString("cocktailNameNameRu");
                    String cocktailNameNameEn = resultSet.getString("cocktailNameNameEn");
                    cocktailName = new CocktailName(cocktailNameId, cocktailNameNameRu, cocktailNameNameEn);
                }
                Method method;
                {
                    int methodId = resultSet.getInt("methodId");
                    String methodNameRU = resultSet.getString("methodNameRu");
                    String methodNameEn = resultSet.getString("methodNameEn");
                    method = new Method(methodId, methodNameRU, methodNameEn);
                }
                Glass glass;
                {
                    int glassId = resultSet.getInt("glassId");
                    String glassNameRu = resultSet.getString("glassNameRu");
                    String glassNameEn = resultSet.getString("glassNameEn");
                    glass = new Glass(glassId, glassNameRu, glassNameEn);
                }

                int imageId = resultSet.getInt("image_id");
                Mix mix = new MixDao(connection).getMix(id);
                cocktailList.add(new Cocktail(id, cocktailName, mix, method, glass, imageId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return cocktailList;
    }

    public List<Cocktail> getNonAlcoList() {

        final String QUERY = "SELECT c.id,/*c.name,*/c.glass,c.image_id," +
                "cn.id AS cocktailNameId, " +
                "cn.name_ru AS cocktailNameNameRu, " +
                "cn.name_en AS cocktailNameNameEn, " +
                "b.id AS methodId, " +
                "b.name_ru AS methodNameRu, " +
                "b.name_en AS methodNameEn, " +
                "g.id AS glassId, " +
                "g.name_ru AS glassNameRu, " +
                "g.name_en AS glassNameEn " +
                "FROM cocktail c " +
                "INNER JOIN cocktail_name cn ON c.name_id = cn.id " +
                "INNER JOIN build_method b ON c.method = b.id " +
                "INNER JOIN glass g ON c.glass_id = g.id " +
                "WHERE strength = 0 " +
                "AND c.deleted IS NOT TRUE " +
                "ORDER BY c.name";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        List<Cocktail> cocktailList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                CocktailName cocktailName;
                {
                    int cocktailNameId = resultSet.getInt("cocktailNameId");
                    String cocktailNameNameRu = resultSet.getString("cocktailNameNameRu");
                    String cocktailNameNameEn = resultSet.getString("cocktailNameNameEn");
                    cocktailName = new CocktailName(cocktailNameId, cocktailNameNameRu, cocktailNameNameEn);
                }
                Method method;
                {
                    int methodId = resultSet.getInt("methodId");
                    String methodNameRU = resultSet.getString("methodNameRu");
                    String methodNameEn = resultSet.getString("methodNameEn");
                    method = new Method(methodId, methodNameRU, methodNameEn);
                }
                Glass glass;
                {
                    int glassId = resultSet.getInt("glassId");
                    String glassNameRu = resultSet.getString("glassNameRu");
                    String glassNameEn = resultSet.getString("glassNameEn");
                    glass = new Glass(glassId, glassNameRu, glassNameEn);
                }

                int imageId = resultSet.getInt("image_id");
                Mix mix = new MixDao(connection).getMix(id);
                cocktailList.add(new Cocktail(id, cocktailName, mix, method, glass, imageId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        connectionPool.returnConnection(connection);
        return cocktailList;
    }

    public boolean deleteById(int cocktailId) {

        String QUERY = "UPDATE cocktail " +
                "SET deleted = '1' " +
                "WHERE id ='" + cocktailId + "'";

        Boolean success = false;

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

    public boolean setStrength(int cocktailId, Double strength) {

        String QUERY = "UPDATE cocktail " +
                "SET strength = " + strength +
                " WHERE id=" + cocktailId;
        Boolean success = false;

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

}
