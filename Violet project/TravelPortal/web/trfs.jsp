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
    <jsp:include page="scripts.jsp"></jsp:include>
    <script type='text/javascript'
            src="<%=request.getContextPath()%>/assets/js/path.js">
    </script>
    <script type='text/javascript'
            src="<%=request.getContextPath()%>/assets/js/travelSupportjs/travelSupportTables.js">
    </script>-->
    <script type='text/javascript'
            src="<%=request.getContextPath()%>/assets/js/travelSupportjs/processTravelSupport.js">
    </script>
    <link rel="stylesheet" type="text/css" media="all"
          href="<c:out value="${pageContext.request.contextPath}"/>/assets/css/jsDatePick_ltr.min.css" />
    <script type="text/javascript"
    src="<c:out value="${pageContext.request.contextPath}"/>/assets/js/jsDatePick.min.1.3.js"></script>
    <script type="text/javascript"
    src="<c:out value="${pageContext.request.contextPath}"/>/assets/js/path.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="assets/css/smoothness/jquery-ui-1.7.3.custom.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="assets/css/ui.jqgrid.css" />

</head>
<body>
    <c:import url="menu.jsp">
        <c:param name="page" value="trfs"></c:param>
    </c:import>
    <div class="container">
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
                                    <td><a data-toggle="modal" href="#myModal"
                                           class="btn btn-mini span1"><fmt:message
                                                key="page.forms.buttonEdit" /></a></td>
                                    <td>New York U.S.A</td>
                                    <td>12.06.2012</td>
                                    <td>12.08.2012</td>
                                    <td>Entering</td>
                                    <td>Some comment</td>
                                </tr>
                            </tbody>
                        </table>
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

<!--                        <form class="form-horizontal">
                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label" for="appendedInput">Calendar will be here</label>
                                    <div class="controls">
                                        <div class="input-append" rel="calendarDiv">
                                            <input class="span2" id="inputField3" size="16" type="text"><span
                                                class="add-on"><i class="icon-calendar"></i></span>
                                        </div>
                                    </div>
                                </div>
                           </fieldset>
                       </form>-->



                        <table class="table">
                            <tbody>
                                <tr>
                                    <td><fmt:message key="page.forms.filterDepartment" /></td>
                                    <td><select class="combobox" style="width : 180px;" id="department">
                                        </select></td>
                                    <td><fmt:message key="page.forms.filterTimeframe" /></td>
                                    <td><fmt:message key="page.forms.filterTimeframe.from" />
                                        <input type="text" size="12" id="beginDate" /> 
                                        <img src="<c:out value="${pageContext.request.contextPath}"/>/assets/img/calendar/icon_calendar.png"
                                             onmousedown="document.getElementById('beginDate').focus();"
                                             width=20px; height=20px; id="image1" /> <!-- <input	type="text">XX</td> -->
                                    <td><fmt:message key="page.forms.filterTimeframe.to" /> <input
                                            type="text" size="12" id="endDate" /> <img
                                            src="<c:out value="${pageContext.request.contextPath}"/>/assets/img/calendar/icon_calendar.png"
                                            onmousedown="document.getElementById('endDate').focus();"
                                            width=20px; height=20px; id="image2" /> <!--<input type="text">XX</td>  -->
                                </tr>
                            </tbody>
                        </table>
                        <table id="allTRFs"> </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /container -->

    <script type="text/javascript"
    src="<c:out value="${pageContext.request.contextPath}"/>/assets/js/calendar-init.js"></script>
    <jsp:include page="modalform.jsp"></jsp:include>
</body>
</html>
