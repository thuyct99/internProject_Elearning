package com.codeenginestudio.elearning.dao.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "questiontypes")
public class QuestionTypeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questiontypeid")
	private Long questionTypeId;

	@Column(name = "questiontypename")
	private String questionTypeName;

	@Column(name="questiontypecode")
	private String questionTypeCode;

	public QuestionTypeEntity(Long questionTypeId, String questionTypeName, String questionTypeCode) {
		super();
		this.questionTypeId = questionTypeId;
		this.questionTypeName = questionTypeName;
		this.questionTypeCode = questionTypeCode;
	}

	public QuestionTypeEntity() {
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
