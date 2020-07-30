<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/taglibs/util.tld" prefix="util"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/fontawesome.min.css">
</head>
<%
	String deleteImageAddress = "https://img.icons8.com/cotton/2x/delete-sign--v2.png";
	String editImageAddress = "https://img.icons8.com/cotton/2x/edit.png";
%>
<body>
	<div class="container-fluid">
			<div class="row">
				<div class="col-sm-7">
					<div class="alert success" style="display:none">
						<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
						<strong id="message">${messageSuccess}</strong> 
					</div>
				</div>
				<div class="col-sm-5">
					<div class="row">
						<div class="col-sm-8">
						</div>
						<div class="col-sm-4">
							<a href="/admin/user/addUser">
								<button class="btn btn-dark">Add New User</button>
							</a>
						</div>
					</div>
				</div>
			</div>
	</div>
	<br><br>
	<div class="container-fluid">
		<div class="row">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">User Name</th>
						<th scope="col">First Name</th>
						<th scope="col">Last Name</th>
						<th scope="col">Email</th>
						<th scope="col">Role</th>
						<th scope="col">Status</th>
						<th scope="col">Options</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="i" value="1" />
					<c:forEach items="${userPage.getContent()}" var="user">
						<tr>
							<td>${i}</td>
							<td>${user.getUsername()}</td>
							<td>${user.getFirstname()}</td>
							<td>${user.getLastname()}</td>
							<td>${user.getEmail()}</td>
							<td>${user.getRole().getRolename()}</td>
							<td>
								<a href="#" onclick="confirmation('/admin/user/editUserEnabled/${user.getUserid()}', 'update')">
									<button class="btn ${user.isEnabled() ? 'btn-dark' : ''}">${user.isEnabled() ? 'Active' : 'Inactive'}</button>
								</a>
							</td>
							<td>
								<a href="/admin/user/editUser/${user.getUserid()}"> 
									<img alt="edit" src="<%=editImageAddress%>" class="optionSize"/>
								</a>
								<a href="#" onclick="confirmation('/admin/user/deleteUser/${user.getUserid()}', 'delete')">
									<img alt="delete" src="<%=deleteImageAddress%>" class="optionSize"/>
								</a>
							</td>
						</tr>
						<c:set var="i" value="${i+1}" />
					</c:forEach>

				</tbody>
			</table>
		<util:pagination count="${userPage.getTotalElements()}"
			totalPages="${userPage.getTotalPages()}"
			url="${pageContext.request.contextPath}/admin/user"
			curpage="${userPage.getNumber()}" />
	</div>
	</div>

	<div id="confirm" class="modal">

		<form class="modal-content">
			<div class="container-model">
				<span onclick="document.getElementById('confirm').style.display='none'" class="close" title="Close Modal">&times;</span>
				<h1 id="title"></h1>
			<p id="ask"></p>
		
				<div class="clearfix">
					<a id="cancelConfirm" href="#" onclick="document.getElementById('confirm').style.display='none'">
						<button type="button" class="cancelbtn btn">No</button>
					</a>
					<a id="acceptConfirm" href="#"> <button type="button" class="btn-dark btn acceptbtn">Yes</button></a>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {

			if($("#message").html() != ""){
				$(".alert").css("display", "block");
				setTimeout(function(){ $(".alert").css("display", "none"); }, 5000);
			}
		});

		function confirmation(success, action) {
			
			$('#acceptConfirm').attr("href", success);
			$('#title').html(action + ' Item');
			$('#ask').html('Are you sure you want to ' + action + ' this Item ?');
			$('#confirm').show();
		}
	</script>
</body>
</html>
