package com.codeenginestudio.elearning.validation;

import java.util.List;

import org.springframework.util.StringUtils;

import com.codeenginestudio.elearning.dto.ClassDTO;
import com.codeenginestudio.elearning.service.ClassService;

public class ClassValidation {

	private static String errClassname = null;

	public static String getErrClassname() {
		return errClassname;
	}

	public static void setErrClassname(String errClassname) {
		ClassValidation.errClassname = errClassname;
	}

	public static boolean checkEmpty(String classname) {

		if (StringUtils.isEmpty(classname)) {

			ClassValidation.errClassname = "class-name-could-not-be-null";

			return false;
		}

		return true;
	}

	public static boolean checkClassnameExisted(Long classid, String classname, ClassService classService) {

		List<ClassDTO> listClass = classService.getAllClass();

		if (listClass.size() != 0) {

			for (ClassDTO existed : listClass) {

				if (classname.equals(existed.getClassname())) {

					if (classid != existed.getClassid()) {

						ClassValidation.errClassname = "class-name-already-exists";

						return false;
					} 
				}
			}
		}

		return true;
	}
}
