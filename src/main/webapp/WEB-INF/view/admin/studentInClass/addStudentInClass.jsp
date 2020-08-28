<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List student in class</title>

</head>
<body>
	<br><br><br>
	<div class="container-fluid center">
		<h1><spring:message code="list-student-in-class"/></h1>
	</div>
	<br>
	<div class="container-fluid">
		<form class="form-group"
			action="<%=request.getContextPath()%>/admin/saveStudentInClass?classid=<c:out value='${classid}'/>"
			method="POST">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th scope="col"><spring:message code=".NO"/></th>
						<th scope="col"><spring:message code="first-name"/></th>
						<th scope="col"><spring:message code="last-name"/></th>
						<th scope="col"><spring:message code="email"/></th>
						<th scope="col"><spring:message code="status"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user" varStatus="num">
						<c:if test="${user.enabled}">
							<tr class="checkedRow row_${num.index} ${studentChecked.contains(user.getUserid()) ? 'row-grey' : ''}"> 
								<td>
									<input class="checkboxChecked" type="checkbox" name="checkSelected"
										id="checkbox_${num.index}"
										value="${user.getUserid()}"
										onclick="changeColor('.row_' + ${num.index}, '#checkbox_' + ${num.index})"
										${studentChecked.contains(user.getUserid()) ? 'checked' : ''}>
								</td>
								<td>${user.getFirstname()}</td>
								<td>${user.getLastname()}</td>
								<td>${user.getEmail()}</td>
								<td>${user.isEnabled() == true ? 'Enable' : 'Disable'}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>	
			<br>
			<div class="form-group row flex-around ">
			<input class="btn btn-pink large-btn mg-left-10" type="submit" value="Save" ${!listClassEnable.contains(classid) ? 'disabled' : ''}>
			<a href="/admin/class" class="large-btn mg-right-6" >
				<input class="btn btn-warning large-btn" type="button" value="Cancel">
			</a>
		</div>
		</form>
	</div>
	<script type="text/javascript">

		function changeColor(className, idCheckBox){
			if($(idCheckBox).is(":checked") ){
				$(className).addClass("row-grey");
			}else{
				$(className).removeClass("row-grey");
				}
			}
	</script>
</body>
</html>
