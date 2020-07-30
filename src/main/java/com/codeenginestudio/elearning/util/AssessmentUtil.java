package com.codeenginestudio.elearning.util;

import com.codeenginestudio.elearning.dao.entity.AssessmentEntity;
import com.codeenginestudio.elearning.dto.AssessmentDTO;
import com.codeenginestudio.elearning.util.general.UtilGeneral;

public class AssessmentUtil {

	public static AssessmentDTO parseToDTO(AssessmentEntity assessmentEntity) {

		AssessmentDTO assessmentDTO = UtilGeneral.modelMapper.map(assessmentEntity, AssessmentDTO.class);

		return assessmentDTO;
	}

	public static AssessmentEntity parseToEntity(AssessmentDTO assessmentDTO) {

		AssessmentEntity assessmentEntity = UtilGeneral.modelMapper.map(assessmentDTO, AssessmentEntity.class);
		return assessmentEntity;
	}
}
