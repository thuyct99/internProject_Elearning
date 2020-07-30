<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<!DOCTYPE html>
<html>
<%
	String deleteImageAddress = "https://img.icons8.com/cotton/2x/delete-sign--v2.png";
String editImageAddress = "https://img.icons8.com/cotton/2x/edit.png";
%>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-7">
				<div class="alert success" style="display: none">
					<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
					<strong id="message">${messageSuccess}</strong>
				</div>
			</div>
			<div class="col-sm-5">
				<div class="row">
					<div class="col-sm-8"></div>
					<div class="col-sm-4">
						<a href='/admin/class/addClass'><button class="btn btn-dark"
								type="button">Add new class</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="container-fluid">
		<div class="row">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Class Name</th>
						<th scope="col">Teacher</th>
						<th scope="col">Status</th>
						<th scope="col">Total Students</th>
						<th scope="col">Total Assessments</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>

					<c:set var="i" value="1" />
					<c:forEach items="${classPage.getContent()}" var="class">
						<tr>
							<td>${i}</td>
							<td>${class.classname}</td>
							<td>${class.getUser().getUsername()}</td>
							<td>
								<a href="#"
									onclick="confirmation('/admin/class/editClassStatus/${class.classid}', 'update')">
										<button class="btn ${class.getStatus() ? 'btn-dark' : ''}">${class.getStatus() ? 'Active' : 'Inactive'}</button>
								</a>
							</td>
							<td>${class.totalStudents}</td>
							<td>${class.totalAssessments}</td>
							<td>
								<a href="/admin/class/editClass/${class.classid}"> 
									<img alt="edit" src="<%=editImageAddress%>" class="optionSize" />
								</a> 
								<a onclick="confirmation('/admin/class/deleteClass?classid=<c:out value='${class.classid}'/>', 'delete ')">
									<img alt="delete" src="<%=deleteImageAddress%>" class="optionSize" />
								</a>&emsp; 
								<a href="/admin/getStudentInClass?classid=<c:out value='${class.classid}'/>">
								<button class="btn btn-dark">Assign</button>
								</a>
							</td>

						</tr>
						<c:set var="i" value="${i+1}" />
					</c:forEach>

				</tbody>
			</table>

		</div>
	</div>

	<util:pagination count="${classPage.getTotalElements()}"
		totalPages="${classPage.getTotalPages()}"
		url="${pageContext.request.contextPath}/admin/class"
		curpage="${classPage.getNumber()}" />

	<div id="confirm" class="modal">

		<form class="modal-content">
			<div class="container-model">
				<span
					onclick="document.getElementById('confirm').style.display='none'"
					class="close" title="Close Modal">&times;</span>
				<h1 id="title"></h1>
				<p id="ask"></p>

				<div class="clearfix">
					<a id="cancelConfirm" href="#"
						onclick="document.getElementById('confirm').style.display='none'"><button
							type="button" class="btn cancelbtn">No</button></a>
					<a id="acceptConfirm" href="#"> 
						<button type="button" class="btn-dark btn acceptbtn">Yes</button>
					</a>
				</div>
			</div>
		</form>
	</div>

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