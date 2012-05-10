/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * author : Merle
 */
$(document).ready(function(){
    $(window).bind('hashchange', function() {
        //window.location.reload();
        //alert(window.location)
        checkTrfId()
    });

    $("#country").change(function() {
        changeCountry($("#country option:selected").attr("sysId"));
    });

    $("#city").change(function() {
        changeCity($("#city option:selected").attr("sysId"));
    });

    $("#hotelName").change(function() {
        $('#hotelSite').val($("#hotelName option:selected").attr("site"));
    }); 
    
    var dataAddHotelTitle = 'Add hotel <a class="close" onclick=\'$("#pop1").popover("toggle")\'>&times;</a>';
    var dataAddHotel = 'Hotel name <input type="text" id="hotelNameAdd"> <br>' +
    'Hotel website <input type="text" id="hotelSiteAdd"> <br>' +
    '<input type="button" class="btn" value="Add" onclick="addDestination()">'
    $('#pop1').attr("data-content", dataAddHotel);
    $('#pop1').attr("title", dataAddHotelTitle)
    $('#pop1').popover({
        trigger : 'manual', 
        title : dataAddHotelTitle, 
        content : dataAddHotel
    }); 
    
    checkTrfId()
});
  
function checkTrfId(){
    var q = window.location;
    var reg=/.*#(.*)/  
    var arr=reg.exec(q)
    if ((arr != null) && (arr.length >1)){
        var id = arr[1];
        if (id == 'new'){
            createTrf();
        } else {
            var idInt = parseInt(id);
            if (id == idInt){
                getTrfUsingAJAX(id);
            } 
        }
    }
}

function createTrf(){
    alert('creating trf');
    $('#myModal').modal('show');
    $("#accordion3").hide();
    $.getJSON(getContextPath() + "/ajaxtrfshandle?new=new",
        function (data){
            alert('got the data');
            fillTrfForm(data);
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

function fillTrfForm(data){
//    var string ="";
//    $.each(data, function(key, value) { 
//        string += '[' + key + ',' + value + '] ';
//    });
    alert('start filling');
    $('#employee').text(data['employeeName']);
    $('#office').text(data['officeName']);
    $('#lineManager').text(data['lineManagerName']);
    prepareComboBox($("#projectManager"), data['projectManagers'], data['projectManagerId'])
    alert('fill employee');
    prepareComboBox($("#customer"), data['customers'], data['customerId'])
    
    if (!(data['state'] == -1)){
        //Edit trf
        alert('if+' + data['state']);
        $('#beginDate').val(data['beginDate']);
        $('#endDate').val(data['endDate']);
        prepareComboBox($("#country"), data['countries'], data['countryId']);
        prepareComboBox($("#city"), data['cities'], data['cityId'])
        prepareComboBox($("#hotelName"), data['hotelNames'], data['destinationId'])
        $('#hotelSite').val(data['hotelSites'][data['destinationId']]);
        if (data['payByCash']) {
            $('#payByCash').attr("checked","checked")
        } else {
            $('#payByCash').removeAttr("checked")
        }
        if (data['carRental']) {
            $('#car').attr("checked","true")
        } else {
            $('#car').removeAttr("checked")
        }
    } else {
        alert('else+' + data['state']);
        //New trf
        $("#city").empty();
        $("#hotelName").empty();
        $('#beginDate').val("");
        $('#endDate').val("");
        $('#hotelSite').val("");
        $('#payByCash').removeAttr("checked");
        $('#car').removeAttr("checked")
        prepareComboBox($("#country"), data['countries'], 'empty');
        //prepareComboBox($("#city"), data['cities'], data['cityId'])
    
    }
    
    $("#accordion3").show();
}

function addDestination(){
    alert('addingDestination');
    var resultMap = [
    {'cityId':$("#city option:selected").attr("sysId")},
    {'hotelName':$("#hotelNameAdd").val()},
    {'hotelSite':$("#hotelSiteAdd").val()}];
    alert(JSON.stringify(resultMap));
    $.ajax({
        url: getContextPath() + "/ajaxdest",
        type: "POST",
        data: {
            "ajaxdata" : JSON.stringify(resultMap)
        },
        dataType: "json",
        success: function(result) {
            changeCity($("#city option:selected").attr("sysId"));
            alert('success'+ result['newDestinationId']);
            $("#pop1").popover("toggle");
            $('#hotelName option[sysid="' + result['newDestinationId'] + '"]').attr("selected", "selected");
        }
    });
}

function processTRF(state){
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
    {'state':state}];
    alert(JSON.stringify(resultMap));
    $.ajax({
        url: getContextPath() + "/ajaxtrfsprocess",
        type: "POST",
        data: {
            "ajaxdata" : JSON.stringify(resultMap)
        },
        dataType: "json",
        success: function(result) {
            alert('success')
        }
    });
}

function changeCountry(id){
    $.getJSON(getContextPath() + "/ajaxcities?id="+id,
        function (data){
            prepareComboBox($("#city"), data['cities'], 1);
            changeCity($("#city option:selected").attr("sysId"));
        });
}

function changeCity(id){
    //loading message here
    //alert("somesing, id = " + id);
    $.getJSON(getContextPath() + "/ajaxhotels?id="+id,
        function (data){
            prepareHotelsComboBox($("#hotelName"), data['hotelNames'], data['hotelSites'], 1)
            $('#hotelSite').val(data['hotelSites'][$("#hotelName option:selected").attr("sysId")]);
        });
}

function prepareComboBox(combobox, data, id){
    combobox.empty();
    $.each(data, function(key, value) { 
        var selected = "";
        if (key == id) {
            selected = 'selected="selected"';
        }
        combobox.prepend( $('<option ' + selected + ' sysid="' + key + '">' + value + '</option>'))
    });
    if (id == 'empty') {
        combobox.prepend( $('<option selected=selected sysid="empty"></option>'))
    }
}

function prepareHotelsComboBox(combobox, data1, data2, id){
    combobox.empty();
    $.each(data1, function(key, value) { 
        var selected = "";
        if (key == id) {
            selected = 'selected="selected"';
        }
        combobox.prepend( $('<option ' + selected + ' sysid="' + key + '" site="' + data2[key] + '">' + value + '</option>'))
    });
}
