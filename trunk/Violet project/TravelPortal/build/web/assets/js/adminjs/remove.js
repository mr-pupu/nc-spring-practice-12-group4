/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function removeEmployee(id){
    
    $.ajax({type:"GET",
    url: getContextPath() + "/employeedeletehandle",
    data: "id="+id,
    success: function(res) {$('#emptable').trigger("reloadGrid"); return 0;}
    });
}

function removeDepartment(id){
    
    $.ajax({type:"GET",
    url: getContextPath() + "/departmentdeletehandle",
    data: "id="+id,
    success: function(res) {$('#tree').trigger("reloadGrid");}
    });
}