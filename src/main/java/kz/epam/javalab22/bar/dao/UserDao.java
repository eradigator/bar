package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {

    private Connection connection;

    private static final String SQL_GET_USER = "SELECT * FROM users WHERE NAME=? AND deleted IS NOT TRUE";
    private static final String SQL_CREATE = "INSERT INTO users (name,password,email,role) VALUES(?,?,?,?)";
    private static final String SQL_GET_PASS = "SELECT * FROM users WHERE NAME=? AND deleted IS NOT TRUE";
    private static final String SQL_DELETE = "UPDATE users SET deleted = TRUE WHERE name=?";
    private static final String SQL_GET_LIST = "SELECT * FROM users WHERE deleted IS NOT TRUE";


    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User update(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(User entity) {

        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setString(Const.SQL_PARAM_INDEX_1, entity.getName());

            if (preparedStatement.executeUpdate() > Const.N_0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public User getUser(String login) {

        User user = new User();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER)) {
            preparedStatement.setString(Const.SQL_PARAM_INDEX_1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(Const.COLUMN_LABEL_ID);
                Role role = Role.valueOf(resultSet.getString(Const.COLUMN_LABEL_ROLE));
                String password = resultSet.getString(Const.COLUMN_LABEL_PASSWORD);
                String email = resultSet.getString(Const.COLUMN_LABEL_EMAIL);
                user = new User(id, login, password, email, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean create(User entity) {

        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setString(Const.SQL_PARAM_INDEX_1, entity.getName());
            preparedStatement.setString(Const.SQL_PARAM_INDEX_2, DigestUtils.md5Hex(entity.getPassword()));
            preparedStatement.setString(Const.SQL_PARAM_INDEX_3, entity.getEmail());
            preparedStatement.setString(Const.SQL_PARAM_INDEX_4, entity.getRole().toString());

            if (preparedStatement.executeUpdate() > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public String getPass(String login) {

        String password = Const.STR_EMPTY;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PASS)) {
            preparedStatement.setString(Const.SQL_PARAM_INDEX_1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString(Const.COLUMN_LABEL_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return password;
    }

    public List<User> getList() {

        List<User> userList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_LIST);
            while (resultSet.next()) {
                String name = resultSet.getString(Const.COLUMN_LABEL_NAME);
                String email = resultSet.getString(Const.COLUMN_LABEL_EMAIL);
                Role role = Role.valueOf(resultSet.getString(Const.COLUMN_LABEL_ROLE));
                userList.add(new User(name, email, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

}
