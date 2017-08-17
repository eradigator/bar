<%@ page language="java" pageEncoding="UTF-8" session="true" %>

<div id="styleMenu">
    <a href="${pageContext.request.contextPath}/jsp/controller?command=full_catalog">
        <fmt:message key="catalog" bundle="${ rb }"/><br>
    </a>
    <a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=alcoholic">
        <fmt:message key="alcogolic" bundle="${ rb }"/><br>
    </a>
    <a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=non_alcoholic">
        <fmt:message key="nonalcoholic" bundle="${ rb }"/><br>
    </a>
    <a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=calculator">
        <fmt:message key="calculator" bundle="${ rb }"/><br>
    </a>
    <a href="${pageContext.request.contextPath}/jsp/controller?command=page&chosen=contact">
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

</div>