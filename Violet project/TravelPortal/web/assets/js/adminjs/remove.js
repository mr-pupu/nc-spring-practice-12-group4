/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function removeEmployee(id){
    
    $.ajax({
        type:"GET",
        url: getContextPath() + "/employeedeletehandle",
        data: "id="+id,
        error:function(){
            alert('Can not run the servlet');
            window.location.href="index.jsp" 
        },
        success: function(data,textStatus, res) {
            alert(data);
            $('#emptable').trigger("reloadGrid");
            return 0;
        }
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