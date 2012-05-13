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
    '<input type="button" class="btn" value="Add" onclick="verifyDestination()">'
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



function processTRF(state){
    alert('processTRF');
    var resultMap = [
    {
        'beginDate':$('#beginDate').val()
    }, 

    {
        'endDate':$('#endDate').val()
    },

    //{'cityId':$("#city option:selected").attr("sysId")},

    {
        'projectManagerId':$("#projectManager option:selected").attr("sysId")
    },

    //        {'hotelName':$('#hotelName').val()},

    //        {'hotelSite':$('#hotelSite').val()},

    {
        'destinationId':$("#hotelName option:selected").attr("sysId")
    },

    {
        'customerId':$("#customer option:selected").attr("sysId")
    },

    {
        'car':($('#car').attr("checked")!=undefined).toString()
    },

    {
        'payByCash':($('#payByCash').attr("checked")!=undefined).toString()
    },

    {
        'state':state
    }];
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
            prepareComboBox($("#city"), data['cities'], id);
            changeCity($("#city option:selected").attr("sysId"));
        });
}

function changeCity(id){
    $.getJSON(getContextPath() + "/ajaxhotels?id="+id,
        function (data){
            prepareHotelsComboBox($("#hotelName"), data['hotelNames'], data['hotelSites'], id)
            $('#hotelSite').val(data['hotelSites'][$("#hotelName option:selected").attr("sysId")]);
        });
}


function verifyDestination(){
    alert('addingDestination');
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