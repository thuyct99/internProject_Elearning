package com.codeenginestudio.elearning.util;

import com.codeenginestudio.elearning.dao.entity.QuestionTypeEntity;
import com.codeenginestudio.elearning.dto.QuestionTypeDTO;
import com.codeenginestudio.elearning.util.general.UtilGeneral;

public class QuestionTypeUtil {

	public static QuestionTypeDTO parseToQuestionTypeDTO(QuestionTypeEntity questionTypeEntity) {

		QuestionTypeDTO questionTypeDTO = UtilGeneral.modelMapper.map(questionTypeEntity, QuestionTypeDTO.class);
		return questionTypeDTO;
	}

	public static QuestionTypeEntity parseToQuestionTypeEntity(QuestionTypeDTO questionTypeDTO) {

		QuestionTypeEntity questionTypeEntity = UtilGeneral.modelMapper.map(questionTypeDTO, QuestionTypeEntity.class);
		return questionTypeEntity;
	}
}
