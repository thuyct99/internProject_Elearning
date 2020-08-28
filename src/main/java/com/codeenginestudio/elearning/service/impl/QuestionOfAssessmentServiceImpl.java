package com.codeenginestudio.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.codeenginestudio.elearning.constant.QuestionTypeEnum;
import com.codeenginestudio.elearning.dao.AssessmentDAO;
import com.codeenginestudio.elearning.dao.QuestionOfAssessmentDAO;
import com.codeenginestudio.elearning.dao.QuestionTypeDAO;
import com.codeenginestudio.elearning.dao.entity.QuestionOfAssessmentEntity;
import com.codeenginestudio.elearning.dao.entity.QuestionTypeEntity;
import com.codeenginestudio.elearning.dto.OptionDTO;
import com.codeenginestudio.elearning.dto.QuestionOfAssessmentDTO;
import com.codeenginestudio.elearning.service.QuestionOfAssessmentService;
import com.codeenginestudio.elearning.service.ResultService;
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

	@Autowired
	private ResultService resultService;

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

				if (questionOfAssessmentEntity.getQuestiontype().getQuestionTypeId() != 3) {

					questionOfAssignmentDTO
							.setOptions(OptionUtil.parseToObject(questionOfAssessmentEntity.getOptions()));
				}

				listQuestionDTOs.add(questionOfAssignmentDTO);
			}
		}

		return listQuestionDTOs;
	}

	@Override
	public void addQuestionOfAssessment(QuestionOfAssessmentDTO questionOfAssessmentDTO)
			throws JsonProcessingException {

		QuestionOfAssessmentEntity questionOfAssignmentEntity = new QuestionOfAssessmentEntity();
		QuestionTypeEntity questionTypeEntity = questionTypeDAO
				.getOne(questionOfAssessmentDTO.getQuestionType().getQuestionTypeId());

		questionOfAssignmentEntity.setQuestiontype(questionTypeEntity);
		questionOfAssignmentEntity.setNumericalorder(Integer.parseInt(questionOfAssessmentDTO.getNumericalorder()));
		questionOfAssignmentEntity.setContent(questionOfAssessmentDTO.getContent());
		questionOfAssignmentEntity.setScore(questionOfAssessmentDTO.getScore());
		questionOfAssignmentEntity
				.setAssessment(assessmentDAO.getOne(questionOfAssessmentDTO.getAssessment().getAssessmentid()));
		questionOfAssignmentEntity.setCorrectanswer(questionOfAssessmentDTO.getCorrectanswer());

		if (!questionTypeEntity.getQuestionTypeCode().equals(QuestionTypeEnum.INPUT.getCode())) {

			questionOfAssignmentEntity.setOptions(_serializeOptions(questionOfAssessmentDTO.getOptions()));
		}

		questionOfAssessmentDAO.saveAndFlush(questionOfAssignmentEntity);
	}

	@Override
	public void deleteQuestionOfAssessment(Long questionId) {

		resultService.deleteResultByQuestionId(questionId);
		questionOfAssessmentDAO.deleteById(questionId);
	}

	@Override
	public void editQuestionOfAssessment(QuestionOfAssessmentDTO questionOfAssessmentDTO)
			throws JsonProcessingException {

		QuestionOfAssessmentEntity questionOfAssignmentEntity = questionOfAssessmentDAO
				.getOne(questionOfAssessmentDTO.getQuestionid());
		QuestionTypeEntity questionTypeEntity = questionTypeDAO
				.getOne(questionOfAssessmentDTO.getQuestionType().getQuestionTypeId());

		questionOfAssignmentEntity.setNumericalorder(Integer.parseInt(questionOfAssessmentDTO.getNumericalorder()));
		questionOfAssignmentEntity.setQuestiontype(questionTypeEntity);
		questionOfAssignmentEntity.setContent(questionOfAssessmentDTO.getContent());
		questionOfAssignmentEntity.setScore(questionOfAssessmentDTO.getScore());
		questionOfAssignmentEntity.setCorrectanswer(questionOfAssessmentDTO.getCorrectanswer());

		if (!questionTypeEntity.getQuestionTypeCode().equals(QuestionTypeEnum.INPUT.getCode())) {

			questionOfAssignmentEntity.setOptions(_serializeOptions(questionOfAssessmentDTO.getOptions()));
		}

		questionOfAssessmentDAO.saveAndFlush(questionOfAssignmentEntity);
	}

	@Override
	public QuestionOfAssessmentDTO getOneQuestionOfAssessment(Long questionId) {

		QuestionOfAssessmentEntity questionOfAssignmentEntity = questionOfAssessmentDAO.getOne(questionId);
		QuestionOfAssessmentDTO questionOfAssignmentDTO = QuestionOfAssignmentUtil
				.parseToQuestionOfAssignmentDTO(questionOfAssignmentEntity);

		if (!questionOfAssignmentEntity.getQuestiontype().getQuestionTypeCode()
				.equals(QuestionTypeEnum.INPUT.getCode())) {
			questionOfAssignmentDTO.setOptions(OptionUtil.parseToObject(questionOfAssignmentEntity.getOptions()));
		}

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

	@Override
	public void deleteQuestionsByAssessmentId(Long assessmentid) {

		List<QuestionOfAssessmentDTO> listQuestions = getListQuestionOfAssessmentByAssessment(assessmentid);

		for (QuestionOfAssessmentDTO question : listQuestions) {

			questionOfAssessmentDAO.deleteById(question.getQuestionid());
		}
	}

	private String _serializeOptions(List<OptionDTO> options) throws JsonProcessingException {

		List<OptionDTO> listOptionsAfterCheckEmpty = _removeEmptyOption(options);

		return OptionUtil.parseToJson(listOptionsAfterCheckEmpty);
	}

	private List<OptionDTO> _removeEmptyOption(List<OptionDTO> options) {

		List<OptionDTO> result = new ArrayList<>();

		if(options != null) {
			for (OptionDTO optionDTO : options) {

				if (optionDTO != null && !StringUtils.isEmpty(optionDTO.getValue())) {
					result.add(optionDTO);
				}
			}
		}

		return result;
	}
}