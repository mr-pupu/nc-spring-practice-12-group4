<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="init.jsp"></jsp:include>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Bootstrap, from Twitter</title>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="">
            <meta name="author" content="">

            <!-- Le styles -->
            <link href="<%=request.getContextPath()%>/assets/css/bootstrap.css"
              rel="stylesheet">
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>
        <link
            href="<%=request.getContextPath()%>/assets/css/bootstrap-responsive.css"
            rel="stylesheet">

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
              <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
            <![endif]-->

        <!-- Le fav and touch icons -->
        <link rel="shortcut icon"
              href="<%=request.getContextPath()%>/assets/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="114x114"
              href="<%=request.getContextPath()%>/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72"
              href="<%=request.getContextPath()%>/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed"
              href="<%=request.getContextPath()%>/assets/ico/apple-touch-icon-57-precomposed.png">
    </head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <!-- 
            Copyright 2009 Itamar Arjuan
            jsDatePick is distributed under the terms of the GNU General Public License.
            ****************************************************************************************
    -->
    <link rel="stylesheet" type="text/css" media="all"
          href="<c:out value="${pageContext.request.contextPath}"/>/assets/css/jsDatePick_ltr.min.css" />
    <!-- 
    -->

    <jsp:include page="scripts.jsp"></jsp:include>
        <script type="text/javascript"
        src="<c:out value="${pageContext.request.contextPath}"/>/assets/js/jsDatePick.min.1.3.js"></script>
    <script type="text/javascript"
    src="<c:out value="${pageContext.request.contextPath}"/>/assets/js/path.js"></script>
    <!-- 
    -->
    <script type="text/javascript">
        window.onload = function(){
            var first = new JsDatePick({
                useMode:2,
                target:"inputField1",
                dateFormat:"%d-%M-%Y"
            });
            second = new JsDatePick({
                useMode:2,
                target:"inputField2",
                dateFormat:"%d-%M-%Y"
            });
            third = new JsDatePick({
                useMode:2,
                target:"inputField3",
                dateFormat:"%d-%M-%Y"
            });
        };
    </script>





</head>
<body >
    <c:import url="menu.jsp">
        <c:param name="page" value="mytrfs"></c:param>
    </c:import>
    <div class="container">
        <br>
        <div class="accordion" id="accordion2">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse"
                       data-parent="#accordion2" href="#collapseOne">
                        <h4>
                            <fmt:message key="page.forms.text1" />
                        </h4>
                    </a>
                </div>
                <div id="collapseOne" class="accordion-body collapse in">
                    <div class="accordion-inner">

                        <table class="table table-bordered table-condensed">
                            <thead>
                                <tr>
                                    <th style="width: 20px;">
                                        <button type="submit" class="btn btn-mini span1">
                                            <fmt:message key="page.forms.buttonAdd" />
                                        </button>
                                    </th>
                                    <th><fmt:message key="table.trfs.destination" /></th>
                                    <th><fmt:message key="table.trfs.dateBegin" /></th>
                                    <th><fmt:message key="table.trfs.dateEnd" /></th>
                                    <th><fmt:message key="table.trfs.status" /></th>
                                    <th><fmt:message key="table.trfs.comment" /></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                <tr>
                                    <td><a href="#1"
                                           class="btn btn-mini span1"><fmt:message
                                                key="page.forms.buttonEdit" /></a></td>
                                    <td>New York U.S.A</td>
                                    <td>12.06.2012</td>
                                    <td>12.08.2012</td>
                                    <td>Entering</td>
                                    <td>Some comment</td>
                                </tr>
                                <tr>
                                    <td><a href="#2"
                                           class="btn btn-mini span1"><fmt:message
                                                key="page.forms.buttonEdit" /></a></td>
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
                            <fmt:message key="page.forms.text2" />
                        </h4>
                    </a>
                </div>
                <div id="collapseTwo" class="accordion-body collapse">
                    <div class="accordion-inner">
                        <table class="table table-bordered table-condensed">
                            <thead>
                                <tr>
                                    <th><fmt:message key="table.trfs.destination" /></th>
                                    <th><fmt:message key="table.trfs.dateBegin" /></th>
                                    <th><fmt:message key="table.trfs.dateEnd" /></th>
                                    <th><fmt:message key="table.trfs.status" /></th>
                                    <th><fmt:message key="table.trfs.comment" /></th>
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

        <table id="list4"></table>
    </div>
    <!-- /container -->
    <jsp:include page="modalform.jsp"></jsp:include>
        <script type="text/javascript"
        src="<c:out value="${pageContext.request.contextPath}"/>/assets/js/modal-trf-edit-by-id.js"></script>

    <script type="text/javascript"
    src="<c:out value="${pageContext.request.contextPath}"/>/assets/js/calendar-init.js"></script>
</body>
</html>
