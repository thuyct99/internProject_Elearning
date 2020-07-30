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

	public QuestionTypeEntity(Long questionTypeId, String questionTypeName) {
		super();
		this.questionTypeId = questionTypeId;
		this.questionTypeName = questionTypeName;
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
}
