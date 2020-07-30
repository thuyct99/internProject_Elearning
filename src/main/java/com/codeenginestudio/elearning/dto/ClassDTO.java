package com.codeenginestudio.elearning.dto;

public class ClassDTO {

	private Long classid;

	private String classname;

	private int totalStudents;

	private Boolean status;

	private UserDTO user;

	private int totalAssessments;

	public ClassDTO() {
		super();
	}

	public ClassDTO(Long classid, String classname, int totalStudents, Boolean status, UserDTO user) {
		super();
		this.classid = classid;
		this.classname = classname;
		this.totalStudents = totalStudents;
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

	public int getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public int getTotalAssessments() {
		return totalAssessments;
	}

	public void setTotalAssessments(int totalAssessments) {
		this.totalAssessments = totalAssessments;
	}

}
