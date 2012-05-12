/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    
    
    jQuery("#travelinprogress").jqGrid(
    {
        url:getContextPath() + "/ajaxinprogresstrf?travel=true",
        datatype : "json",
        height : 'auto',
        mtype: "POST",
        rowNum:10, //Number of records we want to show per page
        rowList:[10,25,50,100], //Row List, to allow user to select how many rows they want to see per page
        colNames : ['Employee', 'Destination', 'Date Begin', 'Date end', 'Status', 'Comment'],
        colModel : [
        {
            name : 'employee',
            index : 'employee'
        },
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
            index : 'comment',
            width : 200
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
        caption : "In progress TRFs",
        beforeSelectRow: function(rowid, e){
            jQuery("#travelinprogress").jqGrid('resetSelection');
            return(true);
        },
        onSelectRow: function(id){
            document.getElementById("traveledit").setAttribute("href", '#'+id);
        },
        pager: $('#travelprogresspager')
    });
    var myGrid = jQuery("#travelinprogress");
    $("#cb_"+myGrid[0].id).hide();
        
    $("#travelinprogress").jqGrid('navGrid','#travelprogresspager',
    {
        //        modal:true,
        edit:false,
        add:false,
        del:false, 
        search:false,
        refresh: true
    }); 
});


