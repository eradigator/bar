<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

    <div style="color:#02834b">
        ${addCocktailResult}
    </div>
    <div style="color: crimson">
        ${removeCocktailResult}
        ${errorLoginPassMessage}
        ${wrongAction}
        ${nullPage}
    </div>

