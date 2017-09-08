<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<div id="remove" class="tabcontent">

    <form name='deleteUserForm' method='POST' action='${pageContext.request.contextPath}/jsp/controller'>
        <input type='hidden' name='command' value='delete_user'>

        <table width="100%">

            <tr>
                <td><b><fmt:message key="enterLogin" bundle="${rb}"/></b></td>
                <td><b><fmt:message key="email" bundle="${rb}"/></b></td>
                <td><b><fmt:message key="role" bundle="${rb}"/></b></td>
                <td><b><fmt:message key="del" bundle="${rb}"/></b></td>
            </tr>

            <c:forEach items="${users}" var="user">
                <tr>
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
        <input type="submit" value="<fmt:message key="delUser" bundle="${rb}" />"/>
    </form>

</div>

