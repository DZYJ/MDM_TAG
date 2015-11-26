$(function() {
	/*初始化运力grid高度*/
	$("#transcapacity_grid").height($(window).height()-120);
	
	/*半日达信息GRID*/
	$('#transcapacityView').myDatagrid({
		"title": "半日达列表",
		"url": "../transCapacity/list.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
			{field : 'id', title : 'ID', width : 40, align : 'center', sortable : true},
			{field : 'masLoc', title : '仓库', 	 width : 40, align : 'center', sortable : true, hidden:true},
			{field : 'masName', title : '仓库', 	 width : 40, align : 'center', sortable : true},
			{field : 'divisionName', title : '区域', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'lspCode', title : '承运商', 	 width : 70, align : 'center', sortable : true, hidden:true}, 			
			{field : 'lspName', title : '承运商', 	 width : 70, align : 'center', sortable : true}, 			
			{field : 'transQty',title : '运能数量',  width : 30, align : 'center'}, 
			{field : 'lotSeq',title : '时间规则', width : 30, align : 'center', sortable : true, hidden:true}, 
			{field : 'deliveryTimeLot',title : '配送时间', width : 30, align : 'center', sortable : true}, 
			{field : 'startTime',	title : '开始时间',width : 40, align : 'center'},
			{field : 'endTime',title : '结束时间', width : 40, align : 'center', sortable : true},
			{field : 'standardTime',title : '标准时间', width : 40, align : 'center', sortable : true},
			{field : 'enabled',title : '有效标志', width : 20, align : 'center', sortable : true,formatter:function(val){
				if(val=="1"){
					val="是";
				}else if(val=="0"){
					val="否";
				}
				return val;
			}}
		]],
		"model": "transcapacity", 
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../transCapacity/deleteByIds.json", 
			"idField": "id",
			"idParams": "ids"
		},
		"toolbar": [{
			id : 'btnrole',
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				showAddDialog();
				$("#startTime").mask("99:99"); //时间
				$("#endTime").mask("99:99");
				$("#standardTime").mask("99:99");
				/*承运商下拉框*/
				$.ajax({
					url:'../lspCarrier/getLspListCombobox.json',
					cache:false,
				    type:"get",
				    error: function () {//请求失败处理函数
					      $.messager.alert("提示", "获取承运商信息失败！");
					},
					dataType:"json",
				    success:function(data){
				    	
				        $("#lspCodeAdd").combobox({
				        	data:data
				        });	
				    }
				});
				
				
				
				/*仓库下拉框*/
				$.ajax({
					url:'../masLoc/getMasLocListCombobox.json',
					cache:false,
				    type:"get",
				    error: function () {//请求失败处理函数
					      $.messager.alert("提示", "仓库请求失败!");
					},
				    dataType:"json",
				    success:function(data){
				        $("#masLocAdd").combobox({
				        	data:data
				        });	
				    }
				});
				
				
				//时间规则下拉框
				$.ajax({
					url:'../transCapacity/getLotSeqListCombobox.json',
					cache:false,
				    type:"get",
				    error: function () {//请求失败处理函数
					      $.messager.alert("提示", "一级区域请求失败!");
					},
				    dataType:"json",
				    success:function(data){
				        $("#lotSeq").combobox({
				        	data:data,
				        	onChange:function(option){
				        		$.ajax({
    			        			url:'../transCapacity/getLotBySeq.json?lotSeq='+option,
    			        			cache:false,
    			        		    type:"post",
    			        		    error: function () {//请求失败处理函数
    			        			      $.messager.alert("提示", "时间规则请求失败!");
    			        			},
    			        		    dataType:"json",
    			        		    success:function(data){
    			        		    	$("#startTime").val(data.startTime);
    			        		    	$("#endTime").val(data.endTime);
    			        		    	$("#standardTime").val(data.standardTime);
    			        		    }
				        		});	
				        	}
				        });
				    }
				});
				
				
				//区域下拉框
				$.ajax({
					url:'../division/getFirstLevelList.json',
					cache:false,
				    type:"get",
				    error: function () {//请求失败处理函数
					      $.messager.alert("提示", "一级区域请求失败!");
					},
				    dataType:"json",
				    success:function(data){
				        $("#firstLevel").combobox({
				        	data:data,
				        	onChange:function(option){
				        		$.ajax({
				        			url:'../division/getChildListByPCode.json?parent_division_code='+option,
				        			cache:false,
				        		    type:"get",
				        		    error: function () {//请求失败处理函数
				        			      $.messager.alert("提示", "请求失败!");
				        			},
				        		    dataType:"json",
				        		    success:function(data){
				        		    	 $("#secondLevel").combobox({
				        			        	data:data,
				        			        	onChange:function(option){
				        			        		$.ajax({
				        			        			url:'../division/getChildListByPCode.json?parent_division_code='+option,
				        			        			cache:false,
				        			        		    type:"get",
				        			        		    error: function () {//请求失败处理函数
				        			        			      $.messager.alert("提示", "二级区域请求失败!");
				        			        			},
				        			        		    dataType:"json",
				        			        		    success:function(data){
				        			        		    	$("#divisionCode").combobox({
				        		        			        	data:data
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
				
				
			}
		},{
			id : 'btnEdit',
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				var selectRows = $("#transcapacityView").datagrid("getSelections");
				if(!selectRows || selectRows.length<=0){
					$.messager.alert("提示", "&nbsp;&nbsp;请选择一行数据！");
					return;
				}
				if(selectRows.length>=2){
					$.messager.alert("提示", "&nbsp;&nbsp;不能选择多行数据！");
					return;
				}
				showEditDialog(selectRows[0].id);
				var editForm = $("#transcapacity_edit_form");
				
				/*承运商下拉框*/
				$.ajax({
					url:'../lspCarrier/getLspListCombobox.json',
					cache:false,
				    type:"get",
				    error: function () {//请求失败处理函数
					      $.messager.alert("提示", "获取承运商信息失败！");
					},
					dataType:"json",
				    success:function(data){
				    	
				    	editForm.find("#lspCodeAdd").combobox({
				        	data:data,
				        	onLoadSuccess :function(){
				        		editForm.find("#lspCodeAdd").combobox('setValue', selectRows[0].lspCode);
				        	}
				        });	
				    }
				});
				
				
				
				/*仓库下拉框*/
				$.ajax({
					url:'../masLoc/getMasLocListCombobox.json',
					cache:false,
				    type:"get",
				    error: function () {//请求失败处理函数
					      $.messager.alert("提示", "仓库请求失败!");
					},
				    dataType:"json",
				    success:function(data){
				    	editForm.find("#masLocAdd").combobox({
				        	data:data,
				        	onLoadSuccess :function(){
				        		editForm.find("#masLocAdd").combobox('setValue', selectRows[0].masLoc);
				        	}
				        });	
				    }
				});
				
				
				//时间规则下拉框
				$.ajax({
					url:'../transCapacity/getLotSeqListCombobox.json',
					cache:false,
				    type:"get",
				    error: function () {//请求失败处理函数
					      $.messager.alert("提示", "一级区域请求失败!");
					},
				    dataType:"json",
				    success:function(data){
				    	
				    	editForm.find("#lotSeq").combobox({
				        	data:data,
				        	onChange:function(option){
				        		$.ajax({
    			        			url:'../transCapacity/getLotBySeq.json?lotSeq='+option,
    			        			cache:false,
    			        		    type:"post",
    			        		    error: function () {//请求失败处理函数
    			        			      $.messager.alert("提示", "时间规则请求失败!");
    			        			},
    			        		    dataType:"json",
    			        		    success:function(data){
    			        		    	editForm.find("#startTime").val(data.startTime);
    			        		    	editForm.find("#endTime").val(data.endTime);
    			        		    	editForm.find("#standardTime").val(data.standardTime);
    			        		    }
				        		});	
				        	},
				        	onLoadSuccess :function(){
				        		editForm.find("#lotSeq").combobox('setValue', selectRows[0].lotSeq);
				        	}
				        });
				    	
				    }
				});

				editForm.find("#startTime").mask("99:99"); //时间
				editForm.find("#endTime").mask("99:99");
				editForm.find("#standardTime").mask("99:99");
				editForm.find("#deliveryTimeLot").val(selectRows[0].deliveryTimeLot);
				editForm.find("#enabled").combobox('setValue', selectRows[0].enabled);
				editForm.find("#transQty").numberbox('setValue',selectRows[0].transQty);
				editForm.find("#startTime").val(selectRows[0].startTime);
				editForm.find("#endTime").val(selectRows[0].endTime);
				editForm.find("#standardTime").val(selectRows[0].standardTime);
				editForm.find("#divisionCode").val(selectRows[0].divisionName);
			}
		}]
		
	});
	
	function showAddDialog(){
		var $dialog = $("#transcapacity_add_dialog");
		var buttons = [];
		buttons.push({
			"text" : "提交",
			"iconCls" : "icon-ok",
			"iconAlign": "right",
			"handler" : function() {
				var sValidate = $("#transcapacity_add_form").form("validate");
				if(!sValidate){
					return;
				} 
				
		        $('#transcapacity_add_form').form('submit', {
		        	url: "../transCapacity/insertTransCapacity.json",
		    		method: "post",
		    		onSubmit: function(){
		    			return true;
		    		},
		    		success: function(data){
		    			data = $.parseJSON(data);
		    			$.messager.show({
		    				"title" : "操作成功",
		    				"msg" : data.msg
		    			});
		    			$dialog.dialog('close');
		    			$("#transcapacity_add_form").form("reset");
		    		}
		    	});
			}
		});
		buttons.push({
			"text" : "取消",
			"iconCls" : 'icon-cancel',
			"iconAlign": "right",
			"handler" : function() {
				$dialog.dialog("close");
				$("#transcapacity_add_form").form("reset");
			}
		});
		$dialog.show().dialog({
			"title" : "新增半日达信息",
			"closed" : false,
			"modal" : true,
			"buttons" : buttons
		});
		
	}
	
	function showEditDialog(id){
		alert("id:"+id);
		var $dialog = $("#transcapacity_edit_dialog");
		var buttons = [];
		buttons.push({
			"text" : "提交",
			"iconCls" : "icon-ok",
			"iconAlign": "right",
			"handler" : function() {
				var sValidate = $("#transcapacity_edit_form").form("validate");
				if(!sValidate){
					return;
				}
				//var lspCode = $("#lspCodeAdd").val(); 
				
		        $('#transcapacity_edit_form').form('submit', {
		        	url: "../transCapacity/updateTransCapacity.json?id="+id,
		    		method: "post",
		    		onSubmit: function(){
		    			return true;
		    		},
		    		success: function(data){
		    			data = $.parseJSON(data);
		    			$.messager.show({
		    				"title" : "操作成功",
		    				"msg" : data.msg
		    			});
		    			$dialog.dialog('close');
		    			$("#transcapacity_edit_form").form("reset");
		    		}
		    	});
			}
		});
		buttons.push({
			"text" : "取消",
			"iconCls" : 'icon-cancel',
			"iconAlign": "right",
			"handler" : function() {
				$dialog.dialog("close");
				$("#transcapacity_edit_form").form("reset");
			}
		});
		$dialog.show().dialog({
			"title" : "修改半日达信息",
			"closed" : false,
			"modal" : true,
			"buttons" : buttons
		});
		
	}
	

	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#transcapacityView').datagrid("load", $("#transcapacity_search_form").form("formToJson"));
	});
	
	$("#search_form_reset").on("click", function(){
		$("#transcapacity_search_form").form("reset");
	});
	
	$("#divitionTree").tree({
		url:'../division/getTreeList.json',
		onBeforeExpand:function(node){
			if(node.divLevel<3){
				$('#divitionTree').tree('options').url = '../division/getChildList.json?childId='+node.id;
			}else{
				return false;
			}                  
	    }
	
	});
	

	/*承运商下拉框*/
	$.ajax({
		url:'../lspCarrier/getLspListCombobox.json',
		cache:false,
	    type:"get",
	    error: function () {//请求失败处理函数
		      $.messager.alert("提示", "获取承运商信息失败！");
		},
		dataType:"json",
	    success:function(data){
	    	
	        $("#lspCode").combobox({
	        	data:data
	        });	
	    }
	});
	
	/*仓库下拉框*/
	$.ajax({
		url:'../masLoc/getMasLocListCombobox.json',
		cache:false,
	    type:"get",
	    error: function () {//请求失败处理函数
		      $.messager.alert("提示", "仓库请求失败!");
		},
	    dataType:"json",
	    success:function(data){
	        $("#masLoc").combobox({
	        	data:data
	        });	
	    }
	});
	
});
