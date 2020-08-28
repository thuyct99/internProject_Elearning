package com.codeenginestudio.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeenginestudio.elearning.dao.entity.QuestionTypeEntity;

@Repository
public interface QuestionTypeDAO extends JpaRepository<QuestionTypeEntity, Long> {

}
