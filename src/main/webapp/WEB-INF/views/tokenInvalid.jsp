<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<%@include file="/WEB-INF/sections/head.jsp" %>
<body>
<%@include file="/WEB-INF/sections/header.jsp" %>

<section class="login-page">
    <div class="slogan container container--90">
        <h2>
            Nieprawidłowy link.
        </h2>
    </div>
</section>

<%@include file="/WEB-INF/sections/footer.jsp" %>

<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>
