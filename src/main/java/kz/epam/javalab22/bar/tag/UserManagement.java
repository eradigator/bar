package kz.epam.javalab22.bar.tag;

import kz.epam.javalab22.bar.dao.UserDao;
import kz.epam.javalab22.bar.entity.user.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;


public class UserManagement extends TagSupport {

    private final static String BR = "<br/>";
    private final static String TR_OPN = "<tr>";
    private final static String TR_CLS = "</tr>";
    private final static String TD_OPN = "<td>";
    private final static String TD_CLS = "</td>";
    private final static String TABLE_OPN = "<table width='100%'>";
    private final static String TABLE_CLS = "</table>";

    @Override
    public int doStartTag() throws JspException {

        List<User> userList;
        userList = new UserDao().getUserList();

        try {
            JspWriter out = pageContext.getOut();
            out.write("Пользователи:" + BR);
            out.write(TABLE_OPN + TR_OPN);
            out.write("<form name='deleteUserForm' method='POST' action='/bar/jsp/controller'>");
            out.write("<input type='hidden' name='command' value='delete_user'>");
            out.write(TD_OPN + "img" + TD_CLS);
            out.write(TD_OPN + "Name" + TD_CLS);
            out.write(TD_OPN + "email" + TD_CLS);
            out.write(TD_OPN+"Role"+TD_CLS);
            out.write(TD_OPN+"Delete"+TD_CLS);
            out.write(TR_CLS);

            for (User user : userList) {
                out.write(TR_OPN);
                out.write(TD_OPN + "<img src='/bar/images/user_ico.png' style='width:75px;height:75px;'>"
                        + TD_CLS);
                out.write(TD_OPN + user.getName() + TD_CLS);
                out.write(TD_OPN + user.getEmail() + TD_CLS);
                out.write(TD_OPN + user.getRole() + TD_CLS);
                out.write(TD_OPN + "<input type='checkbox' name = 'checkedName' value='" + user.getName()+ "'>" + TD_CLS);
                out.write(TR_CLS);
            }

            out.write(TABLE_CLS);
            out.write(BR);
            out.write("<input type='submit' value='Delete'/>");
            out.write("</form>");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

}
