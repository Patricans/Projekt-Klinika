<%@tag description="Extended input tag to allow for sophisticated errors" pageEncoding="UTF-8" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="name" required="true" type="java.lang.String"%>
<%@attribute name="required" required="false" type="java.lang.Boolean"%>
<%@attribute name="cssclass" required="false" type="java.lang.String"%>
<%@attribute name="placeholder" required="false" type="java.lang.String"%>
<%@attribute name="label" required="false" type="java.lang.String"%>
<%@attribute name="type" required="true" type="java.lang.String"%>
<%@attribute name="readonly" required="false" type="java.lang.Boolean"%>

<spring:bind path="${name}">
        <c:set var="key" value="form.${name}.label" />
        <c:set var="localError"><form:errors path="${name}"/></c:set>
        <c:if test="${not empty localError}">
            <span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
        </c:if>
        <label for="${name}" class="form-label"> ${label} <c:if test="${required}"><span class="required">*</span></c:if></label>
        <c:choose>
            <c:when test="${not empty fn:trim(localError)}">
                <springForm:input path="${name}" type="${type}" placeholder="${placeholder}" id="${name}" class="form-control ${cssclass} is-invalid" readonly="${readonly}"/>
                <div class="invalid-feedback">
                    <springForm:errors path="${name}" />
                </div>
            </c:when>
            <c:when test="${isFormResend}">
                <springForm:input path="${name}" type="${type}" placeholder="${placeholder}" id="${name}" class="form-control is-valid ${cssclass}" readonly="${readonly}"/>
            </c:when>
            <c:otherwise>
                <springForm:input path="${name}" type="${type}" placeholder="${placeholder}" id="${name}" class="form-control ${cssclass}" readonly="${readonly}"/>
            </c:otherwise>
        </c:choose>
</spring:bind>