<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container-fluid row">
		<div class="alert success" style="display: none">
			<span class="closebtn"
				onclick="this.parentElement.style.display='none';">&times;</span>
			<strong id="message">${messageSuccess}</strong>
		</div>
	</div>
	<br>
	<br>
	<div class="container-fluid">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th scope="col"><spring:message code=".NO"/></th>
					<th scope="col"><spring:message code="assessment-name"/></th>
					<th scope="col"><spring:message code="class-name"/></th>
					<th scope="col"><spring:message code="start-date"/></th>
					<th scope="col"><spring:message code="expired-date"/></th>
					<th scope="col"><spring:message code="total-score"/></th>
					<th scope="col"><spring:message code="total-question"/></th>
					<th scope="col"><spring:message code="action"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listAssessment}" var="assessment" varStatus="count">
					<c:if test="${listClassAssigned.contains(assessment.getClassForeign().getClassid()) }">
						<tr>
							<td>${count.index + 1}</td>
							<td>${assessment.assessmentname}</td>
							<td>${assessment.getClassForeign().getClassname()}</td>
							<td>${assessment.startdate}</td>
							<td>${assessment.expireddate}</td>
							<td class="pink-highlight">${assessment.getUserscore()} /
								${assessment.getTotalscore()}</td>
							<td class="pink-highlight">${assessment.getTotalquestion()}</td>
							<td>
								<c:choose>
									<c:when test="${listIdOfAssessment.contains(assessment.assessmentid)}">
										<a class="href" href="/student/assessment/history/viewResult/${assessment.assessmentid }">
											<input class="btn btn-pink" type="button" value="View Result">
										</a>
									</c:when>
									<c:otherwise>
										<span><spring:message code="have-not-submit"/></span>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {

			if ($("#message").html() != "") {
				$(".alert").css("display", "block");
				setTimeout(function() {
					$(".alert").css("display", "none");
				}, 1000);
			}
		});
	</script>
</body>
</html>