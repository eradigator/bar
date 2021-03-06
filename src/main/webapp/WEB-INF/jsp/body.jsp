<c:set var="content" value="${content}" scope="page"/>
<c:choose>
    <c:when test="${content == 'main'}">
        <my:main/>
    </c:when>
    <c:when test="${content == 'cocktails'}"> <my:cocktails/> </c:when>
    <c:when test="${content == 'favorite'}"> <my:favorite/> </c:when>
    <c:when test="${content == 'calculator'}"> <my:calculator/> </c:when>
    <c:when test="${content == 'cocktail'}"> <my:cocktail/> </c:when>
    <c:when test="${content == 'componentManager'}"> <my:component/> </c:when>
    <c:when test="${content == 'userManager'}"> <my:UserManager/> </c:when>
    <c:when test="${content == 'cocktailManager'}">
        <my:addCocktail/>
        <my:removeCocktail/>
    </c:when>
    <c:otherwise/>
</c:choose>

