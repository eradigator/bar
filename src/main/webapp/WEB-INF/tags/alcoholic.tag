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

<%List<Cocktail> cocktailList = new CocktailDao().getCocktailsList();%>
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