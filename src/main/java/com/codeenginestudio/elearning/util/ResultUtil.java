package com.codeenginestudio.elearning.util;

import com.codeenginestudio.elearning.dao.entity.ResultEntity;

import com.codeenginestudio.elearning.dto.ResultDTO;
import com.codeenginestudio.elearning.util.general.UtilGeneral;

public class ResultUtil {

	public static ResultDTO parseToDTO(ResultEntity resultEntity) {

		ResultDTO resultDTO = UtilGeneral.modelMapper.map(resultEntity, ResultDTO.class);

		return resultDTO;
	}

	public static ResultEntity parseToEntity(ResultDTO resultDTO) {

		ResultEntity resultEntity = UtilGeneral.modelMapper.map(resultDTO, ResultEntity.class);

		return resultEntity;
	}

}
