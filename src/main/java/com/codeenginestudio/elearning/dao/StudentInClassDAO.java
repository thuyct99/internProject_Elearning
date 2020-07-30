package com.codeenginestudio.elearning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeenginestudio.elearning.dao.entity.ClassEntity;
import com.codeenginestudio.elearning.dao.entity.StudentInClassEntity;
import com.codeenginestudio.elearning.dao.entity.UserEntity;

public interface StudentInClassDAO extends JpaRepository<StudentInClassEntity, Long> {

	List<StudentInClassEntity> findByClassForeign(ClassEntity classForeign);

	List<StudentInClassEntity> findByStudent(UserEntity student);
}
