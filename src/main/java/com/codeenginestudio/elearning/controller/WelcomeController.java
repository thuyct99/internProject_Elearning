package com.codeenginestudio.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeenginestudio.elearning.dto.UserDTO;
import com.codeenginestudio.elearning.service.ResultService;

@Controller
public class WelcomeController {
	@Autowired
	JavaMailSender emailSende;

	@Autowired
	ResultService resultService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {

		// TODO: need discuss later
		// sendSimpleEmail();

		return "welcome";
	}

	private String sendSimpleEmail() {

		// Create a Simple MailMessage.
		SimpleMailMessage message = new SimpleMailMessage();
		List<UserDTO> listUsers = resultService.getListStudentNotyetSubmitAssessment();

		for (UserDTO user : listUsers) {

			message.setTo(user.getEmail());
			message.setSubject("The Assignment Has Expired Submit");
			message.setText("Hello " + user.getUsername().toUpperCase() + ", Today is expired date to submit the "
					+ user.getAssignmentNotSubmit().getAssessmentname().toUpperCase()
					+ " assignment. Please submit it. Thank you.");
			emailSende.send(message);
		}

		// Send Message!
		return "Email Sent!";
	}
}
