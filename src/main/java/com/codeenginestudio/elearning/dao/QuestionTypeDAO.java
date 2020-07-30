package com.codeenginestudio.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeenginestudio.elearning.dao.entity.QuestionTypeEntity;

public interface QuestionTypeDAO extends JpaRepository<QuestionTypeEntity, Long> {

}
