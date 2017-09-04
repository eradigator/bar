<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>


<a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=alcoholic">
    <fmt:message key="alcoholic" bundle="${ rb }"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=nonalcoholic">
    <fmt:message key="nonalcoholic" bundle="${ rb }"/><br>
</a>
