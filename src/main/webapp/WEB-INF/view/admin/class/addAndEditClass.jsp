<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<title><spring:message code="add-edit-class"/></title>
</head>
<div class="container-fluid center mg-top-2">

	<div class="col-sm-2"></div>

	<form action="<%=request.getContextPath()%>${url}" method="post" class="col-sm-8 form-general">
		<h1 class="form-title"><spring:message code="class-form"/></h1>
		<br>
		<div class="form-group row">
			<c:if test="${errors != null}">
				<c:forEach items="${errors}" var="error">
					<span style="color: red; margin-left: 234px;"><spring:message code="${error}"/></span>
				</c:forEach>
			</c:if>
			<input type="hidden" name="classid" value="${editClass.classid}" />

		</div>
		
		<div class="form-group row">
			<label for="classname" class="col-sm-3 col-form-label"><spring:message code="class-name"/></label>
			<div class="col-sm-8">
				<input type="text" class="form-control" name="classname" value="${editClass.classname}" placeholder="Please enter class's name here"> 
			</div>
		</div>

		<div class="form-group row">
			<label for="Teacher" class="col-sm-3 col-form-label"><spring:message code="teacher-name"/></label>
			<div class="col-sm-8">
				<select class="form-control" name="user.userid">
					<option value="" ${editClass.getUser().getUserid() == null ? 'selected="selected"' : ''}><spring:message code="none"/></option>
					<c:forEach items="${users}" var="user">
						<option value="${user.userid}" ${user.userid == editClass.getUser().getUserid() ? 'selected="selected"' : ''}>${user.getUsername()}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group row">
			<input type="hidden" value="${url == '/admin/class/saveAddClass' ? 'true' : editClass.status}"  name="status" />
		</div>
		<div class="form-group row flex-around mt-5">
			<input class="btn btn-pink large-btn mg-left-10" type="submit" value="Save">
			<a href="/admin/class" class="large-btn mg-right-6"">
				<input class="btn btn-warning large-btn" type="button" value="Cancel">
			</a>
		</div>
	</form>
	<div class="col-sm-2"></div>
</div>
