$(function() {
	/*初始化配送员信息grid高度*/
	$("#dispatcher_grid").height($(window).height()-120);
	
	/*配送员信息GRID*/
	$('#dispatcherView').myDatagrid({
		"title": "配送员信息列表",
		"url": "../dispatcher/list.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
			{field : 'id', title : '配送员编号', 	 width : 60, align : 'center', sortable : true},
			{field : 'siteCode', title : '配送站点', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'name',title : '姓名',  width : 40, align : 'center'}, 
			{field : 'sex',title : '性别', width : 40, align : 'center', sortable : true,formatter:function(val){
				if(!val || val==0){
					val="男";
				}else {
					val="女";
				}
				return val;
			}}, 
			{field : 'telephoneNum',	title : '电话',width : 40, align : 'center'},
			{field : 'identification',title : '身份证号', width : 40, align : 'center', sortable : true},
			{field : 'effectDate',title : '密码生效日期', width : 70, align : 'center', sortable : true},
			{field : 'expireDate',title : '密码失效日期', width : 70, align : 'center', sortable : true},
			{field : 'active',title : '是否活动', width : 40, align : 'center', sortable : true,formatter:function(val){
				if(val=="Y"){
					val="是";
				}else if(val=="N"){
					val="否";
				}
				return val;
			}}
			/*{field : 'insertBy',title : '创建人', width : 40, align : 'center', sortable : true},
			{field : 'insertDate',title : '创建时间', width : 40, align : 'center', sortable : true,formatter : function(val, rec){
				return UI.Date.dateStr(val, "yyyy-mm-dd hh:mm:ss");
		   }},
			{field : 'modifiedBy',title : '修改人', width : 40, align : 'center', sortable : true},
			{field : 'modifiedDate',title : '修改时间', width : 40, align : 'center', sortable : true,formatter : function(val, rec){
				return UI.Date.dateStr(val, "yyyy-mm-dd hh:mm:ss");
		    }}*/
		]],
		"model": "dispatcher", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改配送员信息窗口，将自动匹配ID：dispatcher_edit_dialog
		"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"validate":true, "submitUrl": "../dispatcher/update.do"}
	    },
		"addHandler": {
			"enable": true,
			"title": "添加", 
			"form": {"validate":true, "submitUrl": "../dispatcher/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看",
			"handler": function(){
				//性别显示
				var sex = $(this).find("input[name='sex']");
				var val = sex.val();
				if(!val || val==0){
					sex.val("男");
				}else {
					sex.val("女");
				}
				
				//是否活动显示
				var active = $(this).find("input[name='active']");
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
			"removeUrl": "../dispatcher/deleteByIds.json", 
			"idField": "id",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#dispatcherView').datagrid("load", $("#dispatcher_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#dispatcher_search_form").form("reset");
	});
	
	$.ajax({
		url:'../site/listAll.json',
		cache:false,
		type:"get",
	    error: function () {//请求失败处理函数
		      alert('请求失败');
		},
	    dataType:"json",
	    success:function(data){
	    	$('#search_siteCode').combobox({
	    		 data:data
	    	});
	    	$('#add_siteCode').combobox({
	    		 data:data
	    	});
	    	$('#edit_siteCode').combobox({
	    		 data:data
	    	});
	    }
	});
});
