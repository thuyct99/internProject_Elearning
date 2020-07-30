package com.codeenginestudio.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codeenginestudio.elearning.service.AssessmentService;
import com.codeenginestudio.elearning.service.QuestionOfAssessmentService;
import com.codeenginestudio.elearning.service.ResultService;
import com.codeenginestudio.elearning.util.SecurityUtil;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.codeenginestudio.elearning.dto.AssessmentDTO;
import com.codeenginestudio.elearning.dto.ClassDTO;
import com.codeenginestudio.elearning.dto.ResultDTO;
import com.codeenginestudio.elearning.dto.StudentInClassDTO;
import com.codeenginestudio.elearning.service.ClassService;
import com.codeenginestudio.elearning.service.StudentInClassService;

@Controller
public class ResultController {

	@Autowired
	private AssessmentService assessmentService;

	@Autowired
	private ResultService resultService;

	@Autowired
	private ClassService classService;

	@Autowired
	private StudentInClassService studentInClassService;

	@Autowired
	private QuestionOfAssessmentService questionOfAssessmentService;

	// Student role

	@GetMapping("/student/assessment/history/viewResult/{assessmentid}")
	public String getResultWithStudentRole(Model model, @PathVariable(name = "assessmentid") Long assessmentid) {

		Long userId = SecurityUtil.getUserPrincipal().getUserid();
		model.addAttribute("listQuestionOfAssessment",
				questionOfAssessmentService.getListQuestionOfAssessmentByAssessment(assessmentid));
		model.addAttribute("assessment", assessmentService.getAssessmentByAssessmentid(assessmentid));

		List<ResultDTO> resultDTO = resultService.findByAssessmentAndStudent(assessmentid, userId);
		model.addAttribute("listResult", resultDTO);
		model.addAttribute("urlBack", "/student/assessment/history");

		return PREFIX_STUDENT + "history/viewResultAssessment";
	}

	// role Teacher

	@GetMapping("/teacher/viewResultOfStudent/{assessmentid}/{userid}")
	public String showStudentResult(Model model, @PathVariable(name = "assessmentid") Long assessmentid,
			@PathVariable(name = "userid") Long userid) {

		model.addAttribute("listQuestionOfAssessment",
				questionOfAssessmentService.getListQuestionOfAssessmentByAssessment(assessmentid));
		model.addAttribute("assessment", assessmentService.getAssessmentByAssessmentid(assessmentid));
		model.addAttribute("listResult", resultService.findByAssessmentAndStudent(assessmentid, userid));
		model.addAttribute("urlBack", "/teacher/viewResult?assessmentid=" + assessmentid);
		return PREFIX_STUDENT + "history/viewResultAssessment";
	}

	@GetMapping("/teacher/viewResult")
	public String showListStudentCompletedTheTest(Model model, @ModelAttribute("assessmentid") Long assessmentid) {

		AssessmentDTO assessment = assessmentService.getAssessmentByAssessmentid(assessmentid);
		ClassDTO classDTO = classService.getClassByClassid(assessment.getClassForeign().getClassid());
		List<Long> listIdOfStudentDTOs = resultService.getListStudentIdtByAssessmentId(assessmentid);
		List<StudentInClassDTO> listStudentsInclass = studentInClassService.getByClassid(classDTO.getClassid());

		for (StudentInClassDTO studentInClassDTO : listStudentsInclass) {
			studentInClassDTO.setScore(
					studentInClassService.setScoreForStudent(assessmentid, studentInClassDTO.getStudent().getUserid()));
		}

		assessment.setTotalquestion(questionOfAssessmentService
				.getListQuestionOfAssessmentByAssessment(assessment.getAssessmentid()).size());
		assessment.setTotalscore(questionOfAssessmentService.getTotalScoreByAssessment(assessment.getAssessmentid()));

		model.addAttribute("listStudentInClass", listStudentsInclass);
		model.addAttribute("listIdOfStudent", listIdOfStudentDTOs);
		model.addAttribute("assessment", assessment);
		model.addAttribute("class", classDTO);

		return PREFIX_TEACHER + "listStudentCompletedOrNot";
	}

	private static final String PREFIX_TEACHER = "/teacher/result/";

	private static final String PREFIX_STUDENT = "/student/assessment/";
}
