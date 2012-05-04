<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="init.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootstrap, from Twitter</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="<%=request.getContextPath() %>/assets/css/bootstrap.css"
	rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<link
	href="<%=request.getContextPath() %>/assets/css/bootstrap-responsive.css"
	rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon"
	href="<%=request.getContextPath() %>/assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<%=request.getContextPath() %>/assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<%=request.getContextPath() %>/assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="<%=request.getContextPath() %>/assets/ico/apple-touch-icon-57-precomposed.png">
</head>
<body>
	<c:import url="menu.jsp">
		<c:param name="page" value="administrator"></c:param>
	</c:import>
	<div class="container">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span2">
					Tree will be here <br> <a data-toggle="modal" href="#myModal"
						class="btn btn-mini span1">Employee editor button</a>
				</div>
				<div class="span10">
					<fmt:message key="page.administrator.rules" />
					<br>
					<fmt:message key="page.administrator.chief" />
					<br>

					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th><fmt:message key="table.employees.name" /></th>
								<th><fmt:message key="table.employees.position" /></th>
								<th style="width: 55px;"><fmt:message
										key="table.employees.edit" /></th>
								<th style="width: 55px;"><fmt:message
										key="table.employees.remove" /></th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<tr>
								<td>John Smith</td>
								<td>Team Lead</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
							<tr>
								<td>James Brown</td>
								<td>Developer</td>
								<td><button type="submit" class="btn btn-mini"
										style="width: 55px;">
										<fmt:message key="table.employees.edit" />
									</button></td>
								<td><button type="submit" class="btn btn-mini">
										<fmt:message key="table.employees.remove" />
									</button></td>
							</tr>
						</tbody>
					</table>
					<div class="row">
						<div class="pagination span4 offset3">
							<ul>
								<li><a href="#">&larr;</a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li class="disabled"><a href="#">...</a></li>
								<li><a href="#">20</a></li>
								<li><a href="#">21</a></li>
								<li><a href="#">&rarr;</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>



	</div>
	<!-- /container -->
	<jsp:include page="modalemployee.jsp"></jsp:include>
	<jsp:include page="scripts.jsp"></jsp:include>
</body>
</html>
