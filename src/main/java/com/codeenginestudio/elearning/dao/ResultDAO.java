package com.codeenginestudio.elearning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeenginestudio.elearning.dao.entity.AssessmentEntity;
import com.codeenginestudio.elearning.dao.entity.ResultEntity;
import com.codeenginestudio.elearning.dao.entity.UserEntity;

public interface ResultDAO extends JpaRepository<ResultEntity, Long> {

	List<ResultEntity> findByAssessment(AssessmentEntity assessment);

	List<ResultEntity> findByStudent(UserEntity userEntity);

	ResultEntity getOneByAssessment(AssessmentEntity assessment);

	List<ResultEntity> findByAssessmentAndStudent(AssessmentEntity assessment, UserEntity student);

}
