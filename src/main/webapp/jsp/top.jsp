<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<div style="float: left"><a href="">EN</a>&nbsp;</div>
<div style="float: left"><a href="">РУС</a></div>
<c:if test="${empty pageContext.request.session.getAttribute('username')}">
    <div style="float: right"><a href="/bar/jsp/login.jsp">вход</a>&nbsp;</div>
</c:if>


<c:if test="${not empty pageContext.request.session.getAttribute('username')}">
    <div style="float: right"><a href="controller?command=logout">выход</a>&nbsp;</div>
    <div style="text-align: center">${pageContext.request.session.getAttribute('username')}</div>
</c:if>
