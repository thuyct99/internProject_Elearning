<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><spring:message code="elearning-math-for-kid"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value="/css/custom.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="content">
		<div class="">
			<span class="title grey"><spring:message code="error-404"/></span>
		</div>
		<div class="">
			<span class="textTitle grey"><spring:message code="page-not-found"/></span>
		</div>
		<span class="description grey"><spring:message code="we-are-not-sure-what-you-looking-for"/></span>
		<span class="description grey"><spring:message code="try-returning-to-the"/> <a> <button class="btnBack" ><spring:message code="login"/></button></a>
			<spring:message code="and-starting-over"/>
		</span>
	</div>
</body>
</html>