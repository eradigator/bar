package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {

    private Connection connection;
    private List<User> userList = new ArrayList<>();

    public UserDao() {
    }

    public UserDao(Connection connection) {
        this.connection = connection;
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
        Boolean success = false;

        String query = "INSERT INTO public.\"webUsers\"(name,password,email,role) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getName());
            ps.setString(2, DigestUtils.md5Hex(entity.getPassword()));
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getRole().toString());
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return success;
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

    public boolean deleteByLogin(String login) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("DELETE FROM public.\"webUsers\" WHERE NAME = '" + login + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            connectionPool.returnConnection(connection);
            return false;
        }

        connectionPool.returnConnection(connection);
        return true;
    }

    public List<User> getUserList() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String name;
        String email;
        Role role = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"webUsers\"");
            while (resultSet.next()) {
                name = resultSet.getString("name");
                email = resultSet.getString("email");
                switch (resultSet.getString("role")) {
                    case "ADMIN":
                        role = Role.ADMIN;
                        break;
                    case "USER":
                        role = Role.USER;
                        break;
                }

                userList.add(new User(name, email, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return userList;
    }

    public boolean createAdmin() {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Boolean isOK = false;

        String query = "INSERT INTO public.\"webUsers\"(name,password,email,role) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "erad");
            ps.setString(2, DigestUtils.md5Hex("pass123"));
            ps.setString(3, "eradigator@mail.ru");
            ps.setString(4, "ADMIN");
            ps.execute();
            isOK = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return isOK;
    }
}
