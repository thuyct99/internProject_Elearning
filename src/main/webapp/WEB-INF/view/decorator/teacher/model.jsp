<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="confirm" class="modal">
	<form class="modal-content">
		<div class="container-model">
 			<span onclick="document.getElementById('confirm').style.display='none'" class="close" title="Close Modal">&times;</span>
			<h1 id="title"></h1>
			<p id="ask"></p>

			<div class="clearfix">
				<a id="cancelConfirm" href="#" onclick="document.getElementById('confirm').style.display='none'">
					<button type="button" class="btn cancelbtn"><spring:message code="no"/></button>
				</a>
				<a id="acceptConfirm" href="#">
					<button type="button" class="btn-dark btn acceptbtn"><spring:message code="yes"/></button>
				</a>
			</div>
		</div>
	</form>
</div>