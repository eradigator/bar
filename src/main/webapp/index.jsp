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


<table width="90%" align="center">
    <tr>
        <td colspan="2">
            <%@ include file="WEB-INF/jsp/top.jsp" %>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <%@ include file="WEB-INF/jsp/header.jsp" %>
        </td>

    </tr>
    <tr>
        <td width="15%" valign="top" bgcolor="white">
            <%@ include file="WEB-INF/jsp/menu.jsp" %>
        </td>
        <td width="75%" bgcolor="white">
            <%@ include file="WEB-INF/jsp/body.jsp" %>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <%@ include file="WEB-INF/jsp/footer.jsp" %>
        </td>
    </tr>
</table>

</body>
</html>
