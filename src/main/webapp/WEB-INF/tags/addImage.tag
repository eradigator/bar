<%@ tag body-content="empty" dynamic-attributes="dynattrs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<h5>Добавление картинки:</h5>

<form name='imageForm' method='post'
      action='${pageContext.request.contextPath}/jsp/controller' enctype="multipart/form-data">
    <input type='hidden' name='command' value='add_cocktail'>
<p>
    Изображение:<br/>
    <input type="file" name="image" accept="image/*" size="5"/>
</p>
    <input type="submit" value="Щас все сломается">
</form>

