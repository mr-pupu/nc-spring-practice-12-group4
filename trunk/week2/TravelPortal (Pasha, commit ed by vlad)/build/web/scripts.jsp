<!-- Le javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=request.getContextPath()%>/assets/js/jquery.js"></script>
<script
src="<%=request.getContextPath()%>/assets/js/bootstrap-transition.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap-alert.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap-modal.js"></script>
<script
src="<%=request.getContextPath()%>/assets/js/bootstrap-dropdown.js"></script>
<script
src="<%=request.getContextPath()%>/assets/js/bootstrap-scrollspy.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap-tab.js"></script>
<script
src="<%=request.getContextPath()%>/assets/js/bootstrap-tooltip.js"></script>
<script
src="<%=request.getContextPath()%>/assets/js/bootstrap-popover.js"></script>
<script
src="<%=request.getContextPath()%>/assets/js/bootstrap-button.js"></script>
<script
src="<%=request.getContextPath()%>/assets/js/bootstrap-collapse.js"></script>
<script
src="<%=request.getContextPath()%>/assets/js/bootstrap-carousel.js"></script>
<script
src="<%=request.getContextPath()%>/assets/js/bootstrap-typeahead.js"></script>
<script type="text/javascript"
src="<%=request.getContextPath()%>/assets/js/jquery.jqGrid.src.js"></script>	
<script type="text/javascript"
src="<%=request.getContextPath()%>/assets/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript">
    $(function() {
        $("#list4").jqGrid(
        {
            url:'<%=request.getContextPath()%>/jQGridTable?q=1&action=fetchData&rows=20&dep=Main department, Santiago, Chili',
            datatype : "xml",
             height: 250,
                    colNames:['#', 'Name','Position',"Action"],
                    colModel:[
                        {name:'#',index:'i', width:90,sortable:true},
                        {name:'Name',index:'Name', width:130,sortable:false},
                        {name:'Position',index:'stdStd', width:100,sortable:false},
                        {name:'view',index:'view', width:100,sortable:false}
                    ],
                    multiselect: true,
                    paging: false,
                    rowNum:10,
                    rowList:[10,20,30],
                    loadonce:true,
                    caption: "Employers in department"
                }).navGrid('#page',{edit:false,add:false,del:false});
            }
           );
        </script>
    </head>
    <body onload="fillGridOnEvent();">
        <div id="jQGrid" align="right">
        </div>
    </body>
       
	
<!--	
	
    $("#addtree").jqGrid({
        url: 'server.php?q=tree',
        treedatatype: "xml",
        mtype: "POST",
        colNames:["id","Account","Acc Num", "Debit", "Credit","Balance"],
        colModel:[
            {name:'id',index:'id', width:1,hidden:true,key:true, editable:true},
            {name:'name',index:'name', width:180, editable:true},
            {name:'num',index:'acc_num', width:80, align:"center",editable:true},
            {name:'debit',index:'debit', width:80, align:"right",editable:true},
            {name:'credit',index:'credit', width:80,align:"right",editable:true},
            {name:'balance',index:'balance', width:80,align:"right",editable:true}
        ],
        height:'auto',
        pager : "#paddtree",
        treeGrid: true,
        ExpandColumn : 'name',
        editurl:'server.php?q=dummy',
        caption: "Add Tree node example"
    });
    $("#addtree").jqGrid('navGrid',"#paddtree");
    $("#list").jqGrid({
        url:'<%=request.getContextPath()%>/JQGridTree?q=1&action=fetchData',
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
</script>
-->

