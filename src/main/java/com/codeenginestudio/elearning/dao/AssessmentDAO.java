package com.codeenginestudio.elearning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeenginestudio.elearning.dao.entity.AssessmentEntity;
import com.codeenginestudio.elearning.dao.entity.ClassEntity;

@Repository
public interface AssessmentDAO extends JpaRepository<AssessmentEntity, Long> {

	List<AssessmentEntity> findByClassForeign(ClassEntity classForeign);

	List<AssessmentEntity> findByStatus(Boolean status);

}
