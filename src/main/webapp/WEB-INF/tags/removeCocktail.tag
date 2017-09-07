<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<div id="remove" class="tabcontent">

    <form name="removeCocktail" method="post" action="${pageContext.request.contextPath}/jsp/controller">
        <input type="hidden" name="command" value="remove_cocktail">

        <p>
            <fmt:message key="cocktailName" bundle="${rb}"/>
            <br/>

            <select name="cocktailIdToDelete" title="">
                <c:forEach items="${cocktailNames}" var="cocktailName">
                    <option value="${cocktailName.id}">
                        <c:choose>
                            <c:when test="${locale.toString() eq 'ru_RU'}">${cocktailName.nameRu}</c:when>
                            <c:when test="${locale.toString() eq 'en_US'}">${cocktailName.nameEn}</c:when>
                        </c:choose>
                    </option>
                </c:forEach>
            </select>
        </p>

        <input type="submit" value="<fmt:message key="del" bundle="${rb}"/>"/>
    </form>

</div>