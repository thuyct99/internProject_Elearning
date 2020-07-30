package com.codeenginestudio.elearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.dto.QuestionTypeDTO;

@Service
public interface QuestionTypeService {

	List<QuestionTypeDTO> getListQuestionType();
}
