package com.codeenginestudio.elearning.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ExampleDTO {

	@Email(message = "Email is invalid")
	private String email;
	@NotBlank(message = "Please enter name")
	private String name;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
