<%@ tag body-content="empty" dynamic-attributes="dynattrs" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1" width="30%" height="20%" style="background-color:#EFEFEF">
    <c:forEach items="${dynattrs}" var="a">
        <tr>
            <td>${a.key}</td>
            <td>${a.value}</td>
        </tr>
    </c:forEach>
</table>
