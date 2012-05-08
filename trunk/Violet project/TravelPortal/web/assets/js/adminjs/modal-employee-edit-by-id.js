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
            fillEmployeeForm(data);
        });
}

function fillEmployeeForm(data){
    var string ="";
    $.each(data, function(key, value) { 
        string += '[' + key + ',' + value + '] ';
    });
    //alert(string);
    $('#firstName').val(data['firstName']);
    $('#lastName').val(data['lastName']);
    prepareComboBox($("#position"), data['positions'], data['positionId']);
    prepareComboBox($("#office"), data['offices'], data['officeId']);
    $('#email').val(data['email']);
    $('#login').val(data['login']);
}

function prepareComboBox(combobox, data, id){
    combobox.empty();
    $.each(data, function(key, value) { 
        var selected;
        if (key == id) {
            selected = 'selected="selected"';
        }
        combobox.prepend( $('<option ' + selected + ' sysid="' + key + '">' + key + '-' + value + '</option>'))
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
            'email':$('#email').val()
        }, 
        {
            'login':$('#login').val()
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
            //alert('success')
        }
        
    });
}




