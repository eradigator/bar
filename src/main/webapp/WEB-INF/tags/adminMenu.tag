<%@ tag body-content="empty" dynamic-attributes="dynattrs" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>


<form name="userManagement" action="${pageContext.request.contextPath}/jsp/controller">
    <input type="hidden" name="command" value="page_user_manager">
    <a href="#" onclick="document.userManagement.submit();return(false)">
        <fmt:message key="users" bundle="${ rb }"/>
    </a>
</form>

<form name="cocktailManager" action="${pageContext.request.contextPath}/jsp/controller">
    <input type="hidden" name="command" value="page_cocktail">
    <a href="#" onclick="document.cocktailManager.submit();return(false)">
        <fmt:message key="cocktailManager" bundle="${ rb }"/>
    </a>
</form>

<form name="componentManager" action="${pageContext.request.contextPath}/jsp/controller">
    <input type="hidden" name="command" value="page_component">
    <a href="#" onclick="document.componentManager.submit();return(false)">
        <fmt:message key="componentManager" bundle="${ rb }"/>
    </a>
</form>
