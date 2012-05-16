/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * author : Allan
 */



$(document).ready(function(){
    prepareTRF(); 
});
    
$(window).bind('hashchange', function() {
    prepareTRF();
});


function prepareTRF(){
    
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
}

  
function checkTrfId(){
    var q = window.location;
    var reg=/.*#(.*)/  
    var arr=reg.exec(q)
    if ((arr != null) && (arr.length >1)){
        var id = arr[1];
        var idInt = parseInt(id);
        if (id == idInt){
            getTravelTrfUsingAJAX(id);
        } 
    }
}

function getTravelTrfUsingAJAX(id){
    $('#editTravelTrfModal').modal('show');
    $("#accordion3").hide();
    $.getJSON(getContextPath() + "/ajaxtraveltrfedithandle?id="+id,
        function (data){
            fillTravelTrfForm(data,id);
        });
}

function fillTravelTrfForm(data,id){
    prepareComboBox($("#employee"), data['employees'], data['employeeId']);
    $('#office').text(data['office']);
    $('#flightBeginDate').val(data['beginDate']);
    $('#flightEndDate').val(data['endDate']);
    prepareComboBox($("#country"), data['countries'], data['countryId']);
    prepareComboBox($("#city"), data['cities'], data['cityId'])
    $('#lineManager').text(data['lineManagerName']);
    prepareComboBox($("#projectManager"), data['employees'], data['projectManagerId']);
    prepareComboBox($("#hotelName"), data['hotelNames'], data['destinationId']);
    $('#hotelSite').text(data['hotelSite']);
    prepareComboBox($("#customer"), data['customers'], data['customerId']);
    if (data['carRental']) {
        $('#carRental').attr("checked","true");
    } else {
        $('#carRental').removeAttr("checked");
    }      
    if (data['payByCash']) {
        $('#payByCash').attr("checked","true");
    } else {
        $('#payByCash').removeAttr("checked");
    }
    $('#commentary').val(data['commentary']);
    
    $("#accordion3").show();
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



function processTRF(button){
    //0 — save, 1 — reject, 2 — cancel, 4 - complete
    
    var resultMap = [
    {
        'employee':$("#employee option:selected").attr("sysId")
    },
    {
        'beginDate':$('#flightBeginDate').val()
    }, 

    {
        'endDate':$('#flightEndDate').val()
    },
    {
        'projectManager':$("#projectManager option:selected").attr("sysId")
    },
    {
        'destination' :$("#hotelName option:selected").attr("sysId")
    },

    {
        'customer':$("#customer option:selected").attr("sysId")
    },

    {
        'carRental':($('#carRental').attr("checked")!=undefined).toString()
    },

    {
        'payByCash':($('#payByCash').attr("checked")!=undefined).toString()
    },
    {
        'status' : button
    },
    {
        'commentary': $('#commentary').val()
    }];
    $.ajax({
        url: getContextPath() + "/ajaxtraveltrfprocess?",
        type: "POST",
        dataType: "json",
        data: {
            "ajaxdata" : JSON.stringify(resultMap)
        }
    }).done(function( msg ) {
        addMessage(msg);
        $('#editTravelTrfModal').modal('hide');
        $('#travelinprogress').trigger("reloadGrid");
    });
}

function countryChange(){
    var countryId = $("#country option:selected").attr("sysId");
    $.getJSON(getContextPath() + "/ajaxcities?id="+countryId,
        function (data){
            prepareComboBox($("#city"), data['cities'], 1);
            changeCity($("#city option:selected").attr("sysId"));
        });
    cityChange();
}

function cityChange(){
    var cityId = $("#city option:selected").attr("sysId");
    $.getJSON(getContextPath() + "/ajaxhotels?id="+cityId,
        function (data){
            prepareComboBox($("#hotelName"), data['hotelNames'], 1);
            var hotelId = $("#hotelName option:selected").attr("sysId");
            $.each(data['hotelSites'], function(key, value){
                if (key == hotelId){
                    $('#hotelSite').text(value);
                }
            });
        });
    hotelChange();
}

function hotelChange(){
    var cityId = $("#city option:selected").attr("sysId");
    var hotelId = $("#hotelName option:selected").attr("sysId");
    $.getJSON(getContextPath() + "/ajaxhotels?id="+cityId,
        function (data){
            $.each(data['hotelSites'], function(key, value){
                if (key == hotelId){
                    $('#hotelSite').text(value);
                }
            });
        });
}

function employeeChange(){
    var employeeId = $("#employee option:selected").attr("sysId");
    $.getJSON(getContextPath() + "/ajaxtravelemployee?id="+employeeId,
        function (data){
            $('#office').text(data['office']);
            $('#lineManager').text(data['lineManagerName']);
        });
}


function addDestination(){
    var resultMap = [
    {
        'cityId':$("#city option:selected").attr("sysId")
    },

    {
        'hotelName':$("#hotelNameAdd").val()
    },

    {
        'hotelSite':$("#hotelSiteAdd").val()
    }];
    $.ajax({
        url: getContextPath() + "/ajaxdest",
        type: "POST",
        dataType: "json",
        data: {
            "ajaxdata" : JSON.stringify(resultMap)
        }
         }).done(function( msg ) {
        addMessage(msg);
        cityChange();
            $("#pop1").popover("toggle");
            $('#hotelName option[sysid="' + result['newDestinationId'] + 
                '"]').attr("selected", "selected");
    });       
}

function checkTrf(){
    var q = window.location;
    var reg=/.*#(.*)/
    var arr=reg.exec(q);
    if(arr!=null){
        var id = arr[1]; 
        var p;
        if ($('#travelinprogress').jqGrid('getGridParam', 'selrow') == null) {
        
            addDynamicMessage("warning", "TRF for editing wasn't selected");
        }
        else{
            p = document.getElementById("traveledit").getAttribute("href");
        }
   
        if((p=="#"+id)&&(id!="")){
            $('#editTravelTrfModal').modal('show');
        }
    }
    else{
        if ($('#travelinprogress').jqGrid('getGridParam', 'selrow') == null) {
        
            addDynamicMessage("warning", "TRF for editing wasn't selected");
        }
    }
}