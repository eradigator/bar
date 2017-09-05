<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<c:if test="${(not empty addUserResult) or (not empty deleteUserResult)}">
    <div class="result_field">
        <div style="color:#02834b">
            <h5>${addUserResult}</h5>
        </div>
        <div style="color: crimson">
            <h5>${deleteUserResult}</h5>
        </div>
    </div>
    <br/>
</c:if>

<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'add')" id="defaultOpen">Регистрация пользователя</button>
    <button class="tablinks" onclick="openTab(event, 'remove')">Удаление пользователя</button>
</div>

<div id="add" class="tabcontent">
    <form onsubmit="return confirm('Уверены?');"
          name="addUser" method="post" action="${pageContext.request.contextPath}/jsp/controller">
        <input type="hidden" name="command" value="add_user"/>

        <p>
            <fmt:message key="enterLogin" bundle="${ rb }"/><br/>
            <input type="text" name="login" value="" required/><br/>
        </p>

        <p>
            <fmt:message key="password" bundle="${ rb }"/><br/>
            <input type="password" name="password" value="" required/><br/>
        </p>

        <p>
            <fmt:message key="email" bundle="${ rb }"/><br/>
            <input type="email" name="email" value="" required/><br/>
        </p>

        <p>
            <fmt:message key="role" bundle="${ rb }"/><br/>
            <select name="role" title="role">
                <option value="admin">Администратор</option>
                <option selected value="user">Пользователь</option>
            </select>
        </p>

        <p>
            <input type="submit" value="Регистрация"/>
        </p>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/tab.js"></script>
