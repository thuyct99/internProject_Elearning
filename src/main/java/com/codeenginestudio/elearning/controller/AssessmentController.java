package com.codeenginestudio.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeenginestudio.elearning.dto.AssessmentDTO;
import com.codeenginestudio.elearning.dto.ClassDTO;
import com.codeenginestudio.elearning.dto.LessonForm;
import com.codeenginestudio.elearning.dto.ResultDTO;
import com.codeenginestudio.elearning.service.AssessmentService;
import com.codeenginestudio.elearning.service.ClassService;
import com.codeenginestudio.elearning.service.QuestionOfAssessmentService;
import com.codeenginestudio.elearning.service.ResultService;
import com.codeenginestudio.elearning.service.StudentInClassService;
import com.codeenginestudio.elearning.util.SecurityUtil;
import com.codeenginestudio.elearning.validation.AssessmentValidation;

@Controller
public class AssessmentController {

	@Autowired
	private AssessmentService assessmentService;

	@Autowired
	private ClassService classService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private QuestionOfAssessmentService questionOfAssessmentService;

	@Autowired
	private ResultService resultService;

	@Autowired
	private StudentInClassService studentInClassService;

	// Teacher role

	@GetMapping("/teacher/assessment/addAssessment")
	public String addAssessment(Model model) {

		Long teacherId = SecurityUtil.getUserPrincipal().getUserid();
		model.addAttribute("url", "/teacher/assessment/saveAddAssessment");
		model.addAttribute("listClass", classService.getClassByTeacherId(teacherId));

		return PREFIX_TEACHER + "addAndEditAssessment";
	}

	@GetMapping("/teacher/assessment/editAssessment/{assessmentid}")
	public String editAssessment(Model model, @PathVariable(name = "assessmentid") Long assessmentid) {

		Long teacherId = SecurityUtil.getUserPrincipal().getUserid();
		model.addAttribute("url", "/teacher/assessment/saveEditAssessment");
		model.addAttribute("listClass", classService.getClassByTeacherId(teacherId));
		model.addAttribute("assessmentEdit", assessmentService.getAssessmentByAssessmentid(assessmentid));

		return PREFIX_TEACHER + "addAndEditAssessment";
	}

	@GetMapping("/teacher/assessment/editAssessmentStatus/{assessmentid}")
	public String editAssessmentStatus(@PathVariable(name = "assessmentid") Long assessmentid,
			RedirectAttributes redirectAttributes) {

		AssessmentDTO assessmentDTO = assessmentService.getAssessmentByAssessmentid(assessmentid);
		List<Long> listClasses = classService.getListIdByStatus(true);

		// if parents object disable users cannot change status of child object
		if (listClasses.contains(assessmentDTO.getClassForeign().getClassid())) {

			assessmentService.editAssessmentStatus(assessmentid);
			redirectAttributes.addFlashAttribute("messageSuccess",
					messageSource.getMessage("edit-status-successfully", null, LocaleContextHolder.getLocale()));
		} else {

			redirectAttributes.addFlashAttribute("messageDanger",
					messageSource.getMessage("edit-status-unsuccessfully", null, LocaleContextHolder.getLocale()));
		}

		return "redirect:/teacher/assessment";
	}

	@GetMapping("/teacher/assessment/deleteAssessment")
	public String deleteAssessment(@ModelAttribute("assessmentid") Long assessmentid,
			RedirectAttributes redirectAttributes) {

		assessmentService.deleteById(assessmentid);
		redirectAttributes.addFlashAttribute("messageSuccess",
				messageSource.getMessage("delete-assessment-successfully", null, LocaleContextHolder.getLocale()));

		return "redirect:/teacher/assessment";
	}

	@GetMapping("/teacher/assessment")
	public String getListAssessment(Model model) {

		Long teacherId = SecurityUtil.getUserPrincipal().getUserid();
		List<ClassDTO> listClass = classService.getClassByTeacherId(teacherId);
		List<AssessmentDTO> listAssessments = assessmentService.getAssessmentByClassForeign(listClass);

		for (AssessmentDTO assessmentDTO : listAssessments) {
			assessmentDTO.setTotalquestion(questionOfAssessmentService
					.getListQuestionOfAssessmentByAssessment(assessmentDTO.getAssessmentid()).size());
		}

		model.addAttribute("listAssessment", listAssessments);

		return PREFIX_TEACHER + "listAssessment";
	}

	@PostMapping("/teacher/assessment/saveAddAssessment")
	public String saveAddAssessment(Model model, AssessmentDTO assessmentDTO, RedirectAttributes redirectAttributes) {

		AssessmentValidation inValid = AssessmentValidation.validateAddAssessment(assessmentDTO, assessmentService);

		if (inValid.getErrAssessmentName() == null && inValid.getErrExpiredDate() == null) {
			assessmentService.saveAddAssessment(assessmentDTO);
			redirectAttributes.addFlashAttribute("messageSuccess",
					messageSource.getMessage("add-assessment-successfully", null, LocaleContextHolder.getLocale()));

			return "redirect:/teacher/assessment";
		} else {

			model.addAttribute("error", inValid);
			model.addAttribute("listClass", classService.getAllClass());

			return PREFIX_TEACHER + "addAndEditAssessment";
		}
	}

	@PostMapping("/teacher/assessment/saveEditAssessment")
	public String saveEditAssessment(Model model, AssessmentDTO assessmentDTO, RedirectAttributes redirectAttributes) {

		AssessmentValidation inValid = AssessmentValidation.validateAddAssessment(assessmentDTO, assessmentService);

		if (inValid.getErrAssessmentName() == null && inValid.getErrExpiredDate() == null) {
			assessmentService.saveEditAssessment(assessmentDTO);
			redirectAttributes.addFlashAttribute("messageSuccess",
					messageSource.getMessage("edit-assessment-successfully", null, LocaleContextHolder.getLocale()));

			return "redirect:/teacher/assessment";
		} else {

			model.addAttribute("error", inValid);
			model.addAttribute("listClass", classService.getAllClass());
			model.addAttribute("assessmentEdit",
					assessmentService.getAssessmentByAssessmentid(assessmentDTO.getAssessmentid()));

			return PREFIX_TEACHER + "addAndEditAssessment";
		}
	}

	// Student role

	@GetMapping("/student/addSubmitLesson/{assessmentid}")
	public String addSubmitLesson(Model model, @PathVariable(name = "assessmentid") Long assessmentid) {

		model.addAttribute("listQuestionOfAssessment",
				questionOfAssessmentService.getListQuestionOfAssessmentByAssessment(assessmentid));
		model.addAttribute("assessment", assessmentService.getAssessmentByAssessmentid(assessmentid));
		model.addAttribute("url", "/student/saveSubmitLesson/" + assessmentid);
		model.addAttribute("lessonForm", new LessonForm());

		return PREFIX_STUDENT + "assessmentForm";
	}

	@GetMapping("/student/editSubmitLesson/{assessmentid}")
	public String editSubmitLesson(Model model, @PathVariable(name = "assessmentid") Long assessmentid) {

		Long userId = SecurityUtil.getUserPrincipal().getUserid();
		List<ResultDTO> resultDTOs = resultService.findByAssessmentAndStudent(assessmentid, userId);
		LessonForm lessonForm = new LessonForm();

		lessonForm.setResultDTOs(resultDTOs);
		model.addAttribute("url", "/student/saveEditSubmitLesson/" + assessmentid);
		model.addAttribute("listQuestionOfAssessment",
				questionOfAssessmentService.getListQuestionOfAssessmentByAssessment(assessmentid));
		model.addAttribute("assessment", assessmentService.getAssessmentByAssessmentid(assessmentid));
		model.addAttribute("lessonForm", lessonForm);
		model.addAttribute("message", "none");

		return PREFIX_STUDENT + "assessmentForm";
	}

	@GetMapping("/student/assessment")
	public String getListAssessmentWithStudentRole(Model model) {

		Long userId = SecurityUtil.getUserPrincipal().getUserid();
		List<Long> listClassid = studentInClassService.getClassIdByStudent(userId);
		List<AssessmentDTO> listAssessments = assessmentService.getListAssessmentByUnExpired(userId);

		for (AssessmentDTO assessmentDTO : listAssessments) {

			assessmentDTO.setTotalquestion(questionOfAssessmentService
					.getListQuestionOfAssessmentByAssessment(assessmentDTO.getAssessmentid()).size());
			assessmentDTO.setTotalscore(
					questionOfAssessmentService.getTotalScoreByAssessment(assessmentDTO.getAssessmentid()));
		}

		model.addAttribute("listAssessmentId", assessmentService.getAssessmentEnable(true));
		model.addAttribute("listClassAssigned", listClassid);
		model.addAttribute("assessmentPage", listAssessments);

		return PREFIX_STUDENT + "listAssessment";
	}

	@GetMapping("/student/assessment/history")
	public String showHistoryWithStudentRole(Model model) {

		Long userId = SecurityUtil.getUserPrincipal().getUserid();
		List<Long> listClassid = studentInClassService.getClassIdByStudent(userId);
		List<AssessmentDTO> listAssessments = assessmentService.getListAssessmentByExpired(userId);

		for (AssessmentDTO assessmentDTO : listAssessments) {

			assessmentDTO.setTotalquestion(questionOfAssessmentService
					.getListQuestionOfAssessmentByAssessment(assessmentDTO.getAssessmentid()).size());
			assessmentDTO.setTotalscore(
					questionOfAssessmentService.getTotalScoreByAssessment(assessmentDTO.getAssessmentid()));
			assessmentDTO.setUserscore(resultService.getUserScoreByAssessment(assessmentDTO.getAssessmentid()));
		}

		model.addAttribute("listIdOfAssessment", resultService.getListAssessmentIdByStudentId(userId));
		model.addAttribute("listClassAssigned", listClassid);
		model.addAttribute("listAssessment", listAssessments);

		return PREFIX_STUDENT + "history/listAssessmentExpired";
	}

	@PostMapping(value = "student/saveSubmitLesson/{assessmentid}")
	public String saveSubmitLesson(Model model, @PathVariable(name = "assessmentid") Long assessmentid,
			@ModelAttribute(name = "lessonForm") LessonForm lessonForm, RedirectAttributes redirectAttributes) {

		for (ResultDTO result : lessonForm.getResultDTOs()) {

			if (!StringUtils.isEmpty(result.getAnswerchoice())) {

				resultService.saveSubmitResult(result);
			}
		}

		redirectAttributes.addFlashAttribute("messageSuccess",
				messageSource.getMessage("submit-lesson-successfully", null, LocaleContextHolder.getLocale()));

		return "redirect:/student/assessment";
	}

	@PostMapping(value = "student/saveEditSubmitLesson/{assessmentid}")
	public String saveEditSubmitLesson(Model model, @PathVariable(name = "assessmentid") Long assessmentid,
			@ModelAttribute(name = "lessonForm") LessonForm lessonForm, RedirectAttributes redirectAttributes) {

		for (ResultDTO result : lessonForm.getResultDTOs()) {

			if (!StringUtils.isEmpty(result.getAnswerchoice())) {

				resultService.saveEditSubmitResult(result);
			}
		}

		redirectAttributes.addFlashAttribute("messageSuccess",
				messageSource.getMessage("edit-lesson-successfully", null, LocaleContextHolder.getLocale()));

		return "redirect:/student/assessment";
	}

	private static final String PREFIX_TEACHER = "/teacher/assessment/";
	private static final String PREFIX_STUDENT = "/student/assessment/";
}
