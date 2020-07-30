package com.codeenginestudio.elearning.dto;

public class RoleDTO {

	private Long roleid;

	private String rolename;

	public RoleDTO(Long roleid, String rolename) {
		this.roleid = roleid;
		this.rolename = rolename;
	}

	public RoleDTO() {
		super();
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
