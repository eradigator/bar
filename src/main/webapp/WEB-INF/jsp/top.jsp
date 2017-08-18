<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="float: left">
    <a href="${pageContext.request.contextPath}/jsp/controller?command=lang&chosen=EN">EN</a>&nbsp;
</div>

<div style="float: left">
    <a href="${pageContext.request.contextPath}/jsp/controller?command=lang&chosen=RU">РУС</a>
</div>

<c:if test="${empty pageContext.request.session.getAttribute('username')}">
    <div style="float: right">
        <a href="${pageContext.request.contextPath}/jsp/login.jsp">
            <fmt:message key="login" bundle="${ rb }"/>
        </a>
    </div>
</c:if>


<c:if test="${not empty pageContext.request.session.getAttribute('username')}">
    <div style="float: right">
        <a href="${pageContext.request.contextPath}/jsp/controller?command=logout">
            <fmt:message key="logout" bundle="${ rb }"/>
        </a>
    </div>
    <div style="text-align: center">
        userName: <b>${pageContext.request.session.getAttribute('username')}</b>&nbsp;
        isAdmin: <b>${pageContext.request.session.getAttribute('isadmin')}</b>&nbsp;
        locale: <b>${pageContext.request.session.getAttribute('locale')}&nbsp;</b>
    </div>
</c:if>

<%--Текущий путь к странице--%>
<%--<div style="text-align: center">
    <c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}"/>
    <c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
    ${requestPath}
    ${params}
    ${content}
</div>--%>

<%--${pageContext.request.getHeader("referer")}--%>
<%--
<script>
    function reloadPageEN() {
        ${pageContext.request.session.setAttribute("locale", "en_US")}
        location.reload();
    }
</script>--%>
