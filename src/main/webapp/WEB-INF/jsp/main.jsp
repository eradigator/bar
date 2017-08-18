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
<div id="container">
    <div id="top">
        <%@ include file="/WEB-INF/jsp/top.jsp" %>
    </div>
    <div id="header">
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
    </div>
    <div id="space">

    </div>

    <div id="menu">
        <%@ include file="/WEB-INF/jsp/menu.jsp" %>
    </div>

    <div id="navigation">
        <h2>Welcome</h2>
        <hr/>
    </div>

    <div id="content">
        ${user}, hello!
        <a href="${pageContext.request.contextPath}/jsp/controller?command=logout">Logout</a><br/>
    </div>

    <div id="clear">
    </div>

    <div id="footer">
        <%@ include file="/WEB-INF/jsp/footer.jsp" %>
    </div>
</div>

</body></html>


