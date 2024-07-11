<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h1>Apteka naszej kliniki</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Nazwa leku</th>
            <th scope="col">Producent</th>
            <th scope="col">Ilość</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${drugs}" var="i">
            <tr>
                <td>${i.name}</td>
                <td>${i.vendor}</td>
                <td>
                    <c:choose>
                        <c:when test="${i.count == 0}"><span class="badge text-bg-danger">Brak leku</span></c:when>
                        <c:when test="${i.count < 5}"><span class="badge text-bg-warning">Mała ilość</span></c:when>
                        <c:when test="${i.count < 10}"><span class="badge text-bg-info">Średnia ilość</span></c:when>
                        <c:otherwise><span class="badge text-bg-success">Duża ilość</span></c:otherwise>
                    </c:choose>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>
<
<%@ include file="parts/footer.jsp" %>
