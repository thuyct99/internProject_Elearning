<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.codeenginestudio.elearning.util.SecurityUtil" %>
<!-- Custom styles for this template-->

<nav class="navbar border-bottom navbar-expand">
	<div class="navbar-top-right col-sm-4">

		<div class="brand-top-right col-sm-4">
			<img class="logo-right"
				style="max-width: 32; vertical-align: middle;"
				src="https://interactive-examples.mdn.mozilla.net/media/examples/grapefruit-slice-332-332.jpg">
			<h6 class="brand-name grey">Intern App</h6>
		</div>

		<div class="col-sm-8 navbar-top-right">
			<a class="navbar-custom"
				href="<c:url value="/admin/user"/>"> <button class="grey tablink" >Users</button> </a> 
			<a class="navbar-custom"
				href="<c:url value="/admin/class"/>"> <button class="grey tablink" >Classes</button> </a>
		</div>
	</div>

	<ul class="navbar-nav ml-auto ml-md-0"
		style="position: absolute; right: 20px; color: white;">

		<li class="nav-item dropdown"><a
			class="nav-link dropdown-toggle grey capitalize" href="#" id="userDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false">${SecurityUtil.getUserPrincipal().getUsername()} </a>
			<div class="dropdown-menu dropdown-menu-right"
				aria-labelledby="userDropdown">
				<a class="dropdown-item" href="/logout">Logout</a>
			</div></li>
	</ul>
</nav>
