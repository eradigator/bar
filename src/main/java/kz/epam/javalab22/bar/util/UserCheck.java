package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

public class UserCheck {

    private ReqWrapper reqWrapper;

    public UserCheck(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
    }

    public boolean roleIsAdminCheck() {

        boolean isAdmin = false;

        if (null != reqWrapper.getSessionAttribute(Const.ATTR_USER)) {
            User user = (User) reqWrapper.getSessionAttribute(Const.ATTR_USER);

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
