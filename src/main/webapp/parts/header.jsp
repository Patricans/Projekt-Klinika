<%@ page pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="pl" data-bs-theme="dark" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <title>${title}</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/custom.css">
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
                <sec:authorize access="hasAuthority('PATIENT')">
                <li class="nav-item">
                    <a class="nav-link" href="#">Strefa pacjenta</a>
                </li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('DOCTOR')">
                <li class="nav-item">
                    <a class="nav-link" href="#">Strefa lekarza</a>
                </li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="#">Strefa administratora</a>
                </li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('NURSE')">
                <li class="nav-item">
                    <a class="nav-link" href="#">Strefa pielęgniarki</a>
                </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/wyloguj'/>">Wyloguj</a>
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
                        <a class="nav-link" href="#">Profil pacjenta</a>
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
                        <a class="nav-link" href="#">Leki dostępne w aptece</a>
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
                        <a class="nav-link" href="#">Profil pielegniarki</a>
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
                        <a class="nav-link" href="#">Leki dostępne w aptece</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</sec:authorize>
    <h2>Zalogowano jako: <sec:authentication property="name"/> </h2>