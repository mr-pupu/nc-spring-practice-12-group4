/* 
 * Author OleksandrDudinskyi
 */

$(document).ready(function(){
    $.getJSON(getContextPath() + "/comboboxhandler",
        function (data){
            prepareComboBox($("#department"), data['departments']);
        });
        var beginDate = {
                useMode:2,			
                target:"beginDate",
                dateFormat:"%d/%m/%Y",
                onAfterSelect: function () {
                    var resultMap = [
                    {
                        'beginDate': $("#beginDate").val()
                    }
                    ];
                    $.ajax({
                        url: getContextPath() + "/travelsupportalltrfs",
                        type: "POST",
                        data: {
                            "ajaxdata" : JSON.stringify(resultMap)
                        },
                        dataType: "json",
                        success: function(result) {
                            $("#allTRFs").trigger("reloadGrid") 
                        }
                    });
                }
            },
            endDate = {
                useMode:2,
                target:"endDate",
                dateFormat:"%d/%m/%Y",
                onAfterSelect: function () {
                    var resultMap = [
                    {
                        'endDate': $("#endDate").val()
                    }
                    ];
                    $.ajax({
                        url: getContextPath() + "/travelsupportalltrfs",
                        type: "POST",
                        data: {
                            "ajaxdata" : JSON.stringify(resultMap)
                        },
                        dataType: "json",
                        success: function(result) {
                            $("#allTRFs").trigger("reloadGrid") 
                        }
                    });  
                }
            };
    new JsDatePick(beginDate);
    new JsDatePick(endDate);
//    beginDate.onAfterSelect();
//    endDate.onAfterSelect();
    var currentTime = new Date();
    var beginMonth = currentTime.getMonth() -1;
    var EndMonth = currentTime.getMonth();
    var day = currentTime.getDate();
    var year = currentTime.getFullYear();
    document.getElementById('beginDate').value = day + "/" + beginMonth + "/" + year;
    document.getElementById('endDate').value = day + "/" + EndMonth + "/" + year;
    beginDate.onAfterSelect();
    endDate.onAfterSelect();
    $("#department").change(function(){
        var resultMap = [
        {
            'id':$("#department option:selected").val()
        }
        ];
        $.ajax({
            url: getContextPath() + "/travelsupportalltrfs",
            type: "POST",
            data: {
                "ajaxdata" : JSON.stringify(resultMap)
            },
            dataType: "json",
            success: function(result) {
                $("#allTRFs").trigger("reloadGrid") 
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