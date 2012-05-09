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
            url: getContextPath() + "/?",
            type: "POST",
            data: {
                "ajaxdata" : JSON.stringify(resultMap)
            },
            dataType: "json",
            success: function(result) {
                $("#?").trigger("reloadGrid") 
            }
        });
    });
    $("#officeCurrentlyTrip").change(function(){
        var resultMap = [
            {
                'office':$("#dofficeCurrentlyTrip option:selected").val()
            }
        ];
        $.ajax({
            url: getContextPath() + "/?",
            type: "POST",
            data: {
                "ajaxdata" : JSON.stringify(resultMap)
            },
            dataType: "json",
            success: function(result) {
                $("#?").trigger("reloadGrid") 
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
            url: getContextPath() + "/?",
            type: "POST",
            data: {
                "ajaxdata" : JSON.stringify(resultMap)
            },
            dataType: "json",
            success: function(result) {
                $("#?").trigger("reloadGrid") 
            }
        });
    });
    $("#officePlannedTrips").change(function(){
        var resultMap = [
            {
                'office':$("#officePlannedTrips option:selected").val()
            }
        ];
        $.ajax({
            url: getContextPath() + "/?",
            type: "POST",
            data: {
                "ajaxdata" : JSON.stringify(resultMap)
            },
            dataType: "json",
            success: function(result) {
                $("#?").trigger("reloadGrid") 
            }
        });
    });
});
function prepareComboBox(combobox, data){
    combobox.empty();
    $.each(data, function(key, value) { 
        var selected = "";
        combobox.prepend( $('<option ' + selected + ' sysid="' + key + '">' + value + '</option>'))
    });
}