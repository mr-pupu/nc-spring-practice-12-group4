/* 
 * JavaScript file that fill reportsCo,comboboxes and put that datas
 * into tables servlets.
 * Author OleksandrDudinskyi
 */
$(document).ready(function(){
    $.getJSON(getContextPath() + "/reportsdatahandler",
        function (data){
            prepareComboBox($("#departmentCurrentlyTrip"), data['departments']);
            prepareComboBox($("#officeCurrentlyTrip"), data['offices']);
            prepareComboBox($("#departmentPlannedTrips"), data['departments']);
            prepareComboBox($("#officePlannedTrips"), data['offices']);
        });
    function departmentCurrentlyTrip() {
        var resultMap = [
        {
            'department':$("#departmentCurrentlyTrip option:selected").val()
        }
        ];
        $.ajax({
            url: getContextPath() + "/ajaxcurrenttrips",
            error:function(){
            alert('Error loading data');
            window.location.href="index.jsp" 
       },
            type: "GET",
            data: {
                "ajaxdata" : JSON.stringify(resultMap)
            },
            dataType: "json",
            success: function(result) {
                $("#currenttrips").trigger("reloadGrid") 
            }
        });
    }
    function officeCurrentlyTrip() {
        var resultMap = [
        {
            'office':$("#officeCurrentlyTrip option:selected").attr("sysId")
        }
        ];
        $.ajax({
            url: getContextPath() + "/ajaxcurrenttrips",
           error:function(){
            alert('Error loading data');
            window.location.href="index.jsp" 
           },
            type: "GET",
            data: {
                "ajaxdata" : JSON.stringify(resultMap)
            },
            dataType: "json",
            success: function(result) {
                $("#currenttrips").trigger("reloadGrid") 
            }
        });
    }
    function departmentPlannedTrips() {
        var resultMap = [
        {
            'department':$("#departmentPlannedTrips option:selected").val()
        }
        ];
        $.ajax({
            url: getContextPath() + "/ajaxplannedtrips",
            error:function(){
            alert('Error loading data');
            window.location.href="index.jsp" 
               },
            type: "GET",
            data: {
                "ajaxdata" : JSON.stringify(resultMap)
            },
            dataType: "json",
            success: function(result) {
                $("#plannedtrips").trigger("reloadGrid") 
            }
        }); 
    }
    function officePlannedTrips() {
        var resultMap = [
        {
            'office':$("#officePlannedTrips option:selected").attr("sysId")
        }
        ];
        $.ajax({
            url: getContextPath() + "/ajaxplannedtrips",
            error:function(){
            alert('Error loading data');
            window.location.href="index.jsp" 
            },
            type: "GET",
            data: {
                "ajaxdata" : JSON.stringify(resultMap)
            },
            dataType: "json",
            success: function(result) {
                $("#plannedtrips").trigger("reloadGrid") 
            }
        }); 
    }
    departmentCurrentlyTrip();
    officeCurrentlyTrip();
    departmentPlannedTrips();
    officePlannedTrips();
    $("#departmentCurrentlyTrip").change(function () {
        departmentCurrentlyTrip();
    });
    $("#officeCurrentlyTrip").change(function () {
        officeCurrentlyTrip();
    });
    $("#departmentPlannedTrips").change(function () {
        departmentPlannedTrips();
    });
    $("#officePlannedTrips").change(function () {
        officePlannedTrips();
    });
});
function prepareComboBox(combobox, data){
    combobox.empty();
    $.each(data, function(key, value) { 
        combobox.prepend( $('<option sysid="' + key + '">' + value + '</option>'))
    });
}