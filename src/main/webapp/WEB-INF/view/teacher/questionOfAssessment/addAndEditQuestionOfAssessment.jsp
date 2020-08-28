<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="com.codeenginestudio.elearning.constant.QuestionTypeEnum" %>

<div class="container-fluid center mg-top-2">
	<form action="<%=request.getContextPath()%>${url}" method="post" class="col-sm-8 form-general" id="formQuestion">

		<h1 class="form-title mb-5">
			<spring:message code="new-question"/>
		</h1>

		<input type="hidden" name="questionid" value="${questionInf.questionid}"/>

		<div class="form-group row">
			<label for="numericalorder" class="col-sm-3 col-form-label">
				<spring:message code="numerical-order"/>
			</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" name="numericalorder"
					value="${questionInf.numericalorder}"
					placeholder="<spring:message code="please-enter-numerical-order-here"/>">
				<c:if test="${error.getErrNumericalOrder() != null}">
					<p class="err errContent"><spring:message code="${error.getErrNumericalOrder()}"/></p>
				</c:if>
			</div>
		</div>

		<div class="form-group row">
			<label for="score" class="col-sm-3 col-form-label">
				<spring:message code="score"/>
			</label>
			<div class="col-sm-8">
				<select name="score" class="form-control">
					<c:forEach begin="1" end="10" step="1" varStatus="num">
						<option value="${num.index}" ${questionInf.getScore() == num.index ? 'selected' : ''}>${num.index}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group row">
			<label for="content" class="col-sm-3 col-form-label">
				<spring:message code="content"/>
			</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" name="content"
					value="${questionInf.content}"
					placeholder="<spring:message code="please-enter-question's-content-here"/>">
				<c:if test="${error.getErrContent() != null}">
					<p class="err errContent"><spring:message code="${error.getErrContent()}"/></p>
				</c:if>
			</div>
		</div>

		<div class="form-group row">
			<label for="questiontypeid" class="col-sm-3 col-form-label">
				<spring:message code="question-type"/>
			</label>
			<div class="col-sm-8">
				<select name="questionType.questionTypeId" id="questionType" class="form-control">
					<c:forEach items="${listQuestionType}" var="questionType">
						<option value="${questionType.getQuestionTypeId()}" questionTypeCode="${questionType.getQuestionTypeCode()}" 
							${questionInf.getQuestionType().getQuestionTypeId() == questionType.getQuestionTypeId() ? 'selected' : ""}>
							${questionType.getQuestionTypeName()}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group row block-question-type" id="input-type-block">
			<label for="questiontypeid" class="col-sm-3 col-form-label">
				<spring:message code="input-answer"/>
			</label>
			<div class="col-sm-8">
				<input class="form-control" type="text" name="correctanswer"> 
			</div>
		</div>

		<div class="form-group row block-question-type" id="yesno-type-block">
			<label for="options" class="col-sm-3 col-form-label">
				<spring:message code="options"/>
			</label>
			<div class="col-sm-8">
					<div class="optionItem">
						<input type="radio" name="correctanswer" value="A"
							class="radioOption" />
						<label for="A" class="col-sm-1 col-form-label">A: </label> 
						<input type="hidden" name="options[0].name" value="A">
						<input type="text" class="form-control" name="options[0].value" 
							value="Yes">
					</div>
					<div class="optionItem">
						<input type="radio" name="correctanswer" value="B"
							class="radioOption" />
						<label for="B" class="col-sm-1 col-form-label">B: </label> 
						<input type="hidden" name="options[1].name" value="B">
						<input type="text" class="form-control" name="options[1].value" value="No">
					</div>
			</div>
		</div>
		<div class="form-group row block-question-type" id="multiple-type-block">
			<label for="options" class="col-sm-3 col-form-label">
				<spring:message code="options"/>
			</label>

			<div class="col-sm-8 options-container">
				<c:if test="${questionInf.getOptions() != null && questionInf.getOptions().size() > 0}">
					<c:forEach items="${questionInf.getOptions()}" var="option" varStatus="num" begin="0" step="1">
						<div class='optionItem'>

							<input type="radio" class="radioOption" name="correctanswer" value="${option.getName()}">

							<label for="${option.getName()}" class="col-sm-1 col-form-label">
								${option.getName()}:
							</label>

							<input type="text" class="form-control" name="options[${num.index}].value" value="${option.getValue()}">

							<span class='remove btn btn-primary'>-</span>

							<input type="hidden" name="options[${num.index}].name" value="${option.getName()}"> 
						</div>
					</c:forEach>
				</c:if>
			</div>
			<div class="col-sm-11 addOptionDiv">
				<span class='addOption btn btn-primary'><spring:message code="add-option"/></span>
			</div>
		</div>
		<div class="form-group row flex-around ">
			<input type="hidden" name="assessment.assessmentid" value="${assessmentid}">

			<button id="submitBtn" type="submit" class="large-btn btn btn-pink"><spring:message code="save"/></button>

			<a href="/teacher/questionOfAssessment?assessmentid=${assessmentid}"><button
				type="button" class="large-btn btn btn-warning mg-right-6 "><spring:message code="cancel"/></button></a>
		</div>
	</form>
</div>

<script type="text/javascript">
	var alphabet = new Array("A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J" , "K", "L",
			"M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z");
	var nextindex = 0;

	<c:if test="${questionInf.getOptions() != null && questionInf.getOptions().size() > 0}">
		nextindex = ${questionInf.getOptions().size()};
	</c:if>

	$(document).ready(function() {

		$(".addOption").click(function() {
			addNewOption();

			registerEventClickRemoveOption();
		});

		// Register event change question type
		registerEventChangeQuestionType();

		// Trigger event on change when start up
		triggerEventChangeOnStartup();

		// Fill correct answer
		fillCorrectAnswer('${questionInf.getCorrectanswer()}');

		// Submit form, prevent default action
		submitForm();
	});

	function fillCorrectAnswer(correctAnswer) {
		var questionTypeCode = $('option:selected', $("#questionType")).attr("questionTypeCode");

		if('${QuestionTypeEnum.MULTIPLE.getCode()}' == questionTypeCode) {
			$("#multiple-type-block").find("input:radio[value='" + correctAnswer + "']").prop('checked',true);
		} else if('${QuestionTypeEnum.YESNO.getCode()}' == questionTypeCode) {
			$("#yesno-type-block").find("input:radio[value='" + correctAnswer + "']").prop('checked',true);
		} else if('${QuestionTypeEnum.INPUT.getCode()}' == questionTypeCode) {
			$("#input-type-block").find("input[name='correctanswer']").val(correctAnswer);
		}
	}

	function addNewOption() {
		var $optionRow = $("<div class='optionItem'></div>");
		// Add radio button
		$optionRow.append("<input type='radio' class='radioOption' name='correctanswer' value='"+ alphabet[nextindex] +"'>");
		// Add label
		$optionRow.append("<label for='"+ alphabet[nextindex] +"' class='col-sm-1 col-form-label'>"+ alphabet[nextindex] +": </label>");
		// Add input type
		$optionRow.append("<input type='text' class='form-control' name='options["+ nextindex +"].value' value=''>");
		// Add remove button
		$optionRow.append("&nbsp;<span class='remove btn btn-primary'>-</span>");
		// Add hidden field
		$optionRow.append("<input type='hidden' name='options["+ nextindex +"].name' value='"+ alphabet[nextindex] +"'>");

		$(".options-container").append($optionRow);
		nextindex++;
	}

	function triggerEventChangeOnStartup() {
		$('#questionType').trigger('change');
	}

	function registerEventClickRemoveOption() {
		$('.options-container').on('click','.remove', function() {
			$(this).closest(".optionItem").remove();
		});
	}

	function registerEventChangeQuestionType() {
		$('#questionType').change(function(e) {
			$(".block-question-type").addClass("hide_block");

			var questionTypeCode = $('option:selected', this).attr("questionTypeCode");
			if('${QuestionTypeEnum.MULTIPLE.getCode()}' == questionTypeCode) {
				$("#multiple-type-block").removeClass("hide_block");
			} else if('${QuestionTypeEnum.YESNO.getCode()}' == questionTypeCode) {
				$("#yesno-type-block").removeClass("hide_block");
			} else if('${QuestionTypeEnum.INPUT.getCode()}' == questionTypeCode) {
				$("#input-type-block").removeClass("hide_block");
			}
		});
	}

	function submitForm() {
		$("#submitBtn").click(function(e) {
			e.preventDefault();
			$(".hide_block").remove();
			$("#formQuestion").submit();
		});
	}
</script>