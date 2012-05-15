/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * author : Merle
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
            getTrfUsingAJAX(id);
        } 
    }
}


function getTrfUsingAJAX(id){
    $('#editTrfModal').modal('show');
    $("#accordion3").hide();
    $.getJSON(getContextPath() + "/ajaxtrfshandle?id="+id,
        function (data){
            fillTrfForm(data, id);
            if(id>0){
                $('#modalTitle').text('TRF editing');
            }
            else{
                $('#modalTitle').text('TRF creating');
            }
        });
}

function fillTrfForm(data, id){
    $('#employee').text(data['employeeName']);
    $('#office').text(data['officeName']);
    $('#lineManager').text(data['lineManagerName']);
    prepareComboBox($("#projectManager"), data['projectManagers'], data['projectManagerId'])
    alert('fill employee');
    prepareComboBox($("#customer"), data['customers'], data['customerId'])
    
    if (id>0){
        //Edit trf
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
    } else {
        //New trf
        prepareComboBox($("#employee"), data['employees'], 1);
        $('#office').text(data['office']);
        $('#flightBeginDate').val(data['beginDate']);
        $('#flightEndDate').val(data['endDate']);
        prepareComboBox($("#country"), data['countries'], 1);
        prepareComboBox($("#city"), data['cities'], 1)
        $('#lineManager').text(data['lineManagerName']);
        prepareComboBox($("#projectManager"), 1);
        prepareComboBox($("#hotelName"), data['hotelNames'], 1);
        $('#hotelSite').text(data['hotelSite']);
        prepareComboBox($("#customer"), data['customers'], 1);
        $('#carRental').removeAttr("checked");
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


function processTRF(button){
//0 — save, 2 — cancel, 3 - commit
    var comment ="";
    if(button!=0) comment = $('#commentary').val();
    
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
        'commentary': comment
    }];
    $.ajax({
        url: getContextPath() + "/ajaxtrfsprocess?",
        type: "POST",
        dataType: "json",
        data: {
            "ajaxdata" : JSON.stringify(resultMap)
        }
        }).done(function( msg ) {
        addMessage(msg);
        $('#editTrfModal').modal('hide');
        $('#inprogress').trigger("reloadGrid");
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