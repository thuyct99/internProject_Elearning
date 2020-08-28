<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="list-users-finished-assignment"/></title>
</head>
<body>
	<div class="container-fluid mg-top-2">
		<div class="row display-center">
			<h1 class="name-class">&nbsp; ${assessment.getAssessmentname()}</h1>
		</div>
		
		<div class="row">
			<div class="col-sm-1" ></div>
			<div class="col-sm-6">
				<div class="row mt-2">
					<h5 class="pl-3"><spring:message code="class"/></h5>
					<h5 class="name-class">&nbsp; ${class.getClassname()} ${classNull}</h5>
				</div>
			</div>
			<div class="col-sm-5 justify-right">
				<h6 class="pl-3">
					<spring:message code="start-date-x" arguments="${assessment.getStartdate()}" htmlEscape="false" />
				</h6>
				<h6>
					<spring:message code="expired-date-x" arguments="${assessment.getExpireddate()}" htmlEscape="false" />
				</h6>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-3">
				<a href='/teacher/assessment'>
					<button class="btn btn-warning medium-btn" type="button"><spring:message code="cancel"/></button>
				</a>
			</div>
		</div>
	</div>
	<br><br>
	<div class="container-fluid">
		<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th scope="col"><spring:message code=".NO"/></th>
				<th scope="col"><spring:message code="first-name"/></th>
				<th scope="col"><spring:message code="last-name"/></th>
				<th scope="col"><spring:message code="email"/></th>
				<th scope="col"><spring:message code="gender"/></th>
				<th scope="col"><spring:message code="total-score"/></th>
				<th scope="col"><spring:message code="options"/></th>
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
									<button class="btn btn-pink"><spring:message code="view-detail"/></button>
								</a>
							</c:when>
							<c:otherwise>
								<p><spring:message code="haven't-done-the-assignment-yet"/></p>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
</body>
</html>