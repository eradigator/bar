<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>

<div style="text-align: center">
    <jsp:useBean id="now" class="java.util.Date" scope="page"/>
    <h5>Special for &lt;epam&gt; by V.Ten</h5>
    <h5>&copy; <fmt:formatDate type="time" value="${now}" pattern="yyyy"/></h5>
</div>