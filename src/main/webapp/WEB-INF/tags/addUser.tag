<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<div style="color: crimson">
    <h5>${deleteUserResult}</h5>
</div>
<div style="color:#02834b">
    <h5>${addUserResult}</h5>
</div>

<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'add')" id="defaultOpen">Регистрация пользователя</button>
    <button class="tablinks" onclick="openTab(event, 'remove')">Удаление пользователя</button>
    <%--<button class="tablinks" onclick="openTab(event, 'Tokyo')">Tokyo</button>--%>
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

<script>
    function openTab(evt, tabName) {
        // Declare all variables
        var i, tabcontent, tablinks;

        // Get all elements with class="tabcontent" and hide them
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }

        // Get all elements with class="tablinks" and remove the class "active"
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }

        // Show the current tab, and add an "active" class to the button that opened the tab
        document.getElementById(tabName).style.display = "block";
        evt.currentTarget.className += " active";
    }

    document.getElementById("defaultOpen").click();
</script>
