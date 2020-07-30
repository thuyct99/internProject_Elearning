package com.codeenginestudio.elearning.decorator;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SiteMeshFilter extends ConfigurableSiteMeshFilter {
	private final String PRE_FIX = "/WEB-INF/view/decorator/";
	private final String NEXT_FIX = ".jsp";

	private String callDecorator(String decorator) {
		return PRE_FIX + decorator + NEXT_FIX;
	}

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/admin/**", callDecorator("administrator/decorator"));
		builder.addDecoratorPath("/teacher/**", callDecorator("teacher/decorator"));
		builder.addDecoratorPath("/student/**", callDecorator("student/decorator"));
	}
}
