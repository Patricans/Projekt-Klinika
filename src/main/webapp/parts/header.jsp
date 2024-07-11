<%@ page pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="pl" data-bs-theme="dark" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <title>${title}</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
    <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.14.0-beta3/dist/css/bootstrap-select.min.css">


</head>
<body class="d-flex flex-column h-100 flex-nowrap">
<main class="flex-shrink-0">
<nav class="navbar navbar-dark bg-dark navbar-expand-sm fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="/home">System zarządzania kliniką</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="mainnav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainnav">
            <ul class="navbar-nav me-auto mb-2">
                <li class="nav-item">
                    <a class="nav-link" href="/home">Strona główna kliniki</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/lokalizacja">Lokalizacja</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto mb-2">
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Zalogowano jako: <sec:authentication property="name"/>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="nav-link" href="<c:url value='/wyloguj'/>">Wyloguj</a></li>

                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/logowanie">Zaloguj</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/rejestracja">Rejestracja użytkownika</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<sec:authorize access="hasAuthority('PATIENT')">

    <nav class="navbar navbar-dark bg-dark navbar-expand-sm subnavbar">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Strefa pacjenta</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="subnavbar1">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="subnavbar1">
                <ul class="navbar-nav me-auto mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Twój profil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#">Umów się na wizytę</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Moje wizyty</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Moje recepty</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Apteka</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</sec:authorize>

<sec:authorize access="hasAuthority('NURSE')">

    <nav class="navbar navbar-dark bg-dark navbar-expand-sm subnavbar">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Strefa pielegniarska</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="subnavbar2">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="subnavbar2">
                <ul class="navbar-nav me-auto mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Twój profil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Magazyn Apteki</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/dyzury">Grafik</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</sec:authorize>

<sec:authorize access="hasAuthority('DOCTOR')">

    <nav class="navbar navbar-dark bg-dark navbar-expand-sm subnavbar">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Strefa lekarska</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="subnavbar3">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="subnavbar3">
                <ul class="navbar-nav me-auto mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Twój profil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/doktor/wizyty">Wizyty</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/doktor/pacjenci">Pacjenci</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/dyzury">Grafik</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</sec:authorize>
<c:if test="${not empty flashMessage}">
    <div class="alert ${flashClass}">${flashMessage}</div>
</c:if>