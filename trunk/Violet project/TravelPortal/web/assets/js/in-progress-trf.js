/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * by Gangbang34
 */

$(function() {
    
    
    jQuery("#inprogress").jqGrid(
    {
        url:getContextPath() + "/ajaxinprogresstrf?travel=false",
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
            jQuery("#inprogress").jqGrid('resetSelection');
            jQuery("#alltrfs").jqGrid('resetSelection');
            return(true);
        },
        onSelectRow: function(id){
            document.getElementById("editTrf").setAttribute("href", '#'+id);
        },
        pager: $('#progresspager')
    });
    var myGrid = jQuery("#inprogress");
    $("#cb_"+myGrid[0].id).hide();
        
    $("#inprogress").jqGrid('navGrid','#progresspager',
    {
        //        modal:true,
        edit:false,
        add:false,
        del:false, 
        search:false,
        refresh: true
    }); 
});