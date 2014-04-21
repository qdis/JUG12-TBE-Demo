<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/functions.tld" prefix="functions"%>
<nav class="navbar navbar-inverse navbar-static-top">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
				class="icon-bar"></span><span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="/">Spring BOOT</a>
	</div>

	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<c:choose>
				<c:when test="${functions:isLoggedIn() }">
					<li><a href="/logout">Logout</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/login">Login</a></li>
					<li><a href="/register">Register</a></li>
				</c:otherwise>
			</c:choose>


		</ul>

		<div class="nav navbar-form navbar-right">
			<c:if test="${functions:isLoggedIn() }">
				<h5 class="text-info">User : ${functions:getCurrentUsername() }</h5>
			</c:if>
				<ul class="pager" style="margin-top: 0px; margin-bottom: 1px">
					<li><a href="/?page=${page <= 0 ? 0 : page-1 }">Previous</a></li>
					<li><a href="/?page=${messages.size() == 0 ? page : page+1 }">Next</a></li>
				</ul>
		</div>

	</div>

</nav>

