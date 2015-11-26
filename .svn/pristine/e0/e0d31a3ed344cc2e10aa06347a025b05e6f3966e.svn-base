$(function() {
	/*初始化用户grid高度*/
	$("#vessel_grid").height($(window).height()-110);
	
	/*用户GRID*/
	$('#vesselview').myDatagrid({
		"title": "容器列表",
		"url": "../vessel/list.do",
		"singleSelect": false, //多选
		"method": "post", //默认为get,此处是因为后台处理是POST方式
		"columns" : [[
			{field : 'vesselNo', 		title : '容器编号', 	width : 40, align : 'center', sortable : true},
			{field : 'vesselName', 		title : '容器名称', 	width : 60, align : 'center', sortable : true}, 
			{field : 'fromDepartment',	title : '所属部门', 	width : 60, align : 'center'}, 
			{field : 'vesselCoverage',	title : '容器面积', 	width : 60, align : 'center', sortable : true}, 
			{field : 'material',		title : '材质 ',	 	width : 60, align : 'center', sortable : true},
			{field : 'quality',			title : '质量情况 ',	width : 60, align : 'center', sortable : true},
			{field : 'vesselState',		title : '容器状态 ',	width : 60, align : 'center', sortable : true},
			{field : 'vesselHeight',	title : '容器的高度 ',	width : 60, align : 'center', sortable : true},
			{field : 'vesselWidth',		title : '容器宽度 ',	width : 60, align : 'center', sortable : true},
			{field : 'vesselLength',	title : '容器长度 ',	width : 60, align : 'center', sortable : true},
			{field : 'vesselVolume',	title : '容器称重', 	width : 40, align : 'center', sortable : true}
		]],
		"model": "vessel",
		"dblClickHandler": "detailHandler",
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"submitUrl": "../vessel/update.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "添加", 
			"form": {"submitUrl": "../vessel/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看"
		},
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../vessel/deleteByIds.json", 
			"idField": "vesselNo",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#vesselview').datagrid("load", $("#vessel_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#vessel_search_form").form("reset");
	});
});

function form_export() {
	var sValidate = $("#reachareas1_form").form("validate");
	if(!sValidate){
		return;
	}
	
	$('#reachareas1_form').form('submit', {
		url: "../lsp/export.json",
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

$("#reachareas1_form_import").on("click", function(){
	var file = $("#file").val();
    if (file == "") {
        $.messager.alert('Excel批量导入', '请选择将要上传的文件!');        
    }
    
    else {
    	var strtype = file.substr(file.length-4,file.length-1);
    	strtype = strtype.toUpperCase();
        if (strtype != '.XLS' && strtype != 'XLSX') {
            $.messager.alert('Excel批量导入','文件类型不正确，请选择EXCEL文件!'); 
        }
        else { 
        	$('#import_form').form('submit', {
        		url: "../lsp/import.json",
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
    }    
	
});
