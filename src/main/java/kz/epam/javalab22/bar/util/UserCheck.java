package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.entity.user.Role;
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
            if (user.getRole() == Role.ADMIN) {
                isAdmin = true;
            }
        }

        return isAdmin;
    }
}
