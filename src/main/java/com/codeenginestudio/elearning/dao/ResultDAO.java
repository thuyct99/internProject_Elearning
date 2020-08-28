package com.codeenginestudio.elearning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeenginestudio.elearning.dao.entity.AssessmentEntity;
import com.codeenginestudio.elearning.dao.entity.QuestionOfAssessmentEntity;
import com.codeenginestudio.elearning.dao.entity.ResultEntity;
import com.codeenginestudio.elearning.dao.entity.UserEntity;

@Repository
public interface ResultDAO extends JpaRepository<ResultEntity, Long> {

	ResultEntity getOneByAssessment(AssessmentEntity assessment);

	ResultEntity findByQuestion(QuestionOfAssessmentEntity question);

	List<ResultEntity> findByAssessment(AssessmentEntity assessment);

	List<ResultEntity> findByAssessmentAndStudent(AssessmentEntity assessment, UserEntity student);

	List<ResultEntity> findByStudent(UserEntity userEntity);

}
