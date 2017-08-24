<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<h5>Калькулятор</h5>

<form name='calculator' method='post' action='${pageContext.request.contextPath}/jsp/controller'>
    <input type='hidden' name='command' value='calc_strength'>

    <p>
        "A"<br/>
        <input type='text' name='a' value='' required title="">
        <br/>
    </p>
    +
    <p>
        "B"<br/>
        <input type='text' name='b' value='' required title="">
        <br/>
    </p>


    <input type='submit' value='Равно'/><br/>
    ${result}

</form>

