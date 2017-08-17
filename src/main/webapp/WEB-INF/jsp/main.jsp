<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<html>
<head>
    <title>Welcome</title>
    <meta charset="UTF-8">
    <title>BAR</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<table width="90%" align="center">
    <tr>
        <td colspan="2">
            <%@ include file="/WEB-INF/jsp/top.jsp" %>

        </td>
    </tr>
    <tr>
        <td colspan="2">
            <%@ include file="/WEB-INF/jsp/header.jsp" %>
        </td>

    </tr>
    <tr>
        <td width="15%">
            <%@ include file="/WEB-INF/jsp/menu.jsp" %>
        </td>
        <td width="75%">
            <h2>Welcome</h2>
            <hr/>
            ${user}, hello!
            <a href="${pageContext.request.contextPath}/jsp/controller?command=logout">Logout</a><br/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <%@ include file="/WEB-INF/jsp/footer.jsp" %>
        </td>
    </tr>
</table>


</body></html>