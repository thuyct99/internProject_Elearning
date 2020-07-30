package com.codeenginestudio.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.dao.AssessmentDAO;
import com.codeenginestudio.elearning.dao.QuestionOfAssessmentDAO;
import com.codeenginestudio.elearning.dao.QuestionTypeDAO;
import com.codeenginestudio.elearning.dao.entity.QuestionOfAssessmentEntity;
import com.codeenginestudio.elearning.dto.OptionDTO;
import com.codeenginestudio.elearning.dto.QuestionOfAssessmentDTO;
import com.codeenginestudio.elearning.service.QuestionOfAssessmentService;
import com.codeenginestudio.elearning.util.OptionUtil;
import com.codeenginestudio.elearning.util.QuestionOfAssignmentUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.data.domain.Sort;

@Service
public class QuestionOfAssessmentServiceImpl implements QuestionOfAssessmentService {

	@Autowired
	private QuestionOfAssessmentDAO questionOfAssessmentDAO;

	@Autowired
	private AssessmentDAO assessmentDAO;

	@Autowired
	private QuestionTypeDAO questionTypeDAO;

	@Override
	public List<QuestionOfAssessmentDTO> getListQuestionOfAssessmentByAssessment(Long assessmentid) {

		List<QuestionOfAssessmentEntity> listQuestionEntities = questionOfAssessmentDAO
				.findAll(Sort.by(Sort.Direction.ASC, "numericalorder"));
		List<QuestionOfAssessmentDTO> listQuestionDTOs = new ArrayList<>();
		QuestionOfAssessmentDTO questionOfAssignmentDTO = new QuestionOfAssessmentDTO();

		for (QuestionOfAssessmentEntity questionOfAssessmentEntity : listQuestionEntities) {

			if (questionOfAssessmentEntity.getAssessment().getAssessmentid() == assessmentid) {

				questionOfAssignmentDTO = QuestionOfAssignmentUtil
						.parseToQuestionOfAssignmentDTO(questionOfAssessmentEntity);
				questionOfAssignmentDTO.setOptions(OptionUtil.parseToObject(questionOfAssessmentEntity.getOptions()));

				listQuestionDTOs.add(questionOfAssignmentDTO);
			}
		}

		return listQuestionDTOs;
	}

	@Override
	public int generateNumericalOrder(Long assessmentid) {

		return getListQuestionOfAssessmentByAssessment(assessmentid).size() + 1;
	}

	@Override
	public void addQuestionOfAssessment(QuestionOfAssessmentDTO questionOfAssessmentDTO)
			throws JsonProcessingException {

		QuestionOfAssessmentEntity questionOfAssignmentEntity = new QuestionOfAssessmentEntity();

		questionOfAssignmentEntity
				.setQuestiontype(questionTypeDAO.getOne(questionOfAssessmentDTO.getQuestionType().getQuestionTypeId()));
		questionOfAssignmentEntity.setNumericalorder(Integer.parseInt(questionOfAssessmentDTO.getNumericalorder()));
		questionOfAssignmentEntity.setContent(questionOfAssessmentDTO.getContent());
		questionOfAssignmentEntity.setScore(questionOfAssessmentDTO.getScore());
		questionOfAssignmentEntity
				.setAssessment(assessmentDAO.getOne(questionOfAssessmentDTO.getAssessment().getAssessmentid()));
		questionOfAssignmentEntity.setCorrectanswer(questionOfAssessmentDTO.getCorrectanswer());
		List<OptionDTO> revisedOption = removeNullOption(questionOfAssessmentDTO.getOptions());
		questionOfAssignmentEntity.setOptions(OptionUtil.parseToJson(revisedOption));

		questionOfAssessmentDAO.saveAndFlush(questionOfAssignmentEntity);
	}

	public List<OptionDTO> removeNullOption(List<OptionDTO> options) {

		List<OptionDTO> newOptions = new ArrayList<>();

		for (OptionDTO optionDTO : options) {
			if (!optionDTO.getOptionValue().equals("")) {
				newOptions.add(optionDTO);
			}
		}

		return newOptions;
	}

	@Override
	public void deleteQuestionOfAssessment(Long questionId) {
		questionOfAssessmentDAO.deleteById(questionId);
	}

	@Override
	public void editQuestionOfAssessment(QuestionOfAssessmentDTO questionOfAssessmentDTO)
			throws JsonProcessingException {

		QuestionOfAssessmentEntity questionOfAssignmentEntity = questionOfAssessmentDAO
				.getOne(questionOfAssessmentDTO.getQuestionid());

		questionOfAssignmentEntity.setNumericalorder(Integer.parseInt(questionOfAssessmentDTO.getNumericalorder()));
		questionOfAssignmentEntity
				.setQuestiontype(questionTypeDAO.getOne(questionOfAssessmentDTO.getQuestionType().getQuestionTypeId()));
		questionOfAssignmentEntity.setContent(questionOfAssessmentDTO.getContent());
		questionOfAssignmentEntity.setScore(questionOfAssessmentDTO.getScore());
		questionOfAssignmentEntity.setCorrectanswer(questionOfAssessmentDTO.getCorrectanswer());
		questionOfAssignmentEntity.setOptions(OptionUtil.parseToJson(questionOfAssessmentDTO.getOptions()));

		questionOfAssessmentDAO.saveAndFlush(questionOfAssignmentEntity);
	}

	@Override
	public QuestionOfAssessmentDTO getOneQuestionOfAssessment(Long questionId) {

		QuestionOfAssessmentEntity questionOfAssignmentEntity = questionOfAssessmentDAO.getOne(questionId);
		QuestionOfAssessmentDTO questionOfAssignmentDTO = QuestionOfAssignmentUtil
				.parseToQuestionOfAssignmentDTO(questionOfAssignmentEntity);
		questionOfAssignmentDTO.setOptions(OptionUtil.parseToObject(questionOfAssignmentEntity.getOptions()));

		return questionOfAssignmentDTO;
	}

	@Override
	public Float getTotalScoreByAssessment(Long assessmentid) {
		List<QuestionOfAssessmentDTO> listQuestions = getListQuestionOfAssessmentByAssessment(assessmentid);
		Float totalScore = (float) 0;
		for (QuestionOfAssessmentDTO question : listQuestions) {
			totalScore += question.getScore();
		}
		return totalScore;
	}

}
