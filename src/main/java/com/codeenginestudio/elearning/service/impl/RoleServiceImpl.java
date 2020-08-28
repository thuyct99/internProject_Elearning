package com.codeenginestudio.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.dao.RoleDAO;
import com.codeenginestudio.elearning.dao.entity.RoleEntity;
import com.codeenginestudio.elearning.dto.RoleDTO;
import com.codeenginestudio.elearning.service.RoleService;
import com.codeenginestudio.elearning.util.RoleUtil;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;

	@Override
	public List<RoleDTO> getListRole() {

		List<RoleEntity> listRoleEntity = roleDAO.findAll();
		List<RoleDTO> listRoleDTO = new ArrayList<>();

		for (RoleEntity roleEntity : listRoleEntity) {

			listRoleDTO.add(RoleUtil.parseToRoleDTO(roleEntity));
		}

		return listRoleDTO;
	}

	@Override
	public Long getRoleIdByRolename(String rolename) {

		RoleDTO roleDTO = RoleUtil.parseToRoleDTO(roleDAO.getRoleIdByRolename(rolename));

		return roleDTO.getRoleid();
	}

}
