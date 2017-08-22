package kz.epam.javalab22.bar.tag;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;


public class UserManagement extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        List<User> userList;
        userList = new UserDao().getUserList();

        try {
            JspWriter out = pageContext.getOut();
            out.write("<h5>" + "Пользователи:" + "</h5>");
            out.write(Const.TABLE_OPN + Const.TR_OPN);
            out.write("<form name='deleteUserForm' method='POST' action='/bar/jsp/controller'>");
            out.write("<input type='hidden' name='command' value='delete_user'>");
            out.write(Const.TD_OPN + "img" + Const.TD_CLS);
            out.write(Const.TD_OPN + "Name" + Const.TD_CLS);
            out.write(Const.TD_OPN + "email" + Const.TD_CLS);
            out.write(Const.TD_OPN + "Role" + Const.TD_CLS);
            out.write(Const.TD_OPN + "Delete" + Const.TD_CLS);
            out.write(Const.TR_CLS);

            for (User user : userList) {
                out.write(Const.TR_OPN);
                out.write(Const.TD_OPN + "<img src='/bar/images/user_ico.png' style='width:75px;height:75px;'>"
                        + Const.TD_CLS);
                out.write(Const.TD_OPN + user.getName() + Const.TD_CLS);
                out.write(Const.TD_OPN + user.getEmail() + Const.TD_CLS);
                out.write(Const.TD_OPN + user.getRole() + Const.TD_CLS);
                out.write(Const.TD_OPN + "<input type='checkbox' name = 'checkedName' value='" +
                        user.getName()+ "'>" + Const.TD_CLS);
                out.write(Const.TR_CLS);
            }

            out.write(Const.TABLE_CLS);
            out.write(Const.BR);
            out.write("<input type='submit' value='Delete'/>");
            out.write("</form>");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

}
