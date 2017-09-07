package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;

public class UserLogic {

    private ReqWrapper reqWrapper;

    public UserLogic(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
    }

    public boolean addUser() {

        boolean success;

        String login = reqWrapper.getParam(Const.PARAM_LOGIN);
        String password = reqWrapper.getParam(Const.PARAM_PASSWORD);
        String email = reqWrapper.getParam(Const.PARAM_EMAIL);
        Role role = Role.valueOf(reqWrapper.getParam(Const.PARAM_ROLE).toUpperCase());

        User user = new User(login,password,email,role);

        Connection connection = ConnectionPool.getInstance().getConnection();
        success = new UserDao(connection).create(user);
        ConnectionPool.getInstance().returnConnection(connection);

        return success;
    }

    public boolean delUser() {

        String login = reqWrapper.getParam("checkedName");
        Connection connection = ConnectionPool.getInstance().getConnection();
        Boolean result = new UserDao(connection).deleteByLogin(login);
        ConnectionPool.getInstance().returnConnection(connection);

        return result;
    }

}
