/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function removeEmployee(id){
    
    $.ajax({
        type:"GET",
        url: getContextPath() + "/employeedeletehandle",
        data: "id="+id
    }).done(function( msg ) {
        addMessage(msg);
        $('#emptable').trigger("reloadGrid");
    });
}

function removeDepartment(id) {
    //    setLeaf(id)
    var selparent = $('#tree').getRowData(id, true);
    var parent = $('#tree').getNodeParent(selparent);
    $.getJSON(getContextPath() + "/departmentdeletehandle?id="+id,
        function (data){
            if (data['code'] == 0) {
                $('#tree').jqGrid('delTreeNode', id);
            
                if (parent!= null) {
                    //                alert(($('#tree').jqGrid('getNodeChildren', parent)).length);
                    //                if (($('#tree').jqGrid('getNodeChildren', parent)).length == 0) {
                    //                    window.location.reload();
                    //                    var top_rowid = $('#tree tbody:first-child tr:first').attr('id');
                    //                    var top_rowid = $("tr:first","#tree").attr("id");
                    $("#tree").setSelection(parent.id, true);
                //                }
                }
                else {
                    $("#tree").setSelection($('#tree').jqGrid('getDataIDs')[0], true);
                }
                addMessage(data);
            }
        });
}