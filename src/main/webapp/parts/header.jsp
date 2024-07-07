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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/custom.css">
    <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css"/>
</head>
<body class="d-flex flex-column h-100 flex-nowrap">
<main class="flex-shrink-0">
<nav class="navbar navbar-dark bg-dark navbar-expand-sm fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">System zarządzania kliniką</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="mainnav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainnav">
            <ul class="navbar-nav me-auto mb-2">
                <li class="nav-item">
                    <a class="nav-link" href="#">Strona główna kliniki</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logowanie">Lokalizacja</a>
                </li>
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
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="subnavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="subnavbar">
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
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="subnavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="subnavbar">
                <ul class="navbar-nav me-auto mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Twój profil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Magazyn Apteki</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Grafik</a>
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
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="subnavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="subnavbar">
                <ul class="navbar-nav me-auto mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Twój profil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Wystaw receptę</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Historia recept</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Pacjenci</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Grafik</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</sec:authorize>
<c:if test="${not empty flashMessage}">
    <div class="alert ${flashClass}" >${flashMessage}</div>
</c:if>