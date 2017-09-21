<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<c:set var="locale" value="${pageContext.request.session.getAttribute('locale')}" scope="page"/>


<c:forEach var="cocktail" items="${cocktailList.cocktailList}">
    <c:if test="${not empty cocktail}">
        <div class="cocktail" style="cursor: pointer;" onclick="showCocktail(${cocktail.id})">
            <img class="cocktail_image" src="${pageContext.request.contextPath}/image?id=${cocktail.imageId}">
            <div style="text-align: right; float: right">
                <c:choose>
                    <c:when test="${cocktail.isFavorite eq false}">
                        <a href="${pageContext.request.contextPath}/jsp/controller?command=add_to_favorites&id=${cocktail.id}">
                            <img class="favicon" src="${pageContext.request.contextPath}/images/fav_blank.png"
                                 title="<fmt:message key="add_to_favorites" bundle="${rb}"/>">
                        </a>
                    </c:when>
                    <c:when test="${cocktail.isFavorite eq true}">
                        <a href="${pageContext.request.contextPath}/jsp/controller?command=del_from_favorites&id=${cocktail.id}">
                            <img class="favicon" src="${pageContext.request.contextPath}/images/fav_active.png"
                                 title="<fmt:message key="del_from_favorites" bundle="${rb}"/>">
                        </a>
                    </c:when>
                </c:choose>
            </div>
            <div style="text-align: left; float: left">
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
        </div>
        <br/>
    </c:if>
</c:forEach>


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
