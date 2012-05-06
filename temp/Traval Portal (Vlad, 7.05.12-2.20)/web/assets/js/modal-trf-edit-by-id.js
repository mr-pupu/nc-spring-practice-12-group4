/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * author : Merle
 */
$("#country").change(function() {
  changeCountry($("#country option:selected").attr("sysId"));
});


var dataAddHotelTitle = 'Add hotel <a class="close" onclick=\'$("#pop1").popover("toggle")\'>&times;</a>';
var dataAddHotel = 'Hotel name <input type="text"> <br>' +
    'Hotel website <input type="text"> <br>' +
    '<input type="button" class="btn" value="Add">'
$('#pop1').attr("data-content", dataAddHotel);
$('#pop1').attr("title", dataAddHotelTitle)
$('#pop1').popover({trigger : 'manual', title : dataAddHotelTitle, content : dataAddHotel});    

$("#city").change(function() {
  changeCity($("#city option:selected").attr("sysId"));
});

$("#hotelName").change(function() {
  $('#hotelSite').val(data['hotelSites'][$("#hotelName option:selected").attr("sysId")]);
});

$(document).ready(function(){
    checkTrfId() 
});
    
$(window).bind('hashchange', function() {
    //window.location.reload();
    //alert(window.location)
    checkTrfId()
});

function changeCity(id){
    //loading message here
    //alert("somesing, id = " + id);
    $.getJSON(getContextPath() + "/ajaxhotels?id="+id,
        function (data){
//            var string ="";
//            $.each(data['hotelNames'], function(key, value) { 
//                string += '[' + key + ',' + value + '] ';
//            });$.each(data['hotelSites'], function(key, value) { 
//                string += '[' + key + ',' + value + '] ';
//            });
//            alert(string);
            //prepareComboBox($("#hotelName"), data['hotelNames'], 1);
            prepareHotelsComboBox($("#hotelName"), data['hotelNames'], data['hotelSites'], 1)
            $('#hotelSite').val(data['hotelSites'][$("#hotelName option:selected").attr("sysId")]);
        });
}

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
    prepareComboBox($("#hotelName"), data['hotelNames'], data['destinationId'])
    $('#hotelSite').val(data['hotelSites'][data['destinationId']]);
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
        combobox.prepend( $('<option ' + selected + ' sysid="' + key + '">' + key + '-' + value + '</option>'))
    });
}

function prepareHotelsComboBox(combobox, data1, data2, id){
    combobox.empty();
    $.each(data1, function(key, value) { 
        var selected;
        if (key == id) {
            selected = 'selected="selected"';
        }
        combobox.prepend( $('<option ' + selected + ' sysid="' + key + '" site="' + data2[key] + '">' + key + '-' + value + '</option>'))
    });
    
}

function changeCountry(id){
    //loading message here
    $.getJSON(getContextPath() + "/ajaxcities?id="+id,
        function (data){
//            var string ="";
//            $.each(data, function(key, value) { 
//                string += '[' + key + ',' + value + '] ';
//            });
//            alert(string);
            prepareComboBox($("#city"), data['cities'], 1);
            changeCity($("#city option:selected").attr("sysId"));
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



function processTRF(mode){
    alert('processTRF');
    var resultMap = [
        {'beginDate':$('#beginDate').val()}, 
        {'endDate':$('#endDate').val()},
        //{'cityId':$("#city option:selected").attr("sysId")},
        {'projectManagerId':$("#projectManager option:selected").attr("sysId")},
//        {'hotelName':$('#hotelName').val()},
//        {'hotelSite':$('#hotelSite').val()},
        {'destinationId':$("#hotelName option:selected").attr("sysId")},
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
 