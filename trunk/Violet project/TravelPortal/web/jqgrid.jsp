<%-- 
    Document   : jqgrid
    Created on : 01.05.2012, 23:39:07
    Author     : click
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JQGrid Special</title>               
        <script src="gridJS/jquery-1.4.3.min.js"  type="text/javascript"></script>
        <script src="gridJS/grid.locale-en.js"  type="text/javascript"></script>
        <script src="gridJS/jquery.jqGrid.min.js" type="text/javascript"></script>
        <link href="gridJS/jquery-ui-1.8.6.custom.css" rel="stylesheet" type="text/css" />
        <link href="gridJS/ui.jqgrid.css" rel="stylesheet" type="text/css" />
        <link href="gridJS/ui.multiselect.css" rel="stylesheet" type="text/css" />
        <script>
            function fillGridOnEvent(){
                $("#jQGrid").html("<table id=\"list\"></table><div id=\"page\"></div>");
                jQuery("#list").jqGrid({
                    url:'<%=request.getContextPath()%>/JQGridServlet?q=1&action=fetchData',
                    datatype: "xml",
                    height: 250,
                    colNames:['Sr. No.','Student Name','Student Std.','Student RollNo.',"Action"],
                    colModel:[
                        {name:'srNo',index:'srNo', width:90,sortable:true},
                        {name:'stdName',index:'stdName', width:130,sortable:false},
                        {name:'stdStd',index:'stdStd', width:100,sortable:false},
                        {name:'stdRollNo',index:'stdRollNo', width:180,sortable:false},
                        {name:'view',index:'view', width:100,sortable:false}
                    ],
                    multiselect: false,
                    paging: true,
                    rowNum:10,
                    rowList:[10,20,30],
                    pager: $("#page"),
                    loadonce:true,
                    caption: "Student Details"
                }).navGrid('#page',{edit:false,add:false,del:false});
            }
            jQuery().ready(function (){
                //fillGrid();
            });
        </script>
      
    </head>
    <body onload="fillGridOnEvent();">
        <div id="jQGrid" align="center">
            <jsp:include page="modalform.jsp"></jsp:include>
	<jsp:include page="scripts.jsp"></jsp:include>
        </div>
    </body>
      
</html>

