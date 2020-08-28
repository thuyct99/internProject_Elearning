<%@page import="java.util.List"%>
<%@page import="com.codeenginestudio.elearning.dto.ResultDTO"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.codeenginestudio.elearning.constant.QuestionTypeEnum" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<body>
	<div class="container-fluid row">
		<div class="col-sm-2"></div>
			<div class="col-sm-9 border-preview" id="borderTest">
				<div class="descriptionAssessment row" >
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
							<div class="questionName mt-5">
								<span>Question ${question.numericalorder}: ${question.content}</span>
							</div>
							<c:choose>
								<c:when test="${question.getQuestionType().getQuestionTypeCode().equals(QuestionTypeEnum.INPUT.getCode())}">
									<div class="form-group row seperate-question ml-2">
										<input type="text" class="form-control ml col-sm-12" name="${question.getQuestionid()}" value="${question.correctanswer}" disabled="disabled">
									</div>
								</c:when>
								<c:when test="${question.getQuestionType().getQuestionTypeCode().equals(QuestionTypeEnum.YESNO.getCode())}">
									<div data-track="hel" class="form-group row ml-2 seperate-question">
										<table>
											<tr>
												<th>
													<div class="optionItem ${question.correctanswer == 'A' ? 'checkCorrect' : ''}">
														<img data-id="yes_choice_${question.questionid}" data-question="${question.questionid}" src="<c:url value="/images/_yes.png"/>" class="yesnoImage" />
													</div>
												</th>
												<th>
													<div class="optionItem ${question.correctanswer == 'B' ? 'checkCorrect' : ''}">
														<img data-id="yes_choice_${question.questionid}" data-question="${question.questionid}" src="<c:url value="/images/_no.png"/>" class="yesnoImage" />
													</div>
												</th>
											</tr>
										</table>
									</div>
								</c:when>
								<c:otherwise>
									<div class="form-group row ml-2 seperate-question">
										<c:forEach items="${question.options}" var="option">
											<c:set var="check" value='${option.getName() eq question.correctanswer ? "checked" : ""}' />
											<div class="col-sm-12 answer-group">
												<lable class="answer-item">${option.getValue()}
													<input value="${option.getName()}" ${check} type="radio" disabled />
													<span class="radio-btn"></span>
												</lable>
											</div>
										</c:forEach>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<div class="table-mg-top-8">
							<a href="/teacher/questionOfAssessment?assessmentid=${assessment.getAssessmentid()}"><input class="btn btn-warning large-btn" type="button" value="Back"></a>
						</div>
					</c:when>
					<c:otherwise>
						<h1><strong ><spring:message code="no-question"/></strong></h1>
						<img src="<c:url value="../../../images/no-data.jpg"/>" class="welcomeImg"/>
					</c:otherwise>
				</c:choose>
			</div>
		<div class="col-sm-1"></div>
	</div>
</body>
</html>
