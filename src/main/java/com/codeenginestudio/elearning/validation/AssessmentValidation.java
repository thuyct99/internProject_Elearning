package com.codeenginestudio.elearning.validation;

import java.time.LocalDate;

import com.codeenginestudio.elearning.dto.AssessmentDTO;
import com.codeenginestudio.elearning.service.AssessmentService;

public class AssessmentValidation {

	private String errAssessmentName;
	private String errExpiredDate = "";

	public String getErrAssessmentName() {
		return errAssessmentName;
	}

	public void setErrAssessmentName(String errAssessmentName) {
		this.errAssessmentName = errAssessmentName;
	}

	public String getErrExpiredDate() {
		return errExpiredDate;
	}

	public void setErrExpiredDate(String errExpiredDate) {
		this.errExpiredDate = errExpiredDate;
	}

	String checkEmpty(String value, String error) {
		if (value == "") {
			return error;
		}
		return "";
	}

	public String checkAssessmentNameExisted(Long assessmentid, String assessmentname,
			AssessmentService assessmentService) {
		if (assessmentname == "") {
			return "Assessment name could not be null";
		} else {
			if (assessmentService.findByAssessmentName(assessmentname) != null) {
				if (assessmentService.findByAssessmentName(assessmentname).getAssessmentid() == assessmentid) {
					return "";
				} else {
					return "Assessment name already exists !";
				}
			}
		}
		return "";
	}

	public String checkExpiredDate(LocalDate startDate, LocalDate expiredDate) {
		if (expiredDate.isBefore(startDate)) {
			return "Expired date must be after start date";
		}
		return "";
	}

	public AssessmentValidation validateAddAssessment(AssessmentDTO assessmentDTO,
			AssessmentService assessmentService) {

		AssessmentValidation inValid = new AssessmentValidation();
		inValid.errAssessmentName = checkEmpty(assessmentDTO.getAssessmentname(), "Assessment name could not be null");
		inValid.errAssessmentName = checkAssessmentNameExisted(assessmentDTO.getAssessmentid(),
				assessmentDTO.getAssessmentname(), assessmentService);
		inValid.errExpiredDate = checkExpiredDate(assessmentDTO.getStartdate(), assessmentDTO.getExpireddate());
		return inValid;
	}
}
