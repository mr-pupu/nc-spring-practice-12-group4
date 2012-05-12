/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * by Gangbang34 and Allan
 */

$(function() {
    
    jQuery("#emptable").jqGrid(
    {
        url:getContextPath() + "/ajaxemployeehandle?id=1",
        datatype : "json",
        height : 'auto',
        mtype: "POST",
        rowNum:10, //Number of records we want to show per page
        rowList:[10,25,50,100], //Row List, to allow user to select how many rows they want to see per page
        colNames : ['Name', 'Position'],
        colModel : [
        {
            name : 'name',
            index : 'name',
            width : 400
        }, {
            name : 'position',
            index : 'position',
            width : 300
        }],
        //        autowidth: true,
        scrollOffset:0,
        pgtext : "{0} of {1}",
        recordtext : "{0} - {1} of {2}",
        viewrecords: true,
        jsonReader: {
            //                root: 'rows',
            repeatitems: true
        //                page: function(obj) {return 1;},
        //                total: function(obj) {return 1;},
        //                records: function(obj) {return 22;}
        },
        multiselect : true,
        caption : "Department employees",
        beforeSelectRow: function(rowid, e){
            jQuery("#emptable").jqGrid('resetSelection');
            return(true);
        },
        onSelectRow: function(id){
            document.getElementById("employeeedit").setAttribute("href", '#'+id);
        },
        pager: $('#divid')
    });
    var myGrid = jQuery("#emptable");
    $("#cb_"+myGrid[0].id).hide();
        
    $("#emptable").jqGrid('navGrid','#divid',
    {
        //        modal:true,
        edit:false,
        add:false,
        del:false, 
        search:false,
        refresh: true
//        searchtext:"Search",
//        refreshtext: "Refresh"
//        'cloneToTop':true
    }); 
});