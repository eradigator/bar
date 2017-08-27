<%@ tag import="kz.epam.javalab22.bar.dao.ComponentDao" %>
<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<div style="color:#02834b">
    <c:out value="${addCocktailResult}"/>
</div>
<div style="color: crimson">
    ${removeCocktailResult}
</div>

<form>
    <select id="mySelect" size="8">
        <option>Apple</option>
        <option>Pear</option>
        <option>Banana</option>
        <option>Orange</option>
    </select>
    <output id="outputTag">
    </output>
</form>
<br>

<hr/>
<h5>ДОБАВЛЕНИЕ КОМПОНЕНТА</h5>
<p>
    Компонент:
    <br/>
    <select name='component' title='component'>
        <% for (String componentName : new ComponentDao().getList()) { %>
        <option value=<%=componentName%>>
            <%=componentName%>
        </option>
        <%} %>
    </select>
    Количество
    <input name='amount' value='' title=""/>
    <input type='button' value='Добавить компонент'/>
</p>

<hr/>


<p>Click the button to add a "Kiwi" option at the end of the dropdown list.</p>

<button type="button" onclick="myFunction()">Insert option</button>

<script>
    function myFunction() {
        var x = document.getElementById("mySelect");
        var y = document.getElementById("outputTag");
        var option = document.createElement("option");
        option.text = "Kiwi";
        x.add(option);
        y.value += ("Vodka");
    }
</script>