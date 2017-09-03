<%@ tag import="java.util.Map" %>
<%@ tag import="kz.epam.javalab22.bar.dao.ComponentDao" %>
<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<h5>Калькулятор</h5>

<form name="calculator" method="post" action='${pageContext.request.contextPath}/jsp/controller'>
    <input type="hidden" name="command" value="calculator">

    <p>
        Компонент:
        <br/>
        <select id="component" name="component" title="">
            <% for (Map.Entry<Integer, String> pair : new ComponentDao().getComponents().entrySet()) { %>
            <option value='<%=pair.getKey()%>'>
                <%=pair.getValue()%>
            </option>
            <%} %>
        </select>
        Количество
        <input type="number" id="amount" name="amount" title="" min="0" max="500" step="1" value="0"/>
        <button type="button" onclick="addComponent()">Добавить компонент</button>
    </p>

    <div id="outputPlace"></div>

    <p>
        <input type='submit' value='Рассчитать'/><br/>
    </p>
    <p>
    <h4>${result}</h4>
    </p>

</form>

<script>
    function addComponent() {

        var form = document.forms.calculator;
        var select = form.elements.component;
        var br = document.createElement('br');
        var amount = document.getElementById("amount");
        var div = document.getElementById("outputPlace");

        for (var i = 0; i < select.options.length; i++) {
            var option = select.options[i];
            if (option.selected) {
                var selectedComponentId = option.value;
                var selectedComponentText = select.options[select.options.selectedIndex].text;
            }
        }

        var selectedComponentAmount = amount.value;

        var output = document.createElement("output");
        output.value = selectedComponentText + ":" + selectedComponentAmount;
        div.appendChild(output);
        div.appendChild(br);

        var input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "ingredient");
        input.setAttribute("value", selectedComponentId);
        form.appendChild(input);

        var input1 = document.createElement("input");
        input1.setAttribute("type", "hidden");
        input1.setAttribute("name", "amountOfIngredient");
        input1.setAttribute("value", selectedComponentAmount);
        form.appendChild(input1);

        select.remove(select.selectedIndex);
        amount.value="0";
    }
</script>

