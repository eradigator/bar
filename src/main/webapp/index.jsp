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
<body background="${pageContext.request.contextPath}/images/bg.png">


<table width="90%" align="center" bgcolor="white" border="1" cellpadding="4" cellspacing="0">
    <tr>
        <td colspan="2">
            <%@ include file="jsp/top.jsp" %>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <%@ include file="jsp/header.jsp" %>
        </td>

    </tr>
    <tr>
        <td width="15%" valign="top">
            <%@ include file="jsp/menu.jsp" %>
        </td>
        <td width="75%">
            <%@ include file="jsp/body.jsp" %>
            <mytag:getinfo/>
            <my:person Age="28" EmployeeId="74852" Name="Bruce Wayne"  />
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <%@ include file="jsp/footer.jsp" %>
        </td>
    </tr>
</table>

</body>
</html>
