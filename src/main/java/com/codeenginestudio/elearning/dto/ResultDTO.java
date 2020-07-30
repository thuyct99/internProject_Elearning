package com.codeenginestudio.elearning.dto;

import java.time.LocalDate;

public class ResultDTO {

	private Long id;

	private UserDTO student;

	private QuestionOfAssessmentDTO question;

	private AssessmentDTO assessment;

	private String answerchoice;

	private LocalDate startdate;

	private LocalDate updatedate;

	private Float score;

	public ResultDTO() {
		super();
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getStudent() {
		return student;
	}

	public void setStudent(UserDTO student) {
		this.student = student;
	}

	public QuestionOfAssessmentDTO getQuestion() {
		return question;
	}

	public void setQuestion(QuestionOfAssessmentDTO question) {
		this.question = question;
	}

	public AssessmentDTO getAssessment() {
		return assessment;
	}

	public void setAssessment(AssessmentDTO assessment) {
		this.assessment = assessment;
	}

	public String getAnswerchoice() {
		return answerchoice;
	}

	public void setAnswerchoice(String answerchoice) {
		this.answerchoice = answerchoice;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(LocalDate updatedate) {
		this.updatedate = updatedate;
	}

}
