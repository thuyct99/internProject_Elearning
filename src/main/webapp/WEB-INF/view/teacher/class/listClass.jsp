<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<body>
	<br><br><br>
	<div class="container-fluid">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th scope="col"><spring:message code=".NO"/></th>
					<th scope="col"><spring:message code="class-name"/></th>
					<th scope="col"><spring:message code="teacher-name"/></th>
					<th scope="col"><spring:message code="status"/></th>
					<th scope="col"><spring:message code="total-student"/></th>
					<th scope="col"><spring:message code="action"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${classPage}" var="class" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${class.classname}</td>
						<td>${class.getUser().getUsername()}</td>
						<td>${class.getStatus() == true ? 'Active' : 'Inactive'}</td>
						<td class="pink-highlight">${class.totalStudents}</td>
						<td>
							<a href="/teacher/getStudentInClass?classid=<c:out value='${class.classid}'/>">
								<input class="btn btn-pink" value="View Students In Class">
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>