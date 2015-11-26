$(function() {
	/*初始化承运商信息grid高度*/
	$("#virtualWareHouse_grid").height($(window).height()-120);
	
	$("#parentMasLoc_box").combobox({
		url: '../sysParam/list.json?orderBy="CODE"',
		method: 'post',
		valueField:'code',
		textField:'description',
		loadFilter : function(_data) {
		    return _data.rows;
		},
		formatter : function(row) {
			var opts = $(this).combobox("options");	
			return row[opts.valueField]+" - "+row[opts.textField];
		}
	});
	
	/*承运商信息GRID*/
	$('#virtualWareHouseView').myDatagrid({
		"title": "虚拟承运商列表",
		"url": "../virtualWareHouse/virtualWareHouseList.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
			{field : 'lspCode', title : '承运商编号', 	 width : 40, align : 'center', sortable : true},
			{field : 'lspName', title : '承运商名称', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'masLoc',title :  '虚拟仓库',  width : 60, align : 'center'}, 
			{field : 'bucketCode',title : '仓库对应区域', width : 60, align : 'center', sortable : true}, 
			{field : 'location',	title : 'SAP区域代码',width : 60, align : 'center'},
			{field : 'parentMasLoc',title : '对应仓库', width : 40, align : 'center', sortable : true},
		]],
		"model": "virtualWareHouse", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改系统参数窗口，将自动匹配ID：sysParam_edit_dialog
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"validate":true, "submitUrl": "../virtualWareHouse/update.do"}
		}

	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#virtualWareHouseView').datagrid("load", $("#virtualWareHouse_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#virtualWareHouse_search_form").form("reset");
	});
	
});