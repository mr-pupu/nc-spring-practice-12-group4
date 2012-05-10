/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * by Gangbang34
 */

$(function() {
    
    
    jQuery("#alltrfs").jqGrid(
    {
        url:getContextPath() + "/ajaxallmytrfs?id=1",
        datatype : "json",
        height : 'auto',
        mtype: "POST",
        rowNum:10, //Number of records we want to show per page
        rowList:[10,25,50,100], //Row List, to allow user to select how many rows they want to see per page
        colNames : ['Destination', 'Date Begin', 'Date end', 'Status', 'Comment'],
        colModel : [
        {
            name : 'destination',
            index : 'destination'
        }, {
            name : 'date begin',
            index : 'date begin'
        }, {
            name : 'date end',
            index : 'date end'
        }, {
            name : 'status',
            index : 'status'
        }, {
            name : 'comment',
            index : 'comment'
        }],
        //autowidth: true,
        scrollOffset:0,
        viewrecords: true,
        jsonReader: {
            //                root: 'rows',
            repeatitems: true
        //                page: function(obj) {return 1;},
        //                total: function(obj) {return 1;},
        //                records: function(obj) {return 22;}
        },
        multiselect : true,
        caption : "All my TRFs",
        beforeSelectRow: function(rowid, e){
            jQuery("#alltrfs").jqGrid('resetSelection');
            return(true);
        },
        onSelectRow: function(id){
            document.getElementById("a").setAttribute("href", '#'+id);
        },
        pager: $('#trfspager')
    });
    var myGrid = jQuery("#alltrfs");
    $("#cb_"+myGrid[0].id).hide();
    $("#alltrfs").jqGrid('navGrid','#trfspager',
    {
        edit:false,
        add:false,
        del:false, 
        search:false,
        refresh: true
    }); 
});