/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * Author Allan
 */

function setDepartmentChiefAJAX(id){
    $.getJSON(getContextPath() + "/ajaxdepartmentchiefhandle?id="+id,
        function (data){
            fillChiefCombobox(data);
        });
    
}

function fillChiefCombobox(data){
    var string ="";
    $.each(data, function(key, value) { 
        string += '[' + key + ',' + value + '] ';
    });
    //alert(string);
    prepareComboBox($("#depChief"), data['depemployees'], data['depemployeeId']);
}

function processChiefChange(id){
     var resultMap = [
        {
            'chiefId':$("#depChief option:selected").attr("sysId")
        }
        ];
        $.ajax({
        url: getContextPath() + "/ajaxdepchiefrocess",
        type: "POST",
        data: {
            "ajaxdata" : JSON.stringify(resultMap)
            },
        dataType: "json",
        success: function(result) {
        }
        
    });
}
