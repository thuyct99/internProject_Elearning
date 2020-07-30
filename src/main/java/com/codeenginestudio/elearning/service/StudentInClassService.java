package com.codeenginestudio.elearning.service;

import java.util.List;

import com.codeenginestudio.elearning.dto.StudentInClassDTO;

public interface StudentInClassService {

	void saveStudentInClass(Long classid, Long userid);

	void deleteStudentInClass(Long id);

	void deleteAllByClass(Long classid);

	Long findIdByValue(Long check);

	List<Long> getListStudenIdtByClassid(Long classid);
	
	List<StudentInClassDTO> getByClassid(Long classid);

	List<StudentInClassDTO> getAllStudentInClass();

	List<Long> getClassIdByStudent(Long userid);

	Float setScoreForStudent(Long assessmentId, Long studentId);
}
