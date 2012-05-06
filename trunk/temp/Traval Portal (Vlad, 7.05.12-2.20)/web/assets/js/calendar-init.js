$(document).ready(calendarsInit() );
function calendarsInit(){
     $('div[rel="calendarDiv"]').each(function (index)
     {  
         var id = $(this).find("input").attr('id');
         new JsDatePick({
                useMode:2,
                target:id,
                dateFormat:"%d-%m-%Y"
         });
         $(this).find("span").attr('onclick', 'document.getElementById("' + id + '").focus();');
     });
}