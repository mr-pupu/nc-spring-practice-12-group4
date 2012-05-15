/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * by Gangbang34
 */

$(function() {
    
    jQuery("#dest").jqGrid(
    {
        url:getContextPath() + "/destinationapproval",
        loaderror:function(){
            alert('Error loading data');
            window.location.href="index.jsp" 
        },
        datatype : "json",
        height : 'auto',
        mtype: "POST",
        rowNum:10, //Number of records we want to show per page
        sortable: true,
        rowList:[10,25,50,100], //Row List, to allow user to select how many rows they want to see per page
        colNames : ['Hotel', 'Site', 'City', 'Country', ' ', ' '],
        colModel : [
        {
            name : 'Hotel',
            index : 'Hotel',
            sortable: true,
            sorttype: 'text',
            width : 150
        }, {
            name : 'Site',
            index : 'Site',
            sortable: true,
            sorttype: 'text',
            width : 150
        }, {
            name : 'City',
            index : 'City',
            sortable: true,
            sorttype: 'text',
            width : 150
        }, {
            name : 'Country',
            index : 'Country',
            sortable: true,
            sorttype: 'text',
            width : 150
        }, {
            name : 'approve',
            label : 'approve',
            sortable: false,
            width : 50
        }, {
            name : 'reject',
            label : 'reject',
            sortable: false,
            width : 50
        }],
        scrollOffset:0,
        pgtext : "{0} of {1}",
        recordtext : "{0} - {1} of {2}",
        viewrecords: true,
       
        jsonReader: {
            repeatitems: true
        },
        multiselect : false,
        caption : "Unconfirmed destinations",
        beforeSelectRow: function(rowid, e){
            jQuery("#dest").jqGrid('resetSelection');
            return(true);
        },
        pager: $('#destPager')
    });
    var myGrid = jQuery("#dest");
    $("#cb_"+myGrid[0].id).hide();
        
    $("#dest").jqGrid('navGrid','#destPager',
    {
        //        modal:true,
        edit:false,
        add:false,
        del:false, 
        search:false,
        refresh: true
    }); 
});

function approver(id, app){
    $.getJSON(getContextPath() + "/destinationapprover?id=" + id + "&approve="+app, function(data) {
        $("#dest").trigger('reloadGrid');
        addMessage(data);
    });
}