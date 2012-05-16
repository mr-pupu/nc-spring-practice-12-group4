$(function() {
    jQuery("#statushistory").jqGrid(
    {
        url:getContextPath() + "/ajaxstatushistory",
        loaderror:function(){
            alert('Error loading data');
            window.location.href="index.jsp" 
        },
        //        data: "id",
        datatype : "json",
        height : 'auto',
        //        width: '100%',
        //        mtype: "POST",
        rowNum:10, //Number of records we want to show per page
        rowList:[10,25,50,100], //Row List, to allow user to select how many rows they want to see per page
        colNames : ['Date','New Status', 'Altered by', 'Comment'],
        colModel : [
        {
            name : 'date',
            width: '100%',
            index : 'date'
            
        },
        {
            name : 'status',
            width: '50%',
            index : 'status'
            
        }, {
            name : 'who',
            width: '75%',
            index : 'who'
            
        },  {
            name : 'comment',
            index : 'comment'
        }],
        autowidth: true,
//        shrinkToFit: false,
        scrollOffset:0,
        viewrecords: true,
        jsonReader: {
            //                root: 'rows',
            repeatitems: true
        //                page: function(obj) {return 1;},
        //                total: function(obj) {return 1;},
        //                records: function(obj) {return 22;}
        },
        multiselect : false,
        caption : "Status History",
        beforeSelectRow: function(){
            jQuery("#statushistory").jqGrid('resetSelection');
            return(true);
        },
        onSelectRow: function(id){
            document.getElementById("a").setAttribute("href", '#'+id);
        },
        beforeRequest: function() {
            var q = window.location;
            var reg=/.*#(.*)/
            var arr=reg.exec(q);
            if (arr != null) {
                var id = arr[1];
                if (id != null) {
                    jQuery("#statushistory").jqGrid('setGridParam', {
                        url: getContextPath() + "/ajaxstatushistory?id="+id
                    });
                }
            }
        },
        pager: $('#statushistorypager')
    });
    var myGrid = jQuery("#statushistory");
    $("#cb_"+myGrid[0].id).hide();
    $("#statushistory").jqGrid('navGrid','#statushistorypager',
    {
        edit:false,
        add:false,
        del:false, 
        search:false,
        refresh: true
    }); 
   
});

//var fixGridWidth = function (grid) {
//    var gviewScrollWidth = grid[0].parentNode.parentNode.parentNode.scrollWidth;
//    var mainWidth = jQuery('#inner').width();
//    var gridScrollWidth = grid[0].scrollWidth;
//    var htable = jQuery('table.ui-jqgrid-htable', grid[0].parentNode.parentNode.parentNode);
//    var scrollWidth = gridScrollWidth;
//    if (htable.length > 0) {
//        var hdivScrollWidth = htable[0].scrollWidth;
//        if ((gridScrollWidth < hdivScrollWidth))
//            scrollWidth = hdivScrollWidth; // max (gridScrollWidth, hdivScrollWidth)
//    }
//    if (gviewScrollWidth != scrollWidth || scrollWidth > mainWidth) {
//        var newGridWidth = (scrollWidth <= mainWidth)? scrollWidth: mainWidth;  // min (scrollWidth, mainWidth)
//        // if the grid has no data, gridScrollWidth can be less then hdiv[0].scrollWidth
//        if (newGridWidth != gviewScrollWidth)
//            grid.jqGrid("setGridWidth", newGridWidth);
//    }
//};
//
//var fixGridHeight = function (grid) {
//    var gviewNode = grid[0].parentNode.parentNode.parentNode;
//    //var gview = grid.parent().parent().parent();
//    //var bdiv = jQuery("#gview_" + grid[0].id + " .ui-jqgrid-bdiv");
//    var bdiv = jQuery(".ui-jqgrid-bdiv", gviewNode);
//    if (bdiv.length) {
//        var delta = bdiv[0].scrollHeight - bdiv[0].clientHeight;
//        var height = grid.height();
//        if (delta !== 0 && height && (height-delta>0)) {
//            grid.setGridHeight(height-delta);
//        }
//    }
//};
//
//var fixGridSize = function (grid) {
//    this.fixGridWidth(grid);
//    this.fixGridHeight(grid);
//};
