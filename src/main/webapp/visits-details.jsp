<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>


<div class="container">
    <h1>Szczegóły wizyty</h1>
    <div class="row">
        <div class="col">
            <h2>Pacjent</h2>
            Imie: ${visit.patient.first_name} <BR/>
            Nazwisko: ${visit.patient.last_name} <BR/>
            PESEL: ${visit.patient.pesel} <BR/>
            <a class="btn  btn-warning float-start my-2" href="/doktor/wizyty"> Powrót</a>
        </div>
        <div class="col">
            <h2>Recepta</h2>
            <form>
                <div class="mb-3">
                    <label for="Lek" class="form-label">Lek </label>
                    <select id="Lek" class="form-select form-select-lg" aria-label=".form-select-sm example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="Amount" class="form-label">Amount </label>
                    <input class="form-control" id="Amount" type="number">
                </div>
                <div class="btn-group w-100" role="group">
                    <button class="btn btn-primary  py-2 mx-2" type="submit">Dodaj do recepty.</button>
                </div>

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Lek</th>
                        <th scope="col">Ilość</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>APAP</td>
                        <td>3</td>

                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>xDXD</td>
                        <td>2</td>
                    </tr>
                    </tbody>
                </table>
                <div class="btn-group w-100" role="group">
                    <button class="btn btn-success  py-2 mx-2" type="submit">Wystaw recepte.</button>
                </div>
            </form>

        </div>
        <div class="col">
            <h2>Komentarz</h2>
            <form:form method="POST" modelAttribute="visitComment">
                <spring:bind path="comment">
                <form:textarea path="comment" class="w-100" rows="5"/>
                </spring:bind>
                <div class="btn-group w-100" role="group">
                    <button class="btn btn-success  py-2 mx-2" type="submit">Zakomentuj.</button>
                </div>
            </form:form>
        </div>
        <div class="col">
            <h2>Historia wizyt</h2>

            <div class="container">
                <div class="list-group">
                    <c:forEach items="${visits}" var="i">
                        <a href="#" class="list-group-item list-group-item-action">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">${i.patient.first_name} ${i.patient.last_name}</h5>
                                <small>
                                    <fmt:formatDate value="${i.startDate}" pattern="YYYY-MM-dd HH:mm"/>
                                    -
                                    <fmt:formatDate value="${i.endDate}" pattern="YYYY-MM-dd HH:mm"/>
                                </small>
                            </div>
                            <p class="mb-1">
                                <c:choose>
                                    <c:when test="${not empty i.doctorNotes}">
                                        ${i.doctorNotes}
                                    </c:when>
                                    <c:otherwise>
                                        <i>Brak notatek.</i>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <small>
                                <c:choose>
                                    <c:when test="${i.EReceipts.size() == 0}">
                                        Nie wystawiono recepty.
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${i.EReceipts}" var="er">
                                            <c:forEach items="${er.receiptDrugs}" var="dr">
                                                ${dr.drug.name} - ${dr.amount} opakowań.
                                            </c:forEach>
                                        </c:forEach>
                                        Wystawiono recepte na ${i.receiptDrugCount} leki.
                                    </c:otherwise>
                                </c:choose>
                            </small>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>


    </div>


</div>


<%@ include file="parts/footer.jsp" %>