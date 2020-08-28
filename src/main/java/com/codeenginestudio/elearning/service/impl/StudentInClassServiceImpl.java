package com.codeenginestudio.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.dao.ClassDAO;
import com.codeenginestudio.elearning.dao.StudentInClassDAO;
import com.codeenginestudio.elearning.dao.UserDAO;
import com.codeenginestudio.elearning.dao.entity.StudentInClassEntity;
import com.codeenginestudio.elearning.dto.ResultDTO;
import com.codeenginestudio.elearning.dto.StudentInClassDTO;
import com.codeenginestudio.elearning.service.ResultService;
import com.codeenginestudio.elearning.service.StudentInClassService;
import com.codeenginestudio.elearning.util.StudentInClassUtil;

@Service
public class StudentInClassServiceImpl implements StudentInClassService {

	@Autowired
	private ClassDAO classDAO;

	@Autowired
	private StudentInClassDAO studentInClassDAO;

	@Autowired
	private ResultService resultService;

	@Autowired
	private UserDAO userDAO;

	@Override
	public void deleteAllByClass(Long classId) {

		List<StudentInClassEntity> studentInClassEntity = studentInClassDAO
				.findByClassForeign(classDAO.getOne(classId));
		studentInClassDAO.deleteAll(studentInClassEntity);
	}

	@Override
	public void deleteStudentInClass(Long userid) {

		List<StudentInClassEntity> listStudentInClass = studentInClassDAO.findByStudent(userDAO.getOne(userid));

		if (listStudentInClass.size() > 0) {

			for (StudentInClassEntity StudentInClass : listStudentInClass) {

				resultService.deleteResultByStudent(StudentInClass.getStudent().getUserid());
				studentInClassDAO.delete(StudentInClass);
			}
		}
	}

	@Override
	public void saveStudentInClass(Long classid, Long userid) {

		StudentInClassEntity studentInClassEntity = new StudentInClassEntity();
		studentInClassEntity.setClassForeign(classDAO.getOne(classid));
		studentInClassEntity.setStudent(userDAO.getOne(userid));
		studentInClassDAO.save(studentInClassEntity);
	}

	@Override
	public Float setScoreForStudent(Long assessmentId, Long studentId) {

		List<ResultDTO> listResult = resultService.findByAssessmentId(assessmentId);
		Float userScore = (float) 0;

		for (ResultDTO result : listResult) {

			if (studentId == result.getStudent().getUserid()) {

				userScore += result.getScore();
			}
		}

		return userScore;
	}

	@Override
	public Long findIdByValue(Long userid) {

		List<StudentInClassDTO> studentInClassDTO = getAllStudentInClass();

		for (StudentInClassDTO student : studentInClassDTO) {

			if (student.getStudent().getUserid().equals(userid)) {

				return student.getIdrow();
			}
		}

		return null;
	}

	@Override
	public List<Long> getClassIdByStudent(Long userid) {

		List<StudentInClassEntity> studentInClassEntity = studentInClassDAO.findByStudent(userDAO.getOne(userid));
		List<Long> listClass = new ArrayList<>();

		for (StudentInClassEntity student : studentInClassEntity) {

			listClass.add(student.getClassForeign().getClassid());
		}

		return listClass;
	}

	@Override
	public List<Long> getListStudenIdtByClassid(Long classid) {

		List<StudentInClassEntity> studentInClassEntities = studentInClassDAO
				.findByClassForeign(classDAO.getOne(classid));
		List<Long> listStudentid = new ArrayList<>();

		for (StudentInClassEntity student : studentInClassEntities) {

			if (student.getStudent().isEnabled()) {

				listStudentid.add(student.getStudent().getUserid());
			}
		}

		return listStudentid;
	}

	@Override
	public List<StudentInClassDTO> getAllStudentInClass() {

		List<StudentInClassEntity> studentInClassEntity = studentInClassDAO.findAll();
		List<StudentInClassDTO> studentInClassDTO = new ArrayList<>();

		for (StudentInClassEntity student : studentInClassEntity) {

			studentInClassDTO.add(StudentInClassUtil.parseToDTO(student));
		}
		return studentInClassDTO;
	}

	@Override
	public List<StudentInClassDTO> getByClassid(Long classid) {

		List<StudentInClassEntity> studentInClassEntities = studentInClassDAO
				.findByClassForeign(classDAO.getOne(classid));
		List<StudentInClassDTO> studentInClassDTOs = new ArrayList<>();

		for (StudentInClassEntity student : studentInClassEntities) {

			studentInClassDTOs.add(StudentInClassUtil.parseToDTO(student));
		}

		return studentInClassDTOs;
	}

}
