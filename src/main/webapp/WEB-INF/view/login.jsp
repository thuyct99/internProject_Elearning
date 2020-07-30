<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Log in</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<style>
#login-box {
	margin-top: 5%;
	margin-left: 46%;
}
</style>

<body>
	<div class="container">
		<div id="login-row"
			class="row justify-content-center align-items-center">
			<div id="login-column" class="col-md-6">
				<div id="login-box" class="col-md-12">
					<form id="login-form" class="form" action="/login" method="post">
						<h3 class="text-center text-info">Login</h3>
						<div class="form-group">
							<label for="username" class="text-info">Username:</label><br>
							<input type="text" name="username" id="username"
								class="form-control">
						</div>
						<div class="form-group">
							<label for="password" class="text-info">Password:</label><br>
							<input type="password" name="password" id="password"
								class="form-control">
						</div>

						<div class="text-right">
							<input type="submit" name="submit" class="btn btn-info btn-md" value="Login">
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>