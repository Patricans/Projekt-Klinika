<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>
<div class="container w-100">
    <form method="POST" action="${contextPath}/logowanie" class="form-signin">
        <img class="mb-4" src="https://dummyimage.com/100x100/d42dd4/0011ff" alt="" width="72" height="57">
        <h1 class="h3 mb-3 fw-normal">Logowanie do systemu</h1>
        <div class="form-group ${error != null ? 'has-error': ''}">
            <span>${message}</span>
            <div class="form-floating">
                <input type="email" class="form-control" id="username" placeholder="name@example.com">
                <label for="username">Adres email</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="password" placeholder="hasło">
                <label for="password">Hasło</label>
            </div>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <div class="form-check text-start my-3">
                <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Zapamiętaj mnie na tym urządzeniu
                </label>
            </div>
            <div class="btn-group w-100" role="group">
                <button class="btn btn-success  py-2 mx-2" type="submit">Zaloguj się</button>
                <a class="btn btn-primary  py-2" href="/rejestracja">Rejestracja</a>
            </div>
        </div>
    </form>
</div>
<%@ include file="parts/footer.jsp" %>