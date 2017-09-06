<%@ tag import="kz.epam.javalab22.bar.entity.BuildMethod" %>
<%@ tag import="kz.epam.javalab22.bar.entity.Glass" %>
<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<c:if test="${(not empty addCocktailResult) or (not empty removeCocktailResult)}">
    <div class="result_field">
        <div style="color:#02834b">
            <h5>${addCocktailResult}</h5>
        </div>
        <div style="color: crimson">
            <h5>${removeCocktailResult}</h5>
        </div>
    </div>
    <br/>
</c:if>

<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'add')" id="defaultOpen">Добавление коктейля</button>
    <button class="tablinks" onclick="openTab(event, 'remove')">Удаление коктейля</button>
</div>

<div id="add" class="tabcontent">

    <form name='addCocktail' method='post' action='${pageContext.request.contextPath}/jsp/controller'
          enctype="multipart/form-data">
        <input type='hidden' name='command' value='add_cocktail'>

        <p>
            Название РУС<br/>
            <input type='text' name='name' value='' required title="">
            <br/>
        </p>

        <p>
            Название EN<br/>
            <input type='text' name='name_en' value='' required title="">
            <br/>
        </p>

        <p>
            Компонент:
            <br/>
            <select id="component" name="component" title="" style="width: 200px">
                <c:forEach items="${components}" var="pair">
                    <option value="${pair.key}">${pair.value}</option>
                </c:forEach>
            </select>
            Количество
            <input type="number" id="amount" name="amount" min="0" max="500" step="1" title="" value="0"/>
            <button type="button" onclick="addComponent()">Добавить компонент</button>
        </p>


        <div id="outputPlace"></div>

        <p>
            Метод:<br/>
            <select name='buildMethod' title='buildMethod'>
                <% for (BuildMethod buildMethod : BuildMethod.values()) { %>
                <option value=<%=buildMethod%>>
                    <%=buildMethod%>
                </option>
                <%} %>
            </select>
        </p>

        <p>
            Стакан:<br/>
            <select name='glass' title='glass'>
                <%for (Glass glass : Glass.values()) {%>
                <option value=<%=glass%>>
                    <%=glass%>
                </option>
                <%} %>
            </select>
        </p>

        <p>
            Изображение:<br/>
            <input type="file" name="image" accept="image/*" size="5"/>
        </p>

        <p>
            <input type='submit' value='Добавить'/>
        </p>

    </form>
</div>

<script src="${pageContext.request.contextPath}/js/tab.js"></script>
<script src="${pageContext.request.contextPath}/js/addCocktail.js">

</script>

