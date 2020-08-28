<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />

<footer class="page-footer font-small blue">

	<div class="footer-copyright text-center py-3">© <fmt:formatDate value="${date}" pattern="yyyy" /> Copyright:
		<a href="http://codeenginestudio.com/"> CodeEngineStudio.com</a>
	</div>

</footer>