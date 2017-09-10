<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date"/>
<h5>Special for &lt;epam&gt; by V.Ten</h5>
<h5>&copy; <fmt:formatDate type="time" value="${now}" pattern="yyyy"/></h5>

