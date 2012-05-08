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
    $('#myModal').modal('show');
    $.getJSON(getContextPath() + "/ajaxemployeehandle?id="+id,
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
    $('#employee').text(data['employeeName']);
    $('#office').text(data['officeName']);
    $('#beginDate').val(data['beginDate']);
    $('#endDate').val(data['endDate']);
    prepareComboBox($("#country"), data['countries'], data['countryId']);
    prepareComboBox($("#city"), data['cities'], data['cityId'])
    $('#lineManager').text(data['lineManagerName']);
    prepareComboBox($("#projectManager"), data['projectManagers'], data['projectManagerId'])
    $('#hotelName').val(data['hotelName']);
    $('#hotelWebSite').val(data['hotelSite']);
    if (data['payByCash']) {
        $('#payByCash').attr("checked","checked")
        } else {
        $('#payByCash').removeAttr("checked")
        }
    if (data['carRental']) {
        $('#car').attr("checked","checked")
        } else {
        $('#car').removeAttr("checked")
        }
    $("#accordion3").show();
}

function prepareComboBox(combobox, data, id){
    combobox.empty();
    $.each(data, function(key, value) { 
        var selected;
        if (key == id) {
            selected = 'selected="selected"';
        }
        combobox.prepend( $('<option ' + selected + ' elemid="' + key + '">' + value + '</option>'))
    });
    
}




