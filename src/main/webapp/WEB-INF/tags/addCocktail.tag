<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
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
    <button class="tablinks" onclick="openTab(event, 'add')" id="defaultOpen">
        <fmt:message key="addCocktail" bundle="${rb}"/>
    </button>
    <button class="tablinks" onclick="openTab(event, 'remove')">
        <fmt:message key="delCocktail" bundle="${rb}"/>
    </button>
</div>

<div id="add" class="tabcontent">

    <form name='addCocktail' method='post' action='${pageContext.request.contextPath}/jsp/controller'
          enctype="multipart/form-data">
        <input type='hidden' name='command' value='add_cocktail'>

        <p>
            <fmt:message key="name_ru" bundle="${rb}"/>
            <br/>
            <input name='name' value='' required title="">
            <br/>
        </p>

        <p>
            <fmt:message key="name_en" bundle="${rb}"/>
            <br/>
            <input name='name_en' value='' required title="">
            <br/>
        </p>

        <p>
            <fmt:message key="component" bundle="${rb}"/>
            <br/>
            <select id="component" name="component" title="" style="width: 200px">
                <c:forEach items="${componentNames}" var="componentName">
                    <option value="${componentName.id}">
                        <c:choose>
                            <c:when test="${sessionScope.locale eq 'ru_RU'}">${componentName.nameRu}</c:when>
                            <c:when test="${sessionScope.locale eq 'en_US'}">${componentName.nameEn}</c:when>
                        </c:choose>
                    </option>
                </c:forEach>
            </select>
            <fmt:message key="amount" bundle="${rb}"/>
            <input type="number" id="amount" name="amount" min="0" max="500" step="1" title="" value="0"/>
            <button type="button" onclick="addComponent()">
                <fmt:message key="addComponent" bundle="${rb}"/>
            </button>
        </p>

        <div id="outputPlace"></div>


        <p>
            <fmt:message key="method" bundle="${rb}"/>
            <br/>
            <select name='method' title="">
                <c:forEach items="${methods}" var="method">
                    <option value="${method.id}">
                        <c:choose>
                            <c:when test="${sessionScope.locale eq 'ru_RU'}">${method.nameRu}</c:when>
                            <c:when test="${sessionScope.locale eq 'en_US'}">${method.nameEn}</c:when>
                        </c:choose>
                    </option>
                </c:forEach>
            </select>
        </p>

        <p>
            <fmt:message key="glass" bundle="${rb}"/>
            <br/>
            <select name='glass' title='glass'>
                <c:forEach items="${glasses}" var="glass">
                    <option value="${glass.id}">
                        <c:choose>
                            <c:when test="${sessionScope.locale eq 'ru_RU'}">${glass.nameRu}</c:when>
                            <c:when test="${sessionScope.locale eq 'en_US'}">${glass.nameEn}</c:when>
                        </c:choose>
                    </option>
                </c:forEach>
            </select>
        </p>

        <p>
            <fmt:message key="image" bundle="${rb}"/>
            <br/>
            <input type="file" name="image" accept="image/*" size="5"/>
        </p>

        <p>
            <input type='submit' value='<fmt:message key="add" bundle="${rb}"/>'/>
        </p>

    </form>
</div>

<script src="${pageContext.request.contextPath}/js/tab.js"></script>
<script src="${pageContext.request.contextPath}/js/addCocktail.js"></script>

