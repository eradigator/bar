<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<br/>
Type User Name to delete:
<form name="deleteUser" method="post" action="${pageContext.request.contextPath}/jsp/controller">
    <input type="hidden" name="command" value="delete_user">
    <input type="text" name="checkedName" value="" title="checkedName"/>
    <input type="submit" value="Delete">
</form>

