/* 
 * author OleksandrDudinskyi
 */
$(function() {
    jQuery("#allTRFs").jqGrid({
        ExpandColumn : 'name',
        url: getContextPath() +"/travelsupportalltrfs?type=AllTRFS",
        datatype: "json",
        mtype: "GET",
        rowNum:10, 
        rowList:[10,25,50,100], 
        colNames:["Employee","Destination","Date Begin","Date End","Status","Comment"],
        colModel:[
        {
            name:'employee',
            index:'employee', 
            width:270
        },

        {
            name:'destination',
            index:'name', 
            width:170
        },

        {
            name:'date_begin',
            index:'date_begin', 
            width:170
        },

        {
            name:'date_end',
            index:'date_end', 
            width:170
        },

        {
            name:'status',
            index:'status', 
            width:105
        },

        {
            name:'comment',
            index:'comment', 
            width:250
        },
        ],
        height:'auto',
        scrollOffset:0,
        viewrecords: true,
        jsonReader: {
            repeatitems: true
        },
        multiselect : false,
        caption : "All TRFs: ",
        pager: $('#allTRFspager')
    });
    $("#allTRFs").jqGrid('navGrid','#allTRFspager',
    {
        edit:false,
        add:false,
        del:false, 
        search:false,
        refresh: true
    }); 
});



