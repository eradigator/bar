<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<div id="remove" class="tabcontent">

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

            <c:forEach items="${users}" var="user">
                <tr>
                    <td>
                        <img src='${pageContext.request.contextPath}/images/user_ico.png'
                             style='width:75px;height:75px;'>
                    </td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>
                        <input type="checkbox" name="checkedName" value="${user.name}" title="">
                    </td>
                </tr>
            </c:forEach>

        </table>
        <br/>
        <input type="submit" value="Delete"/>
    </form>

</div>

