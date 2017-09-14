package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CocktailDao extends AbstractDao<Cocktail> {

    private Connection connection;

    private static final String SQL_DELETE = "UPDATE cocktail SET deleted = '1' WHERE id = ?";

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

    public boolean delete(int id) {
        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, id);
            if (preparedStatement.executeUpdate() > Const.N_0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean create(Cocktail entity) {

        final String QUERY = "INSERT INTO cocktail(name_id,method,glass_id,image_id) " +
                "VALUES(?,?,?,?)";

        Boolean success = false;

        try (PreparedStatement ps = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Const.SQL_PARAM_INDEX_1, entity.getCocktailName().getId());
            ps.setInt(Const.SQL_PARAM_INDEX_2, entity.getMethodId());
            ps.setInt(Const.SQL_PARAM_INDEX_3, entity.getGlass().getId());
            ps.setInt(Const.SQL_PARAM_INDEX_4, entity.getImage().getId());

            if (ps.executeUpdate() > 0) {
                ResultSet resultSet = ps.getGeneratedKeys();
                while (resultSet.next()) {
                    entity.setId(resultSet.getInt("id"));
                }
                success = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public Cocktail get(int cocktailId) {

        final String QUERY = "SELECT c.id,c.image_id," +
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

        final String QUERY = "SELECT c.id,c.image_id," +
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
                "ORDER BY cocktailNameNameRu";

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

        return cocktailList;
    }

    public List<Cocktail> getNonAlcoList() {

        final String QUERY = "SELECT c.id,c.image_id," +
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
                "ORDER BY cocktailNameNameRu";

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

    public boolean setStrength(int cocktailId, Double strength) {

        String query = "UPDATE cocktail " +
                "SET strength = " + strength +
                " WHERE id=" + cocktailId;

        Boolean success = false;

        try {
            Statement statement = connection.createStatement();
            if (statement.executeUpdate(query) > Const.N_0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

}
