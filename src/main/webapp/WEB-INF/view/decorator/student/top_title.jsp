<%@ page import="com.codeenginestudio.elearning.util.SecurityUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar border-bottom navbar-expand">
	<div class="navbar-top-right col-sm-4">

		<div class="brand-top-right col-sm-4">
			<img class="logo-right" src="<c:url value="/images/logo.jpg"/>">
			<h6 class="brand-name white"><spring:message code="intern-app"/></h6>
		</div>

		<div class="col-sm-8 navbar-top-right">
			<a id="history" class="navbar-brand white tab-link" href="<c:url value="/student/assessment/history"/>">
				<spring:message code="histories"/>
			</a>
			<a id="assessment" class="navbar-brand white tab-link" href="<c:url value="/student/assessment"/>">
				<spring:message code="assignments"/>
			</a>
		</div>
	</div>

	<ul class="navbar-nav ml-auto ml-md-0"
		style="position: absolute; right: 20px; color: white;">

		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle white capitalize" href="#" id="userDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">
					${SecurityUtil.getUserPrincipal().getUsername()}
			</a>
			<div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
				<a class="dropdown-item" href="/logout"><spring:message code="logout"/></a>
			</div>
		</li>
	</ul>
</nav>
<script type="text/javascript">
	$(document).ready(function() {
		var url = window.location.href;
		if (url.includes("history")) {
			$("#history").css("border-bottom", "4px solid red");
		} else {
			$("#assessment").css("border-bottom", "4px solid red");
		}
	});
</script>
