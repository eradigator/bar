<%@ page language="java" pageEncoding="UTF-8" session="true" %>


<a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=catalog">
    <fmt:message key="catalog" bundle="${ rb }"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page_alcoholic">
    <fmt:message key="alcoholic" bundle="${ rb }"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page_nonalco">
    <fmt:message key="nonalcoholic" bundle="${ rb }"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page_calc">
    <fmt:message key="calculator" bundle="${ rb }"/><br>
</a>
<br/>


<c:if test="${not empty pageContext.request.session.getAttribute('role')}">
    <c:if test="${pageContext.request.session.getAttribute('role') eq 'admin'}">
        <my:adminMenu/>
    </c:if>
</c:if>