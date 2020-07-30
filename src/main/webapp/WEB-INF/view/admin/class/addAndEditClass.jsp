<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container-fluid center">

	<div class="col-sm-3"></div>

	<form action="<%=request.getContextPath()%>${url}" method="post" class="col-sm-6 form-general">
		<h1 class="form-title">Class Form</h1>
		<br>
		<c:forEach items="${errors}" var="error">
			<span style="color: red">${error}</span>
		</c:forEach>
		<input type="hidden" name="classid" value="${editClass.classid}" />

		<div class="form-group row">
			<div class="col-sm-1"></div>
			<label for="classname" class="col-sm-2 col-form-label">Class Name</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" name="classname" value="${editClass.classname}">
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-1"></div>
			<label for="Teacher" class="col-sm-2 col-form-label">Teacher</label>
			<div class="col-sm-8">
	
				<select class="form-control" name="user.userid">
					<option value="" ${editClass.getUser().getUserid() == null ? 'selected="selected"' : ''}>None</option>
					<c:forEach items="${users}" var="user">
						<option value="${user.userid}" ${user.userid == editClass.getUser().getUserid() ? 'selected="selected"' : ''}>${user.getUsername()}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group row">
			<input type="hidden" value="${url == '/admin/class/saveAddClass' ? 'true' : editClass.status}"  name="status" />
		</div>

		<input class="btn btn-dark" type="submit" value="Save">
		<a href="/admin/class"><input class="btn btn-dark" type="button" value="Cancel"></a>
	</form>

	<div class="col-sm-3"></div>
</div>
