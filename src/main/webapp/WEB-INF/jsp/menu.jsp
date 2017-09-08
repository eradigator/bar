<%@ page language="java" pageEncoding="UTF-8" session="true" %>


<a href="${pageContext.request.contextPath}/jsp/controller?command=page_main">
    <fmt:message key="main" bundle="${rb}"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page_alcoholic">
    <fmt:message key="alcoholic" bundle="${rb}"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page_nonalco">
    <fmt:message key="nonalcoholic" bundle="${rb}"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page_calc">
    <fmt:message key="calculator" bundle="${rb}"/><br>
</a>
<br/>

<c:if test="${user.role eq 'ADMIN'}">
    <my:adminMenu/>
</c:if>
