$(function() {
	/*初始化入库单grid高度*/
	$("#recordConfirm_grid").height($(window).height()-120);
	/*入库单GRID--展示界面*/
	$('#recordConfirmView').myDatagrid({
		"title": "入库单列表",
		"url": "../recordConfirm/recordConfirmLists.json?tradeType="+1,
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
		            {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},
		  			{field : 'customerName', title : '客户名称', align : 'center', sortable : true},
		  			{field : 'customerBank', title : '客户银行', align : 'center', sortable : true}, 
		  			{field : 'customerAccount', title : '客户账号', align : 'center', sortable : true}, 
		  			{field : 'sum',title : '金额', align : 'center',sortable : true},
		  			{field : 'productType',title : '商品类型',align : 'center', sortable : true,formatter:function(value){
		  				if(value=="01"){
		  					value="旧饰";
		  				}else if(value=="02"){
		  					value="黄金券";
		  				}
		  				return value;
		  			}
		  			},
		  			{field : 'orderChannel',title : '销售渠道',align : 'center', sortable : true},
		  			{field : 'recoveryNum',title : '入库单号',align : 'center', sortable : true},
					{field : 'orderDate',title : '入库日期',align : 'center', sortable : true, formatter:function(value){
						if(value=='' || value==null){
							//提醒....
						}else{
							var inputDate = new Date(value);
							return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate();
						}
						return "录入时间有误";
					}},
		  			{field : 'whName',title : '仓库',align : 'center', sortable : true},
		  			{field : 'orderStatus',title : '付款状态',align : 'center', sortable : true,formatter:function(value){
		  				if(value=="1"){
		  					value="未付款";
		  				}
		  				if(value=="4"){
		  					value="已取消";
		  				}else if(value=="9"){
		  					value="已付款";
		  				}else if(value=="4"){
		  					value="已取消";
		  				}
		  				return value;
		  			}}, 
		  			{field : 'traceNum',title : '录入状态',align : 'center', sortable : true,formatter:function(value){
		  				if(value==''||value==null){
		  					value="未录入";
		  				}else{
		  					value="已录入";
		  				}
		  				return value;
		  			}},
		  			{field : 'remark',title : '备注',align : 'center', sortable : true}
		]],
		"toolbar": [
//		 {
//			id : 'bt_cataAdd',
//			text : '旧金回收审核',
//			iconCls : 'icon-add',
//			handler : function() {
//				var bMany = false;
//				var selectRow = false;
//				comboboxVenName('1');
//				if(!selectRow){
//					var dataGrid = $("#recordConfirmView");
//					var selectRows = dataGrid.datagrid("getSelections");
//					if (!selectRows || selectRows.length <= 0) {
//						$.messager.alert("提示", "&nbsp;&nbsp;请选择一行数据！");
//						return;
//					}
//					selectRow = selectRows[0]; // 默认对选中的第一条数据进行操作
//					if (selectRows.length > 1) {
//						bMany = true; // 用户选中多条数据
//						var firstIndex = dataGrid.datagrid("getRowIndex", selectRow);
//						dataGrid.datagrid("unselectAll");
//						dataGrid.datagrid("selectRow", firstIndex);
//					}
//				}
//			},
//			"permission": "mdm:recordConfirm:" //新建
//		},
		{
			id : 'bt_cataAdd',
			text : '录入付款单',
			iconCls : 'icon-add',
			handler : function() {
				var bMany = false;
				var selectRow = false;
				comboboxVenName('1');
				if(!selectRow){
					var dataGrid = $("#recordConfirmView");
					var selectRows = dataGrid.datagrid("getSelections");
					if (!selectRows || selectRows.length <= 0) {
						$.messager.alert("提示", "&nbsp;&nbsp;请选择一行数据！");
						return;
					}
					selectRow = selectRows[0]; // 默认对选中的第一条数据进行操作
					if (selectRows.length > 1) {
						bMany = true; // 用户选中多条数据
						var firstIndex = dataGrid.datagrid("getRowIndex", selectRow);
						dataGrid.datagrid("unselectAll");
						dataGrid.datagrid("selectRow", firstIndex);
					}
				}
				
				var dataForm  = $("#recordConfirm_add_form");//form
                var formData  = selectRow;
                formData.id1 = formData.id;	
                if(formData.orderStatus=="2"){
                	$.messager.alert("提示", "付款单已提交不可录入");
                	return;
                }
                if(formData.orderStatus=="4"){
                	$.messager.alert("提示", "回购单已取消不可付款");
                	return;
                }
                if(formData.traceNum){
                	$.messager.alert("提示", "付款单已录入不可重复录入");
                    return;
                }
                $("#traceNum1").val("");
                $("#clearingForm1").val("");
                $("#settlementSubject1").val("");
                $("#oppSubject1").val("22020101");
				formData.customerName1 = formData.customerName;
				formData.customerBank1 = formData.customerBank;
				formData.customerAccount1 = formData.customerAccount;
				formData.sum1 = formData.sum;
				var inputDate = new Date(formData.ticketDate);
				inputDate = inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
				inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
				formData.ticketDate1 = inputDate;
				if (formData) {
					dataForm.form("load", formData); // 修改或查询，绑定数据到编辑表单
				}
				
				var dataDialog = $("#recordConfirm_add_dialog");
				getDateFromSession();
				var buttons = [];
					/* 编辑或新增的时候的提交按钮 */
					buttons.push({
						"text" : "保存",
						"iconCls" : "icon-ok",
						"handler" : function() {
							var bValidate = true;
							bValidate = $(dataForm).form("validate");
							if(bValidate){
								// 异步提交
								updateRecordAddStatus(dataForm);
							}
						}
					}); 
				/* 取消按钮 */
				buttons.push({
					"text" : "取消",
					"iconCls" : 'icon-cancel',
					"handler" : function() {
						dataDialog.dialog("close");
					}
				});
				dataDialog.show().dialog({
					"title" : "新建",
					//"width": handler.dialog.width,
					//"height": dh,
					"closed" : false,
					"modal" : true,
					"buttons" : buttons
				});
				if(bMany){
					multiRowMessge();
				}
				btnExt();
			},
			"permission": "mdm:recordConfirm:add" //新建
		},
		{
			id : 'bt_cataUpdate',
			text : '修改付款单',
			iconCls : 'icon-edit',
			handler : function() {
				var selectRow = false;
				var bMany = false;
				comboboxVenNameUpdate(2);
				if(!selectRow){
					var dataGrid = $("#recordConfirmView");
					var selectRows = dataGrid.datagrid("getSelections");
					if (!selectRows || selectRows.length <= 0) {
						$.messager.alert("提示", "&nbsp;&nbsp;请选择一行数据！");
						return;
					}
					selectRow = selectRows[0]; // 默认对选中的第一条数据进行操作
					if (selectRows.length > 1) {
						bMany = true; // 用户选中多条数据
						var firstIndex = dataGrid.datagrid("getRowIndex", selectRow);
						dataGrid.datagrid("unselectAll");
						dataGrid.datagrid("selectRow", firstIndex);
					}
				}
				var dataForm  = $("#recordConfirm_edit_form");//form
				var formData  = selectRow;
				
				 if(formData.orderStatus=="2"){
	                	$.messager.alert("提示", "付款单已提交不可修改");
	                	return;
	                }
	                if(!formData.traceNum){
	                	$.messager.alert("提示", "付款单未录入，请录入后再修改");
	                    return;
	                }
	                if (selectRows.length > 1) {
						multiRowMessge();
					}
                formData.id2 = formData.id;
				formData.traceNum2 = formData.traceNum;
				formData.oppSubject2 = formData.oppSubject;
				formData.clearingForm2 = formData.clearingForm;
				$("#clearingForm2").val(formData.clearingForm2);
				formData.settlementSubject2= formData.settlementSubject;
				formData.customerName2 = formData.customerName;
				formData.customerBank2 = formData.customerBank;
				formData.customerAccount2 = formData.customerAccount;
				formData.sum2 = formData.sum;
				var inputDate = new Date(formData.ticketDate);
				inputDate = inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
				inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
				formData.ticketDate2 = inputDate;
				if (formData) {
					dataForm.form("load", formData); // 修改或查询，绑定数据到编辑表单
				}
				var dataDialog = $("#recordConfirm_edit_dialog");
				var buttons = [];
				buttons.push({
					"text" : "保存",
					"iconCls" : "icon-ok",
					"handler" : function() {
						var bValidate = true;
						bValidate = $(dataForm).form("validate");//校验是否必录
						if(bValidate){
							// 异步提交
							updateRecordUpdateStatus(dataForm);
						}
					}
				});
				/* 取消按钮 */
				buttons.push({
					"text" : "取消",
					"iconCls" : 'icon-cancel',
					"handler" : function() {
						dataDialog.dialog("close");
					}
				});
				dataDialog.show().dialog({
					"title" : "修改",
					"closed" : false,
					"modal" : true,
					"buttons" : buttons
				});
				btnExt();
				/* 当用户选中多条数据时，提示用户将默认对其选择的第一条数据进行操作 */
				if (bMany) {
					multiRowMessge();
				}
			},
			"permission": "mdm:recordConfirm:edit" //修改
		},
		    {
			id : 'bt_cataPass',
			text : '付款确认',
			iconCls : 'icon-remove',
			handler : function() {
				var $tb  = $("#recordConfirmView");
				var bMany = false;
				var selectRows = $tb.datagrid("getSelections");
				if(!selectRows || selectRows.length<=0){
					$.messager.alert("提示", "请先选择所要提交的单据。");
					return;
				} 
				selectRow = selectRows[0]; // 默认对选中的第一条数据进行操作
				if (selectRow.length > 1) {
					bMany = true; // 用户选中多条数据
					var firstIndex = $tb.datagrid("getRowIndex", selectRow);
					$tb.datagrid("unselectAll");
					$tb.datagrid("selectRow", firstIndex);
				}
				if (bMany) {
					multiRowMessge();
				}
				//已提交，不能再次提交
				var orderStatus = selectRows[0].orderStatus;
				if( orderStatus=='2' ){
					$.messager.alert("提示", "&nbsp;&nbsp;已提交，不允许进行再次提交！");
					return;
				}
				if(!selectRows[0].traceNum){
					$.messager.alert("提示", "请先录入付款单后再确认提交！");
					return;
				}
				//自动生成凭证提示
				$.messager.confirm("操作提示", "提交后将自动生成凭证，您确定要执行操作吗？", function (data) {
		            if (data) {
		            	updateRecordStatus(selectRows[0].id,orderStatus,"0");
		            }
		            else {
		            }
		        });
			},
			"permission": "mdm:recordConfirm:commit" //提交
		},
		
		{

			id : 'data_form_detail',
			text : '查看明细',
			iconCls : 'icon-search',
			handler : function() {
				var $tb  = $("#recordConfirmView");
				var bMany = false;
				var selectRows = $tb.datagrid("getSelections");
				if(!selectRows || selectRows.length<=0){
					$.messager.alert("提示", "请先选择要查看的单据。");
					return;
				} 
				selectRow = selectRows[0]; // 默认对选中的第一条数据进行操作
				if (selectRows.length > 1) {
					bMany = true; // 用户选中多条数据
					var firstIndex = $tb.datagrid("getRowIndex", selectRow);
					$tb.datagrid("unselectAll");
					$tb.datagrid("selectRow", firstIndex);
				}
				var $d = $('#data_detail_dialog');
				$d.show().dialog({
					"title" : "详细信息",
					"closed" : false,
					"width": 1050,    
				    "height": 400, 
					"modal" : true,
					"buttons" : [{
						"text" : "关闭",
						"iconCls" : 'icon-cancel',
						"handler" : function() {
							//$('#pomainRecordDetailView').datagrid('loadData',{total:0,rows:[]}); 
							$d.dialog("close");
						}
					}]
				});
				btnExt();
				/*查看明细GRID*/
				$('#recordConfirmDetailView').myDatagrid(
						{
					"url": "../recordConfirm/getPOMainRecordsDetail.json?recoveryNum="+ selectRows[0].recoveryNum,
					"singleSelect": true, //多选
					"method": "get", 
					"columns" : [[
						{field : 'invCode', title : '存货编码', 	 width : 30, align : 'center' },          
						{field : 'invName', title : '存货名称', 	 width : 60, align : 'center'},
						{field : 'quantity', title : '主计量数量', 	 width : 30, align : 'center'},
						{field : 'price', title : '无税金额', 	     width : 25, align : 'center'},
						{field : 'free', title : '克重', 	         width : 20, align : 'center'}
					]]
				});
				if (bMany) {
					multiRowMessge();
				}
			},
			"permission": "mdm:recordConfirm:recordConfirmDetail" //查看明细
		}],
		//"model": "pomainRecord", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改系统参数窗口，将自动匹配ID：sysParam_edit_dialog
		"dblClickHandler": "detailHandler" //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
	});
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#recordConfirmView').datagrid("load", $("#recordConfirm_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#recordConfirm_search_form").form("reset");
	});
});

function multiRowMessge(){
	$.messager.show({
		"title" : "温馨提示",
		"msg" : "<span style='color:red;'>*</span>由于您选择了多条数据，系统将默认对您选择的第一条数据进行操作。",
		"style" : {
			"right" : "",
			"top" : document.body.scrollTop + document.documentElement.scrollTop,
			"bottom" : ""
		},
		"timeout" : 5000
	});
};
//加载下拉框
function comboboxVenName(index){
	$("#clearingForm1").combobox({ 
		url:"../recordConfirm/querySettles.json",
		valueField:'cCode',
		textField:'cSSName',
		method:'post',
		onSelect:function(param){
			$("input[name='clearingForm1']").val(param.cSSName);
			$("#clearingForm1").val(param.cCode);
			$("#settlementSubject1").val(param.cCode);
		},
		onUnselect:function(param){
			$("#clearingForm1").val("");
		}
	});
}
//加载下拉框
function comboboxVenNameUpdate(index){
	$("#clearingForm2").combobox({ 
		url:"../recordConfirm/querySettles.json",
		valueField:'cCode',
		textField:'cSSName',
		method:'post',
		onSelect:function(param){
			$("#clearingForm2").val(param.cSSName);
			$("#settlementSubject2").val(param.cCode);
		},
		onUnselect:function(param){
			$("#clearingForm2").val("");
		}
	});
}


/**
 * 确认付款
 */
function updateRecordStatus(id,orderStatus){
	$.ajax({
		url: "../recordConfirm/commitByIds.json",
		data: {"id":id,"orderStatus":orderStatus},
		dataType: "json",
		type: "post",
		cache: false,
		error:function(data){
		},
		success: function(result){
			 // 返回需要修改的数据信息
			if(result && (result.state == true || result.state == "true")){
				$.messager.show({
					"title" : "确认付款成功",
					"msg" : result.msg
				});
				$('#recordConfirmView').datagrid("load", $("#recordConfirm_search_form").form("formToJson"));// 重绘表格
				
			} else {
				$.messager.alert("确认付款失败", result.msg);
			}
		}
	});
}
/**
 * 录入旧金回收入库单的付款单
 */
function updateRecordAddStatus(dataForm){
	$.ajax({
		url: "../recordConfirm/createRecordConfirm.do",
		data: dataForm.form("formToJson"),
		dataType: "json",
		type: "post",
		cache: false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert("提示", XMLHttpRequest + textStatus+ errorThrown);
		},
		success: function(result){
			// 返回需要修改的数据信息
			if(result && (result.state == true || result.state == "true")){
				$.messager.show({
					"title" : "录入成功",
					"msg" : result.msg
				});
				$("#recordConfirm_add_dialog").dialog("close"); // 关闭DIALOG
				$("#recordConfirm_add_form").form("clear"); // 清空表单
				$('#recordConfirmView').datagrid("load", $("#recordConfirm_search_form").form("formToJson"));// 重绘表格
			} else {
				$.messager.alert("操作失败", result.msg);
			}
			
		}
	});
	
}
/**
 * 修改旧金回收入库单的付款单
 */
function updateRecordUpdateStatus(dataForm){
	$.ajax({
		url: "../recordConfirm/updateRecordConfirm.do",
		data: dataForm.form("formToJson"),
		dataType: "json",
		type: "post",
		cache: false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert("提示", XMLHttpRequest + textStatus+ errorThrown);
		},
		success: function(result){
			// 返回需要修改的数据信息
			if(result && (result.state == true || result.state == "true")){
				$.messager.show({
					"title" : "修改成功",
					"msg" : result.msg
				});
				$("#recordConfirm_edit_dialog").dialog("close"); // 关闭DIALOG
				$("#recordConfirm_edit_form").form("clear"); // 清空表单
				$('#recordConfirmView').datagrid("load", $("#recordConfirm_search_form").form("formToJson"));// 重绘表格
			} else {
				$.messager.alert("修改失败", result.msg);
			}
			
		}
	});
	
}

//制单日期
function getDateFromSession(){
	$.ajax({  
		   type: "POST",  
		   url : "../recordConfirm/getDateFromSession.json",  
		   dataType:"json",  
		   success: function(data){
			   $("#ticketDate1").datebox("setValue", data.ticketDate);
		   }  
		 });
}
//校验方法
$.extend($.fn.validatebox.defaults.rules,{
	//供应商校验
   venNameChang:{ 
    validator:function(value,param){  
    	var cVenName=$("#"+param).val();
    	if(cVenName==value){
    		return true; 
    	}
    }, 
    message:'选择的供应商有误！' 
   },
    //金额校验
   sumCheck:{ 
	   validator:function(value){  
	    	var cc = /^(\-|\+)?\d+(.[0-9]{1,4})?$/;
	//	    	 var reg = /^-[0-9]+([\.]{0,1}[0-9]{1,3})$/;
	    	if(cc.test(value)){
	    		return true; 
	    	}
	   }, 
	   message:'输入金额的位数或格式有误！' 
   },
   //单据编号校验
   paymentCodeCheck:{ 
	   validator:function(value){  
			var reg = /^[A-Za-z0-9]+$/;
			if(reg.test(value)&&value.length<20){
				return true;
			}
	   },
	   message:'单据编号的格式或长度错误' 
   } 
});
