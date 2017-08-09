<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="/WEB-INF/tld/mytaglib.tld" prefix="mytag"%>

<!DOCTYPE html>

<html>
    <head><meta charset="UTF-8">
        <title>BAR</title>
        <link rel="stylesheet" href="/bar/css/style.css">
    </head>
     <style>
       table {
        /*width: 100%;*/ /* Ширина таблицы */
       }
       td {
        padding: 5px; /* Поля в ячейках */
        vertical-align: top; /* Выравнивание по верхнему краю ячеек */
       }

      </style>

<body background="/bar/images/bg.png" topmargin="0" bottommargin="0">

<table align="center" width="90%" bgcolor="white" border="1" cellpadding="4" cellspacing="0">
    <tr>
        <td colspan="2"><%@ include file = "jsp/top.jsp" %></td>
    </tr>
    <tr>
        <td colspan="2"><%@ include file = "jsp/header.jsp" %></td>

    </tr>
    <tr>
        <td width="15%"><%@ include file = "jsp/menu.jsp" %></td>
        <td width="75%">
            <%@ include file = "jsp/body.jsp" %>
            <mytag:getinfo />
        </td>
    </tr>
    <tr>
        <td colspan="2"><%@ include file = "jsp/footer.jsp" %></td>
    </tr>
</table>

</body>
</html>
