<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h1>Twoi pacjenci</h1>
       <div class="list-group">
           <c:forEach items="${patients}" var="i">
            <a href="#" class="list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">${i.last_name} ${i.first_name}</h5>
                </div>
                <p class="mb-1">
                    <c:set var="lastVisit" value="${visitRepository.getLastVisit(i, doctor)}" scope="page" />
                    <c:choose>
                        <c:when test="${lastVisit != null}">
                            Data ostatniej wizyty
                            <fmt:formatDate value="${lastVisit.getStartDate()}" pattern="YYYY-MM-dd HH:mm"/>
                        </c:when>
                        <c:otherwise>
                            <i>Brak ostatniej wizyty.</i>
                        </c:otherwise>
                    </c:choose>
                </p>
                <small>Liczba wizyt u ${doctor.last_name} ${doctor.first_name}: ${visitRepository.getVisitCount(i, doctor)}</small><br/>
                <small>Liczba wizyt u wszystkich lekarzy: ${visitRepository.getVisitCount(i)}</small>
            </a>
        </c:forEach>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>
