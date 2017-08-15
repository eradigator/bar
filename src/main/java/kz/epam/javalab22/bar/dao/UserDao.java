package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.User;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class UserDao extends AbstractDao<User> {

    //private Connection connection;

    public UserDao() {
    }

    public UserDao(Connection connection) {
        //this.connection = connection;
    }

    @Override
    public User update(User entity) {
        throw new UnsupportedOperationException("Так делать нельзя");
    }

    @Override
    public boolean delete(User entity) {
        throw new UnsupportedOperationException("Так пока делать нельзя");
    }

    @Override
    public boolean create(User entity) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query = "INSERT INTO public.\"webUsers\"(name,password,email,isAdmin) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getName());
            ps.setString(2, DigestUtils.md5Hex(entity.getPassword()));
            ps.setString(3, entity.getEmail());
            ps.setBoolean(4, entity.isAdmin());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return false;
    }

    public String getPasswordByLogin(String enterLogin) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String password = "";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM public.\"webUsers\" WHERE NAME = '" + enterLogin + "'");
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);

        return password;
    }

   /* public boolean createAdmin() {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query = "INSERT INTO public.\"webUsers\"(name,password,email,isadmin) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "erad");
            ps.setString(2, DigestUtils.md5Hex("pass123"));
            ps.setString(3, "eradigator@gmail.com");
            ps.setBoolean(4, true);
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.returnConnection(connection);
        return false;
    }*/
}
