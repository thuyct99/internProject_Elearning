<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<body>
	<div class="container-fluid row mg-top-2">
		<div class="col-sm-7">
		</div>
		<div class="col-sm-5">
			<div class="row">
				<div class="col-sm-8"></div>
				<div class="col-sm-4">
					<a href='/admin/class/addClass'>
						<button class="btn btn-pink " type="button" style="margin-left: 18px">
							<spring:message code="add-new-class"/>
						</button>
					</a>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="container-fluid">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th scope="col"><spring:message code=".NO"/></th>
					<th scope="col"><spring:message code="class-name"/></th>
					<th scope="col"><spring:message code="teacher-name"/></th>
					<th scope="col"><spring:message code="status"/></th>
					<th scope="col"><spring:message code="total-student"/></th>
					<th scope="col"><spring:message code="total-assessment"/></th>
					<th scope="col"><spring:message code="action"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${classPage.getContent()}" var="class" varStatus="status">
					<tr>
						<td>${status.index +1}</td>
						<td>${class.classname}</td>
						<td>${class.getUser().getUsername()}</td>
						<td>
							<a href="#" onclick="confirmation('/admin/class/editClassStatus/${class.classid}', 'update')">
								<button class="btn ${class.getStatus() ? 'btn-active' : 'btn-inactive'}">${class.getStatus() ? 'Enable' : 'Disable'}</button>
							</a>
						</td>
						<td>${class.totalStudents}</td>
						<td>${class.totalAssessments}</td>
						<td>
							<a href="/admin/class/editClass/${class.classid}"> 
								<img alt="edit" src="<c:url value="../../images/edit.png"/>" class="optionSize" />
							</a>
							<a onclick="confirmation('/admin/class/deleteClass?classid=<c:out value='${class.classid}'/>', 'delete ')">
								<img alt="delete" src="<c:url value="../../images/delete.png"/>" class="optionSize" />
							</a>&ensp;
							<a href="/admin/getStudentInClass?classid=<c:out value='${class.classid}'/> ">
								<button class="btn btn-pink"><spring:message code="assign"/></button>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<util:pagination count="${classPage.getTotalElements()}"
		totalPages="${classPage.getTotalPages()}"
		url="${pageContext.request.contextPath}/admin/class"
		curpage="${classPage.getNumber()}" />

	<script type="text/javascript">
		$(document).ready(function() {

			if($("#message").html() != ""){
				$(".alert").css("display", "block");
				setTimeout(function(){ $(".alert").css("display", "none"); }, 5000);
			}
		});

		function confirmation(success, action) {
			
			$('#acceptConfirm').attr("href", success);
			$('#title').html(action + ' Item');
			$('#ask').html('Are you sure you want to ' + action + ' this Item ?');
			$('#confirm').show();
		}

	</script>
</body>
</html>