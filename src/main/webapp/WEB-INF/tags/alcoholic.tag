<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<c:set var="locale" value="${pageContext.request.session.getAttribute('locale')}" scope="page"/>

<div class="nav_menu">
    <form name="nav_menu">
        <div style="width: 50%; float:left">
            <fmt:message key="filter" bundle="${rb}"/>
            <br/>

            <input type="hidden" name="command" value="page_alcoholic"/>
            <input id="all" type="radio" name="filter" value="all" title="">
            <fmt:message key="all" bundle="${rb}"/><br/>
            <input id="low" type="radio" name="filter" value="low" title="">
            <fmt:message key="lowAlco" bundle="${rb}"/> 5-10&deg;<br/>
            <input id="middle" type="radio" name="filter" value="middle" title="">
            <fmt:message key="middleAlco" bundle="${rb}"/> 10-20&deg;<br/>
            <input id="strong" type="radio" name="filter" value="strong" title="">
            <fmt:message key="strongAlco" bundle="${rb}"/> 20&deg;+<br/>
            <br/>
            <input type="hidden" name="cocktailList" value="${cocktailList}">
            <input type="submit" value="<fmt:message key="search" bundle="${rb}"/>">

        </div>

        <div style="width: 50%; float:right; text-align: right">
            <fmt:message key="sort" bundle="${rb}"/>
            <select id="sort" name="sort" title="">
                <option value="by_name">
                    <fmt:message key="byName" bundle="${rb}"/>
                </option>
                <option value="by_strength">
                    <fmt:message key="byStrength" bundle="${rb}"/>
                </option>
            </select>
        </div>
    </form>
</div>

<br style="clear:both;"/>

<c:forEach items="${cocktailList}" var="cocktail">
    <div class="cocktail" style="cursor: pointer;" onclick="showCocktail(${cocktail.id})">
        <img class="cocktail_image" src="${pageContext.request.contextPath}/image?id=${cocktail.imageId}">
        <b>
            <c:choose>
                <c:when test="${locale.toString() eq 'ru_RU'}">${cocktail.cocktailName.nameRu}</c:when>
                <c:when test="${locale.toString() eq 'en_US'}">${cocktail.cocktailName.nameEn}</c:when>
            </c:choose>
        </b>
        <br/>

        <fmt:message key="strength" bundle="${rb}"/>
            ${cocktail.strength}&deg;

        <br/>
        <br/>
        <fmt:message key="components" bundle="${rb}"/>
        <br/>

        <c:forEach items="${cocktail.mix.mix}" var="currentComponent">
            <c:choose>
                <c:when test="${locale.toString() eq 'ru_RU'}">${currentComponent.key.componentName.nameRu}</c:when>
                <c:when test="${locale.toString() eq 'en_US'}">${currentComponent.key.componentName.nameEn}</c:when>
            </c:choose>
            ${currentComponent.value}
            <fmt:message key="ml" bundle="${rb}"/>
            <br/>
        </c:forEach>

    </div>
    <br/>
</c:forEach>

<c:choose>
    <c:when test="${locale.toString() eq 'ru_RU'}">${uiText.textRu}</c:when>
    <c:when test="${locale.toString() eq 'en_US'}">${uiText.textEn}</c:when>
</c:choose>


<script>
    function showCocktail(i) {
        window.location = "${pageContext.request.contextPath}/jsp/controller?command=show_cocktail&cocktailId=" + i;
    }
</script>

<script>
    radiobtn = document.getElementById("${filter_checked_id}");
    radiobtn.checked = true;
    select = document.getElementById("sort");
    select.options[${sort_checked_index}].selected = true;
</script>


