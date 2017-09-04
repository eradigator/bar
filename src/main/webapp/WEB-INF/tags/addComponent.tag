<%@ tag import="java.util.Map" %>
<%@ tag import="kz.epam.javalab22.bar.dao.ComponentTypeDao" %>
<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<div style="color:#02834b">
    <h5>${result}</h5>
</div>
<div style="color: crimson">
    <h5>${redResult}</h5>
</div>

<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'add')" id="defaultOpen">Добавление компонента</button>
    <button class="tablinks" onclick="openTab(event, 'remove')">Удаление компонента</button>
    <%--<button class="tablinks" onclick="openTab(event, 'Tokyo')">Tokyo</button>--%>
</div>

<div id="add" class="tabcontent">

    <form name='addComponent' method='post' action='${pageContext.request.contextPath}/jsp/controller'>
        <input type='hidden' name='command' value='add_component'>

        <p>
            Название РУС
            <br/>
            <input name='name_RU' value='' required title="">
            <br/>
        </p>
        <p>
            Название EN
            <br/>
            <input name='name_EN' value='' required title="">
            <br/>
        </p>

        <p>
            Тип
            <br/>
            <select name='componentType' title='componentType'>

                <%
                    Map<Integer, String> componentTypes = new ComponentTypeDao().getComponentTypes();
                    for (Map.Entry<Integer, String> pair : componentTypes.entrySet()) {
                %>
                <option value='<%=pair.getKey()%>'>
                    <%=pair.getValue()%>
                </option>
                <%}%>

            </select>
        </p>

        <p>
            Крепость (%)
            <br/>
            <input type="number" name="strength" min="0" max="100" step="0.1" value="" title="" required>
        </p>

        <p>
            Цена (за мл)
            <br/>
            <input type="number" name='price' min="0" max="100000" step="0.01" value="" title="" required>
            <br/>
        </p>
        <input type='submit' value='Добавить'/>
    </form>
</div>

<div id="remove" class="tabcontent">
    <h3>Paris</h3>
    <p>Paris is the capital of France.</p>
</div>

<%--<div id="Tokyo" class="tabcontent">
    <h3>Tokyo</h3>
    <p>Tokyo is the capital of Japan.</p>
</div>--%>


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