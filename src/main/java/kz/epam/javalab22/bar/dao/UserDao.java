package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao extends AbstractDao<User> {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
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
        String query = "INSERT INTO USER(name,password,email,isAdmin) VALUES(?,?,?,?)";
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
        return false;
    }
}
