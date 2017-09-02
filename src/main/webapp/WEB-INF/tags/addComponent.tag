<%@ tag import="java.util.Map" %>
<%@ tag import="kz.epam.javalab22.bar.dao.ComponentTypeDao" %>
<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<h5>Добавление компонента:</h5>

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