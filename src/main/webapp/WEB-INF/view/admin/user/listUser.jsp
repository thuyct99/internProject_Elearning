<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<body>
	<div class="container-fluid mg-top-2 row">
		<div class="col-sm-7 "></div>
		<div class="col-sm-5">
			<div class="row">
				<div class="col-sm-8"></div>
				<div class="col-sm-4">
					<button data-toggle="dropdown" style="margin-left: 23px"  class="btn btn-pink"><spring:message code="add-new-user"/></button>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
						<a class="dropdown-item" href="/admin/user/addUser?role=3"><spring:message code="add-new-student"/></a>
						<a class="dropdown-item" href="/admin/user/addUser?role=2"><spring:message code="add-new-teacher"/></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="container-fluid">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th scope="col"><spring:message code=".NO"/></th>
					<th scope="col"><spring:message code="username"/></th>
					<th scope="col"><spring:message code="first-name"/></th>
					<th scope="col"><spring:message code="last-name"/></th>
					<th scope="col"><spring:message code="email"/></th>
					<th scope="col"><spring:message code="role"/></th>
					<th scope="col"><spring:message code="status"/></th>
					<th scope="col"><spring:message code="total-assigned"/></th>
					<th scope="col"><spring:message code="options"/></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="i" value="1" />
				<c:forEach items="${userPage.getContent()}" var="user">
					<c:if test="${user.getRole().getRoleid() != 1}">
						<tr>
							<td>${i}</td>
							<td>${user.getUsername()}</td>
							<td>${user.getFirstname()}</td>
							<td>${user.getLastname()}</td>
							<td>${user.getEmail()}</td>
							<td>${user.getRole().getRolename()}</td>
							<td>
								<a href="#" onclick="confirmation('/admin/user/editUserEnabled/${user.getUserid()}', 'update')">
									<button class="btn ${user.isEnabled() ? 'btn-active' : 'btn-inactive'}">${user.isEnabled() ? 'Enable' : 'Disable'}</button>
								</a>
							</td>
							<td class="pink-highlight">${user.getTotalAssigned()}</td>
							<td>
								<a href="/admin/user/editUser/${user.getUserid()}"> 
									<img alt="edit" src="<c:url value="../../images/edit.png"/>" class="optionSize"/>
								</a>
								<a href="#" onclick="confirmation('/admin/user/deleteUser/${user.getUserid()}', 'delete')">
									<img alt="delete" src="<c:url value="../../images/delete.png"/>" class="optionSize"/>
								</a>
							</td>
						</tr>
					</c:if>
					<c:set var="i" value="${i+1}" />
				</c:forEach>
			</tbody>
		</table>
		<util:pagination count="${userPage.getTotalElements()}"
			totalPages="${userPage.getTotalPages()}"
			url="${pageContext.request.contextPath}/admin/user"
			curpage="${userPage.getNumber()}" />
	</div>
</body>
</html>
	