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

    public User getUser(String login) {

        User user = new User();
        final String QUERY = "SELECT * FROM users " +
                "WHERE NAME='"+ login + "'" +
                " AND deleted IS NOT TRUE";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Role role = Role.valueOf(resultSet.getString("role"));
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                user = new User(id,login,password,email,role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    @Override
    public boolean create(User entity) {

        Boolean success = false;
        final String QUERY = "INSERT INTO users (name,password,email,role) VALUES(?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
            ps.setString(1, entity.getName());
            ps.setString(2, DigestUtils.md5Hex(entity.getPassword()));
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getRole().toString());
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public String getPass(String login) {

        String password = "";
        final String QUERY = String.format("SELECT * FROM users WHERE NAME = '%s'", login);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return password;
    }

    public boolean deleteByLogin(String login) {

        String QUERY = "UPDATE users " +
                "SET deleted = '1' " +
                "WHERE name ='" + login + "'";

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

    public List<User> getUserList() {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        final String QUERY = "SELECT * " +
                "FROM users " +
                "WHERE deleted IS NOT TRUE";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Role role = Role.valueOf(resultSet.getString("role"));
                userList.add(new User(name, email, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.returnConnection(connection);
        return userList;
    }

}
