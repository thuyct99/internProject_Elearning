<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid row mg-top-2">
		<div class="col-sm-7">
		</div>
		<div class="col-sm-5">
			<div class="row">
				<div class="col-sm-8"></div>
				<div class="col-sm-4">
					<a href='/teacher/assessment/addAssessment'>
						<button class="btn btn-pink button-display-right" type="button">
							<spring:message code="add-new-assessment"/>
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
				<th scope="col"><spring:message code="assessment-name"/></th>
				<th scope="col"><spring:message code="class-name"/></th>
				<th scope="col"><spring:message code="expired-date"/></th>
				<th scope="col"><spring:message code="status"/></th>
				<th scope="col"><spring:message code="total-question"/></th>
				<th scope="col"><spring:message code="action"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listAssessment}" var="assessment" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${assessment.assessmentname}</td>
					<td>${assessment.getClassForeign().getClassname()}</td>
					<td>${assessment.expireddate}</td>
					<td>
						<a href="#" onclick="confirmation('/teacher/assessment/editAssessmentStatus/${assessment.assessmentid}', 'update')">
							<button class="btn ${assessment.getStatus() ? 'btn-active' : 'btn-inactive'}">${assessment.getStatus() ? 'Enable' : 'Disable'}</button>
						</a>
					</td>
					<td class="pink-highlight">${assessment.getTotalquestion()}</td>
					<td>
						<a href="/teacher/assessment/editAssessment/${assessment.assessmentid}">
							<img alt="edit" src="<c:url value="../../images/edit.png"/>" class="optionSize" />
						</a> 
						<a href="#" onclick="confirmation('/teacher/assessment/deleteAssessment?assessmentid=<c:out value='${assessment.assessmentid}'/>', 'delete')">
							<img alt="delete" src="<c:url value="../../images/delete.png"/>" class="optionSize" />
						</a>
						<a href="/teacher/questionOfAssessment?assessmentid=<c:out value='${assessment.assessmentid}'/>" class="ml-2">
							<input class="btn btn-pink" type="button" value="View questions">
						</a>
						<a href="/teacher/viewResult?assessmentid=<c:out value='${assessment.assessmentid}'/>" class="ml-2">
							<input class="btn btn-warning" type="button" value="View result">
						</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
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