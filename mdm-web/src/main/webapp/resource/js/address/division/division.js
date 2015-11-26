$(function() {
	
	/*初始化配送区域grid高度*/
	$("#division_grid").height($(window).height()-135);
	
	/*配送区域GRID*/
	$('#divisionView').myDatagrid({
		"title": "配送区域列表",
		"url": "../division/list.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
			{field : 'divisionCode4', title : '四级区域编码', 	 width : 40, align : 'center', sortable : true},
			{field : 'divisionName1', title : '一级区域', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'divisionName2',title : '二级区域',  width : 60, align : 'center'}, 
			{field : 'divisionName3',title : '三级区域', width : 60, align : 'center', sortable : true}, 
			{field : 'divisionName4',title : '四级区域', width : 60, align : 'center', sortable : true}, 
			{field : 'subDivisionFlag',title : '是否电子地图解析', width : 40, align : 'center', sortable : true,formatter:function(val){
				if(val=="1"){
					val="是";
				}else if(val=="0"){
					val="否";
				}
				return val;
			}},
			{field : 'insertBy',title : '创建人', width : 40, align : 'center', sortable : true},
			{field : 'modifiedBy',title : '修改人', width : 40, align : 'center', sortable : true},
		]],
		"model": "division", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改配送区域窗口，将自动匹配ID：division_edit_dialog
		"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		"addHandler": {
			"enable": true,
			"title": "添加", 
			"form": {"validate":true, "submitUrl": "../division/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看",
			"handler":function(data){
				    	$("#detail_address").val(data.divisionName1+" "+ data.divisionName2+" "+data.divisionName3+" "+data.divisionName4);
				    	
				    	if(data.isActivity=="Y"){
				    		$("#detail_isActivity").val("是");
				    	}else{
				    		$("#detail_isActivity").val("否");
				    	}
				    	if(data.subDivisionFlag=="1"){
				    		$("#detail_subDivisionFlag").val("是");
				    	}else{
				    		$("#detail_subDivisionFlag").val("否");
				    	}
				    	$("#detail_insertBy").val(data.insertBy);
				    	$("#detail_modifiedBy").val(data.modifiedBy);
			}
		},
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../division/deleteByIds.json", 
			"idField": "divisionCode4",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#divisionView').datagrid("load", $("#division_search_form").form("formToJson"));
	});
	
	$("#search_form_reset").on("click", function(){
		$("#division_search_form").form("reset");
	});
	
	/*填充查询区域*/
	var division_code1 ,division_code2,division_code3,division_code4;
	$.ajax({
		url:'../division/getFirstLevelList.json',
		cache:false,
	    type:"get",
	    error: function () {//请求失败处理函数
		      alert('请求失败');
		},
	    dataType:"json",
	    success:function(data){
	        $("#firstLevel").combobox({
	        	data:data,
	        	onSelect:function(option){
	        		division_code1 = $(this).combobox('getValue');
	        		$("#search_divisionCode1").val(division_code1);
	        		$.ajax({
	        			url:'../division/getChildListByPCode.json?parent_division_code='+option.divisionCode,
	        			cache:false,
	        		    type:"get",
	        		    error: function () {//请求失败处理函数
	        			      alert('请求失败');
	        			},
	        		    dataType:"json",
	        		    success:function(data){
	        		    	 $("#secondLevel").combobox({
	        			        	data:data,
	        			        	onSelect:function(option){
	        			        		division_code2 = $(this).combobox('getValue');
	        			        		$("#search_divisionCode2").val(division_code2);
	        			        		$.ajax({
	        			        			url:'../division/getChildListByPCode.json?parent_division_code='+option.divisionCode,
	        			        			cache:false,
	        			        		    type:"get",
	        			        		    error: function () {//请求失败处理函数
	        			        			      alert('请求失败');
	        			        			},
	        			        		    dataType:"json",
	        			        		    success:function(data){
	        			        		    	$("#thirdLevel").combobox({
	        		        			        	data:data,
	        		        			        	onSelect:function(option){
	        		        			        		division_code3 = $(this).combobox('getValue');
	        		        			        		$("#search_divisionCode3").val(division_code3);
	        		        			        		$.ajax({
	        		        			        			url:'../division/getChildListByPCode.json?parent_division_code='+option.divisionCode,
	        		        			        			cache:false,
	        		        			        		    type:"get",
	        		        			        		    error: function () {//请求失败处理函数
	        		        			        			      alert('请求失败');
	        		        			        			},
	        		        			        		    dataType:"json",
	        		        			        		    success:function(data){
	        		        			        		    	$("#fourthLevel").combobox({
	        		        		        			        	data:data,
	        		        		        			        	onSelect:function(option){
	        		        		        			        		division_code4 = $(this).combobox("getValue");
	        		        		        			        		$("#search_divisionCode4").val(division_code4);
	        		        		        			        	}
	        		        		        			        });	// end fourthLevel
	        		        			        		    }
	        		        			        		});
	        		        			        	}
	        		        			        });	// end thirdLevel
	        			        		    }
	        			        		});
	        			        	}
	        			        });	// end secondLevel
	        		      }
	        		});
	        	}// end first onchange
	        });	
	    }
	});
	
	
	var code;
	//新增三级区域按钮事件  1显示一二级区域下拉列表
	$("#thirdBtn").on('click',function(){
		$('#addForm').show();
		$("#division_add_form").form('clear');
		$('#third').hide();//三级区域隐藏，新增四级区域时显示
		//$('#division_add_form').form('disableValidation');
		var divisionCode = $("#add_divisionCode").val();
		$('#add_thirdLevel').combobox('setValue',divisionCode);
		$.ajax({
			url:'../division/getFirstLevelList.json',
			cache:false,
		    type:"get",
		    error: function () {//请求失败处理函数
			      alert('请求失败');
			},
		    dataType:"json",
		    success:function(data){
		    	$("#add_firstLevel").combobox({
		    		data:data,
			    	onSelect:function(option){
			    		$.ajax({
			    			url:'../division/getChildListByPCode.json?parent_division_code='+option.divisionCode,
		        			cache:false,
		        		    type:"get",
		        		    error: function () {//请求失败处理函数
		        			      alert('请求失败');
		        			},
		        		    dataType:"json",
		        		    success:function(data){
		        		    	$("#add_secondLevel").combobox({
		        		    		data:data,
		        			    	onSelect:function(option){
		        			    		$("#parentDivisionCode").val(option.divisionCode);//设置新增三级区域的父divison_code
		        			    		$("#parentDivisionName").val(option.divisionName);//设置新增三级区域的父divison_name
		        			    		$("#add_divLevel").val(3);//设置区域级别
		        			    		$("#add_daId").val('DA'+divisionCode);
		        			    	}// end on select
		        		    	});
		        		    }
			    		});
			    	}
		    	});
		    }
		});
	});
	
	//新增四级区域
	$("#fourthBtn").on('click',function(){
		$('#addForm').show();
		$("#division_add_form").form('clear');
		$('#third').show();
		var divisionCode = $("#add_divisionCode").val();
		$.ajax({
			url:'../division/getFirstLevelList.json',
			cache:false,
		    type:"get",
		    error: function () {//请求失败处理函数
			      alert('请求失败');
			},
		    dataType:"json",
		    success:function(data){
		    	$("#add_firstLevel").combobox({
		    		data:data,
			    	onSelect:function(option){
			    		$.ajax({
			    			url:'../division/getChildListByPCode.json?parent_division_code='+option.divisionCode,
		        			cache:false,
		        		    type:"get",
		        		    error: function () {//请求失败处理函数
		        			      alert('请求失败');
		        			},
		        		    dataType:"json",
		        		    success:function(data){
		        		    	$("#add_secondLevel").combobox({
		        		    		data:data,
		        			    	onSelect:function(option){
		        			    		$.ajax({
		        			    			url:'../division/getChildListByPCode.json?parent_division_code='+option.divisionCode,
		        			    			cache:false,
		        		        		    type:"get",
		        		        		    error: function () {//请求失败处理函数
		        		        			      alert('请求失败');
		        		        			},
		        		        		    dataType:"json",
		        		        		    success:function(data){
		        		        		    	$("#add_thirdLevel").combobox({
		        		        		    		data:data,
		        		        		    		onSelect:function(option){
		        		        		    			$("#parentDivisionCode").val(option.divisionCode);//设置新增四级区域的父divison_code
		        		        			    		$("#parentDivisionName").val(option.divisionName);//设置新增四级区域的父divison_name
		        		        			    		$("#add_divLevel").val(4);//设置区域级别
		        		        			    		$("#add_daId").val('DA'+divisionCode);
	        		        		    		   }
		        		        		    	});
		        		        		    }
		        			    		});
		        			    	}// end on select
		        		    	});
		        		    }
			    		});
			    	}
		    	});
		    }
		});
	});
	
});
