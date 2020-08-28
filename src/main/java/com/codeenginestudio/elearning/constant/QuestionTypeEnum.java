package com.codeenginestudio.elearning.constant;

public enum QuestionTypeEnum {

	YESNO("YESNO", "Yes/No Question"),
	MULTIPLE("MULTIPLE", "Multiple Choice"),
	INPUT("INPUT", "Input");

	private String code;

	private String description;

	QuestionTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
