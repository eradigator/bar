<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>


<div id="remove" class="tabcontent">

<form name="removeCocktail" method="post" action="${pageContext.request.contextPath}/jsp/controller">
    <input type="hidden" name="command" value="remove_cocktail">

    <p>
        Название коктейля
        <br/>

        <select name="cocktailToDelete" title="">
            <c:forEach items="${cocktailNames}" var="name">
                <option value="${name}">${name}</option>
            </c:forEach>
        </select>
    </p>

    <input type="submit" value="Удалить"/>
</form>

</div>