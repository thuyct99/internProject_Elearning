package com.codeenginestudio.elearning.validation;

import org.springframework.util.StringUtils;

import com.codeenginestudio.elearning.dto.QuestionOfAssessmentDTO;

public class QuestionValidator {

	private String errContent = "";
	private String errNumericalOrder = "";
	private String errCorrectAnswer = "";

	public String getErrContent() {
		return errContent;
	}

	public void setErrContent(String errContent) {
		this.errContent = errContent;
	}

	public String getErrNumericalOrder() {
		return errNumericalOrder;
	}

	public void setErrNumericalOrder(String errNumericalOrder) {
		this.errNumericalOrder = errNumericalOrder;
	}

	public String getErrCorrectAnswer() {
		return errCorrectAnswer;
	}

	public void setErrCorrectAnswer(String errCorrectAnswer) {
		this.errCorrectAnswer = errCorrectAnswer;
	}

	String checkNull(String value, String error) {
		if (value == "") {
			return error;
		}
		return "";
	}

	public QuestionValidator validateQuestion(QuestionOfAssessmentDTO questionOfAssessmentDTO) {
		QuestionValidator inValid = new QuestionValidator();

		inValid.errContent = checkNull(questionOfAssessmentDTO.getContent(), "Content could not be null");
		inValid.errNumericalOrder = checkNumericalOrder(questionOfAssessmentDTO.getNumericalorder());
		inValid.errCorrectAnswer = checkNull(questionOfAssessmentDTO.getCorrectanswer(),
				"Correct answer could not be null");

		return inValid;
	}
	
	public boolean checkOnlyDigital (String data) {
		String regex = "\\p{Digit}+";
		return data.matches(regex);
	}
	
	public String checkNumericalOrder(String numerical) {
		if(numerical.equals("")) {
			return "Numerical Order should not be null";
		}else {
			if(!checkOnlyDigital(numerical)) {
				return "Numerical Order should be only digital";
			}else {
				if(Integer.parseInt(numerical) <= 0) {
					return "Numerical Order should be more than 0";
				}
			}
			
		}
		return "";
	}
	
	public boolean noError() {

		return StringUtils.isEmpty(this.getErrContent()) && StringUtils.isEmpty(this.getErrCorrectAnswer()) && StringUtils.isEmpty(this.getErrNumericalOrder());
	}
}
