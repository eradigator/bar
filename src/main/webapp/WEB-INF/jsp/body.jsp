<%@ page language="java" pageEncoding="UTF-8" session="true" %>


<c:set var="content" value="${content}" scope="page"/>
<c:choose>
    <c:when test="${content == 'alcoholic'}">
        <mytag:getinfo/>
    </c:when>
    <c:when test="${content == 'non_alcoholic'}">
        <my:person Age="28" EmployeeId="74852" Name="Bruce Wayne" />
    </c:when>
    <c:when test="${content == 'calculator'}">
        <my:calculator />
    </c:when>
    <c:when test="${content == 'contact'}">
        Контакты
    </c:when>
    <c:otherwise>
        <p>текст на русском языке</p>
        <p>Коктейль такой-то</p>
        <p>
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
            industry's
            standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it
            to make
            a
            type specimen book. It has survived not only five centuries, but also the leap into electronic
            typesetting,
            remaining
            essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing
            Lorem
            Ipsum
            passages, and more recently with desktop publishing software like Aldus PageMaker including versions of
            Lorem
            Ipsum.
        </p>
        <p>
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
            industry's
            standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it
            to make
            a
            type specimen book. It has survived not only five centuries, but also the leap into electronic
            typesetting,
            remaining
            essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing
            Lorem
            Ipsum
            passages, and more recently with desktop publishing software like Aldus PageMaker including versions of
            Lorem
            Ipsum.
        </p>
        <p>
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
            industry's
            standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it
            to make
            a
            type specimen book. It has survived not only five centuries, but also the leap into electronic
            typesetting,
            remaining
            essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing
            Lorem
            Ipsum
            passages, and more recently with desktop publishing software like Aldus PageMaker including versions of
            Lorem
            Ipsum.
        </p>
    </c:otherwise>
</c:choose>

