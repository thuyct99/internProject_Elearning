package com.codeenginestudio.elearning.validation;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

import com.codeenginestudio.elearning.dto.AssessmentDTO;
import com.codeenginestudio.elearning.service.AssessmentService;

public class AssessmentValidation {

	private String errAssessmentName = null;
	private String errExpiredDate = null;

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

	private static String _checkEmpty(String value) {

		if (StringUtils.isEmpty(value)) {

			return "assessment-name-could-not-be-null";
		}

		return null;
	}

	public static AssessmentValidation validateAddAssessment(AssessmentDTO assessmentDTO,
			AssessmentService assessmentService) {

		AssessmentValidation inValid = new AssessmentValidation();
		inValid.errAssessmentName = _checkEmpty(assessmentDTO.getAssessmentname());
		inValid.errAssessmentName = checkAssessmentNameExisted(assessmentDTO.getAssessmentid(),
				assessmentDTO.getAssessmentname(), assessmentService);
		inValid.errExpiredDate = _checkExpiredDate(assessmentDTO.getStartdate(), assessmentDTO.getExpireddate());

		return inValid;
	}

	public static String checkAssessmentNameExisted(Long assessmentid, String assessmentname,
			AssessmentService assessmentService) {

		if (StringUtils.isEmpty(assessmentname)) {

			return "assessment-name-could-not-be-null";
		}
		
		if (assessmentService.findByAssessmentName(assessmentname) != null) {

			if (assessmentService.findByAssessmentName(assessmentname).getAssessmentid() == assessmentid) {

				return null;
			} 

			return "assessment-name-already-exists";
		}

		return null;
	}

	private static String _checkExpiredDate(LocalDate startDate, LocalDate expiredDate) {

		if (expiredDate.isBefore(startDate)) {

			return "expired-date-must-be-after-start-date";
		}

		return null;
	}
}
