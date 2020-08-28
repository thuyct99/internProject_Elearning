package com.codeenginestudio.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.constant.Constant;
import com.codeenginestudio.elearning.constant.RoleConstant;
import com.codeenginestudio.elearning.dao.ClassDAO;
import com.codeenginestudio.elearning.dao.RoleDAO;
import com.codeenginestudio.elearning.dao.UserDAO;
import com.codeenginestudio.elearning.dao.entity.ClassEntity;
import com.codeenginestudio.elearning.dao.entity.UserEntity;
import com.codeenginestudio.elearning.dto.UserDTO;
import com.codeenginestudio.elearning.service.ClassService;
import com.codeenginestudio.elearning.service.StudentInClassService;
import com.codeenginestudio.elearning.service.UserService;
import com.codeenginestudio.elearning.util.CommonUtil;
import com.codeenginestudio.elearning.util.PasswordUtil;
import com.codeenginestudio.elearning.util.UserUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private ClassDAO classDAO;

	@Autowired
	private UserService userService;

	@Autowired
	private StudentInClassService studentInClassService;

	@Autowired
	private ClassService classService;

	@Override
	public Page<UserDTO> getUserPage(Integer page) {

		Pageable pageable = PageRequest.of(CommonUtil.getInt(page), Constant.ITEM_PER_PAGE);
		Page<UserEntity> listUserEntity = userDAO.findAll(pageable);

		return listUserEntity.map(x -> (UserUtil.parseToUserDTO(x)));
	}

	@Override
	public void addUser(UserDTO user) {

		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(user.getUsername());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(PasswordUtil.encode(user.getPassword()));
		userEntity.setFirstname(user.getFirstname());
		userEntity.setLastname(user.getLastname());
		userEntity.setGender(user.getGender());
		userEntity.setRole(roleDAO.getRoleNameByRoleid(user.getRole().getRoleid()));
		userEntity.setAvartar(user.getAvartar());
		userEntity.setEnabled(user.getEnabled());

		userDAO.saveAndFlush(userEntity);
	}

	@Override
	public void deleteUser(long userId) {

		UserDTO userDTO = userService.getUserByUserId(userId);

		if (userDTO.getRole().getRoleid() == 2) {

			classService.deleteClassByTeacherId(userId);

		} else if (userDTO.getRole().getRoleid() == 3) {

			studentInClassService.deleteStudentInClass(userId);

		}

		userDAO.deleteById(userId);
	}

	@Override
	public void editUser(UserDTO user) {

		UserEntity userEntity = userDAO.getOne(user.getUserid());
		userEntity.setUsername(user.getUsername());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(PasswordUtil.encode(user.getPassword()));
		userEntity.setFirstname(user.getFirstname());
		userEntity.setLastname(user.getLastname());
		userEntity.setGender(user.getGender());
		userEntity.setRole(roleDAO.getRoleNameByRoleid(user.getRole().getRoleid()));
		userEntity.setAvartar(user.getAvartar());
		userEntity.setEnabled(user.getEnabled());

		userDAO.saveAndFlush(userEntity);
	}

	@Override
	public void editUserStatus(long userId) {

		UserEntity userEntity = userDAO.getOne(userId);
		userEntity.setEnabled(!userEntity.isEnabled());

		if (userEntity.isEnabled() == false) {

			List<ClassEntity> listClasses = classDAO.findAll();
			List<Long> listUsers = getUserIdByRoleAndStatus(RoleConstant.TEACHER, true);

			for (ClassEntity classEntity : listClasses) {

				if (!listUsers.contains(classEntity.getUser().getUserid())) {

					classEntity.setStatus(false);
				}
			}
		}

		userDAO.saveAndFlush(userEntity);
	}

	@Override
	public List<UserDTO> findByUsername(String username) {

		List<UserDTO> listUserDTOs = new ArrayList<>();

		for (UserEntity userEntity : userDAO.findByUsername(username)) {

			listUserDTOs.add(UserUtil.parseToUserDTO(userEntity));
		}

		return listUserDTOs;
	}

	@Override
	public List<UserDTO> findByEmail(String email) {

		List<UserDTO> listUserDTOs = new ArrayList<>();

		for (UserEntity userEntity : userDAO.findByEmail(email)) {

			listUserDTOs.add(UserUtil.parseToUserDTO(userEntity));
		}

		return listUserDTOs;
	}

	@Override
	public UserDTO getUserByUserId(Long userid) {

		return UserUtil.parseToUserDTO(userDAO.getOne(userid));
	}

	@Override
	public UserDTO getUserByUsername(String username) {

		return UserUtil.parseToUserDTO(userDAO.getUserByUsername(username));
	}

	@Override
	public List<UserDTO> getUserByRoleAndStatus(String roleName, Boolean status) {

		List<UserEntity> listUserEntity = userDAO.findAll();
		List<UserDTO> listUserDTO = new ArrayList<>();

		for (UserEntity userEntity : listUserEntity) {

			if (userEntity.getRole().getRolename().equals(roleName) && userEntity.isEnabled().equals(status)) {
				listUserDTO.add(UserUtil.parseToUserDTO(userEntity));
			}
		}

		return listUserDTO;
	}

	@Override
	public List<Long> getUserIdByRoleAndStatus(String roleName, boolean status) {

		List<UserEntity> listUserEntity = userDAO.findAll();
		List<Long> listUserId = new ArrayList<>();

		for (UserEntity userEntity : listUserEntity) {

			if (userEntity.getRole().getRolename().equals(roleName) && userEntity.isEnabled().equals(status)) {
				listUserId.add(userEntity.getUserid());
			}
		}

		return listUserId;
	}
}