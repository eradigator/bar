package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.Glass;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CocktailDao extends AbstractDao<Cocktail> {

    private Connection connection;

    public CocktailDao() {
    }

    public CocktailDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Cocktail update(Cocktail entity) {
        throw new UnsupportedOperationException("Так делать нельзя");
    }

    @Override
    public boolean delete(Cocktail entity) {
        throw new UnsupportedOperationException("Так делать нельзя");
    }

    @Override
    public boolean create(Cocktail entity) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Boolean success = false;

        String query = "INSERT INTO public.\"cocktail\"(name/*,component*/,buildmethod,glass) VALUES(?/*,?*/,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getName());
            /*ps.setString(2, "123");*/
            ps.setString(2, entity.getBuildMethod().toString());
            ps.setString(3, entity.getGlass().toString());
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
    }

    public String getNameById(int id) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String entity = "";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM public.\"cocktail\" WHERE ID = '" + id + "'");
            while (resultSet.next()) {
                entity = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return entity;
    }

    public List<Cocktail> getCocktailsList() {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        List<Cocktail> cocktailList = new ArrayList<>();

        String name;
        String imgPath;
        BuildMethod buildMethod;
        Glass glass;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"cocktail\"");
            while (resultSet.next()) {
                name = resultSet.getString("name");
                buildMethod = BuildMethod.valueOf(resultSet.getString("buildmethod"));
                glass = Glass.valueOf(resultSet.getString("glass"));
                imgPath = resultSet.getString("img");
                cocktailList.add(new Cocktail(name, buildMethod, glass, imgPath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return cocktailList;
    }

    public boolean deleteByName(String name) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Boolean success = false;

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("DELETE FROM public.\"cocktail\" WHERE NAME = '" + name + "'");
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
    }

    public boolean update(String name) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Boolean success = false;

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.\"cocktail\" SET img = '/bar/images/cocktails/black_russian.jpg'" +
                    "WHERE NAME= '" + name + "'");
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
    }

}
