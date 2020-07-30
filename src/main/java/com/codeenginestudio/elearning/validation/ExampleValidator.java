package com.codeenginestudio.elearning.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codeenginestudio.elearning.dto.ExampleDTO;

@Component
public class ExampleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ExampleDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ExampleDTO exampleDTO = (ExampleDTO) target;

		if (exampleDTO.getName().equals("Example")) {

			errors.rejectValue("name", "name-should-not-be-example");
		}

	}

}
