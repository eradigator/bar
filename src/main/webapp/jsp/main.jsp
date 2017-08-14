<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Welcome</title>
    <meta charset="UTF-8">
    <title>BAR</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body background="../images/bg.png">
<table align="center" bgcolor="white" border="1" cellpadding="4" cellspacing="0">
    <tr>
        <td colspan="2">
            <%@ include file="/jsp/top.jsp" %>

        </td>
    </tr>
    <tr>
        <td colspan="2">
            <%@ include file="/jsp/header.jsp" %>
        </td>

    </tr>
    <tr>
        <td width="15%">
            <%@ include file="/jsp/menu.jsp" %>
        </td>
        <td width="75%">
            <h2>Welcome</h2>
            <hr/>
            ${user}, hello!
            <a href="controller?command=logout">Logout</a></br>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <%@ include file="/jsp/footer.jsp" %>
        </td>
    </tr>
</table>


</body></html>