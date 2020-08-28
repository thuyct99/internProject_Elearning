package com.codeenginestudio.elearning.validation;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.codeenginestudio.elearning.dto.UserDTO;
import com.codeenginestudio.elearning.service.UserService;

public class UserValidator {

	private static final String SPACE = " ";
	private static final String REGEX_EMAIL_FORMAT = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	private String errUsername = null;
	private String errPassword = null;
	private String errFirstname = null;
	private String errLastname = null;
	private String errEmail = null;

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

	public static String checkUsernameUnique(String username, UserService userService) {

		if (StringUtils.isEmpty(username)) {
	
			return "username-could-not-be-null";
		} 
	
		int err = username.indexOf(SPACE);
	
		if (err >= 0) {
	
			return "username-could-not-contains-the-space";
		}
	
		if (!CollectionUtils.isEmpty(userService.findByUsername(username))) {
	
			return "username-is-already-exsits";
		}

		return null;
	}

	public static String checkEmailUnique(String email, UserService userService) {

		if (StringUtils.isEmpty(email)) {

			return "email-could-not-be-null";

		} else if (!isValidEmail(email)) {

			return "email-is-wrong-format";

		} else if (!CollectionUtils.isEmpty(userService.findByEmail(email))) {

			return "email-is-already-exsits";
		}

		return null;
	}

	public static String checkNull(String value, String error) {

		if (StringUtils.isEmpty(value)) {

			return error;
		}

		return null;
	}

	public static boolean isValidEmail(String email) {

		return email.matches(REGEX_EMAIL_FORMAT);
	}

	public static UserValidator validateAddUser(UserDTO userDTO, UserService userService) {

		UserValidator inValid = new UserValidator();
		inValid.errUsername = checkUsernameUnique(userDTO.getUsername(), userService);
		inValid.errPassword = checkNull(userDTO.getPassword(), "password-could-not-be-null");
		inValid.errFirstname = checkNull(userDTO.getFirstname(), "firstname-could-not-be-null");
		inValid.errLastname = checkNull(userDTO.getLastname(), "lastname-could-not-be-null");
		inValid.errEmail = checkEmailUnique(userDTO.getEmail(), userService);

		return inValid;
	}

	public static UserValidator validateEditUser(UserDTO userDTO, UserService userService, long userId) {

		UserValidator inValid = new UserValidator();
		UserDTO originUser = userService.getUserByUserId(userId);

		if (!originUser.getUsername().equals(userDTO.getUsername())) {

			inValid.errUsername = checkUsernameUnique(userDTO.getUsername(), userService);
		}

		inValid.errPassword = checkNull(userDTO.getPassword(), "password-could-not-be-null");
		inValid.errFirstname = checkNull(userDTO.getFirstname(), "firstname-could-not-be-null");
		inValid.errLastname = checkNull(userDTO.getLastname(), "lastname-could-not-be-null");

		if (!originUser.getEmail().equals(userDTO.getEmail())) {

			inValid.errEmail = checkEmailUnique(userDTO.getEmail(), userService);
		}

		return inValid;
	}

	public static boolean noError(UserValidator inValid) {

		return StringUtils.isEmpty(inValid.getErrUsername()) && StringUtils.isEmpty(inValid.getErrPassword())
				&& StringUtils.isEmpty(inValid.getErrFirstname()) && StringUtils.isEmpty(inValid.getErrLastname())
				&& StringUtils.isEmpty(inValid.getErrEmail());
	}
}