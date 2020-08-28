<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<style>
		p {
			color: red
		}
	</style>

	<div class="container-fluid center mg-top-2">
		<div class="col-sm-2"></div>
		<form action="<%=request.getContextPath()%>${url}" method="post" class="col-sm-8 form-general">
			<h1 class="form-title mb-4"><spring:message code="register-user"/></h1>
			<br>
			<input type="hidden" name="userid" value="${userInf.getUserid()}" />

			<div class="form-group row">
				<label for="userName" class="col-sm-3 col-form-label"><spring:message code="username"/></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="username" placeholder="<spring:message code="please-enter-user-name-here"/>"
						value="${userInf.getUsername()}">						
						<c:if test="${err.errUsername != null}">
							<div class="error">
								<p id="errUsername"><spring:message code="${err.errUsername}"/></p>
							</div>
						</c:if>
				</div>
			</div>

			<div class="form-group row">
				<label for="password" class="col-sm-3 col-form-label"><spring:message code="password"/></label>
				<div class="col-sm-8">
					<input type="password" class="form-control" name="password" placeholder="<spring:message code="please-enter-password-here"/>"
						value="${userInf.getPassword()}">
						<c:if test="${err.errPassword != null}">
							<div class="error">
								<p id="errPassword"><spring:message code="${err.errPassword}"/></p>
							</div>
						</c:if>
				</div>
			</div>

			<div class="form-group row">
				<label for="firstName" class="col-sm-3 col-form-label"><spring:message code="first-name"/></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="firstname" placeholder="<spring:message code="please-enter-first-name-here"/>"
						value="${userInf.getFirstname()}">
						<c:if test="${err.errFirstname != null}">
							<div class="error">
								<p id="errFirstname"><spring:message code="${err.errFirstname}"/></p>
							</div>
						</c:if>
				</div>
			</div>

			<div class="form-group row">
				<label for="lastName" class="col-sm-3 col-form-label"><spring:message code="last-name"/></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="lastname" placeholder="<spring:message code="please-enter-last-name-here"/>"
						value="${userInf.getLastname()}">
						<c:if test="${err.errLastname != null}">
							<div class="error">
								<p id="errLastname"><spring:message code="${err.errLastname}"/></p>
							</div>
						</c:if>
				</div>
			</div>

			<div class="form-group row">
				<label for="email" class="col-sm-3 col-form-label"><spring:message code="email"/></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="email" placeholder="<spring:message code="please-enter-email-here"/>"
						value="${userInf.getEmail()}">
						<c:if test="${err.errEmail != null}">
							<div class="error">
								<p id="errEmail"><spring:message code="${err.errEmail}"/></p>
							</div>
						</c:if>
				</div>
			</div>

			<div class="form-group row">
				<label for="gender" class="col-sm-3 col-form-label"><spring:message code="gender"/></label>
				<div class="col-sm-8 space-evenly">
					<div>
						<input type="radio" name="gender" value="Male" class="checkbox"
						${userInf.getGender() == 'Male' ? 'checked' : "" } checked><span><spring:message code="male"/></span>
					</div>
					<div>
						<input type="radio" name="gender" value="Female" class="checkbox"
						${userInf.getGender() == 'Female' ? 'checked' : "" }><span><spring:message code="female"/></span>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="role" class="col-sm-3 col-form-label"><spring:message code="role"/></label>
				<div class="col-sm-8">
						<c:choose>
							<c:when test="${roleId == null}">
								<select name="role.roleid" class="form-control">
									<c:forEach items="${listRole}" var="role">
										<option value="${role.getRoleid()}" ${userInf.getRole().getRoleid() == role.getRoleid() ? 'selected' : "" }>
												${role.getRolename()}</option>
									</c:forEach>
								</select>
							</c:when>
							<c:otherwise>
								<c:forEach items="${listRole}" var="role">
									<c:if test="${roleId eq role.getRoleid()}">
										<input type="hidden" name="role.roleid" value="${role.getRoleid()}"/>
										<input type="text" class="form-control" value="${role.getRolename()}" disabled="disabled">
									</c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>
				</div>
			</div>

			<div class="form-group row">
				<input type="hidden"
					value="${url == '/admin/user/saveAddUser' ? 'true' : userInf.isEnabled()}"
					name="enabled" />
			</div>

			<div class="form-group row flex-around mt-5">
				<input class="btn btn-pink large-btn mg-left-10" type="submit" value="Save">
				<a href="/admin/user" class="large-btn mg-right-6"><input class="large-btn btn btn-warning " type="button" value="Cancel"></a>
			</div>
		</form>
		<div  class="col-sm-2"></div>
	</div>










