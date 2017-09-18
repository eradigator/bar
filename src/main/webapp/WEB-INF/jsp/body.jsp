<c:set var="content" value="${content}" scope="page"/>
<c:choose>
    <c:when test="${content == 'main'}">
        <my:main/>
    </c:when>
    <c:when test="${content == 'alcoholic'}">
        <my:alcoholic/>
    </c:when>

    <c:when test="${content == 'nonalcoholic'}">
        <my:nonalco />
    </c:when>

    <c:when test="${content == 'favorite'}">
        <my:favorite/>
    </c:when>

    <c:when test="${content == 'calculator'}">
        <my:calculator />
    </c:when>
    <c:when test="${content == 'cocktail'}">
        <my:cocktail />
    </c:when>
    <c:when test="${content == 'componentManager'}">
        <my:component />
    </c:when>
    <c:when test="${content == 'userManager'}">
        <my:addUser/>
        <my:removeUser/>
    </c:when>
    <c:when test="${content == 'cocktailManager'}">
        <my:addCocktail/>
        <my:removeCocktail/>
    </c:when>
    <c:otherwise />
</c:choose>

