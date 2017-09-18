<a href="${pageContext.request.contextPath}/jsp/controller?command=page_main">
    <fmt:message key="main" bundle="${rb}"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page_alcoholic">
    <fmt:message key="alcoholic" bundle="${rb}"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page_nonalco">
    <fmt:message key="nonalcoholic" bundle="${rb}"/><br>
</a>
<a href="${pageContext.request.contextPath}/jsp/controller?command=page_calc">
    <fmt:message key="calculator" bundle="${rb}"/><br>
</a>
<br/>


<c:if test="${user.role eq 'USER' || user.role eq 'ADMIN'}">
    <a href="${pageContext.request.contextPath}/jsp/controller?command=page_favorite">
        <fmt:message key="favorite" bundle="${rb}"/><br>
    </a>
    <br/>
</c:if>


<c:if test="${user.role eq 'ADMIN'}">
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
</c:if>

