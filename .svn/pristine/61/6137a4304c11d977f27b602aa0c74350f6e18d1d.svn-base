$(function() {
	//加载下拉框
	comboboxVenName("");
	
	/*初始化数据字典grid高度*/
	$("#paymentSettlement_grid").height($(window).height()-120);
	
	/*数据字典GRID*/
	$('#paymentSettlementView').myDatagrid({
		"title": "付款单列表",
		"url": "../woPaymentSettlement/paymentSettlementLists.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
		    {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},
			{field : 'paymentCode', title : '单据编号', align : 'center', sortable : true},
			{field : 'venName', title : '供应商', align : 'center', sortable : true}, 
			{field : 'venCode', title : '供应商', align : 'center', sortable : true,hidden:true}, 
			{field : 'clearingForm',title : '结算方式', align : 'center'}, 
			{field : 'settlementSubject',title : '结算科目',align : 'center', sortable : true}, 
			{field : 'vendorBank',title : '供应商银行',align : 'center'},
			{field : 'vendorAccount',title : '供应商账户',align : 'center', sortable : true},
			{field : 'itemCode',title : '项目大类编码',align : 'center', sortable : true},
			{field : 'oppSubject',title : '对方科目',align : 'center', sortable : true},
			{field : 'oppItemCode',title : '对方项目编码',align : 'center', sortable : true,hidden:true},
			{field : 'payType',title : '预付类型',align : 'center', sortable : true,formatter:function(value){
				if(value=="1"){
					value="采购";
				}else if(value=="2"){
					value="委外";
				}
				return value;
			}},
			{field : 'type',title : '款项类型',align : 'center', sortable : true,formatter:function(value){
				if(value=="1"){
					value="预付款";
				}else if(value=="2"){
					value="应付款";
				}
				return value;
			}},
			{field : 'sum',title : '金额',align : 'center', sortable : true},
			{field : 'comment',title : '入库单编号',align : 'center', sortable : true,hidden:true},
			{field : 'status',title : '状态',align : 'center', sortable : true,formatter:function(value){
				if(value=="1"){
					value="新建";
				}else if(value=="2"){
					value="已提交";
				}else if(value=="3"){
					value="已审核";
				}else if(value=="4"){
					value="作废";
				}
				return value;
			}},
			{field : 'inputer',title : '录入人',align : 'center', sortable : true},
			{field : 'createTd',title : '录入时间',align : 'center', sortable : true, formatter:function(value){
				if(value=='' || value==null){
					//提醒....
				}else{
					var inputDate = new Date(value);
					return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
					inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
				}
				return "录入时间有误";
			}},
			{field : 'digest',title : '摘要',align : 'center', sortable : true},
			{field : 'prePaymentID', title : '预付单ID', align : 'center', sortable : true,hidden:true}, 
			{field : 'invoiceID', title : '发票ID', align : 'center', sortable : true,hidden:true},
			{field : 'ticketDate', title : '制单日期', align : 'center', sortable : true,hidden:true}, 
			{field : 'prePaymentCode', title : '预付单号', align : 'center', sortable : true,hidden:true}, 
			{field : 'prePaymentIDTotal', title : '预付单总金额', align : 'center', sortable : true,hidden:true}, 
			{field : 'invoiceCode', title : '发票号', align : 'center', sortable : true,hidden:true}, 
			{field : 'invoiceIDTotal', title : '发票总金额', align : 'center', sortable : true,hidden:true}, 
			{field : 'redFlag', title : '红字标识', align : 'center', sortable : true,hidden:true}
		]],
		"toolbar": [
			{
				id : 'bt_cataAdd',
				text : '新建',
				iconCls : 'icon-add',
				handler : function() {
					var dataForm  = $("#paymentSettlement_add_form");//form
					dataForm.form("reset"); // 清空表单
					/*if (formData) {//formData
						dataForm.form("load", formData); // 修改或查询，绑定数据到编辑表单
					}*/
					var dataDialog = $("#paymentSettlement_add_dialog");
					comboboxVenName('1');
					getDateFromSession();
					var buttons = [];
			//		if (true) {
						/* 编辑或新增的时候的提交按钮 */
						buttons.push({
							"text" : "保存",
							"iconCls" : "icon-ok",
							"handler" : function() {
								var bValidate = true;
								var sum1 = $("#sum1").val();
								var prePaymentIDTotal1 = $("#prePaymentIDTotal1").val();
								var invoiceIDTotal1 = $("#invoiceIDTotal1").val();
								sum1 = sum1*1;
								prePaymentIDTotal1 = prePaymentIDTotal1*1;
								invoiceIDTotal1 = invoiceIDTotal1*1;
								var totalMoney = prePaymentIDTotal1 + sum1;
			//					if(dataForm.form.validate){
								$("#venCode1").val($("input[name='venCode1']").val());
								bValidate = $(dataForm).form("validate");
								
								var invoiceCode1 = $("#invoiceCode1").val();
								if(invoiceCode1 == null || invoiceCode1 == ""){
									$.messager.alert("提示", "请录入发票单！");
									return;
								}
								
								totalMoney = totalMoney.toFixed(4);
								if(invoiceIDTotal1 != totalMoney){
									$.messager.alert("操作失败", "输入金额有误，选择发票总金额应为选择的预付单金额+付款金额");
									return;
								}
								if($("#redFlag1").attr("checked") == "checked"){
									if(sum1 >= 0){
										$.messager.alert("提示", "红字付款结算金额必须为负数！");
										return;
									}
									if(prePaymentIDTotal1 != null && prePaymentIDTotal1 !="" && prePaymentIDTotal1 !=0){
										if(prePaymentIDTotal1 >= 0){
											$.messager.alert("提示", "红字预付单总金额必须为负数！");
											return;
										}
									}
									if(invoiceIDTotal1 >= 0){
										$.messager.alert("提示", "红字发票总金额必须为负数！");
										return;
									}
								}else{
									if(sum1 < 0){
										$.messager.alert("提示", "蓝字付款结算金额不能小于零！");
										return;
									}
									if(prePaymentIDTotal1 != null && prePaymentIDTotal1 !="" && prePaymentIDTotal1 !=0){
										if(prePaymentIDTotal1 < 0){
											$.messager.alert("提示", "蓝字预付单总金额不能小于零！");
											return;
										}
									}
									if(invoiceIDTotal1 < 0){
										$.messager.alert("提示", "蓝字发票总金额不能小于零！");
										return;
									}
								}
								/*}else{
									bValidate = true;
								}*/
								
								if(bValidate){
									// 异步提交
									updateRecordAddStatus(dataForm,status,"0");
								}
							}
						});
			//		}
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
					btnExt();
				},
				"permission": "mdm:paymentSettlement:add" //新建
			},
			{
				id : 'bt_cataUpdate',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					var selectRow = false;
					var bMany = false;
					if(!selectRow){
						var dataGrid = $("#paymentSettlementView");
						var selectRows = dataGrid.datagrid("getSelections");// var selectRow = $(oGrid).datagrid("getSelected");
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
					var dataForm  = $("#paymentSettlement_edit_form");//form
					var formData  = selectRow;
					formData.status2 = formData.status;
					var _status = formData.status2;
					if (_status == "2") {
						$.messager.alert("警告", "已提交，不能修改");
						return;
					}else if(_status == "4"){
						$.messager.alert("警告", "已作废，不能修改");
						return;
					}
					var _redFlag = formData.redFlag;
					if(_redFlag == "1"){
						formData.redFlag2 = _redFlag;
						$("#redFlag2").attr("checked",true);
					}
//					formData.id2 = formData.id;
					formData.paymentCode2 = formData.paymentCode;
					formData.venName2 = formData.venName;
					$("#venName2").val(formData.venName2);
					$("#venCode2").val(formData.venCode);
					formData.clearingForm2 = formData.clearingForm;
					$("#clearingForm2").val(formData.clearingForm2);
					formData.settlementSubject2 = formData.settlementSubject;
					formData.vendorBank2 = formData.vendorBank;
					formData.vendorAccount2 = formData.vendorAccount;
					formData.itemCode2 = formData.itemCode;
					formData.oppSubject2 = formData.oppSubject;
					formData.oppItemCode2 = formData.oppItemCode;
					formData.payType2 = formData.payType;
					formData.type2 = formData.type;
					formData.sum2 = formData.sum;
					formData.inputer2 = formData.inputer;
					formData.createTd2 = formData.createTd;
					formData.digest2 = formData.digest;
					formData.prePaymentID2 = formData.prePaymentID;
					formData.invoiceID2 = formData.invoiceID;
					var inputDate = new Date(formData.ticketDate);
					inputDate = inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
					inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
					formData.ticketDate2 = inputDate;
					formData.prePaymentCode2 = formData.prePaymentCode;
					formData.prePaymentIDTotal2 = formData.prePaymentIDTotal;
					formData.invoiceCode2 = formData.invoiceCode;
					formData.invoiceIDTotal2 = formData.invoiceIDTotal;
					comboboxVenName('2');
					if (formData) {
						dataForm.form("load", formData); // 修改或查询，绑定数据到编辑表单
					}
					var dataDialog = $("#paymentSettlement_edit_dialog");
					var buttons = [];
					buttons.push({
						"text" : "保存",
						"iconCls" : "icon-ok",
						"handler" : function() {
							var bValidate = true;
							var sum2 = $("#sum2").val();
							var prePaymentIDTotal2 = $("#prePaymentIDTotal2").val();
							var invoiceIDTotal2 = $("#invoiceIDTotal2").val();
							sum2 = sum2*1;
							prePaymentIDTotal2 = prePaymentIDTotal2*1;
							invoiceIDTotal2 = invoiceIDTotal2*1;
							var totalMoney = prePaymentIDTotal2 + sum2;
//							if(dataForm.form.validate){
							bValidate = $(dataForm).form("validate");
							
							var invoiceCode2 = $("#invoiceCode2").val();
							if(invoiceCode2 == null || invoiceCode2 == ""){
								$.messager.alert("提示", "请录入发票单！");
								return;
							}
							
							totalMoney = totalMoney.toFixed(4);
							if(invoiceIDTotal2 != totalMoney){
								$.messager.alert("操作失败", "输入金额有误，选择发票总金额应为选择的预付单金额+付款金额");
								return;
							}
							if($("#redFlag2").attr("checked") == "checked"){
								if(sum2 >= 0){
									$.messager.alert("提示", "红字付款结算金额必须为负数！");
									return;
								}
								if(prePaymentIDTotal2 != null && prePaymentIDTotal2 !="" && prePaymentIDTotal2 !=0){
									if(prePaymentIDTotal2 >= 0){
										$.messager.alert("提示", "红字预付单总金额必须为负数！");
										return;
									}
								}
								if(invoiceIDTotal2 >= 0){
									$.messager.alert("提示", "红字发票总金额必须为负数！");
									return;
								}
							}else{
								if(sum2 < 0){
									$.messager.alert("提示", "蓝字付款结算金额不能小于零！");
									return;
								}
								if(prePaymentIDTotal2 != null && prePaymentIDTotal2 !="" && prePaymentIDTotal2 !=0){
									if(prePaymentIDTotal2 < 0){
										$.messager.alert("提示", "蓝字预付单总金额不能小于零！");
										return;
									}
								}
								if(invoiceIDTotal2 < 0){
									$.messager.alert("提示", "蓝字发票总金额不能小于零！");
									return;
								}
							}
							/*}else{
								bValidate = true;
							}*/
							if(bValidate){
								// 异步提交
								updateRecordUpdateStatus(dataForm,status,"0");
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
						//"width": handler.dialog.width,
						//"height": dh,
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
				"permission": "mdm:paymentSettlement:edit" //修改
			},
				{
				id : 'bt_cataPass',
				text : '查看',
				iconCls : 'icon-search',
				handler : function() {
					var $tb  = $("#paymentSettlementView");
					var rows = $tb.datagrid("getSelections");
					if(!rows || rows.length<=0){
						$.messager.alert("提示", "请先选择所要提交的单据。");
						return;
					} 
					
					var row = rows[0];
					
					var _ticketDate = row.ticketDate;
					_ticketDate = new Date(_ticketDate);
					_ticketDate = _ticketDate.getFullYear()+'-'+(_ticketDate.getMonth()+1)+'-'+_ticketDate.getDate();
					row.ticketDate3 = _ticketDate;
					
					if (rows.length > 1) {
						//bMany = true; // 用户选中多条数据
						var firstIndex = $tb.datagrid("getRowIndex", row);
						$tb.datagrid("unselectAll");
						$tb.datagrid("selectRow", firstIndex);
					}
					
					var dataForm = $("#paymentSettlement_detail_form");
					dataForm.form("reset"); // 清空表单
					if (row) {
						dataForm.form("load", row); // 修改或查询，绑定数据到编辑表单
					}
					var dataDialog = $("#paymentSettlement_detail_dialog");
					dataDialog.show().dialog({
						"title" : "详细信息",
						"closed" : false,
						"width": 900,    
					    "height": 380, 
						"modal" : true,
						"buttons" : [{
							"text" : "关闭",
							"iconCls" : 'icon-cancel',
							"handler" : function() {
								//$('#pomainRecordDetailView').datagrid('loadData',{total:0,rows:[]}); 
								dataDialog.dialog("close");
							}
						}]
					});
					if (rows.length > 1) {
						multiRowMessge();
					}
					btnExt();
				},
				"permission": "mdm:paymentSettlement:detail" //查看权限
			},{
		    id : 'bt_cataPass',
			text : '提交',
			iconCls : 'icon-remove',
			handler : function() {
				var $tb  = $("#paymentSettlementView");
				var rows = $tb.datagrid("getSelections");
				if(!rows || rows.length<=0){
					$.messager.alert("提示", "请先选择所要提交的单据。");
					return;
				} 
				var row = "";
				row = rows[0];
				if (rows.length > 1) {
					//bMany = true; // 用户选中多条数据
					var firstIndex = $tb.datagrid("getRowIndex", row);
					$tb.datagrid("unselectAll");
					$tb.datagrid("selectRow", firstIndex);
					multiRowMessge();
				}
				//已提交，不能再次提交
				var status = rows[0].status;
				if( status=='2' ){
					$.messager.alert("提示", "&nbsp;&nbsp;付款单据已提交，无需再提交！");
					return;
				}else if(status == "4"){
					$.messager.alert("提示", "已作废，不能提交");
					return;
				}
				
				//自动生成凭证提示
				$.messager.confirm("操作提示", "提交后将自动生成凭证，您确定要执行操作吗？", function (data) {
		            if (data) {
		            	updateRecordStatus(rows[0].id,status,"0");
		            }
		            else {
		            }
		        });
				
			},
			"permission": "mdm:paymentSettlement:commit" //提交权限
		},
		{
			id : 'bt_cataPass',
			text : '作废',
			iconCls : 'icon-remove',
			handler : function() {
				var selectRow = false;
				//var bMany = false;
				if(!selectRow){
					var dataGrid = $("#paymentSettlementView");
					var selectRows = dataGrid.datagrid("getSelections");// var selectRow = $(oGrid).datagrid("getSelected");
					if (!selectRows || selectRows.length <= 0) {
						$.messager.alert("提示", "&nbsp;&nbsp;请选择一行数据！");
						return;
					}
					selectRow = selectRows[0]; // 默认对选中的第一条数据进行操作
					if (selectRows.length > 1) {
						//bMany = true; // 用户选中多条数据
						var firstIndex = dataGrid.datagrid("getRowIndex", selectRow);
						dataGrid.datagrid("unselectAll");
						dataGrid.datagrid("selectRow", firstIndex);
						multiRowMessge();
					}
				}
				//var dataForm  = $("#invoice_edit_form");//form
				var formData  = selectRow;
				//formData.status2 = formData.status;
				var _status = formData.status;
				if( _status=='2' ){
					$.messager.alert("提示", "&nbsp;&nbsp;付款单据已提交，不可作废！");
					return;
				}else if(_status == "4"){
					$.messager.alert("提示", "已作废，无需再次作废");
					return;
				}
				//进行作废
				$.messager.confirm("操作提示", "单据将作废，您确定要执行操作吗？", function (data) {
		            if (data) {
		            	updateRecordDeleteStatus(formData.id,formData.prePaymentID,formData.invoiceID,formData.payType,formData.type,"0");
		            }
		            else {
		            }
		        });
			},
			"permission": "mdm:paymentSettlement:delete" //作废
		}
			
		],
		"model": "paymentSettlement", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改数据字典窗口，将自动匹配ID：dataDictionary_edit_dialog
		//"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		/*增删改查配置*/
		"editHandlerPo": {
			"enable": false,
			"title": "修改", 
			"handler" : function() {
				comboboxVenNameUpdate();
			},
			"form": {"validate":true, "submitUrl": "../woPaymentSettlement/updatePaymentSettlement.do"},
			"permission": "mdm:paymentSettlement:edit" //修改权限
		},
		"addHandlerPo": {
			"enable": false,
			"title": "新建", 
			"handler" : function() {
				comboboxVenName();
				getDateFromSession();
			},
			/*"form": {"validate":true, "submitUrl": "../woPaymentSettlement/create.do"}*/
			"form": {"validate":true,"title": "保存", "submitUrl": "../woPaymentSettlement/createPaymentSettlement.do"},
			"permission": "mdm:paymentSettlement:add" //新建权限
		},
		"detailHandler": {
			"enable": false,
			"title": "查看",
            "handler": function(){
            	var active = $(this).find("input[name='isActivity']");
				var val = active.val();
				if(!val || val=="Y"){
					active.val("是");
				}else {
					active.val("否");
				}
			},
		}
		/*"subHandlerPo": {
			"enable": true,
			"title": "提交",
			"submitUrl": "../woPaymentSettlement/commitByIds.json", 
			"dataParams": "rowsData",
			"handler": function(){
				$('#paymentSettlementView').datagrid("load", $("#paymentSettlement_search_form").form("formToJson"));
			}
		}*/
	});
	
	function comboboxVenName(index){
		$("#venCode"+index).combobox({
			url:"../woPaymentSettlement/paymentSettlementVenNameList.json?index="+index,
			valueField:'venCode'+index,
			textField:'venName'+index,
			method:'post'
		});
		if(index != ""){
			$("#clearingForm"+index).combobox({
				url:"../woPaymentSettlement/querySettles.json?index="+index,
				editable:false,
				valueField:'settlementSubject',
				textField:'clearingForm',
				method:'post',
				onSelect:function(param){
					$("#clearingForm"+index).val(param.clearingForm);
					$("#settlementSubject"+index).val(param.settlementSubject);
					
//					var varSettlementSubject = $("input[name='settlementSubject1']");
//					varSettlementSubject[0].value = param.cCode;
				},
				onUnselect:function(param){
					$("#clearingForm"+index).val("");
				}
				
			});
		}
	}
	
	function getDateFromSession(){
		$.ajax({
			   type: "POST",  
			   url : "../woPaymentSettlement/getDateFromSession.json",  
			   dataType:"json",  
			   success: function(data){
				   $("#ticketDate1").datebox("setValue", data.ticketDate1);
			   }  
			 });
	}
	
	$(".datebox :text").attr("readonly","readonly");
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#paymentSettlementView').datagrid("load", $("#paymentSettlement_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#paymentSettlement_search_form").form("reset");
	});
	
	$("#redFlag").on("click",function(){
		var str = $("#redFlag").checked;
		//$("#sum").attr("validType","hedgeCurrency");
		if(str!=null&&str!="")
		{
			if(str){
//				var sum = $("input[name='sum']");
				$("#sum").attr("validType","hedgeCurrency");
//				sum[1].attr("validType","hedgeCurrency");
			}else{
				$("#sum").attr("validType","currency");
			}
		}
	});
	
	/**
	 * 提交
	 */
	function updateRecordStatus(id,status,role){
		$.ajax({
			url: "../woPaymentSettlement/commitByIds.json",
			data: {"id":id,"status":status,"role":role},
			dataType: "json",
			type: "post",
			cache: false,
			error:function(data){
				
			},
			success: function(result){
				 // 返回需要修改的数据信息
				if(result && (result.state == true || result.state == "true")){
					$.messager.show({
						"title" : "操作成功",
						"msg" : result.msg
					});
					$('#paymentSettlementView').datagrid("load", $("#paymentSettlement_search_form").form("formToJson"));// 重绘表格
					
				} else {
					$.messager.alert("操作失败", result.msg);
				}
			
			}
		});
		
	}
	
	/**
	 * 新建
	 */
	function updateRecordAddStatus(dataForm,status,role){
		$.ajax({
			url: "../woPaymentSettlement/createPaymentSettlement.do",
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
						"title" : "操作成功",
						"msg" : result.msg
					});
					$("#paymentSettlement_add_dialog").dialog("close"); // 关闭DIALOG
					$("#paymentSettlement_add_form").form("clear"); // 清空表单
					$('#paymentSettlementView').datagrid("load", $("#paymentSettlement_search_form").form("formToJson"));// 重绘表格
				} else {
					$.messager.alert("操作失败", result.msg);
				}
				
			}
		});
		
	}
	/**
	 * 修改
	 */
	function updateRecordUpdateStatus(dataForm,status,role){
		$.ajax({
			url: "../woPaymentSettlement/updatePaymentSettlement.do",
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
						"title" : "操作成功",
						"msg" : result.msg
					});
					$("#paymentSettlement_edit_dialog").dialog("close"); // 关闭DIALOG
					$("#paymentSettlement_edit_form").form("clear"); // 清空表单
					$('#paymentSettlementView').datagrid("load", $("#paymentSettlement_search_form").form("formToJson"));// 重绘表格
				} else {
					$.messager.alert("操作失败", result.msg);
				}
				
			}
		});
		
	}
	/**
	 * 作废
	 */
	function updateRecordDeleteStatus(id,prePaymentID,invoiceID,payType,type,role){
		$.ajax({
			url: "../woPaymentSettlement/deletePrePaymentmentAndMgPayAndInv.do",
			data: {"id":id,"prePaymentID":prePaymentID,"invoiceID":invoiceID,"payType":payType,"type":type},
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
						"title" : "操作成功",
						"msg" : result.msg
					});
					$('#paymentSettlementView').datagrid("load", $("#paymentSettlement_search_form").form("formToJson"));// 重绘表格
				} else {
					$.messager.alert("操作失败", result.msg);
				}
				
			}
		});
		
	}
	
	//用户选择多行时，默认只处理第一行
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
	}
	
});

$(document).ready(function(){
	$("#button1").click(function(){
		var vendorSelectVal = $("input[name='venCode1']").val();
//		var vendorSelectVal = $("#venCode1").val();
		var redFlag = "";
		if($("#redFlag1").attr("checked") == "checked"){
			redFlag = "1";
		}
		if(vendorSelectVal == null || vendorSelectVal ==""){
			alert("请先选择供应商！");
			return false;
		}else{
						$('#data_detail_dialog').dialog({
							title: '预付单列表',    
						    width: 900,    
						    height: 380,    
						    closed: false,    
						    cache: false,    
						    href: '',    
						    modal: true
						}); 
						 $('#paymentSettlementDetailView').myDatagrid({  
							 	//"title": '预付单列表',
					            "url":"../woPaymentSettlement/prepayment.json",
					            "queryParams":{"vendorSelectVal":vendorSelectVal,"redFlag":redFlag},
					            "singleSelect": false, //多选
					            "method": "post", 
					            "columns" : [[
					                            
						      		            {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},  
						      					{field : 'paymentCode', title : '单据编号', align : 'center', sortable : true},
						      					{field : 'venName', title : '供应商', align : 'center', sortable : true}, 
						      					{field : 'clearingForm',title : '结算方式', align : 'center'}, 
						      					{field : 'settlementSubject',title : '结算科目',align : 'center', sortable : true}, 
						      					{field : 'vendorBank',title : '供应商银行',align : 'center'},
						      					{field : 'vendorAccount',title : '供应商账户',align : 'center', sortable : true},
						      					{field : 'itemCode',title : '项目大类编码',align : 'center', sortable : true},
						      					{field : 'oppSubject',title : '对方科目',align : 'center', sortable : true},
						      					{field : 'oppItemCode',title : '对方项目编码',align : 'center', sortable : true,hidden:true},
						      					{field : 'payType',title : '预付类型',align : 'center', sortable : true,formatter:function(value){
						      						if(value=="1"){
						      							value="采购";
						      						}else if(value=="2"){
						      							value="委外";
						      						}
						      						return value;
						      					}},
						      					{field : 'type',title : '款项类型',align : 'center', sortable : true,formatter:function(value){
						      						if(value=="1"){
						      							value="预付款";
						      						}else if(value=="2"){
						      							value="应付款";
						      						}
						      						return value;
						      					}},
						      					{field : 'sum',title : '金额',align : 'center', sortable : true},
						      					{field : 'comment',title : '入库单编号',align : 'center', sortable : true,hidden:true},
						      					{field : 'status',title : '状态',align : 'center', sortable : true,formatter:function(value){
						      						if(value=="1"){
						      							value="新建";
						      						}else if(value=="2"){
						      							value="已提交";
						      						}else if(value=="3"){
						      							value="已审核";
						      						}else if(value=="4"){
						      							value="作废";
						      						}
						      						return value;
						      					}},
						      					{field : 'inputer',title : '录入人',align : 'center', sortable : true},
						      					{field : 'createTd',title : '录入时间',align : 'center', sortable : true, formatter:function(value){
						      						if(value=='' || value==null){
						      							//提醒....
						      						}else{
						      							var inputDate = new Date(value);
						      							return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
						      							inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
						      						}
						      						return "录入时间有误";
						      					}},
						      					{field : 'digest',title : '摘要',align : 'center', sortable : true},
						      					{field : 'prePaymentID', title : '预付单ID', align : 'center', sortable : true,hidden:true}, 
						      					{field : 'invoiceID', title : '发票ID', align : 'center', sortable : true,hidden:true}
						      					]],
						      		"toolbar": [{
						      			id : 'data_form_detail',
						      			text : '确定',
						      			iconCls : 'icon-remove',
						      			handler : function() {
						      				var $tb  = $("#paymentSettlementDetailView");
						      				var rows = $tb.datagrid("getSelections");
						      				var id ;
						      				var sum ;
						      				var paymentCode = "";
						      				var ids = '';
						      				var sums = 0;
						      				for(var i=0;i<rows.length;i++){
						      					id = rows[i].id;
						      					sum = rows[i].sum;
						      					ids = ids + rows[i].id + ",";
						      					sums += parseFloat(sum);
						      					paymentCode += rows[i].paymentCode+",";
						      				}
						      				determine("pay",ids,sums,paymentCode);
						      			},
						      			"permission": "mdm:paymentSettlement:ok" //确定
						      		}]
					     });
			}
		});
	$("#button3").click(function(){
//		var vendorSelectVal = $("#venCode2").val();
		var vendorSelectVal = $("input[name='venCode2']").val();
		var paymentId = $("#id").val();
		var redFlag = "";
		if($("#redFlag2").attr("checked") == "checked"){
			redFlag = "1";
		}
		if(vendorSelectVal == null || vendorSelectVal ==""){
			alert("请先选择供应商！");
			return false;
		}else{
			$('#data_detail_dialog').dialog({
				title: '预付单列表',    
				width: 900,    
				height: 380,    
				closed: false,    
				cache: false,    
				href: '',    
				modal: true
				
			}); 
			$('#paymentSettlementDetailView').myDatagrid({  
				//"title": '预付单列表',
				"url":"../woPaymentSettlement/prepayment.json",
				"queryParams":{"vendorSelectVal":vendorSelectVal,"paymentId":paymentId,"redFlag":redFlag},
				"singleSelect": false, //多选
				"method": "post", 
				"columns" : [[
				              
				              {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},  
				              {field : 'paymentCode', title : '单据编号', align : 'center', sortable : true},
				              {field : 'venName', title : '供应商', align : 'center', sortable : true}, 
				              {field : 'clearingForm',title : '结算方式', align : 'center'}, 
				              {field : 'settlementSubject',title : '结算科目',align : 'center', sortable : true}, 
				              {field : 'vendorBank',title : '供应商银行',align : 'center'},
				              {field : 'vendorAccount',title : '供应商账户',align : 'center', sortable : true},
				              {field : 'itemCode',title : '项目大类编码',align : 'center', sortable : true},
				              {field : 'oppSubject',title : '对方科目',align : 'center', sortable : true},
				              {field : 'oppItemCode',title : '对方项目编码',align : 'center', sortable : true,hidden:true},
				              {field : 'payType',title : '预付类型',align : 'center', sortable : true,formatter:function(value){
					  				if(value=="1"){
										value="采购";
									}else if(value=="2"){
										value="委外";
									}
									return value;
								}},
				              {field : 'type',title : '款项类型',align : 'center', sortable : true,formatter:function(value){
					  				if(value=="1"){
										value="预付款";
									}else if(value=="2"){
										value="应付款";
									}
									return value;
								}},
				              {field : 'sum',title : '金额',align : 'center', sortable : true},
				              {field : 'comment',title : '入库单编号',align : 'center', sortable : true,hidden:true},
				              {field : 'status',title : '状态',align : 'center', sortable : true,formatter:function(value){
					  				if(value=="1"){
										value="新建";
									}else if(value=="2"){
										value="已提交";
									}else if(value=="3"){
										value="已审核";
									}else if(value=="4"){
										value="作废";
									}
									return value;
								}},
				              {field : 'inputer',title : '录入人',align : 'center', sortable : true},
				              {field : 'createTd',title : '录入时间',align : 'center', sortable : true, formatter:function(value){
					  				if(value=='' || value==null){
										//提醒....
									}else{
										var inputDate = new Date(value);
										return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
										inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
									}
									return "录入时间有误";
								}},
				              {field : 'digest',title : '摘要',align : 'center', sortable : true},
				              {field : 'prePaymentID', title : '预付单ID', align : 'center', sortable : true,hidden:true}, 
				              {field : 'invoiceID', title : '发票ID', align : 'center', sortable : true,hidden:true}
				              ]],
				              "toolbar": [{
				            	  id : 'data_form_detail',
				            	  text : '确定',
				            	  iconCls : 'icon-remove',
				            	  handler : function() {
				            		  var $tb  = $("#paymentSettlementDetailView");
				            		  var rows = $tb.datagrid("getSelections");
				            		  var id ;
				            		  var sum ;
				            		  var paymentCode = "";
				            		  var ids = '';
				            		  var sums = 0;
				            		  for(var i=0;i<rows.length;i++){
				            			  id = rows[i].id;
				            			  sum = rows[i].sum;
				            			  ids = ids + rows[i].id + ",";
				            			  sums += parseFloat(sum);
				            			  paymentCode += rows[i].paymentCode+",";
				            		  }
				            		  determine("payUpdate",ids,sums,paymentCode);
				            	  },
				            	  "permission": "mdm:paymentSettlement:ok" //确定
				              }]
			});
		}
	});
	
	$("#button2").click(function(){
//		var vendorSelectVal = $("#venName1").val();
//		var vendorSelectVal = $("input[name='venName']").val();
//		var vendorSelectVal = $("#venCode1").val();
		var vendorSelectVal = $("input[name='venCode1']").val();
		var redFlag = "";
		if($("#redFlag1").attr("checked") == "checked"){
			redFlag = "1";
		}
		if(vendorSelectVal == null || vendorSelectVal ==""){
			alert("请先选择供应商！");
			return false;
		}else{
			//根据供应商查询发票
			$('#data_detail_dialog').dialog({
				title: '发票列表',    
			    width: 900,    
			    height: 380,    
			    closed: false,    
			    cache: false,    
			    href: '',    
			    modal: true
			    
			});
			$('#paymentSettlementDetailView').myDatagrid({  
				//"title": '发票列表',
	            "url":"../woPaymentSettlement/invoice.json",
	            "queryParams":{"vendorSelectVal":vendorSelectVal,"redFlag":redFlag},
	            "singleSelect": false, //多选
	            "method": "post", 
	            "columns" : [[
	                            
		      		            {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},  
		      					{field : 'invoiceId', title : '单据号', align : 'center', sortable : true},
		      					{field : 'billingDate', title : '开票日期', align : 'center', sortable : true, formatter:function(value){
					  				if(value=='' || value==null){
										//提醒....
									}else{
										var inputDate = new Date(value);
										return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate();
									}
									return "录入时间有误";
								}}, 
		      					/*{field : 'venCode',title : '供应商编码', align : 'center'}, */
		      					{field : 'venName',title : '供应商名称',align : 'center', sortable : true}, 
		      					{field : 'businessType',title : '业务类型',align : 'center'},
		      					{field : 'sum',title : '无税金额',align : 'center', sortable : true},
		      					{field : 'tax',title : '税额',align : 'center', sortable : true},
		      					{field : 'taxRate',title : '税率',align : 'center', sortable : true},
		      					{field : 'price',title : '价税合计',align : 'center', sortable : true},
		      					{field : 'inputer',title : '录入人',align : 'center', sortable : true},
		      					{field : 'createTd',title : '录入时间',align : 'center', sortable : true, formatter:function(value){
		      						if(value=='' || value==null){
		      							//提醒....
		      						}else{
		      							var inputDate = new Date(value);
		      							return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
		      							inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
		      						}
		      						return "录入时间有误";
		      					}},
		      					{field : 'modifier',title : '修改人',align : 'center', sortable : true,hidden:true},
		      					{field : 'updateTd',title : '修改时间',align : 'center', sortable : true,hidden:true, formatter:function(value){
					  				if(value=='' || value==null){
										//提醒....
									}else{
										var inputDate = new Date(value);
										return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
										inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
									}
									return "录入时间有误";
								}},
		      					{field : 'status',title : '状态',align : 'center', sortable : true,formatter:function(value){
		      						if(value=="1"){
		      							value="新建";
		      						}else if(value=="2"){
		      							value="已提交";
		      						}else if(value=="3"){
		      							value="作废";
		      						}
		      						return value;
		      					}},
		      					{field : 'purchaseId',title : '采购订单号',align : 'center', sortable : true}
		      					]],
		      		"toolbar": [{
		      			id : 'data_form_detail',
		      			text : '确定',
		      			iconCls : 'icon-remove',
		      			handler : function() {
		      				var $tb  = $("#paymentSettlementDetailView");
		      				var rows = $tb.datagrid("getSelections");
		      				var id ;
		      				var price ;
		      				var invoiceId = "";
		      				var ids = '';
		      				var prices = 0;
		      				var purchaseId = [];
			        		var booleanFlag = true;
		      				for(var i=0;i<rows.length;i++){
		      					id = rows[i].id;
		      					price = rows[i].price;
		      					ids = ids + rows[i].id + ",";
		      					invoiceId += rows[i].invoiceId + ",";
		      					prices += parseFloat(price);
		      					purchaseId[i] = rows[i].purchaseId;
		      				}
		      				for(var i=0;i<purchaseId.length;i++){
		      					if(purchaseId[i] != purchaseId[0]){
		        				  booleanFlag = false;
		      					}
		      				}
			        		if(booleanFlag){
			        			determine("inv",ids,prices,invoiceId);
			        		}else{
			        			$.messager.alert("提示", "请选择相同采购订单号的发票单！");
			        			return;
			        		}
		      			},
		      			"permission": "mdm:paymentSettlement:ok" //确定
		      		}]
			});
		}
	});
	$("#button4").click(function(){
//		var vendorSelectVal = $("#venCode2").val();
		var vendorSelectVal = $("input[name='venCode2']").val();
		var paymentId = $("#id").val();
		var redFlag = "";
		if($("#redFlag2").attr("checked") == "checked"){
			redFlag = "1";
		}
		if(vendorSelectVal == null || vendorSelectVal ==""){
			alert("请先选择供应商！");
			return false;
		}else{
			//根据供应商查询发票
			$('#data_detail_dialog').dialog({
				title: '发票列表',    
				width: 900,    
				height: 380,    
				closed: false,    
				cache: false,    
				href: '',    
				modal: true
				
			});
			$('#paymentSettlementDetailView').myDatagrid({  
				//"title": '发票列表', 
				"url":"../woPaymentSettlement/invoice.json",
				"queryParams":{"vendorSelectVal":vendorSelectVal,"paymentId":paymentId,"redFlag":redFlag},
				"singleSelect": false, //多选
				"method": "post", 
				"columns" : [[
				              
				              {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},  
				              {field : 'invoiceId', title : '单据号', align : 'center', sortable : true},
				              {field : 'billingDate', title : '开票日期', align : 'center', sortable : true, formatter:function(value){
					  				if(value=='' || value==null){
										//提醒....
									}else{
										var inputDate = new Date(value);
										return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate();
									}
									return "录入时间有误";
								}}, 
				              /*{field : 'venCode',title : '供应商编码', align : 'center'},*/ 
				              {field : 'venName',title : '供应商名称',align : 'center', sortable : true}, 
				              {field : 'businessType',title : '业务类型',align : 'center'},
				              {field : 'sum',title : '无税金额',align : 'center', sortable : true},
				              {field : 'tax',title : '税额',align : 'center', sortable : true},
				              {field : 'taxRate',title : '税率',align : 'center', sortable : true},
				              {field : 'price',title : '价税合计',align : 'center', sortable : true},
				              {field : 'inputer',title : '录入人',align : 'center', sortable : true},
				              {field : 'createTd',title : '录入时间',align : 'center', sortable : true, formatter:function(value){
					  				if(value=='' || value==null){
										//提醒....
									}else{
										var inputDate = new Date(value);
										return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
										inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
									}
									return "录入时间有误";
								}},
				              {field : 'modifier',title : '修改人',align : 'center', sortable : true,hidden:true},
				              {field : 'updateTd',title : '修改时间',align : 'center', sortable : true,hidden:true, formatter:function(value){
					  				if(value=='' || value==null){
										//提醒....
									}else{
										var inputDate = new Date(value);
										return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
										inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
									}
									return "录入时间有误";
								}},
				              {field : 'status',title : '状态',align : 'center', sortable : true,formatter:function(value){
				            	  if(value=="1"){
				  					value="新建";
				  				}else if(value=="2"){
				  					value="已提交";
				  				}else if(value=="3"){
				  					value="作废";
				  				}
								return value;
				              }},
				              {field : 'purchaseId',title : '采购订单号',align : 'center', sortable : true}
				              ]],
				              "toolbar": [{
				            	  id : 'data_form_detail',
				            	  text : '确定',
				            	  iconCls : 'icon-remove',
				            	  handler : function() {
				            		  var $tb  = $("#paymentSettlementDetailView");
				            		  var rows = $tb.datagrid("getSelections");
				            		  var id ;
				            		  var price ;
				            		  var invoiceId = "";
				            		  var ids = '';
				            		  var prices = 0;
				            		  var purchaseId = [];
					        		  var booleanFlag = true;
				      				  for(var i=0;i<rows.length;i++){
				      					  id = rows[i].id;
				      					  price = rows[i].price;
				      					  ids = ids + rows[i].id + ",";
				      					  invoiceId += rows[i].invoiceId + ",";
				      					  prices += parseFloat(price);
				      					  purchaseId[i] = rows[i].purchaseId;
				      				  }
				      				  for(var i=0;i<purchaseId.length;i++){
				      					  if(purchaseId[i] != purchaseId[0]){
				        				    booleanFlag = false;
				      					  }
				      				  }
					        		  if(booleanFlag){
					        			  determine("invUpdate",ids,prices,invoiceId);
					        		  }else{
					        			  $.messager.alert("提示", "请选择相同采购订单号的发票单！");
					        			  return;
					        		  }
				            	  },
				            	  "permission": "mdm:paymentSettlement:ok" //确定
				              }]
			});
		}
	});
	});

function determine(flag,ids,sums,param){
	sums = sums.toFixed(4);
	$.messager.show({
		"title" : "操作成功"
	});
	if(flag == "inv"){
//		$("input[name='invoiceID1']").val(ids);
		$('#invoiceID1').val(ids);
		$('#invoiceCode1').val(param);
		$('#invoiceIDTotal1').val(sums);
	}else if(flag == "invUpdate"){
		$('#invoiceID2').val(ids);
		$('#invoiceCode2').val(param);
		$('#invoiceIDTotal2').val(sums);
	}else if(flag == "pay"){
		$('#prePaymentID1').val(ids);
		$('#prePaymentCode1').val(param);
		$('#prePaymentIDTotal1').val(sums);
	}else{
		$('#prePaymentID2').val(ids);
		$('#prePaymentCode2').val(param);
		$('#prePaymentIDTotal2').val(sums);
	}
	$('#data_detail_dialog').dialog('close');
}

/*function aaa(){
	if($('#invoiceID1').val() != null){
		alert("111");
	}
	alert("222");
}
function bbb(){
	if($('#invoiceID1').val() != null){
		alert("333");
	}
	alert("444");
}*/

function checkOther(obj){
    var str = obj.value;
   	if(str!=null&&str!="")
   	{
	   	var reg = /^([0-9]|(\.))*$/;
		if (reg.test(str)) 
		{
			if(str.length>10)
	   		{
	   			alert("长度不能超过10");
	   			obj.focus();
	   		}
		}else
		{
			alert("只能是数字或英文小数点");
			obj.focus();
		}
   	}
}

function checkRedFlag(obj){
	var str = obj.checked;
	//$("#sum").attr("validType","hedgeCurrency");
	if(str!=null&&str!="")
	{
		if(str){
//			var sum = $("input[name='sum']");
			$("#sum1").attr("validType","hedgeCurrency");
//			sum[1].attr("validType","hedgeCurrency");
		}
	}
}

//redFlag 红字标识校验
function redFlagAddOrUpdateCheck(param){
	var invoiceID = $("#invoiceID"+param).val();
	var prePaymentID = $("#prePaymentID"+param).val();
//	var redFlag1 = $("#redFlag1").ckecked;
	if($("#redFlag"+param).attr("checked")=="checked"){
		if(invoiceID != null && invoiceID != "" || prePaymentID != null && prePaymentID != ""){
    		$.messager.confirm("操作提示", "红字付款单需选择红字预付款单或发票单，勾选后需重新选择，是否确认？", function (data) {
	            if (data) {
	            	$("#invoiceID"+param).val("");
	            	$("#invoiceCode"+param).val("");
	            	$("#invoiceIDTotal"+param).val("");
	            	$("#prePaymentID"+param).val("");
	            	$("#prePaymentCode"+param).val("");
	            	$("#prePaymentIDTotal"+param).val("");
	            }
	            else {
	            }
	        });
    	}
	}
}

//校验方法
$.extend($.fn.validatebox.defaults.rules,{
	//单据编号校验
	paymentCodeCheck:{ 
		validator:function(value){
	     	var reg = /^[A-Za-z0-9]+$/;
	    	if(reg.test(value)&&value.length<20){
	    			return true;
	    	}
		},
		message:'请检查单据编号的位数与格式'
	},
	//供应商校验
	venNameChang:{ 
	    validator:function(value,param){
	    	var _param = param[0];
	    	var cVenName=$("input[name='"+_param+"']").val();

	    	if(cVenName!=null){
	    		return true;
	    	}
	    }, 
	    message:'选择的供应商有误！' 
	},
    //金额校验
	sumCheck:{ 
	    validator:function(value){
	    	var cc = /^(\-|\+)?\d+(.[0-9]{1,4})?$/;
	    	if(value.length<20&&cc.test(value)){
	    		return true; 
	    	}
	    }, 
	    message:'请检查输入金额的位数与格式' 
	},
	//供应商银行校验
	vendorBankCheck:{ 
		validator:function(value){
	    	if(value.length<50){
	    			return true;
	    	}
		},
		message:'请检查供应商银行的位数与格式'
	},
	//供应商账户校验
	vendorAccountCheck:{ 
		validator:function(value){
	     	var reg = /^[0-9]+$/;
	    	if(reg.test(value)&&value.length<30){
	    			return true;
	    	}
		},
		message:'请检查供应商账户的位数与格式'
	},
	//发票校验
	invoiceCodeCheck:{
		validator:function(value){
//			var _param = param[0];
	    	var invoiceID=$('#invoiceID1').val();

	    	if(invoiceID!=null){
	    		return true;
	    	}
		},
		message:'该输入项为必输项111'
	},
	//摘要长度校验
	digestChang:{
		validator : function(value, param) {
			return param[0] >= value.length;
		},
		message : '请输入最大{0}位字符.'
	}
}); 


