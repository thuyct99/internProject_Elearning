package com.codeenginestudio.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.dao.QuestionTypeDAO;
import com.codeenginestudio.elearning.dao.entity.QuestionTypeEntity;
import com.codeenginestudio.elearning.dto.QuestionTypeDTO;
import com.codeenginestudio.elearning.service.QuestionTypeService;
import com.codeenginestudio.elearning.util.QuestionTypeUtil;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {

	@Autowired
	QuestionTypeDAO questionTypeDAO;

	@Override
	public List<QuestionTypeDTO> getListQuestionType() {
		List<QuestionTypeEntity> listQuestionTypeEntity = questionTypeDAO.findAll();
		List<QuestionTypeDTO> listQuestionTypeDTO = new ArrayList<>();
		for (QuestionTypeEntity questionTypeEntity : listQuestionTypeEntity) {
			listQuestionTypeDTO.add(QuestionTypeUtil.parseToQuestionTypeDTO(questionTypeEntity));
		}

		return listQuestionTypeDTO;
	}

}
