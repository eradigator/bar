<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>


<form name="calculator" method="post" action='${pageContext.request.contextPath}/jsp/controller'>
    <input type="hidden" name="command" value="calculator">

    <p>
        <fmt:message key="component" bundle="${rb}"/>
        <br/>
        <select name="component" title="">
            <c:forEach items="${components}" var="component">
                <option value="${component.key}">${component.value}</option>
            </c:forEach>
        </select>

        <fmt:message key="amount" bundle="${rb}"/>
        <input type="number" id="amount" name="amount" title="" min="0" max="500" step="1" value="0"/>
        <button type="button" onclick="addComponent()">
            <fmt:message key="addComponent" bundle="${rb}"/>
        </button>
    </p>

    <div id="outputPlace"></div>

    <p>
        <input type='submit' value='<fmt:message key="calculate" bundle="${rb}"/>'/>
        <br/>
    </p>
</form>

<c:if test="${not empty outMap}">
    <div class="result_field">

        <fmt:message key="components" bundle="${rb}"/>
        <br/>
        <c:forEach items="${outMap}" var="pair">
            ${pair.key}: ${pair.value}
            <br/>
        </c:forEach>

        <br/>
        <c:if test="${not empty strength}">
            <fmt:message key="strength" bundle="${rb}"/>
            ${strength}%<br/>
        </c:if>
        <c:if test="${not empty amount}">
            <fmt:message key="drinkAmount" bundle="${rb}"/>
            ${amount}
            <fmt:message key="ml" bundle="${rb}"/>
            <br/>
        </c:if>
        <c:if test="${not empty cost}">
            <fmt:message key="price" bundle="${rb}"/>
            ${cost}
            <fmt:message key="currency" bundle="${rb}"/>
            <br/>
        </c:if>
    </div>
</c:if>

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

        var ingredientInput = document.createElement("input");
        ingredientInput.setAttribute("type", "hidden");
        ingredientInput.setAttribute("name", "ingredient");
        ingredientInput.setAttribute("value", selectedComponentId);
        form.appendChild(ingredientInput);

        var amountInput = document.createElement("input");
        amountInput.setAttribute("type", "hidden");
        amountInput.setAttribute("name", "amountOfIngredient");
        amountInput.setAttribute("value", selectedComponentAmount);
        form.appendChild(amountInput);

        var ingredientNameInput = document.createElement("input");
        ingredientNameInput.setAttribute("type", "hidden");
        ingredientNameInput.setAttribute("name", "ingredientName");
        ingredientNameInput.setAttribute("value", selectedComponentText);
        form.appendChild(ingredientNameInput);

        select.remove(select.selectedIndex);
        amount.value = "0";
    }
</script>

