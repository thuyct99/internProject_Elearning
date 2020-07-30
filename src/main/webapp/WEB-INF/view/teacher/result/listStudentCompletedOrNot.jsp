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
					<h5> Class Name: ${class.getClassname()}</h5> 
					<h5> Assessment Name: ${assessment.getAssessmentname()}</h5>
					<h5>( ${assessment.getStartdate()} - ${assessment.getExpireddate()})</h5>
					<div class="alert success" style="display:none">
						<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
						<strong id="message">${messageSuccess}</strong>
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
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Email</th>
					<th scope="col">Gender</th>
					<th scope="col">Score/Total</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listStudentInClass}" var="studentInClass" varStatus="num">
					<tr>
						<td>${num.index + 1}</td>
						<td>${studentInClass.student.firstname}</td>
						<td>${studentInClass.student.lastname}</td>
						<td>${studentInClass.student.email}</td>
						<td>${studentInClass.student.gender}</td>
						<td>${studentInClass.score} / ${assessment.totalscore}</td>
						<td>
							<c:choose>
								<c:when test="${listIdOfStudent.contains(studentInClass.student.userid)}">
									<a href="/teacher/viewResultOfStudent/${assessment.assessmentid}/${studentInClass.student.userid}">
										<button class="btn btn-dark">view detail</button>
									</a>
								</c:when>
								<c:otherwise>
									<p>Haven't done the assignment yet</p>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
	</div>
</body>
</html>