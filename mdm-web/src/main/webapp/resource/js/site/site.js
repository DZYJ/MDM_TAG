$(function() {
	/*初始化用户grid高度*/
	$("#site_grid").height($(window).height()-120);
	
	/*用户GRID*/
	$('#siteview').myDatagrid({
		"title": "站点列表",
		"url": "../site/list.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
            {field : 'masLoc', title : '分拨点编号', 	width : 40, align : 'center', sortable : true},
            {field : 'descr', title : '分拨点名称', 	width : 40, align : 'center', sortable : true},
			{field : 'lsp', 	title : '所属承运商', 	width : 50, align : 'center', sortable : true},
			{field : 'contactName',	title : '负责人', 	width :40, align : 'center', sortable : true},
			{field : 'contactPhone',	title : '联系电话', 	width : 40, align : 'center', sortable : true},
			{field : 'address',	title : '联系地址', 	width :80, align : 'center', sortable : true}
		]],
		"model": "site",
		"dblClickHandler": "detailHandler",
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"submitUrl": "../site/update.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "新增", 
			"form": {"submitUrl": "../site/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看",
		},
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../site/deleteByIds.json", 
			"idField": "masLoc",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#siteview').datagrid("load", $("#site_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#site_search_form").form("reset");
	});
	
	//加载部门combobox
	$.ajax({
		url:'../department/listAll.json',
		cache:false,
		type:"get",
	    error: function () {//请求失败处理函数
		      alert('请求失败');
		},
	    dataType:"json",
	    success:function(data){
	    	$('#add_department_code').combobox({
	    		 data:data
	    	});
	    	$('#search_departmentCode').combobox({
	    		 data:data
	    	});
	    	$('#edit_department_code').combobox({
	    		 data:data
	    	});
	    }
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
	    	$('#lsp').combobox({
	    		 data:data
	    	});
	    	$('#edit_lsp').combobox({
	    		 data:data
	    	});
	    	
	    }
	});
	
});
