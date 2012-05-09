/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * author : Allan
 */

$(document).ready(function(){
    checkEmployeeId() 
});
    
$(window).bind('hashchange', function() {
    //window.location.reload();
    //alert(window.location)
    checkEmployeeId()
});


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
    var string ="";
    $.each(data, function(key, value) { 
        string += '[' + key + ',' + value + '] ';
    });
    //alert(string);
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
        data: {
            "ajaxdata" : JSON.stringify(resultMap)
            },
        dataType: "json",
        success: function(result) {
            $('#editEmployeeModal').modal('hide')
            $("#emptable").trigger("reloadGrid") 
        }
        
    });
}




