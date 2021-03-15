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
    <h2>Edytuj swoje dane</h2>
    <form:form modelAttribute="user" method="post">
        <form:hidden path="id"/>
        <form:hidden path="enabled"/>
        <div class="form-group">
            <form:input path="name" placeholder="Imię"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko"/>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło"/>
        </div>
        <div class="form-group">
            <input type="password" name="passConf" placeholder="Powtórz hasło"/>
        </div>

        <div class="form-group form-group--buttons">
            <button type="button" name="back" onclick="history.back()" class="btn btn--without-border">Powrót</button>
            <button class="btn" type="submit">Zapisz</button>
        </div>
    </form:form>
</section>

<%@include file="/WEB-INF/sections/footer.jsp" %>

<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>
