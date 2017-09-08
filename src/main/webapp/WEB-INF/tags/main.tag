<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<c:set var="locale" value="${pageContext.request.session.getAttribute('locale')}" scope="page"/>

<c:choose>
    <c:when test="${locale.toString() eq 'ru_RU'}">${uiText.textRu}</c:when>
    <c:when test="${locale.toString() eq 'en_US'}">${uiText.textEn}</c:when>
</c:choose>

<a href="${pageContext.request.contextPath}/jsp/controller?command=page_alcoholic">
    <fmt:message key="alcoholic" bundle="${ rb }"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page_nonalco">
    <fmt:message key="nonalcoholic" bundle="${ rb }"/><br>
</a>
