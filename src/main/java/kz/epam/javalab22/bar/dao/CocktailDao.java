package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.*;

public class CocktailDao extends AbstractDao<Cocktail> {

    //private Connection connection;


    public CocktailDao() {
    }

    public CocktailDao(Connection connection) {
        //this.connection = connection;
    }

    @Override
    public Cocktail update(Cocktail entity) {
        throw new UnsupportedOperationException("Так делать нельзя");
        //return null;
    }

    @Override
    public boolean delete(Cocktail entity) {
        throw new UnsupportedOperationException("Так пока делать нельзя");
        //return false;
    }

    @Override
    public boolean create(Cocktail entity) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query = "INSERT INTO public.\"cocktail\"(name) VALUES(?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getName());
            /*ps.setString(2, entity.getBuildMethod().toString());*/
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return false;
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

}
