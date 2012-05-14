/* 
 * Author OleksandrDudinskyi
 */

$(document).ready(function(){
    // This function return a date object after accepting 
    function getDateObject(dateString,dateSeperator)
    {
        var curValue=dateString;
        var sepChar=dateSeperator;
        var curPos=0;
        var cDate,cMonth,cYear;

        // day
        curPos=dateString.indexOf(sepChar);
        cDate=dateString.substring(0,curPos);
        //mounth
        endPos=dateString.indexOf(sepChar,curPos+1);
        cMonth=dateString.substring(curPos+1,endPos);

        //year
        curPos=endPos;
        endPos=curPos+5; 
        cYear=curValue.substring(curPos+1,endPos);

        //Create Date Object
        dtObject=new Date(cYear,cMonth,cDate); 
        return dtObject;
    }
    function compareDate(firstDate, secondDate) {
        first = getDateObject(firstDate, "-");
        second = getDateObject(secondDate, "-");
        if (first>second) {
            return false;
        } else {
            return true;
        }
    }
    function postDataToSevlet() {
        if (compareDate($("#beginDate").val(), $("#endDate").val())) {
            var resultMap = [
            {
                'beginDate': $("#beginDate").val(),
                'endDate': $("#endDate").val(),
                'depatmentName':$("#department option:selected").val()
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
    $.getJSON(getContextPath() + "/comboboxhandler",
        function (data){
            prepareSupportComboBox($("#department"), data['departments']);
            postDataToSevlet();
        });
    var beginDate = {
        useMode:2,			
        target:"beginDate",
        dateFormat:"%d-%m-%Y",
        onAfterSelect: function () {
            postDataToSevlet();
        }
    },
    endDate = {
        useMode:2,
        target:"endDate",
        dateFormat:"%d-%m-%Y",
        onAfterSelect: function () {
            postDataToSevlet();
        }
    };
    new JsDatePick(beginDate);
    new JsDatePick(endDate);
    var currentTime = new Date();
    var beginMonth = currentTime.getMonth();
    var EndMonth = currentTime.getMonth()+1;
    var day = currentTime.getDate();
    var year = currentTime.getFullYear();
    document.getElementById('beginDate').value = day + "-" + beginMonth + "-" + year;
    document.getElementById('endDate').value = day + "-" + EndMonth + "-" + year;
    $("#department").change(function(){
        postDataToSevlet();
    });
});
function prepareSupportComboBox(combobox, data){
    combobox.empty();
    $.each(data, function(key, value) { 
        if (value!="All") {
            combobox.prepend( $('<option sysid="' + key + '">' + value + '</option>'));
        } else {
            combobox.prepend( $('<option selected sysid="' + key + '">' + value + '</option>'));
        }
    });
}