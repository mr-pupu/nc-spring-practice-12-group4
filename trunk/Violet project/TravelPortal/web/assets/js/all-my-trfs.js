
jQuery("#grps1").jqGrid({ // Привязка плагина к таблице
   	url: getContextPath()+"/jQGridTable?q=1&action=fetchData",
        datatype: "json", // Формат скрипта-обработчика
   	colNames:['#', 'Date Begin', 'Date End','Status', 'Notes'],
   	colModel:[
   		{name:'id',	key : true,	index:'id',	width:55, searchtype:"integer"},
   		{name:'datebegin',index:'datebegin', width:90},
                {name:'dateend',index:'dateend', width:90},
   		{name:'name', index:'name',	width:100},
   		{name:'comment',index:'note', width:150, sortable:false}
   	],
   	rowNum:10,
   	width:700,
   	rowList:[10,20,30],
   	pager: '#pgrps1', // Привязка к таблице тулбара
   	sortname: 'datebegin',
   	viewrecords: true,
   	sortorder: "desc",
        jsonReader: { repeatitems : false, root:"head" }
        
});


jQuery("#grps1").jqGrid('navGrid','#pgrps1',  // Управление тулбаром таблицы
    {edit:true,add:false,del:true}, // Отключаем от тулбара редактирование, добавление и удаление записей. На тулбаре останутся только две кнопки: "Поиск" и "Обновить"
    {}, // Опции окон редактирования
    {}, // Опции окон добавления
    {}, // Опции окон удаления
    {
        multipleSearch:true, // Поиск по нескольким полям
        multipleGroup:true, // Сложный поиск с подгруппами условий
        showQuery: true // Показывать превью условия
    }
 )