package com.codeenginestudio.elearning.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleid;

	@Column
	private String rolename;

	public RoleEntity() {
		super();
	}

	public RoleEntity(Long roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
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
