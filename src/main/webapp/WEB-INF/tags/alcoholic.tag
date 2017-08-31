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
    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
    industry standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it
    to make a type specimen book. It has survived not only five centuries, but also the leap into electronic
    typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets
    containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including
    versions of Lorem Ipsum.
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