package com.codeenginestudio.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeenginestudio.elearning.emailConfig.SimpleEmailExampleController;
import com.codeenginestudio.elearning.service.ResultService;

@Controller
public class WelcomeController {
	@Autowired
	JavaMailSender emailSende;

	@Autowired
	ResultService resultService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {

		SimpleEmailExampleController.sendSimpleEmail(emailSende, resultService);

		return "welcome";
	}
}
