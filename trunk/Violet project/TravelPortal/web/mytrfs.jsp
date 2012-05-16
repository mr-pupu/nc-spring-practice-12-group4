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
        <meta name="author" content="">

        <!-- Le styles -->
        <link href="<%=request.getContextPath()%>/assets/css/bootstrap.css"
              rel="stylesheet">      <jsp:include page="scripts.jsp"></jsp:include>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/path.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/in-progress-trf.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/all-my-trfs.js">
        </script>

        <link rel="stylesheet" type="text/css" media="screen" href="assets/css/smoothness/jquery-ui-1.7.3.custom.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="assets/css/ui.jqgrid.css" />
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
    <!-- 
            Copyright 2009 Itamar Arjuan
            jsDatePick is distributed under the terms of the GNU General Public License.
            ****************************************************************************************
    -->
    <link rel="stylesheet" type="text/css" media="all"
          href="<%=request.getContextPath()%>/assets/css/jsDatePick_ltr.min.css" />
    <!-- 
    -->
    <script type="text/javascript"
    src="<%=request.getContextPath()%>/assets/js/jsDatePick.min.1.3.js"></script>
    <script type="text/javascript"
    src="<%=request.getContextPath()%>/assets/js/path.js"></script>
    <script type="text/javascript"
    src="<%=request.getContextPath()%>/assets/js/modal-trf-edit-by-id.js"></script>
    <!-- 
    -->

</head>
<body >
    <c:import url="menu.jsp">
        <c:param name="page" value="mytrfs"></c:param>
    </c:import>
    <div class="container" id="notifier">
    </div>
    <div class="container">
        <div>
            <a href="#"
               class="btn btn-success" id="editTrf" onclick="checkTrf()" 
               style="width: 80px">
                <fmt:message key="page.forms.buttonEdit" /></a>
            <a href="#-1"
               class="btn btn-success" id="newTrf" style="width: 80px">New</a>
            <br>
        </div>
        <br> 
        <div>                 
            <table id="inprogress"></table>
            <div id="progresspager"></div> 
        </div>
        <br>
        <table id="alltrfs"></table>
        <div id="trfspager"></div> 

    </div>
</div>
</div>
 
<!-- /container -->
<jsp:include page="modalform.jsp"></jsp:include>

    <script type="text/javascript"
    src="<%=request.getContextPath()%>/assets/js/calendar-init.js"></script>
</body>
</html>
