<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head/head.jsp" />
</head>
<body onload="document.f.username.focus();">
	<div class="container">
		<div class="row clearfix">
			<br> <br>
			<div class="col-md-3 column"></div>
			<div class="col-md-6 column">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3>
							<strong class="text-info">Login</strong>
						</h3>
					</div>
					<div class="panel-body">
						<form name="f" action="/login" method="POST" onsubmit="return validation.login()"
							class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-4 control-label" for="name">Username:</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="useraname"
										name="username" placeholder="username" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="password">Password:</label>
								<div class="col-sm-5">
									<input type="password" class="form-control" id="password"
										name="password" placeholder="password" />
								</div>
							</div>
							<input type="hidden" name="_csrf" value="${_csrf.token}" />
							<div class="form-group">
								<div class="col-sm-offset-4 col-sm-1">
									<button type="submit"
										class="btn btn-primary btn-large btn-info">Login</button>
								</div>
								<div class="col-sm-offset-1 col-sm-1">
									<a href="/" class="btn btn-primary btn-large btn-info">Cancel</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-3 column"></div>
		</div>
	</div>
</body>
</html>