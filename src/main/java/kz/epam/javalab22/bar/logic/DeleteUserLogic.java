package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.Connection;

public class DeleteUserLogic {

    public boolean deleteUser(String checkedUserName) {

        Connection connection = ConnectionPool.getInstance().getConnection();
        Boolean result = new UserDao(connection).deleteByLogin(checkedUserName);
        ConnectionPool.getInstance().returnConnection(connection);

        return result;
    }

}
