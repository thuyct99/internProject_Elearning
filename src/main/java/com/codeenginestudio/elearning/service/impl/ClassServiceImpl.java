package com.codeenginestudio.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.constant.Constant;
import com.codeenginestudio.elearning.dao.ClassDAO;
import com.codeenginestudio.elearning.dao.UserDAO;
import com.codeenginestudio.elearning.dao.entity.ClassEntity;
import com.codeenginestudio.elearning.dto.ClassDTO;
import com.codeenginestudio.elearning.service.ClassService;
import com.codeenginestudio.elearning.util.ClassUtil;
import com.codeenginestudio.elearning.util.CommonUtil;

@Service("classService")
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassDAO classDAO;

	@Autowired
	private UserDAO userDAO;

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
	public void deleteClass(Long id) {

		classDAO.deleteById(id);
	}

	@Override
	public ClassDTO getClassByClassid(Long classid) {

		return ClassUtil.parseToDTO(classDAO.getOne(classid));
	}

	@Override
	public Page<ClassDTO> getClassPageByTeacherId(Integer page, Long teacherId) {

		Pageable pageable = (Pageable) PageRequest.of(CommonUtil.getInt(page), Constant.ITEM_PER_PAGE);

		Page<ClassEntity> listClassEntity = classDAO.findPageByUser(userDAO.getOne(teacherId), pageable);

		return listClassEntity.map(x -> (ClassUtil.parseToDTO(x)));
	}

	@Override
	public void editStatusClass(Long classid) {

		ClassEntity classEntity = classDAO.getOne(classid);
		classEntity.setStatus(!classEntity.getStatus());
		classDAO.saveAndFlush(classEntity);
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

}
