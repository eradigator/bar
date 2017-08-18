<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="/WEB-INF/tld/mytaglib.tld" prefix="mytag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<!DOCTYPE html>

<html>
<head>
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
    <div id="space"></div>

    <div id="menu">
        <%@ include file="/WEB-INF/jsp/menu.jsp" %>
    </div>

    <div id="navigation">
        <h2><fmt:message key="${content}" bundle="${rb}"/></h2>
        <hr/>
    </div>

    <div id="content">
        <%@ include file="/WEB-INF/jsp/body.jsp" %>
    </div>

    <div id="clear">
    </div>

    <div id="footer">
        <%@ include file="/WEB-INF/jsp/footer.jsp" %>
    </div>
</div>


</body>
</html>
