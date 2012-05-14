<%@page import="java.util.List"%>
<%@page import="database.utilities.HibernateUtil"%>
<%@page import="database.mapping.Deprole"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="init.jsp"></jsp:include>

<%
    List<Deprole> roles = HibernateUtil.DeprolesList();
    request.setAttribute("list", roles);
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Hardosoft Travel Portal</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="administrative page">
        <meta name="author" content="Allan and Gangbang34">


        <!-- Le styles -->
        <link href="<%=request.getContextPath()%>/assets/css/bootstrap.css"
              rel="stylesheet">
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
            .popover-inner {
                position: absolute;
            } 
        </style>
        <jsp:include page="scripts.jsp"></jsp:include>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/path.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/adminjs/remove.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/adminjs/grid.treegrid.js">

        </script>
        <script type="text/javascript"
                src="<%=request.getContextPath()%>/assets/js/adminjs/department-roles.js">
        </script>
        <script type="text/javascript"
                src="<%=request.getContextPath()%>/assets/js/adminjs/department-chief.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/adminjs/administrator-tree.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/adminjs/employee-administrator-table.js">
        </script>
        <script type="text/javascript"
                src="<%=request.getContextPath()%>/assets/js/adminjs/modal-employee-edit-by-id.js">
        </script>
        <script type='text/javascript'
                src="<%=request.getContextPath()%>/assets/js/adminjs/message-maker.js">
        </script>
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
        <link rel="stylesheet" type="text/css" media="screen" href="assets/css/smoothness/jquery-ui-1.7.3.custom.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="assets/css/ui.jqgrid.css" />
    </head>
    <body>
        <c:import url="menu.jsp">
            <c:param name="page" value="administrator"></c:param>
        </c:import>
        <div class="container" id="notifier">
        </div>
        <div class="container">
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span4" >
                        <div >
                            <button type="submit" class="btn btn-success" onclick=' $("#pop2").popover("toggle"); alterPop()' id="pop2" rel="popover2">New</button>
                            <!--                            <button type="submit" class="btn btn-success" onclick=' $("#pop1").popover("toggle"); alterPop()' id="pop1" rel="popover">Edit</button>-->
                            <button type="submit" class="btn btn-success" onclick='checkButton()' id="pop1" rel="popover">Edit</button>
                            <button type="submit" 
                                    onclick="if(jQuery('#tree').getGridParam('selrow')!=null){
                                        if (confirm('Confirm deletion?')) {
                                            removeDepartment(jQuery('#tree').getGridParam('selrow'))
                                        }
                                    }" 
                                    class="btn btn-danger">
                                Remove
                            </button>
                        </div>
                        <table id="tree"></table> <br> 
                    </div>
                    <div class="span8" >
                        <!--                    &nbsp;
                                            &nbsp;
                                            <input type="checkbox" id="check1" class="checkbox" checked disabled> Common department
                                            &nbsp;
                                            &nbsp;
                                            <input type="checkbox" id="check3" enabled class="checkbox"> IT department
                                            &nbsp;
                                            &nbsp;
                                            <input type="checkbox" id="check2" enabled class="checkbox"> Travel support department-->
                        <c:forEach items="${list}" var="role">
                            <input type="checkbox"  onchange="checkboxHandler(id)"
                                   class="checkbox" id="check<c:out value="${role.id}"/>">
                            <c:out value="${role.roleName}"/>&nbsp;&nbsp;
                        </c:forEach>

                        <br>
                        <br>
                        <fmt:message key="page.administrator.chief" />
                        &nbsp;
                        &nbsp;
                        <select class="combobox"  id="depChief"  onchange="if 
                        (confirm('Confirm chief change?')) {
                            processChiefChange()
                        }else{cancelChiefChange()}">  </select>
                        <br>
                        <div align="right">
                            <a href="#-1" class="btn btn-success">New</a>
                            <a id ="employeeedit" href="#" class="btn btn-success">
                                Edit</a>
                            <button type="submit" 
                                    onclick="if(jQuery('#emptable').getGridParam('selrow')!=null){
                                    if (confirm('Confirm deletion?')) {
                                        removeEmployee(jQuery('#emptable').getGridParam('selrow'))
                                    }
                                }" 
                                    class="btn btn-danger">Remove</button>
                        </div>
                        <br>
                        <table id="emptable"></table>
                        <div id="divid"></div>

                    </div>
                </div>
            </div>



        </div>
        <!-- /container -->
        <jsp:include page="modalemployee.jsp"></jsp:include>
    </body>
</html>
