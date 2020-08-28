package com.codeenginestudio.elearning.service;

import java.util.List;

import com.codeenginestudio.elearning.dto.ResultDTO;
import com.codeenginestudio.elearning.dto.UserDTO;

public interface ResultService {

	void deleteResultByAssessmentId(Long assessmentid);

	void deleteResultByStudent(Long studentId);

	void deleteResultByQuestionId(Long questionId);

	void saveEditSubmitResult(ResultDTO result);

	void saveSubmitResult(ResultDTO result);

	Float getUserScoreByAssessment(Long assessmentid);

	List<Long> getListAssessmentIdByStudentId(Long studentid);

	List<Long> getListStudentIdtByAssessmentId(Long assessmentid);

	List<UserDTO> getListStudentNotyetSubmitAssessment();

	List<ResultDTO> findByAssessmentId(Long assessmentid);

	List<ResultDTO> findByAssessmentAndStudent(Long assessmentid, Long userId);

	List<ResultDTO> findByStudentId(Long studentid);

}
