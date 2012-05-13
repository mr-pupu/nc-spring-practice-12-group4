window.onload = function(){
     $('div[rel="calendarDiv"]').each(function (index)
     {  
         var id = $(this).find("input").attr('id');
         $(this).find("span").attr('onclick', 'document.getElementById("' + id + '").focus();');
         new JsDatePick({
                useMode:2,
                target:id,
                dateFormat:"%d-%m-%Y"
         });
     });
}
