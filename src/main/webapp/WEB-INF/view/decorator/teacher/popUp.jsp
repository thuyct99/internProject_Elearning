<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-12 mg-top-2">
	<c:if test="${messageSuccess != null}">
		<div class="alert success" style="display:none">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
			<strong id="message">${messageSuccess}</strong> 
		</div>
	</c:if>				
</div>
