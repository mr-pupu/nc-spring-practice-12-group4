/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * by Gangbang34
 */

$(function() {
    
    jQuery("#currenttrips").jqGrid(
    {
        url:getContextPath() + "/ajaxcurrenttrips?id=1",
         loaderror:function(){
            alert('Error loading data');
            window.location.href="index.jsp" 
       },
        hidegrid: false,
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
        multiselect : false,
        
        pager: $('#currentpager')
    });
    var myGrid = jQuery("#currenttrips");
    $("#cb_"+myGrid[0].id).hide();
        
    $("#currenttrips").jqGrid('navGrid','#currentpager',
    {
        edit:false,
        add:false,
        del:false, 
        search:false,
        refresh: true
    }); 
});