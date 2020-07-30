<%@page import="java.util.List"%>
<%@page import="com.codeenginestudio.elearning.dto.ResultDTO"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%
	String wrongAnswerIcon = "https://p1.hiclipart.com/preview/972/429/230/windows-live-for-xp-red-x-illustration-png-clipart.jpg";
	String correctAnswerIcon = "https://img.pngio.com/tilde-png-and-tilde-transparent-clipart-free-download-tilde-png-260_240.jpg";
%>
<meta charset="UTF-8">
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3"></div>

			<div class="col-sm-6" id="borderTest">
				<div class="descriptionAssessment">
					<p class="assignmentTitle">${assessment.getAssessmentname()}</p>
					<p class="dateTime">(${assessment.getStartdate()} -
						${assessment.getExpireddate()})</p>
				</div>
				<br>
				<c:forEach items="${listQuestionOfAssessment}" var="question"
					varStatus="status">

					<div class="questionName">
						<c:if test="${!preview}">
								<c:choose>
									<c:when test="${question.correctanswer eq listResult[status.index].answerchoice}">
										<img alt="correct" src="<%=correctAnswerIcon%>"
											class="optionSize" />
									</c:when>
									<c:otherwise>
										<img alt="incorrect" src="<%=wrongAnswerIcon%>"
											class="optionSize" />
									</c:otherwise>
								</c:choose>
							</c:if>
						<span> Question ${question.numericalorder}: ${question.content}</span>
					</div>
					<div class="form-group row">
						<div class="col-sm-6">
							<c:forEach items="${question.options}" var="option">
								<c:set var="check"
									value='${option.getName() eq listResult[status.index].answerchoice ? "checked" : ""}' />

								<div class="row">
									<div class="col-sm-1">
										<label for="${option.getName()}" class="col-sm-1 col-form-label">${option.getName()}: </label>
									</div>
									<div class="col-sm-1">
										<input type="radio" name="${question.getQuestionid()}"
											value="${option.getName()}"
											class="inputRadioOption"
											${check}
											disabled="disabled">
									</div>
									<div class="col-sm-6">
										<label class="optionName">${option.getOptionValue()}</label>
									</div>
								</div>

							</c:forEach>
								<c:if test="${!preview}">
									<c:if test="${question.correctanswer != listResult[status.index].answerchoice}">
										<div class="correctAnswer">
											<strong id="message">Correct answer is ${question.correctanswer}</strong>
										</div>
									</c:if>
								</c:if>
						</div>
					</div>
				</c:forEach>
				<a href="${urlBack}"><input class="btn btn-dark" type="button" value="Back"></a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {

			$("#submit-button").click(function() {
				if (confirm("Are you sure to submit this assessment?")) {
					return true;
				} else {
					return false;
				}
			});

			if ($("#messageError").html() != "") {
				$(".alert").css("display", "block");
				setTimeout(function() {
					$(".alert").css("display", "none");
				}, 5000);
			}
		});
	</script>

</body>
</html>
