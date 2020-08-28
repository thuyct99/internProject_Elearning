<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title><spring:message code="elearning-math-for-kid" /></title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value="/css/welcome.css"/>" rel="stylesheet" type="text/css">
	<c:url var="loginURL" value="/login" />
</head>
<body>
	<div class="container">
		<div class="headerWelcome">WELCOME</div>
		<div class="img">
			<img src="<c:url value="/images/welcome.jpg"/>" class="welcomeImg">
		</div>

		<form method="get" action="${loginURL }">
			<button id="bt-continue" type="submit" class="btn btn-pink">Continue
				to login</button>
		</form>
	</div>
</body>
</html>