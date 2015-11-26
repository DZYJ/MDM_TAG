$(function() {
	/*初始化用户grid高度*/
	$("#selfsite_grid").height($(window).height()-120);
	
	/*用户GRID*/
	$('#selfsiteview').myDatagrid({
		"title": "自提点列表",
		"url": "../mstSelfPickup/list.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
            {field : 'selfSiteCode', title : '自提点编号', 	width : 40, align : 'center', sortable : true},
            {field : 'selfSiteName', title : '自提点名称', 	width : 40, align : 'center', sortable : true},
			{field : 'lspName', 	title : '所属承运商', 	width : 50, align : 'center', sortable : true},
			{field : 'contact',	title : '自提点联系人', 	width :40, align : 'center', sortable : true},
			{field : 'telZoneCode',	title : '自提点电话区号', 	width : 60, align : 'center', sortable : true},
			{field : 'telNum',	title : '自提点电话', 	width : 40, align : 'center', sortable : true},
			{field : 'zipCode',	title : '自提点邮编', 	width : 40, align : 'center', sortable : true},
			{field : 'divisionName',	title : '自提点地址', 	width :40, align : 'center', sortable : true}
		]],
		/*导入导出按钮*/
		"toolbar": [{
			id : 'data_form_import',
			text : '导入',
			iconCls : 'icon-filter',
			handler : function() {
				var file1 = $("#file"); 
				file1.after(file1.clone().val(""));
				file1.remove();
				$('#data_import_dialog').dialog({
					title: '自提点信息导入',    
				    width: 500,    
				    height: 240,    
				    closed: false,    
				    cache: false,    
				    href: '',    
				    modal: true
				}); 
			}
		},{
			id : 'data_form_export',
			text : '导出',
			iconCls : 'icon-filter',
			handler : function() {
				var checkedItems = $("#selfsiteview").datagrid("getChecked");
				var names = [];
				$.each(checkedItems, function(index, item){
					names.push("'"+item.selfSiteCode+"'");
				});
				$('#ids').val(names);
				$('#mstSelfPickup_search_form').form('submit', {
					url: "../mstSelfPickup/export.htm",
					method: "post",
					onSubmit: function(){
						return true;
					},
					success: function(data){
						var _data = $.parseJSON(data);
						$.messager.alert("提示", _data);
					}
				});
			}
		}],
		"model": "mstSelfPickup",
		"dblClickHandler": "detailHandler",
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"submitUrl": "../mstSelfPickup/update.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "添加", 
			"handler":function(){
				
			},
			"form": {"validate":true,"submitUrl": "../mstSelfPickup/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看",
			 "handler":function(data){
				 $("#detail_lsp").val(data.lspName);
			 }
		},
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../mstSelfPickup/deleteByIds.json", 
			"idField": "selfSiteCode",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#selfsiteview').datagrid("load", $("#mstSelfPickup_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#mstSelfPickup_search_form").form("reset");
	});
	
	//加载承运商combobox
	$.ajax({
		url:'../mstLsp/listAll.json',
		cache:false,
		type:"get",
	    error: function () {//请求失败处理函数
		      alert('请求失败');
		},
	    dataType:"json",
	    success:function(data){
	    	$('#add_lsp').combobox({
	    		 data:data
	    	});
	    	
	    	$('#search_lsp').combobox({
	    		 data:data
	    	});
	    	$('#edit_lsp').combobox({
	    		 data:data
	    	});
	    }
	});
	
});
