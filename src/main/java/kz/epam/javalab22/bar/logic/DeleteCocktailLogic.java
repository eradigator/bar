package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.Connection;

public class DeleteCocktailLogic {

    public boolean deleteCocktail(String cocktailName) {

        Connection connection = ConnectionPool.getInstance().getConnection();
        Boolean result = new CocktailDao(connection).deleteByName(cocktailName);
        ConnectionPool.getInstance().returnConnection(connection);

        return result;
    }

}
