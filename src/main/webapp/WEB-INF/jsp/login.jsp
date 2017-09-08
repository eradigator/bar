<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<html>
<head>
    <title>BAR - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>


<div id="login">
    <div style="text-align: center">
        <h3>
            <fmt:message key="bar" bundle="${rb}"/>
        </h3>
    </div>
    <div class="tab">
        <button class="tablinks" onclick="openTab(event, 'loginTab')" id="defaultOpen" style="width:50%">
            <fmt:message key="login" bundle="${rb}"/>
        </button>
        <button class="tablinks" onclick="openTab(event, 'signUpTab')" style="width:50%">
            <fmt:message key="signUp" bundle="${rb}"/>
        </button>
    </div>

    <div id="loginTab" class="tabcontent" align="center">
        <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
            <input type="hidden" name="command" value="login"/>

            <p>
                <fmt:message key="enterLogin" bundle="${ rb }"/><br/>
                <input type="text" name="login" value="" required title=""/>
            </p>

            <p>
                <fmt:message key="password" bundle="${ rb }"/><br/>
                <input type="password" name="password" value="" required title=""/>
            </p>

            <input type="submit" value="<fmt:message key="login" bundle="${rb}" />"/>
            <div style="color: crimson">
                ${errorLoginPassMessage}
                <br/>
                ${wrongAction}
                <br/>
                ${nullPage}
                <br/>
            </div>
        </form>
    </div>

    <div id="signUpTab" class="tabcontent" align="center">

        <form name="signUp" method="post" action="${pageContext.request.contextPath}/jsp/controller">
            <input type="hidden" name="command" value="sign_up"/>

            <p>
                <fmt:message key="enterLogin" bundle="${ rb }"/><br/>
                <input type="text" name="login" value="" required title=""/><br/>
            </p>

            <p>
                <fmt:message key="password" bundle="${ rb }"/><br/>
                <input type="password" name="password" value="" required title=""/><br/>
            </p>

            <p>
                <fmt:message key="email" bundle="${ rb }"/><br/>
                <input type="email" name="email" value="" required title=""/><br/>
            </p>

            <p>
                <input type="submit" value="<fmt:message key="registration" bundle="${ rb }"/>"/>
            </p>
        </form>
    </div>

    <p>
        <a href="${pageContext.request.contextPath}/jsp/controller">
            <fmt:message key="goBack" bundle="${rb}"/>
        </a>
    </p>
</div>

<script src="${pageContext.request.contextPath}/js/tab.js"></script>
</body>
</html>