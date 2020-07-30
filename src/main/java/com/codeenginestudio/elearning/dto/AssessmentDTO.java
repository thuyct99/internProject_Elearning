package com.codeenginestudio.elearning.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class AssessmentDTO {

	private Long assessmentid;

	private String assessmentname;

	private ClassDTO classForeign;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startdate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate expireddate;

	private Boolean status;

	private boolean isEdit;

	private int totalquestion;

	private Float totalscore;

	private Float userscore;

	public AssessmentDTO() {
		super();
	}

	public AssessmentDTO(Long assessmentid, String assessmentname, ClassDTO classForeign, LocalDate startdate,
			LocalDate expireddate, Boolean status, int totalquestion) {
		super();
		this.assessmentid = assessmentid;
		this.assessmentname = assessmentname;
		this.classForeign = classForeign;
		this.startdate = startdate;
		this.expireddate = expireddate;
		this.status = status;
		this.totalquestion = totalquestion;
	}

	public Long getAssessmentid() {
		return assessmentid;
	}

	public void setAssessmentid(Long assessmentid) {
		this.assessmentid = assessmentid;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public String getAssessmentname() {
		return assessmentname;
	}

	public void setAssessmentname(String assessmentname) {
		this.assessmentname = assessmentname;
	}

	public ClassDTO getClassForeign() {
		return classForeign;
	}

	public void setClassForeign(ClassDTO classForeign) {
		this.classForeign = classForeign;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getExpireddate() {
		return expireddate;
	}

	public void setExpireddate(LocalDate expireddate) {
		this.expireddate = expireddate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public int getTotalquestion() {
		return totalquestion;
	}

	public void setTotalquestion(int totalquestion) {
		this.totalquestion = totalquestion;
	}

	public Float getTotalscore() {
		return totalscore;
	}

	public void setTotalscore(Float totalscore) {
		this.totalscore = totalscore;
	}

	public Float getUserscore() {
		return userscore;
	}

	public void setUserscore(Float userscore) {
		this.userscore = userscore;
	}

}
