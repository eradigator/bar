package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.User;

public class AddUserLogic {

    public boolean addUser(User user) {
         return new UserDao().create(user);
    }

}
