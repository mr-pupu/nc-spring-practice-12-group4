/* 
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
    $("#departmentCurrentlyTrip").change(function(){
        var resultMap = [
            {
                'department':$("#departmentCurrentlyTrip option:selected").val()
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
    });
    $("#officeCurrentlyTrip").change(function(){
        var resultMap = [
            {
                'office':$("#officeCurrentlyTrip option:selected").attr("sysId")
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
    });
    $("#departmentPlannedTrips").change(function(){
        var resultMap = [
            {
                'department':$("#departmentPlannedTrips option:selected").val()
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
    });
    $("#officePlannedTrips").change(function(){
        var resultMap = [
            {
                'office':$("#officePlannedTrips option:selected").attr("sysId")
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
    });
});
function prepareComboBox(combobox, data){
    combobox.empty();
    $.each(data, function(key, value) { 
        combobox.prepend( $('<option sysid="' + key + '">' + value + '</option>'))
    });
}