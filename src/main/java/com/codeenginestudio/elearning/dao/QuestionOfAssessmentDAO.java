package com.codeenginestudio.elearning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeenginestudio.elearning.dao.entity.QuestionOfAssessmentEntity;

@Repository
public interface QuestionOfAssessmentDAO extends JpaRepository<QuestionOfAssessmentEntity, Long> {

	Page<QuestionOfAssessmentEntity> getListQuestionOfAssessmentByAssessment(Pageable pageable, Long assessmentid);
}
