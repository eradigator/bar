<%@ page language="java" pageEncoding="UTF-8" session="true" %>


<div id="logo">
    <a href="${pageContext.request.contextPath}">
        <img src="${pageContext.request.contextPath}/images/logo.png" alt="Это рюмка"
             style="width:150px;height:150px;">
    </a>
</div>
<div id="name">
    <h1>
        <fmt:message key="bar" bundle="${rb}"/>
    </h1>
</div>





