package com.codeenginestudio.elearning.util;

import com.codeenginestudio.elearning.dao.entity.RoleEntity;
import com.codeenginestudio.elearning.dto.RoleDTO;
import com.codeenginestudio.elearning.util.general.UtilGeneral;

public class RoleUtil {

	public static RoleDTO parseToRoleDTO(RoleEntity RoleEntity) {

		RoleDTO RoleDTO = UtilGeneral.modelMapper.map(RoleEntity, RoleDTO.class);

		return RoleDTO;
	}

	public static RoleEntity parseToRoleEntity(RoleDTO RoleDTO) {

		RoleEntity RoleEntity = UtilGeneral.modelMapper.map(RoleDTO, RoleEntity.class);

		return RoleEntity;
	}
}
