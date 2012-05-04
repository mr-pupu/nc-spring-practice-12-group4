<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="deproles" scope="session" value="${sessionScope.deprole}"></c:set>
<div class="navbar  navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="#"><fmt:message key="menu.projectName" /></a>
			<div class="nav-collapse">
				<ul class="nav">
					<c:if test="${deproles==null}">
						<li class="active"><a
							href="<c:out value="${pageContext.request.contextPath}"/>"><fmt:message
									key="menu.home" /></a></li>
					</c:if>
					<c:if test="${deproles!=null}">
						<li <c:if test="${param.page=='mytrfs'}">class="active"</c:if>><a
							href="<c:out value="${pageContext.request.contextPath}"/>/mytrfs"><fmt:message
									key="menu.mytrfs" /></a></li>
					</c:if>
					<c:if test="${deproles.contains('Travel Department')}">
						<li <c:if test="${param.page=='trfs'}">class="active"</c:if>><a
							href="<c:out value="${pageContext.request.contextPath}"/>/trfs"><fmt:message
									key="menu.trfs" /></a></li>
					</c:if>
					<c:if test="${sessionScope.role=='IT Department'}">
						<li <c:if test="${param.page=='administrator'}">class="active"</c:if>><a
							href="<c:out value="${pageContext.request.contextPath}"/>/administrator"><fmt:message
									key="menu.administrator" /></a></li>
					</c:if>
					<c:if test="${deproles!=null}">
						<li <c:if test="${param.page=='reports'}">class="active"</c:if>><a
							href="<c:out value="${pageContext.request.contextPath}"/>/reports"><fmt:message
									key="menu.reports" /></a></li>
					</c:if>
				</ul>
				<c:choose>
					<c:when test="${sessionScope.name==null}">
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
					</c:when>
					<c:otherwise>
						<p class="navbar-text pull-right">
							<fmt:message key="menu.logged" />
							<a
								href="<c:out value="${pageContext.request.contextPath}"/>/quit"><c:out
									value="${sessionScope.name}" /></a>
						</p>
					</c:otherwise>
				</c:choose>
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