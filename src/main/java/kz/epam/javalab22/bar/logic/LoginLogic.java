package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.pool.ConnectionPool;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginLogic {

    public boolean checkLogin(String enterLogin, String enterPass) {

        String password = getPassword(enterLogin);
        return (DigestUtils.md5Hex(enterPass).equals(password));
    }


    private String getPassword(String enterLogin) {

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

}
