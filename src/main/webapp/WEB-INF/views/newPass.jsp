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
    <h2>Nowe hasło</h2>
    <c:if test="${passInvalid != null}"><span style="color: red">${passInvalid}</span></c:if>
    <form action="/new-pass" method="post">
        <div class="form-group">
            <input type="hidden" name="id" value="${id}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="password" name="password" placeholder="Nowe hasło"/>
        </div>
        <div class="form-group">
            <input type="password" name="passConf" placeholder="Powtórz hasło"/>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zapisz</button>
        </div>
    </form>
</section>

<%@include file="/WEB-INF/sections/footer.jsp" %>

</body>
</html>
