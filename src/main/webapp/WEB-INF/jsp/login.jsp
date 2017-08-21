<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<html>
<head>
    <title>BAR - Login</title>
    <link rel="stylesheet" href="../css/style.css">
</head>

<body>
<div id="login">
    <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
        <input type="hidden" name="command" value="login"/>

        <fmt:message key="enterLogin" bundle="${ rb }"/><br/>
        <input type="text" name="login" value=""/><br/>

        <fmt:message key="password" bundle="${ rb }"/><br/>
        <input type="password" name="password" value=""/>
        <br/>
        ${errorLoginPassMessage}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
        <input type="submit" value="Log in"/>
        <p>Нет аккаунта? &nbsp;&nbsp;<a href="#">Регистрация</a></p>
    </form>
</div>
</body>
</html>