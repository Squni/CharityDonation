<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <sec:authorize access="isAuthenticated()">
                <form action="<c:url value="/logout"/>" method="post">
                    <li><button class="btn btn--small btn--without-border active">Wyloguj</button></li>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <li><a href="/app/edit" class="btn btn--small btn--without-border">Edytuj profil</a></li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
                <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            </sec:authorize>
        </ul>

        <ul>
            <sec:authorize access="isAuthenticated()">
                <li><a href="/app/form" class="btn btn--without-border active">Start</a></li>
            </sec:authorize>
            <li><a href="#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#about" class="btn btn--without-border">O nas</a></li>
            <li><a href="#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zacznij pomagać!<br/>
                Oddaj niechciane rzeczy w zaufane ręce
            </h1>
        </div>
    </div>
</header>