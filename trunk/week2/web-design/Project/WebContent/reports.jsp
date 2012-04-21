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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:import url="menu.jsp">
		<c:param name="page" value="reports"></c:param>
	</c:import>
	<div class="container">
		<br>
		<div class="accordion" id="accordion2">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle" data-toggle="collapse"
						data-parent="#accordion2" href="#collapseOne">
						<h4>
							<fmt:message key="table.reports.name" />
						</h4>
					</a>
				</div>
				<div id="collapseOne" class="accordion-body collapse in">
					<div class="accordion-inner">
						<table class="table table-condensed">
							<tbody>
								<tr>
									<td><fmt:message key="page.reports.filter.department" /></td>
									<td><select class="btn">
											<option>&lt;All&gt;</option>
											<option value="PA">ff</option>
											<option value="CT">gg</option>
									</select></td>
									<td><fmt:message key="page.reports.filter.office" /></td>
									<td><select class="btn">
											<option>&lt;All&gt;</option>
											<option value="PA">ff</option>
											<option value="CT">gg</option>
									</select></td>
								</tr>
							</tbody>
						</table>
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th><fmt:message key="table.reports.name" /></th>
									<th><fmt:message key="table.reports.office" /></th>
									<th><fmt:message key="table.reports.destination" /></th>
									<th><fmt:message key="table.reports.dateBegin" /></th>
									<th><fmt:message key="table.reports.dateEnd" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
								<tr>
									<td>New York U.S.A</td>
									<td>12.06.2012</td>
									<td>12.08.2012</td>
									<td>Entering</td>
									<td>Some comment</td>
								</tr>
								<tr>
									<td>Mexico Mexico</td>
									<td>12.06.2012</td>
									<td>12.08.2012</td>
									<td>Entering</td>
									<td>Another comment</td>
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
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle" data-toggle="collapse"
						data-parent="#accordion2" href="#collapseTwo">
						<h4>
							<fmt:message key="page.reports.plannedTrips" />
						</h4>
					</a>
				</div>
				<div id="collapseTwo" class="accordion-body collapse">
					<div class="accordion-inner">

						<table class="table table-condensed">
							<tbody>
								<tr>
									<td><fmt:message key="page.reports.filter.department" /></td>
									<td><select class="btn">
											<option>&lt;All&gt;</option>
											<option value="PA">ff</option>
											<option value="CT">gg</option>
									</select></td>
									<td><fmt:message key="page.reports.filter.office" /></td>
									<td><select class="btn">
											<option>&lt;All&gt;</option>
											<option value="PA">ff</option>
											<option value="CT">gg</option>
									</select></td>
								</tr>
							</tbody>
						</table>
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th><fmt:message key="table.reports.name" /></th>
									<th><fmt:message key="table.reports.office" /></th>
									<th><fmt:message key="table.reports.destination" /></th>
									<th><fmt:message key="table.reports.dateBegin" /></th>
									<th><fmt:message key="table.reports.dateEnd" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
								<tr>
									<td>New York U.S.A</td>
									<td>12.06.2012</td>
									<td>12.08.2012</td>
									<td>Entering</td>
									<td>Some comment</td>
								</tr>
								<tr>
									<td>Mexico Mexico</td>
									<td>12.06.2012</td>
									<td>12.08.2012</td>
									<td>Entering</td>
									<td>Another comment</td>
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
		<br>
		<button type="submit" class="btn">Exel report</button>
	</div>
	<!-- /container -->
	<jsp:include page="modalform.jsp"></jsp:include>
	<jsp:include page="scripts.jsp"></jsp:include>
</body>
</html>