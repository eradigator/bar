<%@ tag import="kz.epam.javalab22.bar.entity.Cocktail" %>
<%@ tag import="kz.epam.javalab22.bar.dao.CocktailDao" %>
<%@ tag import="java.util.List" %>
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
        <select name="cocktailToDelete" title="cocktailToDelete">
            <%
                List<Cocktail> cocktailList = new CocktailDao().getCocktailsList();
                for (Cocktail cocktail : cocktailList) {
            %>
            <option value="<%=cocktail.getName()%>">
                <%=cocktail.getName()%>
            </option>
            <%}%>
        </select>
    </p>

    <input type="submit" value="Удалить"/>
</form>

</div>