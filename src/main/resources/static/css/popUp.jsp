<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-12">
	<c:if test="${messageSuccess != null}">
		<c:choose>
			<c:when test="${messageSuccess.length() > 0}">
				<div class="alert success" style="display: none">
					<span class="closebtn"
						onclick="this.parentElement.style.display='none';">&times;</span>
					<strong id="message">${messageSuccess}</strong>
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert danger" style="display: none">
					<span class="closebtn"
						onclick="this.parentElement.style.display='none';">&times;</span>
					<strong id="message">${messageDanger}</strong>
				</div>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>