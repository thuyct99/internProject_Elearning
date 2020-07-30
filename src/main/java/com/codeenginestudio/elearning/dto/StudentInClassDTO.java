package com.codeenginestudio.elearning.dto;

public class StudentInClassDTO {

	private Long idrow;

	private ClassDTO classForeign;

	private UserDTO student;
	
	private Float score;

	public StudentInClassDTO() {
		super();
	}

	
	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}


	public Long getIdrow() {
		return idrow;
	}

	public void setIdrow(Long idrow) {
		this.idrow = idrow;
	}

	public ClassDTO getClassForeign() {
		return classForeign;
	}

	public void setClassForeign(ClassDTO classForeign) {
		this.classForeign = classForeign;
	}

	public UserDTO getStudent() {
		return student;
	}

	public void setStudent(UserDTO student) {
		this.student = student;
	}

}
