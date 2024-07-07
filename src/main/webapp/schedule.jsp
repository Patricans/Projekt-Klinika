<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h1>Twoje dyżury</h1>
    <div class="row">
    <div class="col">
            <c:forEach items="${schedules}" var="i" begin="0" end="${(scheduleCount/2)*1}">
            <ul class="list-group list-group-horizontal-md">
                <li class="list-group-item"><fmt:formatDate value="${i.startDate}" pattern="yyyy-MM-dd"/></li>
                <li class="list-group-item"><fmt:formatDate value="${i.startDate}" pattern="HH:mm"/></li>
                <li class="list-group-item"><fmt:formatDate value="${i.endDate}" pattern="yyyy-MM-dd"/></li>
                <li class="list-group-item"><fmt:formatDate value="${i.endDate}" pattern="HH:mm"/></li>
            </ul>
            </c:forEach>
        </div>
    <div class="col">
            <c:forEach items="${schedules}" var="i" begin="${1*(scheduleCount/2)+1}">
            <ul class="list-group list-group-horizontal-md">
                <li class="list-group-item"><fmt:formatDate value="${i.startDate}" pattern="yyyy-MM-dd"/></li>
                <li class="list-group-item"><fmt:formatDate value="${i.startDate}" pattern="HH:mm"/></li>
                <li class="list-group-item"><fmt:formatDate value="${i.endDate}" pattern="yyyy-MM-dd"/></li>
                <li class="list-group-item"><fmt:formatDate value="${i.endDate}" pattern="HH:mm"/></li>
            </ul>
            </c:forEach>
        </div>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>