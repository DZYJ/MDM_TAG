$(function() {
	/*初始化数据字典grid高度*/
	$("#dataDictionary_grid").height($(window).height()-120);
	
	/*数据字典GRID*/
	$('#dataDictionaryView').myDatagrid({
		"title": "数据字典列表",
		"url": "../dataDictionary/list.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
			{field : 'id', title : '编号', 	 width : 20, align : 'center', sortable : true},
			{field : 'englishName', title : '英文名称', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'chineseName',title : '中文名称',  width : 60, align : 'center'}, 
			{field : 'indicateName',title : '字段标识名称', width : 60, align : 'center', sortable : true}, 
			{field : 'columnValue',title : '字段值',width : 60, align : 'center'},
			/*{field : 'isActivity',title : '是否活动', width : 40, align : 'center', sortable : true,formatter:function(value){
				if(value=="Y"){
					value="是";
				}else if(value=="N"){
					value="否";
				}
				return value;
			}},*/
			{field : 'insertBy',title : '创建人', width : 40, align : 'center', sortable : true},
			{field : 'modifiedBy',title : '修改人', width : 40, align : 'center', sortable : true},
			{field : 'comment',title : '备注', width : 40, align : 'center', sortable : true}
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
					title: '数据字典信息导入',    
				    width: 450,    
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
				var checkedItems = $("#dataDictionaryView").datagrid("getChecked");
				var names = [];
				$.each(checkedItems, function(index, item){
					names.push(item.id);
				});
				$('#comment').val(names);
				$('#dataDictionary_search_form').form('submit', {
					url: "../dataDictionary/export.htm",
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
		"model": "dataDictionary", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改数据字典窗口，将自动匹配ID：dataDictionary_edit_dialog
		"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"validate":true, "submitUrl": "../dataDictionary/update.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "添加", 
			"form": {"validate":true, "submitUrl": "../dataDictionary/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看",
            "handler": function(){
				//是否活动显示
				var active = $(this).find("input[name='isActivity']");
				var val = active.val();
				if(!val || val=="Y"){
					active.val("是");
				}else {
					active.val("否");
				}
			}
		},
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../dataDictionary/deleteByIds.json", 
			"idField": "id",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#dataDictionaryView').datagrid("load", $("#dataDictionary_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#dataDictionary_search_form").form("reset");
	});
	
});




