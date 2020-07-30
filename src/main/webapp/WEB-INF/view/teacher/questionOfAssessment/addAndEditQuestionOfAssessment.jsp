<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container-fluid center">

		<div class="col-sm-3"></div>

		<form action="<%=request.getContextPath()%>${url}" method="post" class="col-sm-6 form-general">
			<h1 class="form-title">New Question</h1>

			<input type="hidden" name="questionid"
				value="${questionInf.questionid}" />

			<div class="form-group row">
				
				<label for="numericalorder" class="col-sm-3 col-form-label">Numerical Order</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="numericalorder"
						value="${questionInf.numericalorder}">
					<p class="err errContent">${error.getErrNumericalOrder()}</p>
				</div>
			</div>
			<div class="form-group row">
				<label for="score" class="col-sm-3 col-form-label">Score</label>
				<div class="col-sm-8">
					<select name="score" class="form-control">
						<c:forEach begin="1" end="10" step="1" varStatus="num">
							<option value="${num.index}" ${questionInf.getScore() == i ? 'selected' : ''}>${num.index}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group row">
				<label for="content" class="col-sm-3 col-form-label">Content</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="content"
						value="${questionInf.content}">
					<p class="err errContent">${error.getErrContent()}</p>
				</div>
			</div>

			<div class="form-group row">
				<label for="questiontypeid" class="col-sm-3 col-form-label">Question
					Type</label>
				<div class="col-sm-8">
					<select name="questionType.questionTypeId" class="form-control">
						<c:forEach items="${listQuestionType}" var="questionType">
							<option value="${questionType.questionTypeId}" 
								${questionInf.getQuestionType().getQuestionTypeId() == questionType.questionTypeId ? 'selected' : ""}>
								${questionType.questionTypeName}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group row">
				<label for="options" class="col-sm-3 col-form-label">Options </label>

				<div class="col-sm-8">
					<c:forEach begin="0" end="3" step="1" varStatus="num">
						<c:choose>
							<c:when test="${num.index == 0}">
								<c:set var="name" value='A' />
							</c:when>
							<c:when test="${num.index == 1}">
								<c:set var="name" value='B' />
							</c:when>
							<c:when test="${num.index == 2}">
								<c:set var="name" value='C' />
							</c:when>
							<c:otherwise>
								<c:set var="name" value='D' />
							</c:otherwise>
						</c:choose>
						<div class="optionItem">

								<input type="radio" name="correctanswer" value="${name}" 
									class="radioOption" ${questionInf.getCorrectanswer() == name ? 'checked' : '' } ${num.index == 0 ? 'checked' : ''}>

								<label for="${name}" class="col-sm-1 col-form-label">${name}: </label> 
								<input type="hidden" name="options[${num.index}].name" value="${name}"> 
								<input type="text" class="form-control" name="options[${num.index}].optionValue" 
									value="${questionInf.getOptions().size() > num.index ? questionInf.getOptions().get(num.index).getOptionValue() : ''}">
						</div>
					</c:forEach>
				</div>
			</div>

			<input type="hidden" name="assessment.assessmentid"
				value="${assessmentid}">

			<button type="submit" class="btn btn-dark">Save</button>

			<a href="/teacher/questionOfAssessment?assessmentid=${assessmentid}"><button
					type="button" class="btn btn-dark">Cancel</button></a>
		</form>

		<div class="col-sm-3"></div>
	</div>

