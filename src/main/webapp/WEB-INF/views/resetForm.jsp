<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<%@include file="/WEB-INF/sections/head.jsp" %>
<body>
<%@include file="/WEB-INF/sections/header.jsp" %>

<section class="login-page">
    <h2>Resetuj hasło</h2>
    <c:if test="${notFound != null}"><span style="color: red">${notFound}</span></c:if>
    <form action="/reset" method="post">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email"/>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group form-group--buttons">
            <button type="button" name="back" onclick="history.back()" class="btn btn--without-border">Powrót</button>
            <button class="btn" type="submit">Wyślij</button>
        </div>
    </form>
</section>

<%@include file="/WEB-INF/sections/footer.jsp" %>

</body>
</html>
