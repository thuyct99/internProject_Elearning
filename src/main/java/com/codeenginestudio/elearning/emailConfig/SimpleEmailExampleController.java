package com.codeenginestudio.elearning.emailConfig;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.codeenginestudio.elearning.dto.UserDTO;
import com.codeenginestudio.elearning.service.ResultService;

public class SimpleEmailExampleController {

	public static String sendSimpleEmail(JavaMailSender emailSende, ResultService resultService) {

		// Create a Simple MailMessage.
		SimpleMailMessage message = new SimpleMailMessage();

		List<UserDTO> listUsers = resultService.getListStudentNotyetSubmitAssessment();

		for (UserDTO user : listUsers) {

//			message.setTo(user.getEmail());
//			message.setSubject("Test Send Email");
//			message.setText("Hello, Im testing Send Email");
//			emailSende.send(message);

		}

		return "Email Sent!";
	}

}
