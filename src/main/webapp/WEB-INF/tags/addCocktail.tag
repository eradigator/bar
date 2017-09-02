<%@ tag import="kz.epam.javalab22.bar.dao.ComponentDao" %>
<%@ tag import="kz.epam.javalab22.bar.entity.BuildMethod" %>
<%@ tag import="kz.epam.javalab22.bar.entity.Glass" %>
<%@ tag import="java.util.Map" %>
<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<h5>Добавление коктейля:</h5>

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
            <% for (Map.Entry<Integer,String> pair : new ComponentDao().getComponents().entrySet()) { %>
                <option value='<%=pair.getKey()%>'>
            <%=pair.getValue()%>
            </option>
            <%} %>
        </select>
        Количество
        <input type="number" id="amount" name="amount" min="1" max="500" step="1" title="" required/>
        <button type="button" onclick="addComponent()">Добавить компонент</button>
    </p>


    <p>
    <div id="outputPlace"></div>
    </p>

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

    <%--<c:forEach var="s" items="${result}" varStatus="status">
        <option value="${s}">${s}</option>
    </c:forEach>--%>

    <p>
        Изображение:<br/>
        <input type="file" name="image" accept="image/*" size="5"/>
    </p>

    <p>
        <input type='submit' value='Добавить'/>
    </p>

</form>


<script>
    function addComponent() {

        var form = document.forms.addCocktail;
        var select = form.elements.component;
        var br = document.createElement('br');
        var amount = document.getElementById("amount");
        var div = document.getElementById("outputPlace");

        for (var i = 0; i < select.options.length; i++) {
            var option = select.options[i];
            if (option.selected) {
                var selectedComponentId = option.value;
                var selectedComponentText = select.options[select.options.selectedIndex].text;
            }
        }

        var selectedComponentAmount = amount.value;

        var output = document.createElement("output");
        output.value = selectedComponentText + ":" + selectedComponentAmount;
        div.appendChild(output);
        div.appendChild(br);

        var input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "ingredient");
        input.setAttribute("value", selectedComponentId);
        form.appendChild(input);

        var input1 = document.createElement("input");
        input1.setAttribute("type", "hidden");
        input1.setAttribute("name", "amountOfIngredient");
        input1.setAttribute("value", selectedComponentAmount);
        form.appendChild(input1);

        select.remove(select.selectedIndex);
        amount.value="";
    }
</script>

