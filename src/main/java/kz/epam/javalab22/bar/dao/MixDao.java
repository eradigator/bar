package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 24.08.2017.
 */
public class MixDao extends AbstractDao {

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public boolean delete(Object entity) {
        return false;
    }

    @Override
    public boolean create(Object entity) {
        return false;
    }

    public Map<String,Integer> getMix(int cocktailId) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Map<String,Integer> map = new HashMap<>();

        String name;
        Integer amount;

        String QUERY = "SELECT m.amount,cn.ru AS name\n" +
                "FROM public.mix m \n" +
                "INNER JOIN public.component c ON m.component_id = c.id\n" +
                "INNER JOIN public.component_name cn ON c.name_id = cn.id\n" +
                "WHERE cocktail_id = "+ cocktailId +"";

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

        connectionPool.returnConnection(connection);
        return map;
    }

}
