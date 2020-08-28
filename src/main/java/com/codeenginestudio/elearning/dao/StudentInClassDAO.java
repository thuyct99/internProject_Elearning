package com.codeenginestudio.elearning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeenginestudio.elearning.dao.entity.ClassEntity;
import com.codeenginestudio.elearning.dao.entity.StudentInClassEntity;
import com.codeenginestudio.elearning.dao.entity.UserEntity;

@Repository
public interface StudentInClassDAO extends JpaRepository<StudentInClassEntity, Long> {

	List<StudentInClassEntity> findByClassForeign(ClassEntity classForeign);

	List<StudentInClassEntity> findByStudent(UserEntity student);
}
