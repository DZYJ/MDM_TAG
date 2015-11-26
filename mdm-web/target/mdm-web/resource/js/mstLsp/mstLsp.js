$(function() {
	/*初始化承运商信息grid高度*/
	$("#mstLsp_grid").height($(window).height()-120);
	
	/**
	 * 首次进入页面时，查询条件中的是否有效选项默认选中为“有效”
	 */
	 var data = $('#queryEnabled').combobox('getData');
     if (data.length > 0) {
         $('#queryEnabled').combobox('select', data[0].enabled);
     } 
	
	/*承运商信息GRID*/
	$('#mstLspView').myDatagrid({
		"title": "承运商信息列表",
		"url": "../mstLsp/list.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
		    {field : 'lspCode', title : '承运商编码', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'lspName', title : '承运商名称', 	 width : 40, align : 'center', sortable : true},
			{field : 'lspType',title : '承运商类型',  width : 60, align : 'center'}, 
			{field : 'enabled',title : '是否有效', width : 60, 
				formatter: function(value,row,index){
					if (row.enabled==1){
						return '是';
					}else if(row.enabled==0){
						return '否';
					}else{
						return '未知';
					}
				},
				align : 'center', sortable : true}, 
			{field : 'codRate',	title : '代收费率',width : 60, align : 'center'},
			{field : 'insuranceRate',title : '保险费率', width : 40, align : 'center', sortable : true},
			{field : 'lspContact',title : '承运商联系人', width : 40, align : 'center', sortable : true},
			{field : 'lspPhone',title : '承运商电话', width : 40, align : 'center', sortable : true}
		]],
		"model": "mstLsp", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改系统参数窗口，将自动匹配ID：sysParam_edit_dialog
		"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"validate":true, "submitUrl": "../mstLsp/update.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "新增", 
			"handler":function(){
				
				 var data = $('#enabled').combobox('getData');
	             if (data.length > 0) {
	                 $('#enabled').combobox('select', data[0].enabled);
	             } 
			},
			"form": {"validate":true, "submitUrl": "../mstLsp/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看",
			"handler": function(rowData){
				var shipMethodObj = $(this).find("#shipMethod");
				if(rowData.shipMethod==1){
					shipMethodObj.val("陆运");
				}else if(rowData.shipMethod==2){
					shipMethodObj.val("海运");
				}else if(rowData.shipMethod==3){
					shipMethodObj.val("空运");
				}
				
				var enabledObj = $(this).find("#enabled");
				if(rowData.enabled==1){
					enabledObj.val("有效");
				}else if(rowData.enabled==0){
					enabledObj.val("无效");
				}else{
					enabledObj.val("未知");
				}
			}
		},
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../mstLsp/deleteByIds.json", 
			"idField": "lspCode",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#mstLspView').datagrid("load", $("#mstLsp_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#mstLsp_search_form").form("reset");
	});
	
});