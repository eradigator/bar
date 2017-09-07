<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>


<p>
    Существует огромное множество рецептов безалкогольных коктейлей. И если вы думаете, что безалкогольный коктейль –
    это коктейль не совсем настоящий, вы очень ошибаетесь. Неслучайно сейчас безалкогольные коктейли можно встретить в
    любом баре и они пользуются большим спросом, а посетитель ресторана, заказывающий лёгкий и полезный для здоровья
    напиток «без градуса», уже давно не встречает обращенных к нему удивленных взглядов.
</p>
<p>
    Безалкогольные коктейли – отличный вариант для детского праздника. Это могут освежающие напитки на основе лимонада,
    которые, подобно алкогольным коктейлям, можно очень красиво украсить. Очень популярны и безалкогольные коктейли на
    основе молока.
</p>
<p>
    В Британии довольно популярны Pick-me-up («подними меня») – т. е. безалкогольные похмельные коктейли. Как правило,
    это очень пряные напитки, которые бодрят и избавляют от вялости. Среди Pick-me-up стоит назвать и ойстеры
    («устрицы») – напитки с яичным желтком (который не может быть заменён яичным ликёром). Пьют ойстер одним глотком.
</p>


<c:forEach items="${cocktailList}" var="currentCocktail">
    <div class="cocktail">
        <img class="cocktail_image" src="${pageContext.request.contextPath}/image?id=${currentCocktail.imageId}">
        <b>
            <c:choose>
                <c:when test="${locale.toString() eq 'ru_RU'}">${currentCocktail.cocktailName.nameRu}</c:when>
                <c:when test="${locale.toString() eq 'en_US'}">${currentCocktail.cocktailName.nameEn}</c:when>
            </c:choose>
        </b>
        <br/>

        <fmt:message key="method" bundle="${rb}"/>
        <c:choose>
            <c:when test="${locale.toString() eq 'ru_RU'}">${currentCocktail.method.nameRu}</c:when>
            <c:when test="${locale.toString() eq 'en_US'}">${currentCocktail.method.nameEn}</c:when>
        </c:choose>
        <br/>

        <fmt:message key="glass" bundle="${rb}"/>
        <c:choose>
            <c:when test="${locale.toString() eq 'ru_RU'}">${currentCocktail.glass.nameRu}</c:when>
            <c:when test="${locale.toString() eq 'en_US'}">${currentCocktail.glass.nameEn}</c:when>
        </c:choose>

        <br/>
        <br/>
        <fmt:message key="components" bundle="${rb}"/>
        <br/>

        <c:forEach items="${currentCocktail.mix.mix}" var="currentComponent">
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