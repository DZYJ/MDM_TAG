$(function() {
	/*初始化grid高度*/
	$("#driverInfo").height($(window).height()-123);
	/*用户GRID*/
	$('#driverInfoView').myDatagrid({
		"title": "司机信息列表",
		"url": "../driverInfo/list.do",
		"singleSelect": false, //多选
		"method": "post", //默认为get,此处是因为后台处理是POST方式
		"columns" : [[
			{field : 'driverNo', 		            title : '司机编号', 	    width : 40, align : 'center', sortable : true},         
			{field : 'driverName', 		            title : '司机姓名 ', 		width : 60, align : 'center', sortable : true},         
			{field : 'driverLsp',	                title : '承运商', 	    width : 60, align : 'center',sortable : true},                          
			{field : 'driverDepartment',			title : '所属部门 ', 		width : 60, align : 'center', sortable : true,formatter :function(val){if(val=='1' || val== 1){return '配送'} else if(val == 2 || val=='2'){return '运输'} else if(val == 3 || val=='3'){return '快递'}}},         
			{field : 'driverCard',	            	title : '驾驶证号 ',		width : 60, align : 'center', sortable : true},         
			{field : 'driverIdCard',	            title : '身份证',			width : 60, align : 'center', sortable : true},         
			{field : 'driverPhone',		            title : '联系电话  ',		width : 60, align : 'center', sortable : true},     
			{field : 'driverLevel',         		title : '员工评级 ',		width : 60, align : 'center', sortable : true,formatter :function(val){if(val=='1' || val== 1){return '高级'} else if(val == 2 || val=='2'){return '中级'} else if(val == 3 || val=='3'){return '初级'}}},             
			{field : 'driverSkillLevel',            title : '技能等级 ',		width : 60, align : 'center', sortable : true},             
			{field : 'driverCarType',	            title : '驾驶车型 ',		width : 60, align : 'center', sortable : true},             
			{field : 'driverIsActivity',  			title : '是否活动', 		width : 40, align : 'center', sortable : true,formatter :function(val){if(val=='1'){return '是'} else{return '否'}}},      	
			{field : 'comment',  					title : '备注', 			width : 40, align : 'center', sortable : true}      	
		]],
		"model": "driverInfo",
		"dblClickHandler": "detailHandler",
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"submitUrl": "../driverInfo/update.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "添加", 
			"form": {"submitUrl": "../driverInfo/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看",
			"handler": function(){
				var driverLevel = $(this).find("#driverLevel");
		        var driverLevelVal = driverLevel.val();
		        if(driverLevelVal==1 || driverLevelVal=='1'){
		        	driverLevel.val("高级");
		        }else if(driverLevelVal==2 || driverLevelVal=='2')
		        {
		        	driverLevel.val("中级");	
		        }else{
		        	driverLevel.val("初级");	
		        }
		    	var driverSkillLevel = $(this).find("#driverSkillLevel");
		        var driverSkillLevelVal = driverSkillLevel.val();
		        if(driverSkillLevelVal==1 || driverSkillLevelVal=='1'){
		        	driverSkillLevel.val("高级");
		        }else if(driverSkillLevelVal==2 || driverSkillLevelVal=='2')
		        {
		        	driverSkillLevel.val("中级");	
		        }else{
		        	driverSkillLevel.val("初级");	
		        }
		      	var driverDepartment = $(this).find("#driverDepartment");
		        var driverDepartmentVal = driverDepartment.val();
		        if(driverDepartmentVal==1 || driverDepartmentVal=='1'){
		        	driverDepartment.val("配送");
		        }else if(driverDepartmentVal==2 || driverDepartmentVal=='2')
		        {
		        	driverDepartment.val("运输");	
		        }else{
		        	driverDepartment.val("快递");	
		        }
		    	var driverIsActivity = $(this).find("#driverIsActivity");
		        var driverIsActivityVal = driverIsActivity.val();
		        if(driverIsActivityVal==1 || driverIsActivityVal=='1'){
		        	driverIsActivity.val("是");
		        }else{
		        	driverIsActivity.val("否");	
		        }
		        
		    }

		},
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../driverInfo/deleteByIds.json", 
			"idField": "driverId",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#driverInfoView').datagrid("load", $("#driverInfo_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#driverInfo_form").form("reset");
	});
});
