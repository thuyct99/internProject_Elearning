package com.codeenginestudio.elearning.dto;

public class QuestionTypeDTO {

	private Long questionTypeId;

	private String questionTypeName;

	private String questionTypeCode;

	public QuestionTypeDTO(Long questionTypeId, String questionTypeName, String questionTypeCode) {
		super();
		this.questionTypeId = questionTypeId;
		this.questionTypeName = questionTypeName;
		this.questionTypeCode = questionTypeCode;
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

	public String getQuestionTypeCode() {
		return questionTypeCode;
	}

	public void setQuestionTypeCode(String questionTypeCode) {
		this.questionTypeCode = questionTypeCode;
	}
}
