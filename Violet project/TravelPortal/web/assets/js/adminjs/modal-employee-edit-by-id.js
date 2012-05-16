/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * author : Allan
 */

$(document).ready(function(){
    checkEmployeeId();
    var depEditTitle = 'Edit department <a class="close" onclick=\'$("#pop1").popover("toggle")\'>&times;</a>';
    var depEdit = 'Department name <input type="text" size="100" id="depEdit" value=""> <br>' +
    '<input type="button" id="ediButton" class="btn" value="Edit" onclick="if (confirm(\'Confirm new department?\')) { newDep(null) }">'
    $('#pop1').attr("data-content", depEdit);
    //    $('#pop1').attr("title", depEditTitle);
    //    $('#pop1').position = relative;
    
    $('#pop1').popover({
        trigger : 'manual', 
        title : depEditTitle, 
        content : depEdit,
        placement: whereToPlace
    }); 
    var depNewTitle = 'New department <a class="close" onclick=\'$("#pop2").popover("toggle")\'>&times;</a>';
    var depNew = 'Department name <input type="text" id="depNew" value=""> <br>' +
    '<input type="button" id="newButton" class="btn" value="New" onclick="if (confirm(\'Confirm edit?\')) { newDep(null) }">'
    $('#pop2').attr("data-content", depNew);
    $('#pop2').attr("title", depNewTitle);
    //    $('#pop1').position = relative;
    
    $('#pop2').popover({
        trigger : 'manual',
        title : depEditTitle, 
        content : depEdit
    }); 
});

function whereToPlace() {
    var myLeft = $(this).offset.left;
    
    if (myLeft < 500) return 'bottom';
    return 'bottom';
}

function alterPop() {
    if ($('#tree').jqGrid('getGridParam', 'selrow') == null) {
        return;
    }
    else {
        var id = $('#tree').jqGrid('getGridParam', 'selrow');
        var nm = ($('#tree').getRowData(id, true))['name'];
        $('#depEdit').attr('value', nm);
        $('#ediButton').attr('onclick', 'if (confirm(\'Confirm edit?\')) { editDep('+id+')}');
        $('#newButton').attr('onclick', 'if (confirm(\'Confirm new department?\')) { newDep('+id+')}');
    }
};

function checkButton() {
    if ($('#tree').jqGrid('getGridParam', 'selrow') == null) {
        addDynamicMessage('error', 'Select a department first');
        return;
    }
    else {
        $('#pop1').popover("toggle"); 
        alterPop();
    }
}

$(window).bind('hashchange', function() {
    //window.location.reload();
    //alert(window.location)
    checkEmployeeId()
});

function newDep(id) {

    if (id  == null) {
        $.getJSON(getContextPath() + 
            "/departmentaugmenthandler?action=new&depNew=" + $('#depNew').val()+
            "&level=0",
            function(data) {
                //            alert(data['jsdata']);
                $('#tree').jqGrid('addChildNode', data['nodeid'], id, data['jsdata']);
                $("#pop2").popover("toggle");
                addMessage(data);
            });
    }
    else {
        $.getJSON(getContextPath() + 
            "/departmentaugmenthandler?depid="+id+"&action=new&depNew="
            + $('#depNew').val()+ "&level="+(($('#tree').getRowData(id, true)).level + 1),
            function(data) {
                //            alert(data['jsdata']);
                $('#tree').jqGrid('addChildNode',data['nodeid'],id, data['jsdata']);
                $("#pop2").popover("toggle");
                addMessage(data);
            });
    } 
}


function editDep(id) {
    if (id == null) {
        return;
    }
    else {
        $.getJSON(getContextPath() + 
            "/departmentaugmenthandler?depid="+id+"&action=edit&depEdit=" + $('#depEdit').val(),
            function(data) {
                //            alert(data['jsdata']);
                $('#tree').jqGrid('setTreeRow',id, data['jsdata']);
                $("#pop1").popover("toggle");
            });
    }
}

function checkEmployeeId(){
    var q = window.location;
    var reg=/.*#(.*)/  
    var arr=reg.exec(q)
    if ((arr != null) && (arr.length >1)){
        var id = arr[1];
        var idInt = parseInt(id);
        if (id == idInt){
            //ajax request here
            getEmployeeUsingAJAX(id);
        } 
    }
}

function getEmployeeUsingAJAX(id){
    $('#editEmployeeModal').modal('show');
    $.getJSON(getContextPath() + "/ajaxemployeeshandle?id="+id,
        function (data){
            fillEmployeeForm(data, id);
            if(id>0){
                $('#modalTitle').text('Employee editing');
                $('#employeeCommit').text('Save changes');
                $('#password').removeAttr("checked")
            }
            else{
                $('#modalTitle').text('Employee creating');
                $('#employeeCommit').text('Create employee');
                $('#password').attr("checked","true")
            }
        });
    
}

function fillEmployeeForm(data, id){
    
    if(id>0){
        $('#firstName').val(data['firstName']);
        $('#lastName').val(data['lastName']);
        prepareComboBox($("#position"), data['positions'], data['positionId']);
        prepareComboBox($("#office"), data['offices'], data['officeId']);
        prepareComboBox($("#department"), data['departments'], data['departmentId']);
        $('#email').val(data['email']);
        $('#login').val(data['login']);
    }
    else{
        $('#firstName').val('');
        $('#lastName').val('');
        prepareComboBox($("#position"), data['positions'], 1);
        prepareComboBox($("#office"), data['offices'], 1);
        prepareComboBox($("#department"), data['departments'], 1);
        $('#email').val(data['']);
        $('#login').val(data['']);
    }
}

function prepareComboBox(combobox, data, id){
    combobox.empty();
    $.each(data, function(key, value) { 
        var selected;
        if (key == id) {
            selected = 'selected="selected"';
        }
        combobox.prepend( $('<option ' + selected + ' sysid="' + key + '">'+ value + '</option>'))
    });
}

function processEmployee(){
    var resultMap = [
    {
        'firstName': $('#firstName').val()
    }, 
    {
        'lastName':$('#lastName').val()
    },
    {
        'positionId':$("#position option:selected").attr("sysId")
    }, 
    {
        'officeId':$("#office option:selected").attr("sysId")
    },
    {
        'departmentId':$("#department option:selected").attr("sysId")
    },
    {
        'email':$('#email').val()
    }, 
    {
        'login':$('#login').val()
    },
    {
        'password':($('#password').attr("checked")!=undefined).toString()
    }

    ];
    $.ajax({
        url: getContextPath() + "/ajaxemployeeprocess",
        type: "POST",
        dataType: "json",
        data: {
            "ajaxdata" : JSON.stringify(resultMap)
        }
    }).done(function( msg ) {
        addMessage(msg);
        $('#editEmployeeModal').modal('hide');
        $('#emptable').trigger("reloadGrid");
    });
        
}


function checkEmployee(button){

    var q = window.location;
    var reg=/.*#(.*)/
    var arr=reg.exec(q);
    if(arr!=null){
        var id = arr[1]; 
        var p;
        if(button=="edit"){
            if ($('#emptable').jqGrid('getGridParam', 'selrow') == null) {
        
                addDynamicMessage("warning", "Employee for editing wasn't selected");
            }
            else{
                p = document.getElementById("employeeedit").getAttribute("href");
            }
        }
        else{
            p = document.getElementById("employeenew").getAttribute("href");
        }

        if((p=="#"+id)&&(id!="")){
            $('#editEmployeeModal').modal('show');
        }
    } 
    else{
        if (button=="edit"&&$('#emptable').jqGrid('getGridParam', 'selrow') == null) {
        
            addDynamicMessage("warning", "TRF for editing wasn't selected");
        }
    }
}