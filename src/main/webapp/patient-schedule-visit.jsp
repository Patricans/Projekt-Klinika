<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container" style="text-align-last: center; margin-top: 140px !important;">
    <c:choose>
        <c:when test="${scheduleStep == 1}">
            <form:form modelAttribute="selectSpeciality" action="/pacjent/umow_wizyte/1">
                <spring:bind path="speciality">
                    <label for="speciality" class="form-label">Specjalizacja lekarza </label>
                    <form:select path="speciality" cssClass="selectpicker" data-live-search="true">
                        <form:options items="${doctorSpecialities}" itemValue="id" itemLabel="description"/>
                    </form:select>
                </spring:bind>
                <div class="btn-group">
                    <input type="submit" class="btn btn-success" value="Przejdź dalej"/>
                </div>
            </form:form>

        </c:when>
        <c:when test="${scheduleStep==2}">
            <form:form modelAttribute="selectDoctor" action="/pacjent/umow_wizyte/2">
            <spring:bind path="doctor">
                <label for="doctor" class="form-label">Lekarz: </label>
                <form:select path="doctor" cssClass="selectpicker" data-live-search="true">
                    <form:options items="${doctors}" itemValue="id" itemLabel="displayName"/>
                </form:select>
            </spring:bind>
            <div class="btn-group">
                <input type="submit" class="btn btn-success" value="Przejdź dalej"/>
            </div>
            </form:form>
        </c:when>
        <c:when test="${scheduleStep == 3}">
            <h4>Wybrany lekarz: ${doctor.displayName}</h4>
            <small>specjalizacja: ${doctor.doctorSpecialty.description}</small><br/>
            <form:form modelAttribute="selectedTimeslot" action="/pacjent/umow_wizyte/3">
                <form:hidden path="doctor" value="${doctor.id}" />
                <spring:bind path="selectedTimeslot">
                    <label for="selectedTimeslot" class="form-label">Lekarz: </label>
                    <form:select path="selectedTimeslot" cssClass="selectpicker" data-live-search="true">
                        <form:options items="${availableTimeslots}" itemValue="key" itemLabel="displayName"/>
                    </form:select>
                </spring:bind>
                <div class="btn-group">
                    <input type="submit" class="btn btn-success" value="Umów się na wizytę"/>
                </div>
            </form:form>
        </c:when>
    </c:choose>
</div>


<%@ include file="parts/footer.jsp" %>