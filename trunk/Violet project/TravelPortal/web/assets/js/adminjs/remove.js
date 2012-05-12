/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function removeEmployee(id){
    
    $.ajax({
        type:"GET",
        url: getContextPath() + "/employeedeletehandle",
        data: "id="+id,
        success: function(res) {
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
            
            if (data['success'] == 0) {
                $('#tree').jqGrid('delTreeNode', id);
            }
            if (parent!= null) {
                if (($('#tree').jqGrid('getNodeChildren', parent)).length == 0) {
                    $(window).location.reload();
                }
            }
        });
}