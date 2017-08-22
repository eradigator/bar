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
<br/>

<c:if test="${not empty pageContext.request.session.getAttribute('role')}">
    <c:if test="${pageContext.request.session.getAttribute('role') eq 'admin'}">
        <my:adminMenu/>
    </c:if>
</c:if>


<%--<c:if test="${not empty pageContext.request.session.getAttribute('isadmin')}">
    <c:if test="${pageContext.request.session.getAttribute('isadmin') eq 'true'}">
        <my:admMenu/>
    </c:if>
</c:if>--%>

<%--<form name="admin" method="post" action="${pageContext.request.contextPath}/jsp/controller">
    <div style="float: left">
        <input type="hidden" name="command" value="page">
        <input type="hidden" name="chosen" value="admin">
        <a href="#" onclick="document.admin.submit();return(false)">Админка</a>
    </div>
</form>--%>