package com.codeenginestudio.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.constant.Constant;
import com.codeenginestudio.elearning.dao.AssessmentDAO;
import com.codeenginestudio.elearning.dao.ClassDAO;
import com.codeenginestudio.elearning.dao.UserDAO;
import com.codeenginestudio.elearning.dao.entity.AssessmentEntity;
import com.codeenginestudio.elearning.dao.entity.ClassEntity;
import com.codeenginestudio.elearning.dto.ClassDTO;
import com.codeenginestudio.elearning.service.AssessmentService;
import com.codeenginestudio.elearning.service.ClassService;
import com.codeenginestudio.elearning.service.StudentInClassService;
import com.codeenginestudio.elearning.util.ClassUtil;
import com.codeenginestudio.elearning.util.CommonUtil;

@Service("classService")
public class ClassServiceImpl implements ClassService {

	@Autowired
	private AssessmentService assessmentService;

	@Autowired
	private AssessmentDAO assessmentDAO;

	@Autowired
	private ClassDAO classDAO;

	@Autowired
	private StudentInClassService studentInClassService;

	@Autowired
	private UserDAO userDAO;

	@Override
	public void editStatusClass(Long classid) {

		ClassEntity classEntity = classDAO.getOne(classid);
		classEntity.setStatus(!classEntity.getStatus());

		if (classEntity.getStatus() == false) {

			List<AssessmentEntity> listAssessmentEntities = assessmentDAO.findByClassForeign(classEntity);

			for (AssessmentEntity assessmentEntity : listAssessmentEntities) {

				assessmentEntity.setStatus(false);
			}
		}

		classDAO.saveAndFlush(classEntity);
	}

	@Override
	public void deleteClass(Long classId) {

		studentInClassService.deleteAllByClass(classId);
		assessmentService.deleteAssessmentClassid(classId);
		classDAO.deleteById(classId);
	}

	@Override
	public void deleteClassByTeacherId(Long teacherId) {

		List<ClassEntity> listClasses = classDAO.findByUser(userDAO.getOne(teacherId));

		if (listClasses.size() > 0) {

			for (ClassEntity classEntity : listClasses) {

				studentInClassService.deleteAllByClass(classEntity.getClassid());
				assessmentService.deleteAssessmentClassid(classEntity.getClassid());
				classDAO.delete(classEntity);
			}
		}
	}

	@Override
	public void saveAddClass(ClassDTO classDTO) {

		ClassEntity classEntity = new ClassEntity();
		classEntity.setClassname(classDTO.getClassname());
		classEntity.setStatus(classDTO.getStatus());
		Long userid = classDTO.getUser().getUserid();

		if (userid == null) {

			classEntity.setUser(null);
		} else {

			classEntity.setUser(userDAO.getOne(userid));
		}

		classDAO.saveAndFlush(classEntity);
	}

	@Override
	public void saveEditClass(ClassDTO classDTO) {

		ClassEntity classEntity = classDAO.getOne(classDTO.getClassid());
		classEntity.setClassname(classDTO.getClassname());
		classEntity.setStatus(classDTO.getStatus());
		Long userid = classDTO.getUser().getUserid();

		if (userid == null) {

			classEntity.setUser(null);
		} else {

			classEntity.setUser(userDAO.getOne(userid));
		}

		classDAO.saveAndFlush(classEntity);

	}

	@Override
	public ClassDTO getClassByClassid(Long classid) {

		return ClassUtil.parseToDTO(classDAO.getOne(classid));
	}

	@Override
	public List<Long> getListIdByStatus(boolean status) {

		List<ClassEntity> listClass = classDAO.findByStatus(status);
		List<Long> listClassId = new ArrayList<>();

		for (ClassEntity classes : listClass) {

			listClassId.add(classes.getClassid());
		}

		return listClassId;
	}

	@Override
	public List<ClassDTO> getAllClass() {

		List<ClassEntity> listClass = (List<ClassEntity>) classDAO.findAll();
		List<ClassDTO> classDTO = new ArrayList<>();

		for (ClassEntity classes : listClass) {

			classDTO.add(ClassUtil.parseToDTO(classes));
		}

		return classDTO;
	}

	@Override
	public List<ClassDTO> getClassByTeacherId(Long teacherId) {

		List<ClassEntity> listClass = (List<ClassEntity>) classDAO.findByUser(userDAO.getOne(teacherId));
		List<ClassDTO> classDTO = new ArrayList<>();

		for (ClassEntity classes : listClass) {

			classDTO.add(ClassUtil.parseToDTO(classes));
		}

		return classDTO;
	}

	@Override
	public Page<ClassDTO> getClassPage(Integer page) {

		Pageable pageable = (Pageable) PageRequest.of(CommonUtil.getInt(page), Constant.ITEM_PER_PAGE);
		Page<ClassEntity> listClassEntity = classDAO.findAll(pageable);

		return listClassEntity.map(x -> (ClassUtil.parseToDTO(x)));
	}

	@Override
	public Page<ClassDTO> getClassPageByTeacherId(Integer page, Long teacherId) {

		Pageable pageable = (Pageable) PageRequest.of(CommonUtil.getInt(page), Constant.ITEM_PER_PAGE);
		Page<ClassEntity> listClassEntity = classDAO.findPageByUser(userDAO.getOne(teacherId), pageable);

		return listClassEntity.map(x -> (ClassUtil.parseToDTO(x)));
	}

}
