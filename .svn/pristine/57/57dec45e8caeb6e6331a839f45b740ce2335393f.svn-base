$(function() {
	/*初始化grid高度*/
	$("#carInfo").height($(window).height()-123);
	/*用户GRID*/
	$('#carInfoView').myDatagrid({
		"title": "车辆信息列表",
		"url": "../carInfo/list.do",
		"singleSelect": false, //多选
		"method": "post", //默认为get,此处是因为后台处理是POST方式
		"columns" : [[
			{field : 'carNo', 		                title : '车辆编号', 	    width : 40, align : 'center', sortable : true},         
			{field : 'carCardId', 		            title : '车牌号 ', 		width : 60, align : 'center', sortable : true},         
			{field : 'carType',	                    title : '车辆类型', 	    width : 60, align : 'center', formatter :function(val){if(val=='1' || val== 1){return '箱货';} else if(val == 2 || val=='2'){return '卡车';}} },                          
			{field : 'carLsp',			            title : '承运商 ', 		width : 60, align : 'center', sortable : true},         
			{field : 'carBuyingTime',	            title : '购买时间 ',		width : 60, align : 'center', sortable : true},         
			{field : 'defaultDriver',	            title : '默认司机 ',		width : 60, align : 'center', sortable : true},         
			{field : 'carModel',		            title : '车型  ',		    width : 60, align : 'center', sortable : true},     
			{field : 'carLeaveFactoryDate',         title : '出厂日期 ',		width : 60, align : 'center', sortable : true},             
			{field : 'productionCompany',           title : '生产厂家 ',		width : 60, align : 'center', sortable : true},             
			{field : 'carBoxLength',	            title : '箱体长度 ',		width : 60, align : 'center', sortable : true},             
//			{field : 'oilConsumption',	            title : '油耗',   		width : 40, align : 'center', sortable : true},         
//			{field : 'carVolume',		            title : '核定体积',        width : 40, align : 'center', sortable : true},             
//			{field : 'useNature',		            title : '使用性质',     	width : 40, align : 'center', sortable : true},             
//			{field : 'trainstionInsuranceCompany',  title : '交强险投保公司',	width : 40, align : 'center', sortable : true},      	
//			{field : 'trainstionInsuranceOverDate', title : '交强险截止时间', 	width : 40, align : 'center', sortable : true,formatter : function(val){var d = new Date(val);return UI.Date.dateStr(d, "yyyy-mm-dd");}},    	
//			{field : 'businessInsuranceCompany',  	title : '商业险公司',   	width : 40, align : 'center', sortable : true},      	
//			{field : 'bisinessInsuranceOverDate',   title : '商业险截止时间', 	width : 40, align : 'center', sortable : true,formatter : function(val){var d = new Date(val);return UI.Date.dateStr(d, "yyyy-mm-dd");}},      	
//			{field : 'carImportWay',  				title : '车辆引入方式', 	width : 40, align : 'center', sortable : true},      	
//			{field : 'engineNo',  					title : '发动机型号', 		width : 40, align : 'center', sortable : true},      	
			{field : 'carIsActivity',  				title : '是否活动', 		width : 40, align : 'center', sortable : true,formatter :function(val){if(val=='1'){return '是';} else{return '否';}}},      	
			{field : 'comment',  					title : '备注', 			width : 40, align : 'center', sortable : true}      	
		]],
		"model": "carInfo",
		"dblClickHandler": "detailHandler",
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"submitUrl": "../carInfo/update.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "添加", 
			"form": {"submitUrl": "../carInfo/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看",
			"handler": function(){
				var type = $(this).find("#carType");
		        var typeVal = type.val();
		        if(typeVal==1 || typeVal=='1'){
		        	type.val("箱货");
		        }else{
		        	type.val("卡车");	
		        }
		    	var carIsActivity = $(this).find("#carIsActivity");
		        var carIsActivityVal = carIsActivity.val();
		        if(carIsActivityVal==1 || typeVal=='1'){
		        	carIsActivity.val("是");
		        }else{
		        	carIsActivity.val("否");	
		        }
		    }

		},
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../carInfo/deleteByIds.json", 
			"idField": "carId",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#carInfoView').datagrid("load", $("#carInfo_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#carInfo_form").form("reset");
	});
});
