<%@ page language="java" pageEncoding="UTF-8" session="true" %>


<c:set var="content" value="${content}" scope="page"/>
<c:choose>
    <c:when test="${content == 'catalog'}">
        <my:catalog/>
    </c:when>

    <c:when test="${content == 'alcoholic'}">
        <my:alcoholic/>
    </c:when>
    <c:when test="${content == 'nonalcoholic'}">
        <my:nonalco />
    </c:when>
    <c:when test="${content == 'calculator'}">
        <my:calculator />
    </c:when>
    <c:otherwise>
        <p>текст на русском языке</p>
        <p>Коктейль такой-то</p>
    </c:otherwise>
</c:choose>

