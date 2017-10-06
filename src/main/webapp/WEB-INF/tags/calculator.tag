<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<div class="error_message">
    ${error}
</div>

<form name="calculator" method="post" action='${pageContext.request.contextPath}/jsp/controller'>
    <input type="hidden" name="command" value="calculator">

    <div class="component_add">

        <fmt:message key="type" bundle="${rb}"/>
        <select name="componentType" onchange="showSelected(this.value)" title="">
            <c:forEach items="${componentTypes}" var="componentType">
                <option value="${componentType.id}">
                    <c:choose>
                        <c:when test="${locale.toString() eq 'ru_RU'}">${componentType.nameRu}</c:when>
                        <c:when test="${locale.toString() eq 'en_US'}">${componentType.nameEn}</c:when>
                    </c:choose>
                </option>
            </c:forEach>
        </select>
        <br/>
        <br/>


        <fmt:message key="component" bundle="${rb}"/>
        <br/>

        <select name="component" title="" style="width: 200px" size="10">
            <c:forEach items="${components}" var="component">
                <option value="${component.id}" class="${component.componentType.id}">
                    <c:choose>
                        <c:when test="${sessionScope.locale eq 'ru_RU'}">
                            ${component.componentName.nameRu}
                        </c:when>
                        <c:when test="${sessionScope.locale eq 'en_US'}">
                            ${component.componentName.nameEn}
                        </c:when>
                    </c:choose>
                </option>
            </c:forEach>
        </select>
        <br/>
        <br/>

        <fmt:message key="amount" bundle="${rb}"/>
        <input type="number" id="amount" name="amount" min="0" max="500" step="1" title="" value="0"/>
        <br/>
        <br/>
        <button type="button" onclick="addComponent()">
            <fmt:message key="addComponent" bundle="${rb}"/>
        </button>

        <hr/>

        <div id="outputPlace"></div>
    </div>

    <p>
        <input type='submit' value='<fmt:message key="calculate" bundle="${rb}"/>'/>
        <br/>
    </p>
</form>

<c:if test="${not empty mix}">
    <div class="result_field">

        <fmt:message key="components" bundle="${rb}"/>
        <br/>
        <c:forEach items="${mix}" var="pair">
            ${pair.key.componentName.anyLanguageName}: ${pair.value}
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
            <fmt:message key="mlg" bundle="${rb}"/>
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

        if (selectedComponentId === null) {
            alert('<fmt:message key="chooseComponent" bundle="${rb}"/>');
        } else {
            if (selectedComponentAmount > 0) {

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
            } else {
                alert('<fmt:message key="enterAmount" bundle="${rb}"/>');
            }
        }
    }
</script>

<script>
    function showSelected(typeId) {

        var form = document.forms.calculator;
        var options = form.elements.component.options;

        for (var i = 0; i < options.length; i++) {
            options[i].style.display = 'none';
        }

        options = document.getElementsByClassName(typeId);
        for (i = 0; i < options.length; i++) {
            options[0].selected = 'selected';
            options[i].style.display = 'block';
        }
    }

    showSelected(1);
</script>

