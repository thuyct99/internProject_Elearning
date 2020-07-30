package com.codeenginestudio.elearning.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "classes")
public class ClassEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long classid;

	@Column
	private String classname;

	@Column
	private Boolean status;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "teacherid", referencedColumnName = "userid")
	private UserEntity user;

	public ClassEntity() {

	}

	public ClassEntity(Long classid, String classname, Boolean status, UserEntity user) {
		super();
		this.classid = classid;
		this.classname = classname;
		this.status = status;
		this.user = user;
	}

	public Long getClassid() {
		return classid;
	}

	public void setClassid(Long classid) {
		this.classid = classid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
