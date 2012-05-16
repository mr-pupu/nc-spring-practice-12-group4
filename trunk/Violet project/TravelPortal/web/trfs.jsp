<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="init.jsp"></jsp:include>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Hardosoft Travel Portal</title>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="">
            <meta name="author" content="Allan and Dudinskiy(Filters)">

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
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/travelSupportjs/processTravelSupport.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/traveljs/travel-in-progress-trfs.js">
        </script>
        <link rel="stylesheet" type="text/css" media="screen" 
              href="<%=request.getContextPath()%>/assets/css/smoothness/jquery-ui-1.7.3.custom.css" />
        <link rel="stylesheet" type="text/css" media="screen" 
              href="<%=request.getContextPath()%>/assets/css/ui.jqgrid.css" />
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/traveljs/travel-in-progress-trfs.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/traveljs/destination-approval.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/traveljs/modal-travel-trf-edit-by-id.js">
        </script>
        <link rel="stylesheet" type="text/css" media="all"
              href="<%=request.getContextPath()%>/assets/css/jsDatePick_ltr.min.css" />
        <script type="text/javascript"
        src="<%=request.getContextPath()%>/assets/js/jsDatePick.min.1.3.js"></script>
        <script type="text/javascript"
        src="<%=request.getContextPath()%>/assets/js/traveljs/status-history.js"></script>
    </head>
    <body>
        <c:import url="menu.jsp">
            <c:param name="page" value="trfs"></c:param>
        </c:import>
        <div class="container" id="notifier">
        </div>
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
                            <div>
                                <a href="#"
                                   class="btn btn-success" id="traveledit" 
                                   onclick="checkTrf()" style="width: 80px">

                                    <span class="user"><fmt:message
                                            key="page.forms.buttonEdit" /></span></a>
                                <br>
                            </div>
                            <br> 
                            <div>                 
                                <table id="travelinprogress"></table>
                                <div id="travelprogresspager"></div> 
                            </div>
                        </div>
                    </div>
                </div>

                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse"
                           data-parent="#accordion2" href="#collapseTwo">
                            <h4>
                                All TRFs:
                            </h4>
                        </a>
                    </div>
                    <div id="collapseTwo" class="accordion-body collapse">
                        <div class="accordion-inner">

                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td><fmt:message key="page.forms.filterDepartment" /></td>
                                        <td><select class="combobox" style="width : 500px;" id="department">
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="page.forms.filterTimeframe" /></td>
                                        <td><fmt:message key="page.forms.filterTimeframe.from" />
                                            <input type="text" class="span2" id="beginDate" /> 
                                            <img src="<%=request.getContextPath()%>/assets/img/calendar/calen7.jpg"
                                                 onmousedown="document.getElementById('beginDate').focus();"
                                                 width=22px; height=22px; id="image1" /> 
                                            <fmt:message key="page.forms.filterTimeframe.to" /> <input
                                                type="text" class="span2" id="endDate" /> <img
                                                src="<%=request.getContextPath()%>/assets/img/calendar/calen7.jpg"
                                                onmousedown="document.getElementById('endDate').focus();"
                                                width=22px; height=22px; id="image2" /> </td>
                                    </tr>

                                </tbody>
                            </table> 




                            <table id="allTRFs"></table>
                            <div id="allTRFspager"></div> 

                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse"
                           data-parent="#accordion2" href="#collapseThree">
                            <h4>
                                Unconfirmed destinations:
                            </h4>
                        </a>
                    </div>
                    <div id="collapseThree" class="accordion-body collapse">
                        <div class="accordion-inner">
                            <div>
                                <table id="dest"></table>
                                <div id="destPager"></div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--    <!-- /container -->
        <jsp:include page="modaltraveltrf.jsp"></jsp:include>
            <script type="text/javascript"
            src="<%=request.getContextPath()%>/assets/js/calendar-init.js"></script>
    </body>
</html>
