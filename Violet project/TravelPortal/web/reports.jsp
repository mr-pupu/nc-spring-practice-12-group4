<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="init.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="en">
<!-- Le styles -->
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="administrative page">
        <meta name="author" content="Sitner and Poluhovich">
        <link href="<%=request.getContextPath()%>/assets/css/bootstrap.css"
              rel="stylesheet">
        <style type="text/css">
            body {	
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>
        <jsp:include page="scripts.jsp"></jsp:include>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/path.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/reportjs/planned-trips.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/reportjs/current-trips.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/reportjs/processReports.js">
        </script>
        <link
            href="<%=request.getContextPath()%>/assets/css/bootstrap-responsive.css"
            rel="stylesheet">

        <link rel="shortcut icon"
              href="<%=request.getContextPath()%>/assets/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="114x114"
              href="<%=request.getContextPath()%>/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72"
              href="<%=request.getContextPath()%>/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed"
              href="<%=request.getContextPath()%>/assets/ico/apple-touch-icon-57-precomposed.png">

        <link rel="stylesheet" type="text/css" media="screen" href="assets/css/smoothness/jquery-ui-1.7.3.custom.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="assets/css/ui.jqgrid.css" />
    </head>
    <body>
        <c:import url="menu.jsp">
            <c:param name="page" value="reports"></c:param>
        </c:import>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <div class="container">
            <br>

            <c:if test="${deproles.contains('Travel Department')}">
                <button type="submit" class="btn" onclick="location.href='ReportSaver'">Excel report</button>
                <br>
            </c:if>  <br>
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse"
                       data-parent="#accordion2" href="#collapseOne">
                        <h4>
                            Current TRFs:
                        </h4>
                    </a>
                </div>
                <div id="collapseOne" class="accordion-body collapse in">
                    <div class="accordion-inner">
                        <table>
                            <tbody>
                                <tr>
                                    <td><fmt:message key="page.reports.filter.department" /></td>
                                        <td><select class="btn" id="departmentCurrentlyTrip" >
                                        </select></td>
                                    <td><fmt:message key="page.reports.filter.office" /></td>
                                     <td><select class="btn" id="officeCurrentlyTrip">
                                        </select></td>
                                </tr>
                            </tbody>
                        </table>


                        <table id="currenttrips"></table>
                        <div id="currentpager"></div>          
                    </div>
                </div>
            </div>
                          <br>
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

                        <table>
                            <tbody>
                                <tr>
                                    <td><fmt:message key="page.reports.filter.department" /></td>
                                    <td><select class="btn" id="departmentPlannedTrips">
                                        </select></td>
                                    <td><fmt:message key="page.reports.filter.office" /></td>
                                   <td><select class="btn" id="officePlannedTrips">
                                        </select></td>
                                </tr>
                            </tbody>
                        </table>

                        <table id="plannedtrips"></table>
                        <div id="plannedpager"></div>
                        <div class="row">
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- /container -->
        <jsp:include page="modalform.jsp"></jsp:include>
    </body>
</html>