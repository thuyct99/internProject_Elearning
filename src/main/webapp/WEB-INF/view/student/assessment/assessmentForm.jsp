<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.codeenginestudio.elearning.constant.QuestionTypeEnum" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<body>
	<div class="container-fluid row">
		<div class="col-sm-2"></div>
		<div class="col-sm-9 border-preview">
			<form:form method="POST" action="${url}" modelAttribute="lessonForm">
				<div class="descriptionAssessment row">
					<div class="title-preview">
						<p class="assignmentTitle">${assessment.getAssessmentname()}</p>	
						<p class="dateTime grey">( ${assessment.getStartdate()} - ${assessment.getExpireddate()} )</p>
					</div>
					<div>
						<img alt="dog" src="https://i.gifer.com/Ybp.gif" class="image-preview">
					</div>
				</div>
				<c:choose>
					<c:when test="${listQuestionOfAssessment.size() > 0}">
						<c:forEach items="${listQuestionOfAssessment}" var="question" varStatus="status">
							<form:input type="hidden" path="resultDTOs[${status.index}].id" value="${resultDTOs[status.index].id}" />
							<form:input type="hidden" path="resultDTOs[${status.index}].assessment.assessmentid" value="${assessment.assessmentid}" />
							<form:input type="hidden" path="resultDTOs[${status.index}].question.questionid" value="${question.questionid}" />
								<div class="questionName mt-5">
									<span>${question.numericalorder}: ${question.content}</span>
								</div>
								<c:choose>
									<c:when test="${question.getQuestionType().getQuestionTypeCode().equals(QuestionTypeEnum.INPUT.getCode())}">
										<div class="form-group row seperate-question ml-2">
											<form:input class="form-control ml col-sm-12" type="text" path="resultDTOs[${status.index}].answerchoice" value="" />
										</div>
									</c:when>
									<c:when test="${question.getQuestionType().getQuestionTypeCode().equals(QuestionTypeEnum.YESNO.getCode())}">
										<div class="form-group row ml-2 seperate-question">
											<table >
												<tr>
													<th>
														<div class="optionItem" data-id="yes_choice_${question.questionid}" >
															<form:radiobutton data-id="yes_choice_${question.questionid}" path="resultDTOs[${status.index}].answerchoice" value="A" class="radio-none"/>
															<img data-id="yes_choice_${question.questionid}" data-question="${question.questionid}" src="<c:url value="../../../images/_yes.png"/>" class="yesnoImage" />
														</div>
													</th>
													<th>
														<div class="optionItem" data-id="no_choice_${question.questionid}" >
															<form:radiobutton data-id="no_choice_${question.questionid}" path="resultDTOs[${status.index}].answerchoice" value="B" class="radio-none"/>
															<img data-id="no_choice_${question.questionid}" data-question="${question.questionid}" src="<c:url value="../../../images/_no.png"/>" class="yesnoImage" />
														</div>
													</th>
												</tr>
											</table>
										</div>
									</c:when>
									<c:otherwise>
										<div class="form-group row ml-2 seperate-question">
											<c:forEach items="${question.options}" var="option">
												<div class="col-sm-12 answer-group">
													<lable class="answer-item">${option.getValue()}
														<form:radiobutton
															path="resultDTOs[${status.index}].answerchoice"
															value="${option.getName()}" />
														<span class="radio-btn"></span>
													</lable>
												</div>
											</c:forEach>
										</div>
								</c:otherwise>
							</c:choose>	
						</c:forEach>
							<div class="row display-inline table-mg-top-8 flex-around">
								<input id="submit-button" type="submit" class="btn btn-pink mr-2 large-btn" value="Submit">
								<a href="/student/assessment">
									<input class="btn btn-warning large-btn" type="button" value="Cancel">
								</a>
							</div>
					</c:when>
					<c:otherwise>
						<h1 class="mt-5"><strong ><spring:message code="no-question"/>${question.correctanswer}</strong></h1>
						<img src="<c:url value="../../../images/no-data.jpg"/>" class="welcomeImg"/>
					</c:otherwise>
				</c:choose>
			</form:form>
		</div>
		<div class="col-sm-2"></div>
	</div>
<script>
	$(document).ready(() => {

		questionForm = document.querySelector("#lessonForm");
		questionForm.addEventListener("click",(e) => {
			var dataId = e.target.getAttribute("data-id");
			var questionId = e.target.getAttribute("data-question");

			if(dataId){

				var currentYesOption = document.querySelector("div[data-id = 'yes_choice_" + questionId + "']");
				var currentNoOption = document.querySelector("div[data-id = 'no_choice_" + questionId + "']");

				if(dataId.indexOf("yes") === 0){

					currentYesOption.classList.add("optionActive");
					currentNoOption.classList.remove("optionActive");
				}else{

					currentNoOption.classList.add("optionActive");
					currentYesOption.classList.remove("optionActive");
				}

				e.target.previousElementSibling.checked = true;
			}
		});

		var radioSelect = document.querySelectorAll('[checked="checked"]');

		radioSelect.forEach((node) => { 

			if(node.matches('.radio-none')){

				if(node.parentNode){

					node.parentNode.classList.add("optionActive");
				}
			}
		});
	});

</script>
</body>
</html>