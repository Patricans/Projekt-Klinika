<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark">
    <div class="container">
        <h1>Lekarze</h1>
        <div class="container">
            <ol class="list-group list-group-numbered">
                <c:forEach items="${doctors}" var="doctor">
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold">${doctor.last_name} ${doctor.first_name}</div>
                            <c:if test="${doctor.doctorSpecialty != null}">
                                ${doctor.doctorSpecialty.description}
                            </c:if>
                        </div>
                        <sec:authorize access="isAuthenticated()">
                            <form:form modelAttribute="rateDoctorForm" action="/personel/ocena">
                                <form:hidden path="personnel_id" value="${doctor.id}"/>
                                <div class="rating">
                                    <input value="5" name="score" id="star${doctor.id}-5" type="radio"
                                           onchange="this.form.submit()"
                                    >
                                    <label for="star${doctor.id}-5"></label>
                                    <input value="4" name="score" id="star${doctor.id}-4" type="radio"
                                           onchange="this.form.submit()"
                                    >
                                    <label for="star${doctor.id}-4"></label>
                                    <input value="3" name="score" id="star${doctor.id}-3" type="radio"
                                           onchange="this.form.submit()"
                                    >
                                    <label for="star${doctor.id}-3"></label>
                                    <input value="2" name="score" id="star${doctor.id}-2" type="radio"
                                           onchange="this.form.submit()"
                                    >
                                    <label for="star${doctor.id}-2"></label>
                                    <input value="1" name="score" id="star${doctor.id}-1" type="radio"
                                           onchange="this.form.submit()"
                                    >
                                    <label for="star${doctor.id}-1"></label>
                                </div>
                            </form:form>
                        </sec:authorize>

                        <c:set value="${userScoreRepository.getAverageScore(doctor)}" var="score"/>
                        <span class="badge bg-primary rounded-pill">
                        <c:choose>
                            <c:when test="${score == null}">
                                -.-
                            </c:when>
                            <c:otherwise>
                                <fmt:formatNumber value="${score}" pattern="#.#" minFractionDigits="1" maxFractionDigits="1" />
                            </c:otherwise>
                        </c:choose>
                        </span>
                    </li>
                </c:forEach>
            </ol>
        </div>
        <h1>Pielęgniarki</h1>
        <div class="container ">
            <ol class="list-group list-group-numbered">
                <c:forEach items="${nurses}" var="nurse">
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold">${nurse.last_name} ${nurse.first_name}</div>
                            <c:if test="${nurse.doctorSpecialty != null}">
                                ${nurse.doctorSpecialty.description}
                            </c:if>
                        </div>
                        <c:set value="${userScoreRepository.getAverageScore(doctor)}" var="score"/>
                        <span class="badge bg-primary rounded-pill">
                        <c:choose>
                            <c:when test="${score == null}">
                                -.-
                            </c:when>
                            <c:otherwise>
                                <fmt:formatNumber value="${score}" pattern="#.#" minFractionDigits="1" maxFractionDigits="1" />
                            </c:otherwise>
                        </c:choose>
                        </span>
                    </li>
                </c:forEach>
            </ol>
        </div>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>