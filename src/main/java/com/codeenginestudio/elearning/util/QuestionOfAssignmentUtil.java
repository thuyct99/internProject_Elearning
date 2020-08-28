package com.codeenginestudio.elearning.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.codeenginestudio.elearning.dao.entity.QuestionOfAssessmentEntity;
import com.codeenginestudio.elearning.dto.QuestionOfAssessmentDTO;
import com.codeenginestudio.elearning.util.general.UtilGeneral;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionOfAssignmentUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	public static QuestionOfAssessmentDTO parseToQuestionOfAssignmentDTO(
			QuestionOfAssessmentEntity questionOfAssignmentEntity) {

		QuestionOfAssessmentDTO questionOfAssignmentDTO = UtilGeneral.modelMapper.map(questionOfAssignmentEntity,
				QuestionOfAssessmentDTO.class);

		return questionOfAssignmentDTO;
	}

	public static String parseToJson(List<QuestionOfAssessmentDTO> questionDTOs) throws JsonProcessingException {

		String newStringJSON = mapper.writeValueAsString(questionDTOs);

		return newStringJSON;
	}

	public static List<QuestionOfAssessmentDTO> parseToObject(String jsongString) {

		List<QuestionOfAssessmentDTO> questionDTOs = new ArrayList<>();

		try {

			questionDTOs = mapper.readValue(jsongString,
					mapper.getTypeFactory().constructCollectionType(List.class, QuestionOfAssessmentDTO.class));
		} catch (IOException e) {

			e.printStackTrace();
		}

		return questionDTOs;
	}
}
