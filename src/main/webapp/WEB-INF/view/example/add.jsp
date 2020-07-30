<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form method="post" modelAttribute="exampleDTO"
	action="${pageContext.request.contextPath}/example/add">

	<form:label path="email">
		<spring:message code="labels.example.email" />
		<label class="cbm-required-field">*</label>
	</form:label>
	<form:input type="text" cssClass="form-control"
		path="email" autofocus="true" maxlength="200" />
	<form:errors path="email" />


	<form:label path="name">
		<spring:message code="labels.example.name" />
		<label class="cbm-required-field">*</label>
	</form:label>
	<form:input type="text" cssClass="form-control"
		path="name" autofocus="true" maxlength="200" />
	<form:errors path="name" />
	<button type="submit" class="btn btn-success pull-right">
		<spring:message code="labels.button.create" />
	</button>
</form:form>
