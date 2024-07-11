<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h1>Moje recepty</h1>
    <div class="list-group">

        <c:forEach items="${eReceiptList}" var="i">
            <a href="#" class="list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-between">
                    Recepta wystawiona przez: ${i.doctor.first_name} ${i.doctor.last_name}

                    <small>
                        Dnia: <fmt:formatDate value="${i.date}" pattern="YYYY-MM-dd HH:mm"/>
                    </small>
                </div>

                <c:forEach items="${i.receiptDrugs}" var="j" varStatus="jloop">
                    <p class="mb-1">
                            ${jloop.index+1}. ${j.drug.name} - ${j.amount} opakowań.

                    </p>
                    <small>
                    Wygasa dnia <fmt:formatDate value="${j.expirationDate}" pattern="YYYY-MM-dd HH:mm"/>
                    </small><br/>

                     <c:choose>
                            <c:when test="${j.lastPurchased != null}">
                                <small>Wykupiono lek <fmt:formatDate value="${j.lastPurchased}"
                                                                     pattern="YYYY-MM-dd HH:mm"/></small>
                            </c:when>
                            <c:otherwise>
                                <small>Nie wykupiono.</small>
                            </c:otherwise>
                        </c:choose>

                </c:forEach>
            </a>
        </c:forEach>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>
