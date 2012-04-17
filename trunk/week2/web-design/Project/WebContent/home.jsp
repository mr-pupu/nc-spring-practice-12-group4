<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setBundle
	basename="${localeBean.bundlePath}_${sessionScope.bundle}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootstrap, from Twitter</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link
	href="<c:out value="${pageContext.request.contextPath}"/>/assets/css/bootstrap.css"
	rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<link
	href="<c:out value="${pageContext.request.contextPath}"/>/assets/css/bootstrap-responsive.css"
	rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon"
	href="<c:out value="${pageContext.request.contextPath}"/>/assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<c:out value="${pageContext.request.contextPath}"/>/assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<c:out value="${pageContext.request.contextPath}"/>/assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="<c:out value="${pageContext.request.contextPath}"/>/assets/ico/apple-touch-icon-57-precomposed.png">
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<%if (session.getAttribute("messageText") != null) { %>
		<jsp:include page="message.jsp"></jsp:include>
		<% } %>

		<%if (session.getAttribute("name") == null) {%>
		<h1>
			<fmt:message key="page.home.hello1.unauth" />
		</h1>
		<p>
			<fmt:message key="page.home.hello2.unauth" />
			<br>
		<p>
			<fmt:message key="page.home.hello3.unauth" />
			<br>
		<div class="row">
			<div class="span4">
				<form class="well form-vertical"
					action="<%=request.getContextPath()%>/auth" method="post">
					<input type="text" class="span3"
						placeholder="<fmt:message key="form.login.login"/>" name="login">
					<br> <input type="password" class="span3"
						placeholder="<fmt:message key="form.login.password"/>"
						name="password"> <br>
					<button type="submit" class="btn">
						<fmt:message key="form.login.signIn" />
					</button>
				</form>
			</div>
			<div class="span4"></div>
		</div>

		<%} else { %>
		<h1>
			<fmt:message key="page.home.hello1.auth" />
			<a href="<%=request.getContextPath()%>/quit"><%=session.getAttribute("name") %></a>
		</h1>
		<p>
			<fmt:message key="page.home.hello2.auth" />
			<br>
			<%} %>
		
	</div>
	<!-- /container -->
	<jsp:include page="scripts.jsp"></jsp:include>
</body>
</html>
