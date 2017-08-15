package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.dao.UserDao;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginLogic {

    public boolean checkLogin(String enterLogin, String enterPass) {
        String password = new UserDao().getPasswordByLogin(enterLogin);
        return (DigestUtils.md5Hex(enterPass).equals(password));
    }

}
