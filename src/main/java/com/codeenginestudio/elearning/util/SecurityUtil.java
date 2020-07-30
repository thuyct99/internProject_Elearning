package com.codeenginestudio.elearning.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.codeenginestudio.elearning.dto.UserPrincipal;

public class SecurityUtil {

	public static UserPrincipal getUserPrincipal() {

		return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
