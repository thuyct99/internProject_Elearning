package com.codeenginestudio.elearning.util;

import com.codeenginestudio.elearning.dao.entity.ClassEntity;
import com.codeenginestudio.elearning.dto.ClassDTO;
import com.codeenginestudio.elearning.util.general.UtilGeneral;

public class ClassUtil {

	public static ClassDTO parseToDTO(ClassEntity classEntity) {

		ClassDTO classDTO = UtilGeneral.modelMapper.map(classEntity, ClassDTO.class);

		return classDTO;
	}

	public static ClassEntity parseToEntity(ClassDTO classDTO) {

		ClassEntity classEntity = UtilGeneral.modelMapper.map(classDTO, ClassEntity.class);

		return classEntity;
	}

}
