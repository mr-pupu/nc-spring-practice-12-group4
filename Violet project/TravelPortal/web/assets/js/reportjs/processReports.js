/* 
 * JavaScript file that fill reportsCo,comboboxes and put that datas
 * into tables servlets.
 * Author OleksandrDudinskyi
 */
$(document).ready(function(){
    $.getJSON(getContextPath() + "/reportsdatahandler",
        function (data){
            reportsComboBox($("#departmentCurrentlyTrip"), data['departments']);
            reportsComboBox($("#officeCurrentlyTrip"), data['offices']);
            reportsComboBox($("#departmentPlannedTrips"), data['departments']);
            reportsComboBox($("#officePlannedTrips"), data['offices']);
            CurrentlyTrip();
            PlannedTrips();
        });
    function CurrentlyTrip() {
        console.log($("#departmentCurrentlyTrip option:selected").val());
        var resultMap = [
        {
            'department':$("#departmentCurrentlyTrip option:selected").val(),
            'officeId':$("#officeCurrentlyTrip option:selected").attr("sysId"),
            'officeName':$("#officeCurrentlyTrip option:selected").val()
        }
        ];
        $.ajax({
            url: getContextPath() + "/ajaxcurrenttrips",
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
    function PlannedTrips() {
        var resultMap = [
        {
            'department':$("#departmentPlannedTrips option:selected").val(),
            'officedId':$("#officePlannedTrips option:selected").attr("sysId"),
            'officeName':$("#officePlannedTrips option:selected").val()
        }
        ];
        $.ajax({
            url: getContextPath() + "/ajaxplannedtrips",
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
    function reportsComboBox(combobox, data){
        combobox.empty();
        $.each(data, function(key, value) { 
            if (value!="All") {
                combobox.prepend( $('<option sysid="' + key + '">' + value + '</option>'));
            } else {
                console.log("yes");
                combobox.prepend( $('<option selected sysid="' + key + '">' + value + '</option>'));
            }
        });
    }
    $("#departmentCurrentlyTrip").change(function () {
        CurrentlyTrip();
    });
    $("#officeCurrentlyTrip").change(function () {
        CurrentlyTrip();
    });
    $("#departmentPlannedTrips").change(function () {
        PlannedTrips();
    });
    $("#officePlannedTrips").change(function () {
        PlannedTrips();
    });
});