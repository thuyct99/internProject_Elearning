package com.codeenginestudio.elearning.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.dao.AssessmentDAO;
import com.codeenginestudio.elearning.dao.ClassDAO;
import com.codeenginestudio.elearning.dao.ResultDAO;
import com.codeenginestudio.elearning.dao.UserDAO;
import com.codeenginestudio.elearning.dao.entity.AssessmentEntity;
import com.codeenginestudio.elearning.dto.AssessmentDTO;
import com.codeenginestudio.elearning.dto.ClassDTO;
import com.codeenginestudio.elearning.service.AssessmentService;
import com.codeenginestudio.elearning.service.QuestionOfAssessmentService;
import com.codeenginestudio.elearning.service.ResultService;
import com.codeenginestudio.elearning.util.AssessmentUtil;

@Service("assessmentService")
public class AssessmentServiceImpl implements AssessmentService {

	@Autowired
	private AssessmentDAO assessmentDAO;

	@Autowired
	private ClassDAO classDAO;

	@Autowired
	private QuestionOfAssessmentService questionOfAssessmentService;

	@Autowired
	private ResultService resultService;

	@Autowired
	private ResultDAO resultDAO;

	@Autowired
	private UserDAO userDAO;

	@Override
	public void editAssessmentStatus(Long assessmentid) {

		AssessmentEntity assessmentEntity = assessmentDAO.getOne(assessmentid);
		assessmentEntity.setStatus(!assessmentEntity.getStatus());
		assessmentDAO.saveAndFlush(assessmentEntity);
	}

	@Override
	public void deleteById(Long assessmentid) {

		resultService.deleteResultByAssessmentId(assessmentid);
		questionOfAssessmentService.deleteQuestionsByAssessmentId(assessmentid);
		assessmentDAO.deleteById(assessmentid);
	}

	@Override
	public void deleteAssessmentClassid(Long classId) {

		List<AssessmentEntity> listAssessments = assessmentDAO.findByClassForeign(classDAO.getOne(classId));

		if (listAssessments.size() > 0) {

			for (AssessmentEntity assessment : listAssessments) {

				resultService.deleteResultByAssessmentId(assessment.getAssessmentid());
				questionOfAssessmentService.deleteQuestionsByAssessmentId(assessment.getAssessmentid());
				assessmentDAO.delete(assessment);
			}
		}
	}

	@Override
	public void saveAddAssessment(AssessmentDTO assessmentDTO) {

		AssessmentEntity assessmentEntity = new AssessmentEntity();
		assessmentEntity.setAssessmentname(assessmentDTO.getAssessmentname());
		assessmentEntity.setExpireddate(assessmentDTO.getExpireddate());
		assessmentEntity.setStartdate(assessmentDTO.getStartdate());
		assessmentEntity.setStatus(assessmentDTO.getStatus());
		assessmentEntity.setClassForeign(classDAO.getOne(assessmentDTO.getClassForeign().getClassid()));
		assessmentDAO.saveAndFlush(assessmentEntity);

	}

	@Override
	public void saveEditAssessment(AssessmentDTO assessmentDTO) {

		AssessmentEntity assessmentEntity = assessmentDAO.getOne(assessmentDTO.getAssessmentid());
		assessmentEntity.setAssessmentid(assessmentDTO.getAssessmentid());
		assessmentEntity.setAssessmentname(assessmentDTO.getAssessmentname());
		assessmentEntity.setClassForeign(classDAO.getOne(assessmentDTO.getClassForeign().getClassid()));
		assessmentEntity.setExpireddate(assessmentDTO.getExpireddate());
		assessmentEntity.setStartdate(assessmentDTO.getStartdate());
		assessmentEntity.setStatus(assessmentDTO.getStatus());
		assessmentDAO.saveAndFlush(assessmentEntity);
	}

	@Override
	public List<Long> getAssessmentEnable(boolean status) {

		List<AssessmentEntity> listAssessment = assessmentDAO.findByStatus(status);
		List<Long> listAssessmentId = new ArrayList<>();

		for (AssessmentEntity assessment : listAssessment) {

			listAssessmentId.add(assessment.getAssessmentid());
		}

		return listAssessmentId;
	}

	@Override
	public AssessmentDTO getAssessmentByAssessmentid(Long assessmentid) {

		AssessmentEntity assessmentEntity = assessmentDAO.getOne(assessmentid);
		return AssessmentUtil.parseToDTO(assessmentEntity);
	}

	@Override
	public AssessmentDTO findByAssessmentName(String assessmentname) {

		List<AssessmentDTO> listAssessment = getListAssessment();

		for (AssessmentDTO existed : listAssessment) {

			if (assessmentname.equals(existed.getAssessmentname())) {

				return existed;
			}
		}

		return null;
	}

	@Override
	public List<AssessmentDTO> getListAssessment() {

		List<AssessmentEntity> listAssessment = (List<AssessmentEntity>) assessmentDAO.findAll();
		List<AssessmentDTO> assessmentDTO = new ArrayList<>();

		for (AssessmentEntity assessment : listAssessment) {

			assessmentDTO.add(AssessmentUtil.parseToDTO(assessment));
		}

		return assessmentDTO;
	}

	@Override
	public List<AssessmentDTO> getListAssessmentByClassid(Long classid) {

		List<AssessmentEntity> listAssessment = assessmentDAO.findByClassForeign(classDAO.getOne(classid));
		List<AssessmentDTO> assessmentDTO = new ArrayList<>();

		for (AssessmentEntity assessment : listAssessment) {

			assessmentDTO.add(AssessmentUtil.parseToDTO(assessment));
		}

		return assessmentDTO;
	}

	@Override
	public List<AssessmentDTO> getListAssessmentByExpired(Long userId) {

		LocalDate currentDate = LocalDate.now();
		List<AssessmentDTO> listAssessment = getListAssessment();
		List<AssessmentDTO> listAssessmentExpired = new ArrayList<>();

		for (AssessmentDTO assessment : listAssessment) {

			if (assessment.getExpireddate().isBefore(currentDate)) {

				listAssessmentExpired.add(assessment);
				assessment.setEdit(
						!resultDAO.findByAssessmentAndStudent(assessmentDAO.getOne(assessment.getAssessmentid()),
								userDAO.getOne(userId)).isEmpty());
			}
		}

		return listAssessmentExpired;
	}

	@Override
	public List<AssessmentDTO> getListAssessmentByUnExpired(Long userId) {

		LocalDate currentDate = LocalDate.now();
		List<AssessmentDTO> listAssessment = getListAssessment();
		List<AssessmentDTO> listAssessmentUnExpired = new ArrayList<>();

		for (AssessmentDTO assessment : listAssessment) {

			if (!assessment.getExpireddate().isBefore(currentDate) || assessment.getExpireddate().equals(currentDate)) {

				if (assessment.getStatus()) {

					listAssessmentUnExpired.add(assessment);
					assessment.setEdit(
							!resultDAO.findByAssessmentAndStudent(assessmentDAO.getOne(assessment.getAssessmentid()),
									userDAO.getOne(userId)).isEmpty());
				}
			}
		}

		return listAssessmentUnExpired;
	}

	@Override
	public List<AssessmentDTO> getAssessmentByClassForeign(List<ClassDTO> listClass) {

		List<AssessmentEntity> listAssessment = assessmentDAO.findAll();
		List<AssessmentDTO> assessmentDTO = new ArrayList<>();

		for (AssessmentEntity assessment : listAssessment) {

			for (ClassDTO classes : listClass) {

				if (assessment.getClassForeign().getClassid() == classes.getClassid()) {

					assessmentDTO.add(AssessmentUtil.parseToDTO(assessment));
				}
			}
		}

		return assessmentDTO;
	}
}
