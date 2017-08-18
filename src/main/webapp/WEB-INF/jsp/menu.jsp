<%@ page language="java" pageEncoding="UTF-8" session="true" %>


<a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=catalog">
    <fmt:message key="catalog" bundle="${ rb }"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=alcoholic">
    <fmt:message key="alcoholic" bundle="${ rb }"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=nonalcoholic">
    <fmt:message key="nonalcoholic" bundle="${ rb }"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=calculator">
    <fmt:message key="calculator" bundle="${ rb }"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=contacts">
    <fmt:message key="contacts" bundle="${ rb }"/><br>
</a>

<c:if test="${not empty pageContext.request.session.getAttribute('isadmin')}">
    <c:if test="${pageContext.request.session.getAttribute('isadmin') eq 'true'}">
        <br/>
        <a href="<%--путь к админке--%>">
            Админка
        </a>
    </c:if>
</c:if>

