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

    <p>
        <fmt:message key="component" bundle="${rb}"/>
        <br/>
        <select name="component" title="">
            <c:forEach items="${componentNames}" var="componentName">
                <option value="${componentName.id}">
                    <c:choose>
                        <c:when test="${sessionScope.locale eq 'ru_RU'}">${componentName.nameRu}</c:when>
                        <c:when test="${sessionScope.locale eq 'en_US'}">${componentName.nameEn}</c:when>
                    </c:choose>
                </option>
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

<c:if test="${not empty calcResult}">
    <div class="result_field">

        <fmt:message key="components" bundle="${rb}"/>
        <br/>
        <c:forEach items="${calcResult}" var="pair">
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


<script src="${pageContext.request.contextPath}/js/addComponent.js"></script>

