package com.codeenginestudio.elearning.taglib;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Pagination extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCurpage() {
		return curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = this.pageContext.getOut();

		boolean isFristPage = curpage == 0;
		boolean isLastPage = curpage == totalPages - 1;

		if (curpage + 1 > totalPages) {
			curpage = totalPages;
		}

		String first = getItem("Frist", 0, isFristPage);

		String last = getItem("Last", totalPages - 1, isLastPage);

		try {

			String outStr = "<div class=\"row\" id=\"pagination\">" + "<div class=\"col-sm-12 col-md-5\">"
					+ "<div class=\"dataTables_info\" id=\"dtBasicExample_info\" role=\"status\" aria-live=\"polite\">{0}</div>"
					+ "</div>" + "<div class=\"col-sm-12 col-md-7\">"
					+ "<div class=\"dataTables_paginate paging_simple_numbers\">" + "<ul class=\"pagination\">"
					+ "{1}{2}{3}" + "</ul>" + "</div>" + "</div>" + "</div>";
			outStr = MessageFormat.format(outStr, getShowingMessage(), first, getPageSerial(), last);
			out.println(outStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	private String getPageSerial() {

		int start = curpage - 1;
		int end = curpage + 1;

		if (start < 0) {
			end += 1;
		}

		if (end > totalPages - 1) {
			start -= 1;
		}

		if (start < 0) {

			start = 0;
		}

		if (end > totalPages - 1) {
			end = totalPages - 1;
		}

		StringBuilder builder = new StringBuilder();

		if (start > 0) {

			builder.append(getItem("...", 0, true));
		}

		for (int i = start; i <= end; i++) {

			builder.append(getItem(String.valueOf(i + 1), i, i == curpage));
		}

		if (end < totalPages - 1) {

			builder.append(getItem("...", 0, true));
		}

		return builder.toString();
	}

	private String getShowingMessage() {
		int start = (curpage * ITEM_PER_PAGE) + 1;
		int end = (curpage + 1) * ITEM_PER_PAGE;
		if (end > count) {
			end = count;
		}
		return MessageFormat.format("Showing {0} to {1} of {2} entries", String.valueOf(start), String.valueOf(end),
				String.valueOf(count));
	}

	private String getItem(String text, int page, boolean disabled) {

		return MessageFormat.format("<li class=\"paginate_button page-item {0}\">"
				+ "<a class=\"page-link\" href=\"{1}?page={2}\">{3}</a>" + "</li>", disabled ? "disabled" : "", url,
				page, text);
	}

	private static final int ITEM_PER_PAGE = 10;

	private String url; // link address
	private int count;
	private int curpage;// current page
	private int pagesize; // page size
	private int totalPages; // total number of records
}
