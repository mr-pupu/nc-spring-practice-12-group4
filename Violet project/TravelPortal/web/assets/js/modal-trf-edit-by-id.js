/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * author : Merle
 */
$("#country").change(function() {
  changeCountry($("#country option:selected").attr("sysId"));
});

$(document).ready(function(){
    checkTrfId() 
});
    
$(window).bind('hashchange', function() {
    //window.location.reload();
    //alert(window.location)
    checkTrfId()
});

function checkTrfId(){
    var q = window.location;
    var reg=/.*#(.*)/  
    var arr=reg.exec(q)
    if ((arr != null) && (arr.length >1)){
        var id = arr[1];
        var idInt = parseInt(id);
        if (id == idInt){
            //ajax request here
            getTrfUsingAJAX(id);
        } 
    }
}

function fillTrfForm(data){
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
    $('#hotelSite').val(data['hotelSite']);
    prepareComboBox($("#customer"), data['customers'], data['customerId'])
    if (data['payByCash']) {$('#payByCash').attr("checked","checked")} else {$('#payByCash').removeAttr("checked")}
    if (data['carRental']) {$('#car').attr("checked","true")} else {$('#car').removeAttr("checked")}
    $("#accordion3").show();
}

function prepareComboBox(combobox, data, id){
    combobox.empty();
    $.each(data, function(key, value) { 
        var selected;
        if (key == id) {
            selected = 'selected="selected"';
        }
        combobox.prepend( $('<option ' + selected + ' sysid="' + key + '">' + value + '</option>'))
    });
    
}

function changeCountry(id){
    //loading message here
    $.getJSON(getContextPath() + "/ajaxcities?id="+id,
        function (data){
            var string ="";
            $.each(data, function(key, value) { 
                string += '[' + key + ',' + value + '] ';
            });
            //alert(string);
            prepareComboBox($("#city"), data['cities'], id);
        });
}

function getTrfUsingAJAX(id){
    $('#myModal').modal('show');
    $("#accordion3").hide();
    $.getJSON(getContextPath() + "/ajaxtrfshandle?id="+id,
        function (data){
            fillTrfForm(data);
        });
}

$(document).ready(function(){
    checkTrfId()
    
});
    
$(window).bind('hashchange', function() {
    //window.location.reload();
    //alert(window.location)
    checkTrfId()
});

function processTRF(mode){
    alert('processTRF');
    var resultMap = [
        {'beginDate':$('#beginDate').val()}, 
        {'endDate':$('#endDate').val()},
        {'cityId':$("#city option:selected").attr("sysId")},
        {'projectManagerId':$("#projectManager option:selected").attr("sysId")},
        {'hotelName':$('#hotelName').val()},
        {'hotelSite':$('#hotelSite').val()},
        {'customerId':$("#customer option:selected").attr("sysId")},
        {'car':($('#car').attr("checked")!=undefined).toString()},
        {'payByCash':($('#payByCash').attr("checked")!=undefined).toString()},
        {'mode':mode}];
        alert(JSON.stringify(resultMap));
        $.ajax({
          url: getContextPath() + "/ajaxtrfsprocess",
          type: "POST",
          data: {"ajaxdata" : JSON.stringify(resultMap)},
          dataType: "json",
          success: function(result) {
 	     alert('success')
        }
        
});
}
 