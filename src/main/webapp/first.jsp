<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="/WEB-INF/tld/mytaglib.tld" prefix="mytag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${pageContext.request.session.getAttribute('locale')}"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<jsp:forward page="/jsp/controller" />