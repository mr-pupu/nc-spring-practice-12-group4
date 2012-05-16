$(function() {
    alert('true');
    jQuery("#statushistory").jqGrid(
    {
        url:getContextPath() + "/ajaxstatushistory",
        loaderror:function(){
            alert('Error loading data');
            window.location.href="index.jsp" 
       },
        data: "id",
        datatype : "json",
        height : 'auto',
        width: 'auto',
        mtype: "POST",
        rowNum:10, //Number of records we want to show per page
        rowList:[10,25,50,100], //Row List, to allow user to select how many rows they want to see per page
        colNames : ['Date','New Status', 'Who Change', 'Comment'],
        colModel : [
             {
            name : 'date',
            index : 'date'
        },
        {
            name : 'status',
            index : 'status'
        }, {
            name : 'who',
            index : 'who'
        },  {
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
        multiselect : false,
        caption : "Status History",
        beforeSelectRow: function(){
            jQuery("#statushistory").jqGrid('resetSelection');
            return(true);
        },
        onSelectRow: function(id){
            document.getElementById("a").setAttribute("href", '#'+id);
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