<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/functions.tld" prefix="functions"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head/head.jsp" />
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<jsp:include page="fragments/header.jsp" />
				<div class="container">
					<div class="row clearfix">
						<div class="col-md-1 column"></div>
						<div class="col-md-10 column">
							<script>
								window.onload = function() {
									validation.clearMessage();
								};
							</script>
							<c:if test="${functions:isLoggedIn() }">
								<form:form method="post" commandName="message"
									action="/postMessage" name="f" class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-1 control-label" for="name">Message:</label>
										<div class="col-sm-4">
											<form:input path="value" id="value" name="value"
												class="form-control" />
										</div>
										<div class="col-sm-3">
											<button type="submit"
												class="btn btn-primary btn-large btn-info">Post</button>
										</div>
								
									</div>
								</form:form>
								<br>
							</c:if>
							<c:forEach var="message" items="${messages}">
								<blockquote>
									<p class="text-info">
										<strong>${message.value}</strong>
									</p>
									<footer>
										<strong>${message.user.username}</strong> @
										${functions:formatDate(message.date)}
									</footer>
								</blockquote>
								<hr>
							</c:forEach>
						</div>
						<div class="col-md-2 column"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>