package com.codeenginestudio.elearning.validation;

import java.util.List;
import java.util.Map;

import com.codeenginestudio.elearning.dto.QuestionOfAssessmentDTO;
import com.codeenginestudio.elearning.service.QuestionOfAssessmentService;

public class SubmitAssessmentValidation {

	public static String errQuestion = "";

	public static Boolean checkQuantityQuestionSubmit(Map<String, String> allParams, Long assessmentid,
			QuestionOfAssessmentService questionOfAssessmentService) {
		List<QuestionOfAssessmentDTO> listQuestion = questionOfAssessmentService
				.getListQuestionOfAssessmentByAssessment(assessmentid);
		if (allParams.size() < listQuestion.size()) {
			errQuestion = "Please submit enough answers!";
			return false;
		}
		return true;
	}

}
