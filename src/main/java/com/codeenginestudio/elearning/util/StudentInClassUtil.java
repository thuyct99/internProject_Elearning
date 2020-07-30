package com.codeenginestudio.elearning.util;

import com.codeenginestudio.elearning.dao.entity.StudentInClassEntity;
import com.codeenginestudio.elearning.dto.StudentInClassDTO;
import com.codeenginestudio.elearning.util.general.UtilGeneral;

public class StudentInClassUtil {

	public static StudentInClassDTO parseToDTO(StudentInClassEntity studentInClassEntity) {

		StudentInClassDTO studentInClassDTO = UtilGeneral.modelMapper.map(studentInClassEntity,
				StudentInClassDTO.class);

		return studentInClassDTO;
	}

	public static StudentInClassEntity parseToEntity(StudentInClassDTO studentInClassDTO) {

		StudentInClassEntity studentInClassEntity = UtilGeneral.modelMapper.map(studentInClassDTO,
				StudentInClassEntity.class);

		return studentInClassEntity;
	}
}
