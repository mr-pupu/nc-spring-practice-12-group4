<%-- 
    Document   : administratorJQGridScript
    Created on : May 2, 2012, 4:29:52 PM
    Author     : OleksandrDudinskyi
--%>
<script type="text/javascript"
src="<%=request.getContextPath()%>/assets/js/jquery.jqGrid.src.js"></script>	
<script type="text/javascript"
src="<%=request.getContextPath()%>/assets/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript">
    $(function() {
        var mydata = [
            {id: "1", name: "Main department, Santiago, Chili",        level: "0", parent: "null", isLeaf: false, expanded: false, loaded: true},
            {id: "2", name: "Travel, Santiago, Chili",      level: "1", parent: "1",    isLeaf: false, expanded: false, loaded: true},
            {id: "3", name: "Sales department, Santiago, Chili",   level: "1", parent: "1",    isLeaf: true,  expanded: false, loaded: true},
            {id: "4", name: "Travel, Talca, Chili",       level: "1", parent: "1",    isLeaf: true,  expanded: false, loaded: true},
            {id: "5", name: "IT, Talca, Chili",      level: "1", parent: "1", isLeaf: true, expanded: false,  loaded: true},
            {id: "6", name: "Development, Talca, Chili",       level: "1", parent: "1",    isLeaf: false,  expanded: false, loaded: true},
            {id: "7", name: "Software sales, Santiago, Chili",       level: "2", parent: "6",    isLeaf: true,  expanded: false, loaded: true},
            {id: "8", name: "Hardware sales, Santiago, Chili",  level: "2", parent: "6", isLeaf: true,  expanded: false, loaded: true}
        ],
        grid = $("#addtree");
        $.jgrid.formatter.integer.thousandsSeparator=',';
        $.jgrid.formatter.number.thousandsSeparator=',';
        $.jgrid.formatter.currency.thousandsSeparator=',';
        grid.jqGrid({
            datatype: "jsonstring",
            datastr: mydata,
            colNames:["id","Departments"],
            colModel:[
                {name:'id',index:'id', width:1,hidden:true,key:true, editable:true},
                {name:'name',index:'name', width:185, editable:true}
            ],
            height:'auto',
            pager : "#paddtree",
            treeGrid: true,
            treeGridModel: 'adjacency',
            treedatatype: "local",
            ExpandColumn : 'name',
            jsonReader: {
                repeatitems: false,
                root: function (obj) { return obj; },
                page: function () { return 1; },
                total: function () { return 1; },
                records: function (obj) { return obj.length; }
            }
        });
        jQuery("#addtree").jqGrid('navGrid',"#paddtree");
    });
    jQuery("#addtree2").jqGrid({
   	url: '<%=request.getContextPath()%>/jqtree?q=1&action=fetchData',
	treedatatype: "xml",
	mtype: "POST",
   	colNames:["id","Account"],
   	colModel:[
   		{name:'id',index:'id', width:1,hidden:true,key:true, editable:true},
   		{name:'name',index:'name', width:180, editable:true},
   	],
	height:'auto',
	pager : "#paddtree2",
    treeGrid: true,
	ExpandColumn : 'name',
	editurl:'server.php?q=dummy',
	caption: "Add Tree node example"
});
jQuery("#addtree2").jqGrid('navGrid',"#paddtree2");
</script>
