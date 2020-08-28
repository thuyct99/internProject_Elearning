package com.codeenginestudio.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {

		if (error != null) {

			model.addAttribute("messageDanger",
					messageSource.getMessage("login-unsuccess", null, LocaleContextHolder.getLocale()));
		}

		if (logout != null) {

			model.addAttribute("messageSuccess",
					messageSource.getMessage("logout-success", null, LocaleContextHolder.getLocale()));
		}

		return "login";
	}
}
