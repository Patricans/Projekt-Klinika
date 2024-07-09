<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h1>Twoje wizyty</h1>
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
                            Wystawiono recepte na ${i.receiptDrugCount} podane leki.
                        </c:otherwise>
                    </c:choose>
                </small>
            </a>
        </c:forEach>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>