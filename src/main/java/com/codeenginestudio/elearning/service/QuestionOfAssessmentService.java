package com.codeenginestudio.elearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.dto.QuestionOfAssessmentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface QuestionOfAssessmentService {

	void addQuestionOfAssessment(QuestionOfAssessmentDTO questionOfAssessmentDTO) throws JsonProcessingException;

	void deleteQuestionOfAssessment(Long questionId);

	void editQuestionOfAssessment(QuestionOfAssessmentDTO questionOfAssessmentDTO) throws JsonProcessingException;

	QuestionOfAssessmentDTO getOneQuestionOfAssessment(Long questionId);

	List<QuestionOfAssessmentDTO> getListQuestionOfAssessmentByAssessment(Long assessmentid);

	int generateNumericalOrder(Long assessmentid);

	Float getTotalScoreByAssessment(Long assessmentid);
}
