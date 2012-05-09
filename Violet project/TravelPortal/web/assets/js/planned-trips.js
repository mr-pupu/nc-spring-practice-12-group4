/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * by Gangbang34
 */

$(function() {
    
    jQuery("#plannedtrips").jqGrid(
    {
        url:getContextPath() + "/ajaxplannedtrips?id=1",
        datatype : "json",
        height : 'auto',
        mtype: "POST",
        rowNum:10, //Number of records we want to show per page
        rowList:[10,25,50,100], //Row List, to allow user to select how many rows they want to see per page
        colNames : ['Name', 'Office', 'Destination', 'Date Begin', 'Date end'],
        colModel : [
        {
            name : 'name',
            index : 'name'
        }, {
            name : 'office',
            index : 'office'
        }, {
            name : 'destination',
            index : 'destination'
        }, {
            name : 'date begin',
            index : 'date begin'
        }, {
            name : 'date end',
            index : 'date end'
        }],
        //        autowidth: true,
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
        caption : "Planned trips: ",
        beforeSelectRow: function(rowid, e){
            jQuery("#plannedtrips").jqGrid('resetSelection');
            return(true);
        },
        onSelectRow: function(id){
            document.getElementById("a").setAttribute("href", '#'+id);
        },
        pager: $('#plannedpager')
    });
    var myGrid = jQuery("#plannedtrips");
    $("#cb_"+myGrid[0].id).hide();
        
    $("#plannedtrips").jqGrid('navGrid','#plannedpager',
    {
        edit:false,
        add:false,
        del:false, 
        search:false,
        refresh: true
    }); 
});