<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<c:set var="locale" value="${pageContext.request.session.getAttribute('locale')}" scope="page"/>

<a href="" onclick="history.back(); return false;">
    <fmt:message key="backward" bundle="${rb}" />
</a>
<br/>
<br/>

<img width="500px" src="${pageContext.request.contextPath}/image?id=${cocktail.imageId}">
<br/>
<b>
    <c:choose>
        <c:when test="${locale.toString() eq 'ru_RU'}">${cocktail.cocktailName.nameRu}</c:when>
        <c:when test="${locale.toString() eq 'en_US'}">${cocktail.cocktailName.nameEn}</c:when>
    </c:choose>
</b>
<br/>

<fmt:message key="method" bundle="${rb}"/>
<c:choose>
    <c:when test="${locale.toString() eq 'ru_RU'}">${cocktail.method.nameRu}</c:when>
    <c:when test="${locale.toString() eq 'en_US'}">${cocktail.method.nameEn}</c:when>
</c:choose>
<br/>

<fmt:message key="glass" bundle="${rb}"/>
<c:choose>
    <c:when test="${locale.toString() eq 'ru_RU'}">${cocktail.glass.nameRu}</c:when>
    <c:when test="${locale.toString() eq 'en_US'}">${cocktail.glass.nameEn}</c:when>
</c:choose>

<br/>
<fmt:message key="strength" bundle="${rb}"/>
${cocktail.strength}&deg;

<br/>
<br/>
<fmt:message key="components" bundle="${rb}"/>
<br/>

<c:forEach items="${cocktail.mix.mix}" var="currentComponent">
    <c:choose>
        <c:when test="${locale.toString() eq 'ru_RU'}">${currentComponent.key.componentName.nameRu}</c:when>
        <c:when test="${locale.toString() eq 'en_US'}">${currentComponent.key.componentName.nameEn}</c:when>
    </c:choose>
    ${currentComponent.value}
    <fmt:message key="mlg" bundle="${rb}"/>
    <br/>
</c:forEach>

<br/>

