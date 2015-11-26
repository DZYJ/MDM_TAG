$(function() {
	/*初始化运力grid高度*/
	$("#transport_grid").height($(window).height()-120);
	
	/*运力信息GRID*/
	$('#transportView').myDatagrid({
		"title": "承运商运力",
		"url": "../transport/list.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
			{field : 'id', title : 'ID', width : 40, align : 'center', sortable : true, hidden:true},
			{field : 'lsp', title : '承运商编码', 	 width : 40, align : 'center', sortable : true},
			{field : 'lspName', title : '承运商名称', 	 width : 40, align : 'center', sortable : true},
			{field : 'divisionCode', title : '区域编码', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'divisionName', title : '区域名称', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'priority',title : '优先级',  width : 60, align : 'center'}, 
			{field : 'maxOrdQty',title : '最大运单量', width : 60, align : 'center', sortable : true}, 
			{field : 'minDivisor',	title : '份额',width : 60, align : 'center'},
			{field : 'minAmount',title : '金额下限', width : 40, align : 'center', sortable : true},
			{field : 'maxAmount',title : '金额上限', width : 40, align : 'center', sortable : true},
			{field : 'orderDownloadBeginTime',title : '最早订单下载时间', width : 40, align : 'center', sortable : true},
			{field : 'orderDownloadEndTime',title : '最迟订单下载时间', width : 40, align : 'center', sortable : true}
		]],
		"model": "transport", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改系统参数窗口，将自动匹配ID：sysParam_edit_dialog
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"form": {"validate":true, "submitUrl": "../transport/update.do"}
		},
		"removeHandler": {
			"enable": true,
			"title": "删除",
			"removeUrl": "../transport/deleteByIds.json", 
			"idField": "id",
			"idParams": "ids"
		},
		"toolbar": [{
			id : 'btnrole',
			text : '添加',
			iconCls : 'icon-edit',
			handler : function() {
				showAddDialog();
				$("#orderDownloadBeginTime").mask("99:99"); //时间
				$("#orderDownloadEndTime").mask("99:99");
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
			}
		}]
		
	});
	
	function showAddDialog(){
		var $dialog = $("#transport_add_dialog");
		var buttons = [];
		buttons.push({
			"text" : "提交",
			"iconCls" : "icon-ok",
			"iconAlign": "right",
			"handler" : function() {
				var sValidate = $("#transport_add_form").form("validate");
				if(!sValidate){
					return;
				}
				var arrays="";
				var beginTime = $("#orderDownloadBeginTime").val();
				arrays = beginTime.split(":");
				if(arrays[0]>24 || arrays[1]>59){
					$.messager.alert("提示", "时间不能大于24:00！");
					return false;
				}else if(arrays[0]==24 && arrays[1]>0){
					$.messager.alert("提示", "时间不能大于24:00！");
					return false;
				}
				
				var endTime = $("#orderDownloadEndTime").val();
				arrays = endTime.split(":");
				if(arrays[0]>24 || arrays[1]>59){
					$.messager.alert("提示", "时间不能大于24:00！");
					return false;
				}else if(arrays[0]==24 && arrays[1]>0){
					$.messager.alert("提示", "时间不能大于24:00！");
					return false;
				}
				
				
				
				var nodes = $('#divitionTree').tree('getChecked');
				var dataArray =[];
				if(nodes.length<1){
					$.messager.alert("提示", "请选择区域范围！");
					return;
				}
		        for (var i = 0; i < nodes.length; i++) {
		            dataArray.push(nodes[i].id+","+nodes[i].divLevel);
		        }
		        var str = jQuery.param({divisionCodes:dataArray});//jquery用这种方式传数组
		        
		        $('#transport_add_form').form('submit', {
		        	url: "../transport/insertTransportList.json?"+str,
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
		    		}
		    	});
			}
		});
		buttons.push({
			"text" : "取消",
			"iconCls" : 'icon-cancel',
			"iconAlign": "right",
			"handler" : function() {
				$("#divitionTree").tree("reload");
				$dialog.dialog("close");
			}
		});
		$dialog.show().dialog({
			"title" : "新增运力",
			"closed" : false,
			"modal" : true,
			"buttons" : buttons
		});
		
	}

	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#transportView').datagrid("load", $("#transport_search_form").form("formToJson"));
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
	    	
	        $("#lsp").combobox({
	        	data:data
	        });	
	    }
	});
	
});
