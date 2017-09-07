<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<c:set var="locale" value="${pageContext.request.session.getAttribute('locale')}" scope="page"/>


<p>
    Никто не может оспорить тот факт, что свое истинное восхождение на Олимп славы алкогольные коктейли начали в 20-х
    годах прошлого столетия в Америке, когда они стали излюбленным, но нелегальным алкогольным напитком сотен тысяч
    любителей алкоголя, противостоящих жестокому и неумолимому «сухому закону». Безоговорочный запрет на «производство,
    продажу и транспортировку алкогольных напитков» жестко контролировал население Америки с 1919 года и до 1933 года,
    когда в Соединенных Штатах Америки употребление алкогольных напитков стало вновь легальным.
</p>
<p>
    Можно предположить, что первые коктейли были приготовлены на основе джина, имевшего в то время сильный сладковатый
    привкус, который было предпочтительнее спрятать в смеси с другими напитками. А первые рецепты коктейлей,
    сохранившиеся до наших дней, относятся ко второй половине XIX века, например, Martini, Daiquiri и Manhattan. Свое
    настоящее развитие коктейли получили в 1920-1930-х годах, когда были придуманы многие классические коктейли, которые
    сегодня пользуются большим успехом в барах мира. В те времена в Париже появились Bloody Mary и Side Car, в Италии —
    Americano и Negroni. Коктейли называли American Drinks, поскольку их готовили для американцев, которые искали за
    границей ощущения, запрещенные в своей стране. Во время «сухого закона» в Соединенных Штатах также скрыто смешивали
    разные напитки, пытаясь спрятать вкус алкоголя.
</p>
<p>
    Сегодня мода на коктейли возвращается с новой силой, чему способствует появление новых ликеров, необычных ароматов и
    экзотических плодов, а также особая атмосфера, свойственная беспокойным временам и смешанным напиткам.
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
