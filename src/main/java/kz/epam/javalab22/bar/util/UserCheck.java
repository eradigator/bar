package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

public class UserCheck {

    private ReqWrapper reqWrapper;

    public UserCheck(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
    }

    public boolean checkForAdmin() {

        boolean isAdmin = false;

        if (null != reqWrapper.getUser()) {
            User user = reqWrapper.getUser();

            switch (user.getRole()) {
                case ADMIN:
                    isAdmin = true;
                    break;
                case USER:
                    isAdmin = false;
                    break;
            }
        }

        return isAdmin;
    }
}
