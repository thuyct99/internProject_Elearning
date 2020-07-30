<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!DOCTYPE html>
<html>
<style>
.headerWelcome {
	overflow: hidden;
	height: 100px;
	text-align: center;
	font-weight: bold;
	font-size: 150px;
	font-size: 75px;
}

.welcomeImg {
	width: 66%;
	background-position: center;
	background-size: cover;
	position: relative;
	margin-left: 17%;
}

#bt-continue {
	display: inline-block;
	margin-left: 42%;
	margin-top: 16%;
	font-size: 27px;
}
</style>
<body>
	<div class="container">
		<div class="headerWelcome">WELCOME</div>
		<div class="img">
			<img
				src="https://image.freepik.com/free-vector/font-design-word-math-with-boys-girls-illustration_1308-1103.jpg"
				class="welcomeImg">
		</div>

		<form method="get" action="/login">
			<button id="bt-continue" type="submit" class="btn btn-dark">Continue
				to login</button>
		</form>

	</div>
</body>
</html>