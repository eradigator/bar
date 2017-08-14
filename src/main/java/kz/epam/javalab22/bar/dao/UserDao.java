package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.User;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao extends AbstractDao<User> {

    //private Connection connection;

    public UserDao(Connection connection) {
        //this.connection = connection;
    }

    @Override
    public User update(User entity) {
        throw new UnsupportedOperationException("Так делать нельзя");
        //return null;
    }

    @Override
    public boolean delete(User entity) {
        throw new UnsupportedOperationException("Так пока делать нельзя");
        //return false;
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

    public boolean createAdmin() {

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
    }
}
