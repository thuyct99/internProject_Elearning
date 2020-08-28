package com.codeenginestudio.elearning.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.codeenginestudio.elearning.dto.UserDTO;

public interface UserService {

	void addUser(UserDTO user);

	void deleteUser(long userId);

	void editUser(UserDTO user);

	void editUserStatus(long userId);

	UserDTO getUserByUsername(String username);

	UserDTO getUserByUserId(Long userid);

	List<Long> getUserIdByRoleAndStatus(String teacher, boolean status);

	List<UserDTO> findByUsername(String username);

	List<UserDTO> findByEmail(String email);

	Page<UserDTO> getUserPage(Integer page);

	List<UserDTO> getUserByRoleAndStatus(String roleName, Boolean status);

}
