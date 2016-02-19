$(function() {
	/*初始化入库单grid高度*/
	$("#salesReturn_grid").height($(window).height()-120);
	comboboxChannelName();
	/*入库单GRID--展示界面*/
	$('#salesReturnView').myDatagrid({
		"title": "收款信息表",
		"url": "../salesProceeds/salesProceedsLists.json",
		"singleSelect": true, //多选
		"method": "post", 
		"rownumbers": true,
		"columns" : [[
		  			{field : 'paymentCode',title : '订单号', align : 'center',sortable : true},
		  			{field : 'orderDate',title : '订单日期',align : 'center', sortable : true, formatter:function(value){
						if(value=='' || value==null){
							//提醒....
						}else{
							var inputDate = new Date(value);
							return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate();
						}
						return "";
					}},		  			
					{field : 'totalAmount', title : '总金额', align : 'center', sortable : true},
					{field : 'discountAmount', title : '优惠金额', align : 'center', sortable : true},
					{field : 'sum', title : '实际收款', align : 'center', sortable : true},
					{field : 'cremark', title : '渠道', align : 'center', sortable : true},
		  			{field : 'freight',title : '运费', align : 'center',sortable : true}
		]],
		"toolbar": [{
				id : 'bt_cataCommit',
				text : '导入',
				iconCls : 'icon-filter',
				handler : function() {
					var dataForm  = $("#proceeds_import_form");//form
					var dataDialog = $("#proceeds_import_dialog");
					dataForm.form("reset"); // 清空表单
					$("#errorMessage").text("");
					var buttons = [];
					buttons.push({
						"text" : "导入",
						"iconCls" : "icon-filter",
						"handler" : function() {
							var file = ($("#file").val());  
						    if (file == "") {
						    	$("#errorMessage").text("请选择将要上传的文件!");
//						        $.messager.alert('Excel批量用户导入', '请选择将要上传的文件!');          
						    }  
						    else {  
						    	var strRegex = "(.xls|.XLS|.xlsx|.XLSX)$";
						    	var re=new RegExp(strRegex);
						    	
						        if (!re.test(file.toLowerCase())) {
						        	$("#errorMessage").text("文件类型不正确，请选择.xls/.xlsx类型文件!");
//						            $.messager.alert('Excel批量用户导入','文件类型不正确，请选择.xls/.xlsx类型文件!');   
						        }  
						        else {  
						        	$("#errorMessage").text("");
//						            $('#exportload').window('open'); //显示进度条
						        	//获取select选择的的内容
//						        	var xxx = $("#s option:selected").text();
//						        	$("#").val();	//赋值
						            dataForm.form('submit', {  
						                success: function(data) {  
						                	var dataMap =  eval("("+data+")"); 
						                	if(dataMap.message=="success"){
						                		$.messager.alert('Excel批量用户导入', "导入成功！");
						                		dataDialog.dialog("close");
						                		$('#salesReturnView').datagrid("load", $("#salesReturn_search_form").form("formToJson"));
						                	}else{
						                		$.messager.alert('Excel批量用户导入', '导入失败,失败原因：'+dataMap.errorLog);  
						                	}
						                }
						            });   
						        }  
						    }   
						}
					});
					buttons.push({
						"text" : "取消",
						"iconCls" : 'icon-cancel',
						"handler" : function() {
							dataDialog.dialog("close");
						}
					});
					
					dataDialog.show().dialog({
						"title" : "导入第三方收款信息 ",
						"closed" : false,
						"modal" : true,
						"buttons" : buttons
					});
				},
				"permission": "mdm:salesProceeds:import" 
		},
		],
		"dblClickHandler": "detailHandler" //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
	});
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#salesReturnView').datagrid("load", $("#salesReturn_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#salesReturn_search_form").form("reset");
	});
});

//加载渠道下拉框
function comboboxChannelName(){
	$("#orderChannel").combobox({ 
		url:"../salesProceeds/salesProceedsChannelNameList.json",
		valueField:'orderChannel',
		textField:'cremark',
		method:'post'
	});
}
