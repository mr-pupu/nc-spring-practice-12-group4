/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * by Gangbang34
 */

$(function() {
    var page_number=1;
    
    
    jQuery("#emptable").jqGrid(
    {
        url:getContextPath() + "/ajaxemployeehandle?id=1&page="+$("#emptable").getGridParam('selrow'),
        datatype : "json",
        height : 222,
        rowNum:10, //Number of records we want to show per page
        //rowList:[10,25,50,100], //Row List, to allow user to select how many rows they want to see per page
        colNames : ['Name', 'Position'],
        colModel : [
        {
            name : 'name',
            index : 'name',
            width : 460
        }, {
            name : 'position',
            index : 'position',
            width : 250
        }],
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
            document.getElementById("a").setAttribute("href", '#'+id);
        },
        pager: $('#divid')
    });
    //        var mydata = [ {
    //                name : ' ',
    //                position: ' '
    //            }, {
    //                name : ' ',
    //                position: ' '
    //            }, {
    //                name : ' ',
    //                position: ' '
    //            }, {
    //                name : ' ',
    //                position: ' '
    //            }, {
    //                name : ' ',
    //                position: ' '
    //            }, {
    //                name : ' ',
    //                position: ' '
    //            }, {
    //                name : ' ',
    //                position: ' '
    //            }, {
    //                name : ' ',
    //                position: ' '
    //            }, {
    //                name : ' ',
    //                position: ' '
    //            }, {
    //                name : ' ',
    //                position: ' '
    //            } ];
    //        for ( var i = 0; i <= mydata.length; i++)
    //            jQuery("#emptable").jqGrid('addRowData', i + 1, mydata[i]);
    var myGrid = jQuery("#emptable");
    $("#cb_"+myGrid[0].id).hide();
        
    $("#emptable").jqGrid('navGrid','#divid',
    {
        edit:false,
        add:false,
        del:false, 
        search:true,
        refresh: true,
        searchtext:"Search",
        refreshtext: "Refresh",
        'cloneToTop':true
    }); 
});