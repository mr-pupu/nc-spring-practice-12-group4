/* 
 * Author OleksandrDudinskyi
 */

$(document).ready(function(){
    function compareDate(firstDate, secondDate) {
        correctFormat = firstDate.replace(/(\d+)\/(\d+)\/(\d+)/, "$3/$2/$1");
        first = new Date(correctFormat);
        correctFormat1 = secondDate.replace(/(\d+)\/(\d+)\/(\d+)/, "$3/$2/$1");
        second = new Date(correctFormat1);
        if (first>second) {
            return false;
        } else {
            return true;
        }
    }
    function postBeginDateToSevlet() {
        if (compareDate($("#beginDate").val(), $("#endDate").val())) {
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
        } else {
            alert("Begin date later than end date");
        }
    }
    function postEndDateToSevlet() {
        if (compareDate($("#beginDate").val(), $("#endDate").val())) {
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
        } else {
            alert("Begin date later than end date");
        }
    }
    function postDepartmentsSevlet() {
//        console.log($("#department option:selected").val());
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
    }
    $.getJSON(getContextPath() + "/comboboxhandler",
        function (data){
            prepareComboBox($("#department"), data['departments']);
            $('.combobox').combobox();
        });
    var beginDate = {
        useMode:2,
        target:"beginDate",
        dateFormat:"%d/%m/%Y",
        onAfterSelect: function () {
            postBeginDateToSevlet();
        }
    },
    endDate = {
        useMode:2,
        target:"endDate",
        dateFormat:"%d/%m/%Y",
        onAfterSelect: function () {
            postEndDateToSevlet();
        }
    };
    new JsDatePick(beginDate);
    new JsDatePick(endDate);
    var currentTime = new Date();
    var beginMonth = currentTime.getMonth() -1;
    var EndMonth = currentTime.getMonth();
    var day = currentTime.getDate();
    var year = currentTime.getFullYear();
    document.getElementById('beginDate').value = day + "/" + beginMonth + "/" + year;
    document.getElementById('endDate').value = day + "/" + EndMonth + "/" + year;
    postBeginDateToSevlet();
    postEndDateToSevlet();
    postDepartmentsSevlet()
    $("#department").change(function(){
        postDepartmentsSevlet()
    });
});
function prepareComboBox(combobox, data){
    combobox.empty();
    $.each(data, function(key, value) { 
        combobox.prepend( $('<option value="' + key + '">' + value + '</option>'));
    });
}