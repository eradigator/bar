<%@ tag import="kz.epam.javalab22.bar.dao.ComponentDao" %>
<%@ tag import="kz.epam.javalab22.bar.entity.BuildMethod" %>
<%@ tag import="kz.epam.javalab22.bar.entity.Glass" %>
<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<h5>Добавление коктейля:</h5>

<form name='addCocktail' method='post' action='${pageContext.request.contextPath}/jsp/controller'>
    <input type='hidden' name='command' value='add_cocktail'>

    <p>
        Название<br/>
        <input type='text' name='name' value='' required title="">
        <br/>
    </p>

    <p>
        Компонент:
        <br/>
        <select id="component" name="component" title="">
            <% for (String componentName : new ComponentDao().getList()) { %>
            <option value='<%=componentName%>'>
                <%=componentName%>
            </option>
            <%} %>
        </select>
        Количество
        <input id="amount" name="amount" title=""/>
        <button type="button" onclick="addComponent()">Insert option</button>
    </p>

    <p>
        <output id="outputTag" name="output">
        </output>
    </p>

    <p>
        Метод:<br/>
        <select name='buildMethod' title='buildMethod'>
            <% for (BuildMethod buildMethod : BuildMethod.values()) { %>
            <option value=<%=buildMethod%>>
                <%=buildMethod%>
            </option>
            <%} %>
        </select>
    </p>

    <p>
        Стакан:<br/>
        <select name='glass' title='glass'>
            <%for (Glass glass : Glass.values()) {%>
            <option value=<%=glass%>>
                <%=glass%>
            </option>
            <%} %>
        </select>
    </p>

    <%--<c:forEach var="s" items="${result}" varStatus="status">
        <option value="${s}">${s}</option>
    </c:forEach>--%>

    <p>
        Изображение:<br/>
        <input type='file'/>
    </p>

    <p>
        <input type='submit' value='Добавить'/>
    </p>

</form>


<script>
    function addComponent() {

        var form = document.forms.addCocktail;
        var select = form.elements.component;
        var br = document.createElement('br');
        var amount = document.getElementById("amount");
        var output = document.getElementById("outputTag");

        for (var i = 0; i < select.options.length; i++) {
            var option = select.options[i];
            if (option.selected) {
                var selectedComponent = option.value;
            }
        }

        var selectedComponentAmount = amount.value;
        output.value += selectedComponent + ":" + selectedComponentAmount;
        /*output.setAttribute("component", selectedComponent);*/

        var input = document.createElement("input");
         input.setAttribute("name","ingredient");
         input.setAttribute("value",selectedComponent);
         form.appendChild(input);

        var input1 = document.createElement("input");
        input1.setAttribute("name","amountOfIngredient");
        input1.setAttribute("value",selectedComponentAmount);
        form.appendChild(input1);
    }
</script>

