<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<c:if test="${(not empty result) or (not empty delComponentMessage)}">
    <div class="result_field">
        <div style="color:#02834b">
            <h5>${result}</h5>
        </div>
        <div style="color: crimson">
            <h5>${delComponentMessage}</h5>
        </div>
    </div>
    <br/>
</c:if>

<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'add')" id="defaultOpen">Добавление компонента</button>
    <button class="tablinks" onclick="openTab(event, 'remove')">Удаление компонента</button>
</div>

<div id="add" class="tabcontent">

    <form name='addComponent' method='post' action='${pageContext.request.contextPath}/jsp/controller'>
        <input type='hidden' name='command' value='add_component'>

        <p>
            Название РУС
            <br/>
            <input name='name_RU' value='' required title="">
            <br/>
        </p>
        <p>
            Название EN
            <br/>
            <input name='name_EN' value='' required title="">
            <br/>
        </p>

        <p>
            Тип
            <br/>
            <select name="componentType" title="">
                <c:forEach items="${componentTypes}" var="componentType">
                    <option value="${componentType.key}">${componentType.value}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            Крепость (%)
            <br/>
            <input type="number" name="strength" min="0" max="100" step="0.1" value="" title="" required>
        </p>

        <p>
            Цена (за мл)
            <br/>
            <input type="number" name='price' min="0" max="100000" step="0.01" value="" title="" required>
            <br/>
        </p>
        <input type='submit' value='Добавить'/>
    </form>
</div>

<div id="remove" class="tabcontent">

    <form name="delComponent" method="post" action="${pageContext.request.contextPath}/jsp/controller">
        <input type="hidden" name="command" value="del_component">

        <p>
            Название компонента
            <br/>

            <select name="componentToDel" title="">
                <c:forEach items="${components}" var="pair">
                    <option value="${pair.key}">${pair.value}</option>
                </c:forEach>
            </select>
        </p>

        <input type="submit" value="Удалить"/>
    </form>

</div>

<script src="${pageContext.request.contextPath}/js/tab.js"></script>
