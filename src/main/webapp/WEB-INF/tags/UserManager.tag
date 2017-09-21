<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<c:if test="${(not empty addUserResult) or (not empty deleteUserResult) or (not empty error)}">
    <div class="result_field">
        <div style="color:#02834b">
            <h5>${addUserResult}</h5>
            <h5>${deleteUserResult}</h5>
        </div>
        <div style="color: crimson">
            <h5>${error}</h5>
        </div>
    </div>
    <br/>
</c:if>

<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'add')" id="defaultOpen">
        <fmt:message key="regUser" bundle="${rb}"/>
    </button>
    <button class="tablinks" onclick="openTab(event, 'remove')">
        <fmt:message key="delUser" bundle="${rb}"/>
    </button>
</div>

<div id="add" class="tabcontent">
    <form name="addUser" method="post" action="${pageContext.request.contextPath}/jsp/controller">
        <input type="hidden" name="command" value="add_user"/>

        <p>
            <fmt:message key="enterLogin" bundle="${ rb }"/><br/>
            <input name="login" value="" required title=""/><br/>
        </p>

        <p>
            <fmt:message key="password" bundle="${ rb }"/><br/>
            <input type="password" name="password" value="" required title=""/><br/>
        </p>

        <p>
            <fmt:message key="email" bundle="${ rb }"/><br/>
            <input type="email" name="email" value="" required title=""/><br/>
        </p>

        <p>
            <fmt:message key="role" bundle="${ rb }"/><br/>
            <select name="role" title="role">
                <option value="admin">
                    <fmt:message key="administrator" bundle="${rb}"/>
                </option>
                <option selected value="user">
                    <fmt:message key="user" bundle="${rb}"/>
                </option>
            </select>
        </p>

        <p>

            <input type="submit" value="<fmt:message key="registration" bundle="${ rb }"/>"/>
        </p>
    </form>
</div>

<div id="remove" class="tabcontent">

    <form onsubmit="return confirm('<fmt:message key="areYouShure" bundle="${rb}"/>');"
          name='deleteUserForm' method='POST' action='${pageContext.request.contextPath}/jsp/controller'>
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
                        <input type="radio" name="checkedName" value="${user.name}" title="">
                    </td>
                </tr>
            </c:forEach>

        </table>
        <br/>
        <input type="submit" value="<fmt:message key="delUser" bundle="${rb}" />"/>
    </form>

</div>

<script src="${pageContext.request.contextPath}/js/tab.js"></script>
