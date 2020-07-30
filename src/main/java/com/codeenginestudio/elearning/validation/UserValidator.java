package com.codeenginestudio.elearning.validation;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.codeenginestudio.elearning.dto.UserDTO;
import com.codeenginestudio.elearning.service.UserService;

public class UserValidator {

	private String errUsername = "";
	private String errPassword = "";
	private String errFirstname = "";
	private String errLastname = "";
	private String errEmail = "";

	public String getErrUsername() {
		return errUsername;
	}

	public void setErrUsername(String errUsername) {
		this.errUsername = errUsername;
	}

	public String getErrPassword() {
		return errPassword;
	}

	public void setErrPassword(String errPassword) {
		this.errPassword = errPassword;
	}

	public String getErrFirstname() {
		return errFirstname;
	}

	public void setErrFirstname(String errFirstname) {
		this.errFirstname = errFirstname;
	}

	public String getErrLastname() {
		return errLastname;
	}

	public void setErrLastname(String errLastname) {
		this.errLastname = errLastname;
	}

	public String getErrEmail() {
		return errEmail;
	}

	public void setErrEmail(String errEmail) {
		this.errEmail = errEmail;
	}

	String checkUsernameUnique(String username, UserService userService) {

		if (username == "") {
			return "Username could not be null";
		} else {

			int err = username.indexOf(" ");
			if (err >= 0) {
				return "Username could not contains the space";
			} else if (!CollectionUtils.isEmpty(userService.findByUsername(username))) {
				return "Username is already exsits !";
			}
		}

		return "";
	}

	String checkEmailUnique(String email, UserService userService) {

		if (email == "") {
			return "Email could not be null";
		} else if (!isValidEmail(email)) {
			return "Email is wrong format !";
		} else if (!CollectionUtils.isEmpty(userService.findByEmail(email))) {
			return "Email is already exsits !";
		}
		return "";
	}

	String checkNull(String value, String error) {
		if (value == "") {
			return error;
		}
		return "";
	}

	boolean isValidEmail(String email) {

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public UserValidator validateAddUser(UserDTO userDTO, UserService userService) {
		UserValidator inValid = new UserValidator();

		inValid.errUsername = checkUsernameUnique(userDTO.getUsername(), userService);
		inValid.errPassword = checkNull(userDTO.getPassword(), "Password could not be null");
		inValid.errFirstname = checkNull(userDTO.getFirstname(), "Firstname could not be null");
		inValid.errLastname = checkNull(userDTO.getLastname(), "Lastname could not be null");
		inValid.errEmail = checkEmailUnique(userDTO.getEmail(), userService);

		return inValid;
	}

	public UserValidator validateEditUser(UserDTO userDTO, UserService userService, long userId) {
		UserValidator inValid = new UserValidator();

		UserDTO originUser = userService.getUserByUserId(userId);

		if (!originUser.getUsername().equals(userDTO.getUsername())) {
			inValid.errUsername = checkUsernameUnique(userDTO.getUsername(), userService);
		}

		inValid.errPassword = checkNull(userDTO.getPassword(), "Password could not be null");
		inValid.errFirstname = checkNull(userDTO.getFirstname(), "Firstname could not be null");
		inValid.errLastname = checkNull(userDTO.getLastname(), "Lastname could not be null");

		if (!originUser.getEmail().equals(userDTO.getEmail())) {
			inValid.errEmail = checkEmailUnique(userDTO.getEmail(), userService);
		}

		return inValid;
	}

	public boolean noError() {
		return StringUtils.isEmpty(this.getErrUsername()) && StringUtils.isEmpty(this.getErrPassword())
				&& StringUtils.isEmpty(this.getErrFirstname()) && StringUtils.isEmpty(this.getErrLastname())
				&& StringUtils.isEmpty(this.getErrEmail());
	}
}
