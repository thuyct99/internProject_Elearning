package com.codeenginestudio.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeenginestudio.elearning.dto.AssessmentDTO;
import com.codeenginestudio.elearning.dto.QuestionOfAssessmentDTO;
import com.codeenginestudio.elearning.service.AssessmentService;
import com.codeenginestudio.elearning.service.ClassService;
import com.codeenginestudio.elearning.service.QuestionOfAssessmentService;
import com.codeenginestudio.elearning.service.QuestionTypeService;
import com.codeenginestudio.elearning.validation.QuestionValidator;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class QuestionOfAssessmentController {

	@Autowired
	private QuestionOfAssessmentService questionOfAssessmentService;

	@Autowired
	private AssessmentService assessmentService;

	@Autowired
	private ClassService classService;

	@Autowired
	private QuestionTypeService questionTypeService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/teacher/questionOfAssessment/addQuestionOfAssessment/{assessmentid}")
	public String addQuestionOfAssignment(Model model, @PathVariable(name = "assessmentid") Long assessmentid) {

		model.addAttribute("assessmentid", assessmentid);
		model.addAttribute("url", "/teacher/questionOfAssessment/saveAddQuestionOfAssessment/" + assessmentid);
		model.addAttribute("listQuestionType", questionTypeService.getListQuestionType());

		return PREFIX_TEACHER + "addAndEditQuestionOfAssessment";
	}

	@GetMapping("/teacher/questionOfAssessment/deleteQuestionOfAssessment/{assessmentid}/{questionId}")
	public String deleteQuestionOfAssessment(@PathVariable(name = "assessmentid") Long assessmentid,
			@PathVariable(name = "questionId") Long questionId, RedirectAttributes redirectAttributes) {

		questionOfAssessmentService.deleteQuestionOfAssessment(questionId);
		redirectAttributes.addFlashAttribute("messageSuccess",
				messageSource.getMessage("delete-question-uccessfully", null, LocaleContextHolder.getLocale()));

		return "redirect:/teacher/questionOfAssessment?assessmentid=" + assessmentid;
	}

	@GetMapping("/teacher/questionOfAssessment/editQuestionOfAssessment/{assessmentid}/{questionId}")
	public String editQuestionOfAssessment(@PathVariable(name = "questionId") Long questionId,
			@PathVariable(name = "assessmentid") Long assessmentid, Model model) {

		model.addAttribute("questionInf", questionOfAssessmentService.getOneQuestionOfAssessment(questionId));
		model.addAttribute("listAssessment", assessmentService.getListAssessment());
		model.addAttribute("listQuestionType", questionTypeService.getListQuestionType());
		model.addAttribute("url", "/teacher/questionOfAssessment/saveEditQuestionOfAssessment/" + assessmentid);

		return PREFIX_TEACHER + "addAndEditQuestionOfAssessment";
	}

	@GetMapping("/teacher/questionOfAssessment")
	public String getListQuestion(Model model, @ModelAttribute("assessmentid") Long assessmentid) {

		AssessmentDTO assessmentDTO = assessmentService.getAssessmentByAssessmentid(assessmentid);
		model.addAttribute("listQuestionOfAssessment",
				questionOfAssessmentService.getListQuestionOfAssessmentByAssessment(assessmentid));
		model.addAttribute("assessment", assessmentDTO);

		if (assessmentDTO.getClassForeign() == null) {

			model.addAttribute("classNull", "Haven't assigned to any class yet");
		} else {

			model.addAttribute("class", classService.getClassByClassid(assessmentDTO.getClassForeign().getClassid()));
		}

		return PREFIX_TEACHER + "listQuestionOfAssessment";
	}

	@GetMapping("/teacher/assessment/preview/{assessmentid}")
	public String previewQuestionOfAssessment(@PathVariable(name = "assessmentid") Long assessmentid, Model model) {

		model.addAttribute("listQuestionOfAssessment",
				questionOfAssessmentService.getListQuestionOfAssessmentByAssessment(assessmentid));
		model.addAttribute("assessment", assessmentService.getAssessmentByAssessmentid(assessmentid));

		return "/teacher/preview/previewAssessment";
	}

	@PostMapping("teacher/questionOfAssessment/saveAddQuestionOfAssessment/{assessmentid}")
	public String saveAddQuestionOfAssessment(QuestionOfAssessmentDTO questionOfAssessmentDTO, Model model,
			@PathVariable(name = "assessmentid") Long assessmentid, RedirectAttributes redirectAttributes)
			throws JsonProcessingException {

		QuestionValidator invalid = QuestionValidator.validateQuestion(questionOfAssessmentDTO);

		if (invalid.noError()) {

			questionOfAssessmentService.addQuestionOfAssessment(questionOfAssessmentDTO);
			redirectAttributes.addFlashAttribute("messageSuccess",
					messageSource.getMessage("add-question-successfully", null, LocaleContextHolder.getLocale()));

			return "redirect:/teacher/questionOfAssessment?assessmentid=" + assessmentid;
		}

		model.addAttribute("error", invalid);
		model.addAttribute("questionInf", questionOfAssessmentDTO);
		model.addAttribute("assessmentid", assessmentid);
		model.addAttribute("url", "/teacher/questionOfAssessment/saveAddQuestionOfAssessment/" + assessmentid);
		model.addAttribute("listQuestionType", questionTypeService.getListQuestionType());

		return PREFIX_TEACHER + "addAndEditQuestionOfAssessment";

	}

	@PostMapping("/teacher/questionOfAssessment/saveEditQuestionOfAssessment/{assessmentid}")
	public String saveEditQuestionOfAssessment(QuestionOfAssessmentDTO questionOfAssessmentDTO, Model model,
			@PathVariable(name = "assessmentid") Long assessmentid, @ModelAttribute("questionid") Long questionId,
			RedirectAttributes redirectAttributes) throws JsonProcessingException {

		QuestionValidator invalid = QuestionValidator.validateQuestion(questionOfAssessmentDTO);

		if (invalid.noError()) {

			questionOfAssessmentService.editQuestionOfAssessment(questionOfAssessmentDTO);
			redirectAttributes.addFlashAttribute("messageSuccess",
					messageSource.getMessage("edit-question-successfully", null, LocaleContextHolder.getLocale()));

			return "redirect:/teacher/questionOfAssessment?assessmentid=" + assessmentid;
		}

		model.addAttribute("error", invalid);
		model.addAttribute("questionInf", questionOfAssessmentDTO);
		model.addAttribute("assessmentid", assessmentid);
		model.addAttribute("url", "/teacher/questionOfAssessment/saveAddQuestionOfAssessment/" + assessmentid);
		model.addAttribute("listQuestionType", questionTypeService.getListQuestionType());

		return PREFIX_TEACHER + "addAndEditQuestionOfAssessment";
	}

	private static final String PREFIX_TEACHER = "/teacher/questionOfAssessment/";
}