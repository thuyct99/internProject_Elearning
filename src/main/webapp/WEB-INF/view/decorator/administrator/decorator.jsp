<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<sitemesh:write property="head" />
	<%@ include file="/WEB-INF/view/decorator/administrator/header.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="/WEB-INF/view/decorator/administrator/top_title.jsp"%>
		<div id="wrapper">
			<div id="content-wrapper" class="col-8">
				<div class="row">
					<%@ include file="/WEB-INF/view/decorator/administrator/popUp.jsp"%>
				</div>
				<div class="container-fluid">
					<sitemesh:write property="body" />
				</div>
			</div>

			<%@ include file="/WEB-INF/view/decorator/administrator/footer.jsp"%>
		</div>
	</div>

	<%@ include file="/WEB-INF/view/decorator/administrator/model.jsp"%>

	<script type="text/javascript" src="<c:url value="/js/global.js"/>"></script>

	<script type="text/javascript">
		$(document).ready(function() {
	
			if($("#message").html() != ""){
				$(".alert").css("display", "block");
				setTimeout(function(){ $(".alert").css("display", "none"); }, 5000);
			}
		});
	</script>
</body>
</html>