/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * author : Merle
 */

function checkTrfId(){
    var q = window.location;
    var reg=/.*#(.*)/  
    var arr=reg.exec(q)  
    var id = arr[1];
    var idInt = parseInt(id);
    if (id == idInt){
        //ajax request here
        getTrfUsingAJAX(id);
    }   
}
function getSuperPuperUrl(){
    var q = window.location;
    var reg=/.*#(.*)/  
    var arr=reg.exec(q)  
    var id = arr[1];
}

function fillTrfForm(data){
    var string ="";
    $.each(data, function(key, value) { 
        string += '[' + key + ',' + value + '] '; 
    //alert(key + ': ' + value); 
    });
    alert(string);
    $('#myModal').modal('show');
}

function getTrfUsingAJAX(id){
    $.getJSON(getContextPath() + "/ajaxtrfshandle?id="+id,
        function (data){
            fillTrfForm(data);
        });
}

$(document).ready(checkTrfId() );
    
$(window).bind('hashchange', function() {
    alert('url chenged!');
    alert('checking parameters, using ajax');
    if(!$('#myModal').data('modal').isShown) {
        alert('showing modal');
        $('#myModal').modal('show');
    }
    checkTrfId();
});
  
 