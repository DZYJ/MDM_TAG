$(function() {
	/*初始化grid高度*/
	$("#sysParam_grid").height($(window).height()-120);
	
	/*GRID*/
	$('#sysParamView').myDatagrid({
		"title": "系统参数列表",
		"url": "../sysParam/list.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
			{field : 'code', title : '代码', 	 width : 70, align : 'center', sortable : true}, 
			{field : 'description',title : '描述', width : 70, align : 'center', sortable : true}, 
			{field : 'param_type',	title : '类型',width : 70, align : 'center'},
			{field : 'remark',title : '备注', width : 60, align : 'center', sortable : true}
		]],
		"model": "sysParam", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改窗口，将自动匹配ID：sysParam_edit_dialog
		"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"validate":true, "submitUrl": "../sysParam/update.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "添加", 
			"form": {"validate":true, "submitUrl": "../sysParam/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看",
			"queryUrl": "../sysParam/edit.json"
		},
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../sysParam/deleteByIds.json", 
			"idField": "id",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#sysParamView').datagrid("load", $("#sysParam_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#sysParam_search_form").form("reset");
	});
	
});
