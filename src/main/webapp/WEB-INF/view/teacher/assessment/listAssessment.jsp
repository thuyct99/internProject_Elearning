<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String deleteImageAddress = "https://img.icons8.com/cotton/2x/delete-sign--v2.png";
	String editImageAddress = "https://img.icons8.com/cotton/2x/edit.png";
%>
<body>
	<div class="container-fluid">
		 <div class="row">
			<div class="col-sm-7">
				<div class="alert success" style="display:none">
					<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
					<strong id="message">${messageSuccess}</strong> 
				</div>
			</div>
			<div class="col-sm-5">
				<div class="row">
					<div class="col-sm-8"></div>
					 <div class="col-sm-4">
						<a href='/teacher/assessment/addAssessment'><button class="btn btn-dark" type="button">Add new assessment</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>	
	<br><br>
		<div class="container-fluid">
		  <div class="row">
		  	<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Assessment Name</th>
					<th scope="col">Class Name</th>
					<th scope="col">Start Date</th>
					<th scope="col">Expired Date</th>
					<th scope="col">Status</th>
					<th scope="col">Total Questions</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="i" value="1" />
					<c:forEach items="${listAssessment}" var="assessment">
					<tr>
						<td>${i}</td>
						<td>${assessment.assessmentname}</td>
						<td>${assessment.getClassForeign().getClassname()}</td>
						<td>${assessment.startdate}</td>
						<td>${assessment.expireddate}</td>
						<td>
							<a href="#" onclick="confirmation('/teacher/assessment/editAssessmentStatus/${assessment.assessmentid}', 'update')">
								<button class="btn ${assessment.getStatus() ? 'btn-dark' : ''}">${assessment.getStatus() ? 'Active' : 'Inactive'}</button>
							</a>
						</td>
						<td>${assessment.getTotalquestion()}</td>

						<td>
							<a href="/teacher/assessment/editAssessment/${assessment.assessmentid}">
								<img alt="edit" src="<%=editImageAddress%>" class="optionSize" />
							</a> 
							<a href="#" onclick="confirmation('/teacher/assessment/deleteAssessment?assessmentid=<c:out value='${assessment.assessmentid}'/>', 'delete')">
								<img alt="delete" src="<%=deleteImageAddress%>" class="optionSize" />
							</a>&emsp;
							<a href="/teacher/questionOfAssessment?assessmentid=<c:out value='${assessment.assessmentid}'/>">
								<button class="btn btn-dark" type="button">View questions</button>
							</a>&emsp;
							<a href="/teacher/viewResult?assessmentid=<c:out value='${assessment.assessmentid}'/>">
								<button class="btn btn-dark" type="button">View result</button>
							</a>
						</td>

						</tr>
						<c:set var="i" value="${i+1}" />
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>

		<div id="confirm" class="modal">
			<form class="modal-content">
				<div class="container-model">
		 			<span onclick="document.getElementById('confirm').style.display='none'" class="close" title="Close Modal">&times;</span>
					<h1 id="title"></h1>
					<p id="ask"></p>
		
					<div class="clearfix">
						<a id="cancelConfirm" href="#" onclick="document.getElementById('confirm').style.display='none'">
							<button type="button" class="btn cancelbtn">No</button>
						</a>
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
				setTimeout(function(){ $(".alert").css("display", "none"); }, 1000);
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