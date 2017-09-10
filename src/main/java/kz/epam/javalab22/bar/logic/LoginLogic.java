package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;

public class LoginLogic {

    public boolean checkLogin(String enterLogin, String enterPass) {

        Connection connection = ConnectionPool.getInstance().getConnection();
        String password = new UserDao(connection).getPass(enterLogin);
        ConnectionPool.getInstance().returnConnection(connection);

        return (DigestUtils.md5Hex(enterPass).equals(password));
    }

}
