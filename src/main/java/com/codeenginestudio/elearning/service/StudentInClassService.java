package com.codeenginestudio.elearning.service;

import java.util.List;

import com.codeenginestudio.elearning.dto.StudentInClassDTO;

public interface StudentInClassService {

	void deleteAllByClass(Long classid);

	void deleteStudentInClass(Long userid);

	void saveStudentInClass(Long classid, Long userid);

	Float setScoreForStudent(Long assessmentId, Long studentId);

	Long findIdByValue(Long check);

	List<Long> getClassIdByStudent(Long userid);

	List<Long> getListStudenIdtByClassid(Long classid);

	List<StudentInClassDTO> getByClassid(Long classid);

	List<StudentInClassDTO> getAllStudentInClass();

}
