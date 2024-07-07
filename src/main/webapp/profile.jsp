<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<%@ include file="parts/header.jsp" %>

<div class="container">

    <div class="row">
        <h1>Twój profil</h1>
        <div class="container form-register col-6">
            <!-- Poprawić ID -->
            <form:form method="POST" modelAttribute="userForm">
                <div class="mb-3">
                <bs:bsinput name="email" type="text" label="Adres email" placeholder="..." readonly="true" />
                </div>

                <div class="mb-3">
                    <bs:bsinput name="first_name" type="text" label="Imie" placeholder="..." readonly="true" />
                </div>
                <div class="mb-3">
                    <bs:bsinput name="last_name" type="text" label="Nazwisko" placeholder="..." readonly="true" />
                </div>
                <div class="mb-3">
                    <bs:bsinput name="pesel" type="text" label="PESEL" placeholder="..." readonly="true" />
                </div>
            </form:form>

        </div>
        <div class="container form-register col-6">
            <form:form method="POST" action="/zmien_haslo" modelAttribute="passwordChange">
                <div class="mb-3">
                <bs:bsinput type="password" name="oldPassword" placeholder="" label="Poprzednie hasło"/>
                    <div id="old_password_help" class="form-text">Aby upewnić się, że jesteś właścicielem konta</div>
                </div>
                <div class="mb-3">
                    <bs:bsinput type="password" name="newPassword" placeholder="" label="Nowe hasło"/>
                </div>
                <div class="mb-3">
                    <bs:bsinput type="password" name="newPassword2" placeholder="" label="Powtórz nowe hasło"/>
                </div>
                <div class="btn-group w-100 my-3" role="group">
                    <button class="btn btn-outline-warning  py-2 mx-2" type="submit">Zmień hasło</button>

                </div>

            </form:form>

        </div>

    </div>
</div>


<%@ include file="parts/footer.jsp" %>