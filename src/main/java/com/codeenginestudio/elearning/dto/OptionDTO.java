package com.codeenginestudio.elearning.dto;

public class OptionDTO {

	private String name;
	
	private String value;
	
	public OptionDTO() {
		super();
	}

	public OptionDTO(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOptionValue() {
		return value;
	}

	public void setOptionValue(String value) {
		this.value = value;
	}
	
	
}
