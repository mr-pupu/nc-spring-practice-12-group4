/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * Author Allan
 */
var chief=1;

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
    chief= document.getElementById("depChief").selectedIndex;
}

function processChiefChange(id){
     var resultMap = [
        {
            'chiefId':$("#depChief option:selected").attr("sysId")
        }
        ];
        $.ajax({
        url: getContextPath() + "/ajaxdepchiefprocess",
        loaderror:function(){
            alert('Error loading data');
            window.location.href="index.jsp" 
       },
        type: "POST",
        dataType: "json",
        data: {
            "ajaxdata" : JSON.stringify(resultMap)
            }
        }).done(function( msg ) {
            addMessage(msg);  
            $('#emptable').trigger("reloadGrid");
    });
}

function cancelChiefChange(){
     document.getElementById("depChief").selectedIndex=chief;
}

