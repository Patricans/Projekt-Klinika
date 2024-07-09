<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ include file="parts/header.jsp" %>
<div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark">
    <div class="container">
        <h1 style="margin-top: 50px;">Rejestracja użytkownika</h1>
        <div class="container form-register">
            <form:form method="POST" modelAttribute="userForm">
            <div class="mb-3">
                <bs:bsinput type="email" name="email" required="true" placeholder="adam.kowalski@gmail.com"
                            label="Adres email"/>
            </div>
            <div class="mb-3">
                <bs:bsinput type="password" name="password" required="true" placeholder="..." label="Hasło"/>
            </div>
            <div class="mb-3">
                <bs:bsinput type="password" name="passwordConfirm" required="true" placeholder="..."
                            label="Potwierdź hasło"/>
            </div>
            <div class="mb-3">
                <bs:bsinput type="text" name="first_name" required="true" placeholder="..." label="Imię"/>
            </div>
            <div class="mb-3">
                <bs:bsinput type="text" name="last_name" required="true" placeholder="..." label="Nazwisko"/>
            </div>
            <div class="mb-3">
                <bs:bsinput type="number" name="pesel" required="true" placeholder="..." label="PESEL"/>
            </div>
            <div class="btn-group" role="group">
                <button type="submit" class="btn btn-primary mx-2">Wyślij</button>
                <button type="submit" class="btn btn-danger float-end">Wyczyść</button>
            </div>
        </div>
        </form:form>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>

