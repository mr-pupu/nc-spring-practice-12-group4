/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * by allan && Gangbang34
 */

$(function() {
    jQuery("#tree").jqGrid(
    {
        treeGrid: true,
        treeGridModel: 'adjacency',
        url:getContextPath() + "/ajaxdepartmenthandle",
        treedatatype : "json",
        datatype: 'json',
        height : "auto",
        multiselect: 'true',
        mtype:'POST',
        //        subGrid: 'true',
        //        subGridOptions: {
        //            "plusicon" : "ui-icon-triangle-1-e", 
        //            "minusicon" : "ui-icon-triangle-1-s", 
        //            "openicon" : "ui-icon-arrowreturn-1-e"
        //        },
        colNames : ['id', 'name' ],
        colModel : [ {
            name : 'id',
            width: 1,
            hidden: true,
            key: true
        }, {
            name : 'name',
            index: 'name',
            width: '400'
        } ],
        treeIcons: {
            plus:'ui-icon-triangle-1-e',
            minus:'ui-icon-triangle-1-s',
            leaf:'ui-icon-radio-off'
        },
        //        subGridOptions: {
        //            "plusicon" : "ui-icon-triangle-1-e", 
        //            "minusicon" : "ui-icon-triangle-1-s", 
        //            "openicon" : "ui-icon-arrowreturn-1-e"
        //        },
        treeReader : {
            level_field: "level",
            parent_id_field: "parent", 
            leaf_field: "isLeaf",
            expanded_field: "expanded"
        },
        jsonReader: {
            repeatitems : false
        },
        caption: "TREE",
        //        cellEdit: true,
        viewrecords: true,
        gridview: true,
        ExpandColumn: 'name', 
        ExpandColClick: true,
        tree_root_level: 0,
        //pager:"#tree",
        autowidth: true,
        loaderror:function(){
            alert('Error loading data');
            window.location.href="index.jsp" 
       },
        onSelectRow: function(id){
            //            document.getElementById("a").setAttribute("href", '#'+id);
            jQuery("#emptable").jqGrid('setGridParam', 
            {
                url: getContextPath() + "/ajaxemployeehandle?id=" + id
            }).trigger("reloadGrid");
            $('#ediButton').attr('onclick', 'if (confirm(\'Confirm edit?\')) { editDep('+id+')}');
            var nm = ($('#tree').getRowData(id, true))['name'];
            $('#depEdit').attr('value', nm);
            $('#newButton').attr('onclick', 'if (confirm(\'Confirm new department?\')) { newDep()}');
            setCheckboxesAJAX(id);
            setDepartmentChiefAJAX(id);
        }
    })
    $("#tree").jqGrid('navGrid','#tree',
    {
        edit:true,
        add:true,
        del:true, 
        search:false,
        refresh: true,
        searchtext:"Search",
        refreshtext: "Refresh",
        'cloneToTop':true
    }); 
});

//function setCheckboxes(id){
//    
//    /*$.getJSON(getContextPath() + "/checkboxeshandle", function setc);
//    var size=3;
//    $.each(i){*/
//        
//    }
//    $.each(data, function(key, value) { 
//        string += '[' + key + ',' + value + '] ';
//        document.getElementById("").setAttribute("href", '#'+id);
//    });
//    return 0;
//}

        
        


