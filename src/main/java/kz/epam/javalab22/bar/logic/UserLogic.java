package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.util.List;

public class UserLogic {

    private ReqWrapper reqWrapper;
    private Connection connection;

    public UserLogic(ReqWrapper reqWrapper, Connection connection) {
        this.reqWrapper = reqWrapper;
        this.connection = connection;
    }

    public boolean addUser(User user) {
        return new UserDao(connection).create(user);
    }

    public boolean delUser() {
        String login = reqWrapper.getParam(Const.PARAM_CHECKED_NAME);
        User user = new User(login);
        return new UserDao(connection).delete(user);
    }

    public boolean checkForExistence(User user) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());
        boolean isUserExist = false;

        List<User> userList = new UserDao(connection).getList();
        for (User entity : userList) {
            if (entity.getName().equals(user.getName()) || entity.getEmail().equals(user.getEmail())) {
                isUserExist = true;
                reqWrapper.addAttribute(Const.ATTR_ERROR, messageManager.getProperty(Const.PROP_USER_EXIST));
            }
        }

        return isUserExist;
    }

    public boolean checkLogin() {

        String login = reqWrapper.getParam(Const.PARAM_LOGIN);
        String pass = reqWrapper.getParam(Const.PARAM_PASSWORD);

        String password = new UserDao(connection).getPass(login);
        return (DigestUtils.md5Hex(pass).equals(password));
    }

}
