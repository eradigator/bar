<%@ tag import="kz.epam.javalab22.bar.entity.Cocktail" %>
<%@ tag import="java.util.List" %>
<%@ tag import="kz.epam.javalab22.bar.dao.CocktailDao" %>
<%@ tag import="java.util.Map" %>
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

<%List<Cocktail> cocktailList = new CocktailDao().getNonAlcoList();%>
<%for (Cocktail cocktail : cocktailList) {%>

<div class="cocktail">

    <img class="cocktail_image" src="${pageContext.request.contextPath}/image?id=<%=cocktail.getImageId()%>">

    <b>
        Название:
        <%=cocktail.getName()%>
    </b>
    <br/>

    Метод приготовления:
    <%=cocktail.getBuildMethod()%>
    <br/>

    Стакан:
    <%=cocktail.getGlass()%>
    <br/>

    Компоненты:
    <br/>

    <% for (Map.Entry<String, Integer> pair : cocktail.getComponents().entrySet()) {%>
    <%=pair.getKey()%> :  <%=pair.getValue()%>  ml
    <br/>

    <%}%>

</div>

<br/>

<%}%>