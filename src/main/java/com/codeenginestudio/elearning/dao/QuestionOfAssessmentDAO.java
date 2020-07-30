package com.codeenginestudio.elearning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codeenginestudio.elearning.dao.entity.QuestionOfAssessmentEntity;

public interface QuestionOfAssessmentDAO extends JpaRepository<QuestionOfAssessmentEntity, Long> {

	Page<QuestionOfAssessmentEntity> getListQuestionOfAssessmentByAssessment(Pageable pageable, Long assessmentid);
}
