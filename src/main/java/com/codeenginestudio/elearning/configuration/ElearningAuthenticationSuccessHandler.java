package com.codeenginestudio.elearning.configuration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.codeenginestudio.elearning.constant.RoleConstant;

@Component
public class ElearningAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		boolean hasAdminRole = false;
		boolean hasTeacherRole = false;
		boolean hasStudentRole = false;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals(RoleConstant.getRoleAdmin())) {
				hasAdminRole = true;
				break;
			}
			if (grantedAuthority.getAuthority().equals(RoleConstant.getRoleTeacher())) {
				hasTeacherRole = true;
				break;
			}
			if (grantedAuthority.getAuthority().equals(RoleConstant.getRoleStudent())) {
				hasStudentRole = true;
				break;
			}
		}

		if (hasAdminRole) {
			redirectStrategy.sendRedirect(request, response, "/admin/user");
		} else if (hasTeacherRole) {
			redirectStrategy.sendRedirect(request, response, "/teacher/assessment");
		} else if (hasStudentRole) {
			redirectStrategy.sendRedirect(request, response, "/student/assessment");
		}else {
			throw new IllegalStateException();
		}
	}
}
