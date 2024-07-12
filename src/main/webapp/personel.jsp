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
                            <c:set var="lastScore" value="${userScoreRepository.getUserScore(user, doctor)}"/>
                            <form:form modelAttribute="rateDoctorForm" action="/personel/ocena">
                                <form:hidden path="personnel_id" value="${doctor.id}"/> <!-- działa to działa -->
                                <div class="rating">
                                    <c:forEach var="i" begin="1" end="5" step="1">
                                        <c:choose>
                                            <c:when test="${lastScore != null and lastScore.score == 6-i}">
                                                <input value="${6-i}" name="score" id="star${doctor.id}-${6-i}" type="radio"
                                                       onchange="this.form.submit()" checked="checked">
                                            </c:when>
                                            <c:otherwise>
                                                <input value="${6-i}" name="score" id="star${doctor.id}-${6-i}" type="radio"
                                                       onchange="this.form.submit()">
                                            </c:otherwise>
                                        </c:choose>
                                        <label for="star${doctor.id}-${6-i}"></label>
                                    </c:forEach>
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
                                <fmt:formatNumber value="${score}" pattern="#.#" minFractionDigits="1"
                                                  maxFractionDigits="1"/>
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
                         <sec:authorize access="isAuthenticated()">
                            <c:set var="lastScore" value="${userScoreRepository.getUserScore(user, nurse)}"/>
                            <form:form modelAttribute="rateDoctorForm" action="/personel/ocena">
                                <form:hidden path="personnel_id" value="${nurse.id}"/>
                                <div class="rating">
                                    <c:forEach var="i" begin="1" end="5" step="1">
                                        <c:choose>
                                            <c:when test="${lastScore != null and lastScore.score == 6-i}">
                                                <input value="${6-i}" name="score" id="star${nurse.id}-${6-i}" type="radio"
                                                       onchange="this.form.submit()" checked="checked">
                                            </c:when>
                                            <c:otherwise>
                                                <input value="${6-i}" name="score" id="star${nurse.id}-${6-i}" type="radio"
                                                       onchange="this.form.submit()">
                                            </c:otherwise>
                                        </c:choose>
                                        <label for="star${nurse.id}-${6-i}"></label>
                                    </c:forEach>
                                </div>
                            </form:form>
                        </sec:authorize>

                        <c:set value="${userScoreRepository.getAverageScore(nurse)}" var="score"/>
                        <span class="badge bg-primary rounded-pill">
                        <c:choose>
                            <c:when test="${score == null}">
                                -.-
                            </c:when>
                            <c:otherwise>
                                <fmt:formatNumber value="${score}" pattern="#.#" minFractionDigits="1"
                                                  maxFractionDigits="1"/>
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