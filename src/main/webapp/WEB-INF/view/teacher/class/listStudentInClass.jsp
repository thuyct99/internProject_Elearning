<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List student in class</title>

</head>
<body>
	<div style="margin: 5%">
		<h1>List students into class</h1>
		<br>
		<br>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th scope="col">User name</th>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Email</th>

				</tr>
			</thead>
			<tbody>
					<c:forEach items="${users}" var="student">
					<c:if test="${studentChecked.contains(student.getUserid())}">
						<tr>
							<td>${student.getUsername()}</td>
							<td>${student.getFirstname()}</td>
							<td>${student.getLastname()}</td>
							<td>${student.getEmail()}</td>

						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>
