package com.codeenginestudio.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeenginestudio.elearning.dto.ExampleDTO;
import com.codeenginestudio.elearning.validation.ExampleValidator;

@Controller
@RequestMapping("example")
public class ExampleController {

	@Autowired
	ExampleValidator exampleValidator;

	@InitBinder
	private void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(exampleValidator);
	}

	@ModelAttribute
	public ExampleDTO setupForm() {
		return new ExampleDTO();
	}

	@GetMapping(value = "add")
	public String displayRegister() {
		return "example/add";
	}

	@GetMapping(value = "error")
	public String displayError() {
		return "error";
	}

	@PostMapping(value = "add")
	public String add(@Validated ExampleDTO exampleDTO, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "example/add";
		}

		return "redirect:/example/add";
	}
}
