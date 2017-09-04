<%@ tag import="kz.epam.javalab22.bar.dao.UserDao" %>
<%@ tag import="kz.epam.javalab22.bar.entity.user.User" %>
<%@ tag import="java.util.List" %>
<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<div id="remove" class="tabcontent">

    <div style="color: crimson">
        ${deleteUserResult}
    </div>

    <%List<User> userList = new UserDao().getUserList();%>

    <form name='deleteUserForm' method='POST' action='${pageContext.request.contextPath}/jsp/controller'>
        <input type='hidden' name='command' value='delete_user'>

        <table>

            <tr>
                <td>img</td>
                <td>Name</td>
                <td>email</td>
                <td>Role</td>
                <td>Delete</td>
            </tr>

            <%for (User user : userList) {%>
            <tr>
                <td>
                    <img src='${pageContext.request.contextPath}/images/user_ico.png' style='width:75px;height:75px;'>
                </td>
                <td>
                    <%=user.getName()%>
                </td>
                <td>
                    <%=user.getEmail()%>
                </td>
                <td>
                    <%=user.getRole()%>
                </td>
                <td>
                    <input type="checkbox" name="checkedName" value="<%=user.getName()%>" title="">
                </td>
            </tr>
            <%}%>

        </table>
        <br/>
        <input type="submit" value="Delete"/>

    </form>

</div>

