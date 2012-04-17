<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="localeBean" scope="application"
	class="beans.LocalizationsBean"></jsp:useBean>
<fmt:setBundle
	basename="${localeBean.bundlePath}_${sessionScope.bundle}" />
<div class="navbar  navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="#"><fmt:message key="menu.projectName" /></a>
			<div class="nav-collapse">
				<ul class="nav">
					<li><a href="<%=request.getContextPath()%>"><fmt:message
								key="menu.home" /></a></li>
					<%	if (session.getAttribute("name") != null) {%>
					<li><a href="<%=request.getContextPath()%>/forms"><fmt:message
								key="menu.forms" /></a></li>
					<li><a href="<%=request.getContextPath()%>/administrator"><fmt:message
								key="menu.administrator" /></a></li>
					<li><a href="<%=request.getContextPath()%>/reports"><fmt:message
								key="menu.reports" /></a></li>
					<%	} %>
				</ul>
				<%	if (session.getAttribute("name") == null) {%>
				<form class="navbar-form pull-right"
					action="<c:out value="${pageContext.request.contextPath}"/>/auth"
					method="post">
					<input type="text" class="input-small" name="login"
						placeholder="<fmt:message key="form.login.login"/>"> <input
						type="password" class="input-small"
						placeholder="<fmt:message key="form.login.password"/>"
						name="password">
					<button type="submit" class="btn">
						<fmt:message key="form.login.signIn" />
					</button>
				</form>
				<%	} else { %>
				<p class="navbar-text pull-right">
					<fmt:message key="menu.logged" />
					<a href="<c:out value="${pageContext.request.contextPath}"/>/quit"><%=session.getAttribute("name") %></a>
				</p>
				<%	} %>
				<ul class="nav pull-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <fmt:message key="locale.name" /> <b
							class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<c:forEach var="entry" items="${localeBean.localizations}">
								<c:if test="${entry.value!=sessionScope.bundle}">
									<li><a href="?locale=${entry.value}">${entry.key}</a></li>
								</c:if>
							</c:forEach>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</div>