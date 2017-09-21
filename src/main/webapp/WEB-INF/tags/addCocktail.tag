<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<c:if test="${(not empty addCocktailResult) or (not empty removeCocktailResult) or (not empty error)}">
    <div class="result_field">
        <div>
            <h5>${addCocktailResult}</h5>
            <h5>${removeCocktailResult}</h5>
        </div>
        <div class="error_message">
            <h5>${error}</h5>
        </div>
    </div>
    <br/>
</c:if>

<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'add')" id="defaultOpen">
        <fmt:message key="addCocktail" bundle="${rb}"/>
    </button>
    <button class="tablinks" onclick="openTab(event, 'remove')">
        <fmt:message key="delCocktail" bundle="${rb}"/>
    </button>
</div>

<div id="add" class="tabcontent">

    <form name='addCocktail' method='post' action='${pageContext.request.contextPath}/jsp/controller'
          enctype="multipart/form-data">
        <input type='hidden' name='command' value='add_cocktail'>

        <p>
            <fmt:message key="name_ru" bundle="${rb}"/>
            <br/>
            <input name="name_RU" value="" required title="">
            <br/>
        </p>

        <p>
            <fmt:message key="name_en" bundle="${rb}"/>
            <br/>
            <input name="name_EN" value="" required title="">
            <br/>
        </p>

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
            <fmt:message key="method" bundle="${rb}"/>
            <br/>
            <select name='method' title="">
                <c:forEach items="${methods}" var="method">
                    <option value="${method.id}">
                        <c:choose>
                            <c:when test="${sessionScope.locale eq 'ru_RU'}">${method.nameRu}</c:when>
                            <c:when test="${sessionScope.locale eq 'en_US'}">${method.nameEn}</c:when>
                        </c:choose>
                    </option>
                </c:forEach>
            </select>
        </p>

        <p>
            <fmt:message key="glass" bundle="${rb}"/>
            <br/>
            <select name='glass' title='glass'>
                <c:forEach items="${glasses}" var="glass">
                    <option value="${glass.id}">
                        <c:choose>
                            <c:when test="${sessionScope.locale eq 'ru_RU'}">${glass.nameRu}</c:when>
                            <c:when test="${sessionScope.locale eq 'en_US'}">${glass.nameEn}</c:when>
                        </c:choose>
                    </option>
                </c:forEach>
            </select>
        </p>

        <p>
            <fmt:message key="image" bundle="${rb}"/>
            <br/>
            <input type="file" name="image" accept="image/*" size="5"/>
        </p>

        <p>
            <input type='submit' value='<fmt:message key="add" bundle="${rb}"/>'/>
        </p>

    </form>
</div>

<script>
    function addComponent() {

        var form = document.forms.addCocktail;
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

        if (selectedComponentId == null) {
            alert('<fmt:message key="chooseComponent" bundle="${rb}"/>');
        } else {
            if (selectedComponentAmount > 0) {

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
                amount.value = "0";
            } else {
                alert('<fmt:message key="enterAmount" bundle="${rb}"/>');
            }
        }
    }
</script>

<script src="${pageContext.request.contextPath}/js/tab.js"></script>
<script src="${pageContext.request.contextPath}/js/componentFilter.js"></script>
