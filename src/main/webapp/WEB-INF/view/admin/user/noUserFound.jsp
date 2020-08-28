<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/fontawesome.min.css">
</head>
<div class="container-fluid center pd-10">
	<div>
		<div>
			<h4><spring:message code="there-are-no-data.-Please-creating-the-first-user-here"/></h4>
			<a href="/admin/user/addUser?role=3">
				<button class="btn btn-pink"><spring:message code="add-new-student"/></button>
			</a>
			<a href="/admin/user/addUser?role=2">
				<button class="btn btn-warning"><spring:message code="add-new-teacher"/></button>
			</a>		
		</div>
	</div>
</div>
