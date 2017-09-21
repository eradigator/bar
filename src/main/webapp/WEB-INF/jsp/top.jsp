<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form name="nameEn" method="post" action="${pageContext.request.contextPath}/jsp/controller">
    <div style="float: left">
        <input type="hidden" name="command" value="lang">
        <input type="hidden" name="chosen" value="EN">
        <a href="#" onclick="document.nameEn.submit();return(false)">EN</a>&nbsp;
    </div>
</form>

<form name="nameRu" method="post" action="${pageContext.request.contextPath}/jsp/controller">
    <div style="float: left">
        <input type="hidden" name="command" value="lang">
        <input type="hidden" name="chosen" value="RU">
        <a href="#" onclick="document.nameRu.submit();return(false)">RU</a>&nbsp;
    </div>
</form>

<c:if test="${empty user}">
    <form name="login" method="post" action="${pageContext.request.contextPath}/jsp/controller">
        <div style="float: right">
            <input type="hidden" name="command" value="page_login">
            <a href="#" onclick="document.login.submit();return(false)">
                <fmt:message key="login" bundle="${ rb }"/>
            </a>
        </div>
    </form>
</c:if>

<c:if test="${not empty user}">
    <form name="logout" method="post" action="${pageContext.request.contextPath}/jsp/controller">
        <input type="hidden" name="command" value="logout">
        <div style="float: right">
            <a href="#" onclick="document.logout.submit();return(false)">
                <fmt:message key="logout" bundle="${ rb }"/>
            </a>
        </div>
        <div style="text-align: center">
            <b>
                <fmt:message key="hello" bundle="${ rb }"/>&nbsp;${user.name}
            </b>
        </div>
    </form>
</c:if>
