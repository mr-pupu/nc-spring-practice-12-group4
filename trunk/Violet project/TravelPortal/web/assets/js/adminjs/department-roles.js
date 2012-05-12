/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * Author Allan
 */

function setCheckboxesAJAX(id){
    $.getJSON(getContextPath() + "/ajaxdeproleshandle?id="+id,
        function (data){
            fillCheckboxes(data);
        });
}

function fillCheckboxes(data){
    var string ="";
    var n=1;
    $.each(data, function(key, value) { 
        string += '[' + key + ',' + value + '] ';
    });
   document.getElementById("check1").setAttribute("disabled",false);
    for(var i=1; i<=data['rolesNumber']; i++){
        if ($.inArray(i, data['deproles']) != -1) {
            $('#check'+i).attr("checked","true");
        }
        else{
            $('#check'+i).removeAttr("checked");
        }
    }
}

function processRoleChange(checkid){
    var reg=/.*k(.*)/;    
    var arr=reg.exec(checkid);
    if ((arr != null) && (arr.length >1)){
        var id = arr[1];

        var resultMap = [
        {
            'depId':$('#tree').jqGrid('getGridParam', 'selrow')
        },
        {
            'roleId': id
        },
        {
            'checked' : ($('#check'+id).attr("checked")!=undefined).toString()
        }
        ];
        $.ajax({
            url: getContextPath() + "/ajaxdeprolesprocess",
            type: "POST",
            data: {
                "ajaxdata" : JSON.stringify(resultMap)
            },
            dataType: "json",
            success: function(result) {
            }
        
        });
    }
}

function cancelRoleChange(checkid){
    var reg=/.*k(.*)/;    
    var arr=reg.exec(checkid);
    if ((arr != null) && (arr.length >1)){
        var id = arr[1]
    if($('#check'+id).attr("checked")!=undefined){
        $('#check'+id).removeAttr("checked");
    }
    else{
        $('#check'+id).attr("checked","true");
    }
}
}
