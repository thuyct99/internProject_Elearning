package com.codeenginestudio.elearning.service;

import java.util.List;

import com.codeenginestudio.elearning.dto.AssessmentDTO;
import com.codeenginestudio.elearning.dto.ClassDTO;

public interface AssessmentService {

	void editAssessmentStatus(Long assessmentid);

	void deleteById(Long assessmentid);

	void deleteAssessmentClassid(Long classId);

	void saveAddAssessment(AssessmentDTO assessmentDTO);

	void saveEditAssessment(AssessmentDTO assessmentDTO);

	List<Long> getAssessmentEnable(boolean status);

	AssessmentDTO getAssessmentByAssessmentid(Long assessmentid);

	AssessmentDTO findByAssessmentName(String assessmentname);

	List<AssessmentDTO> getListAssessment();

	List<AssessmentDTO> getListAssessmentByClassid(Long classid);

	List<AssessmentDTO> getListAssessmentByExpired(Long userId);

	List<AssessmentDTO> getListAssessmentByUnExpired(Long userId);

	List<AssessmentDTO> getAssessmentByClassForeign(List<ClassDTO> listClass);

}
