<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<br><br>
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
			<c:forEach items="${assessmentPage}" var="assessment" varStatus="count">
				<c:if test="${listClassAssigned.contains(assessment.getClassForeign().getClassid())}">
					<c:if test="${assessment.status}">
						<tr>
							<td>${count.index + 1}</td>
							<td>${assessment.assessmentname}</td>
							<td>${assessment.getClassForeign().getClassname()}</td>
							<td>${assessment.startdate}</td>
							<td>${assessment.expireddate}</td>
							<td class="pink-highlight">${assessment.getTotalscore()}</td>
							<td class="pink-highlight">${assessment.getTotalquestion()}</td>
							<td>
								<c:choose>
									<c:when test="${!assessment.isEdit()}">
										<a class="href" href="/student/addSubmitLesson/${assessment.assessmentid }">
											<input class="btn btn-pink" type="button" ${!listAssessmentId.contains(assessment.assessmentid) ? 'disabled' : ''} value="Do Exercise">
										</a>
									</c:when>
									<c:otherwise>
										<a class="href" href="/student/editSubmitLesson/${assessment.assessmentid }">
											<input class="btn btn-warning" type="button" ${!listAssessmentId.contains(assessment.assessmentid) ? 'disabled' : ''} value="Edit Exercise">
										</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:if>
				</c:if>
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

	</script>
</body>
</html>