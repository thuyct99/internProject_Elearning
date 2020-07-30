package com.codeenginestudio.elearning.dto;

public class QuestionTypeDTO {

	private Long questionTypeId;

	private String questionTypeName;

	public QuestionTypeDTO(Long questionTypeId, String questionTypeName) {
		super();
		this.questionTypeId = questionTypeId;
		this.questionTypeName = questionTypeName;
	}

	public QuestionTypeDTO() {
		super();
	}

	public Long getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(Long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public String getQuestionTypeName() {
		return questionTypeName;
	}

	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}
}
