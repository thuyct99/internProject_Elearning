package com.codeenginestudio.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeenginestudio.elearning.constant.RoleConstant;
import com.codeenginestudio.elearning.service.ClassService;
import com.codeenginestudio.elearning.service.StudentInClassService;
import com.codeenginestudio.elearning.service.UserService;

@Controller
public class StudentInClassController {

	@Autowired
	private ClassService classService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserService userService;

	@Autowired
	private StudentInClassService studentInClassService;

	// Admin role

	@GetMapping("/admin/getStudentInClass")
	public String getStudentInClass(ModelMap model, @ModelAttribute("classid") Long classid) {

		model.addAttribute("classid", classid);
		model.addAttribute("listClassEnable", classService.getListIdByStatus(true));
		model.addAttribute("users", userService.getUserByRoleAndStatus(RoleConstant.STUDENT, true));
		model.addAttribute("studentChecked", studentInClassService.getListStudenIdtByClassid(classid));

		return PREFIX + "addStudentInClass";
	}

	@PostMapping("/admin/saveStudentInClass")
	public String saveStudentsInClass(RedirectAttributes redirectAttributes, ModelMap model,
			@ModelAttribute("classid") Long classid,
			@RequestParam(required = false, name = "checkSelected") List<Long> listCheckedId) {

		List<Long> listStudentIdInClass = studentInClassService.getListStudenIdtByClassid(classid);
		if (listCheckedId != null) {
			// check student existed from url
			for (Long userid : listCheckedId) {
				if (!_checkDuplicateStudentInClass(userid, classid)) {
					studentInClassService.saveStudentInClass(classid, userid);
				}
			}
			// check student existed in db
			for (Long check : listStudentIdInClass) {
				if (!listCheckedId.contains(check)) {
					Long id = studentInClassService.findIdByValue(check);
					studentInClassService.deleteStudentInClass(id);
				}
			}

		}
		// check list remove all
		if (listCheckedId == null) {
			studentInClassService.deleteAllByClass(classid);
		}
		redirectAttributes.addFlashAttribute("messageSuccess", messageSource
				.getMessage("assign-studentinclass-success", null, LocaleContextHolder.getLocale()));
		return "redirect:/admin/class";
	}

	// Teacher role
	@GetMapping("/teacher/getStudentInClass")
	public String getStudentInClassWithTeacherRole(ModelMap model, @ModelAttribute("classid") Long classid) {

		model.addAttribute("studentsInClass", studentInClassService.getByClassid(classid));
		model.addAttribute("class", classService.getClassByClassid(classid));

		return "/teacher/class/listStudentInClass";
	}

	private Boolean _checkDuplicateStudentInClass(Long check, Long classid) {

		List<Long> list = studentInClassService.getListStudenIdtByClassid(classid);
		if (list.contains(check)) {
			return true;
		}
		return false;
	}

	private static final String PREFIX = "/admin/studentInClass/";

}
