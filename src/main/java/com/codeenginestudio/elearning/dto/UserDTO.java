package com.codeenginestudio.elearning.dto;

public class UserDTO {

	private Long userid;

	private String username;

	private String password;

	private String firstname;

	private String lastname;

	private String email;

	private String gender;

	private Boolean enabled;

	private String avartar;

	private RoleDTO role;

	public UserDTO() {

	}

	public UserDTO(String username, String password, String firstName, String lastname, String email, String gender,
			String avartar, Boolean enabled, RoleDTO role) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstName;
		this.lastname = lastname;
		this.email = email;
		this.gender = gender;
		this.avartar = avartar;
		this.enabled = enabled;
		this.role = role;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAvartar() {
		return avartar;
	}

	public void setAvartar(String avartar) {
		this.avartar = avartar;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public Boolean getEnabled() {
		return enabled;
	}

}
