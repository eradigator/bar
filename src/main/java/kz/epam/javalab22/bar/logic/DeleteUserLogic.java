package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.dao.UserDao;
import org.apache.commons.codec.digest.DigestUtils;

public class DeleteUserLogic {

    public boolean deleteUser(String checkedUserName) {
         return new UserDao().deleteByLogin(checkedUserName);
    }

}
