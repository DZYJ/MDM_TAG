$(function() {
	//加载下拉框
	//var ven = $("input[name='venName']")[0].id;
	
	/*var ven = $("input[name='venName']")[1].id;
	$("#"+ven).combobox({ 
		//$("#venName1").combobox({ 
		url:"../paymentSettlement/paymentSettlementVenNameList.json",
		valueField:'name',
		textField:'name',
		method:'post'
	});*/
	
	/*初始化数据字典grid高度*/
	$("#paymentSettlement_grid").height($(window).height()-120);
	
	/*数据字典GRID*/
	$('#paymentSettlementView').myDatagrid({
		"title": "付款单列表",
		"url": "../paymentSettlement/paymentSettlementLists.json",
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
			{field : 'oppItemCode',title : '对方项目编码',align : 'center', sortable : true},
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
			{field : 'comment',title : '入库单编号',align : 'center', sortable : true},
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
			{field : 'redFlag', title : '红字标识', align : 'center', sortable : true,hidden:true}
		]],
		"model": "paymentSettlement", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改数据字典窗口，将自动匹配ID：dataDictionary_edit_dialog
		//"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		/*增删改查配置*/
		"editHandlerPo": {
//			"queryUrl":"../paymentSettlement/updateQueryPaymentSettlementLists.json",
//			"method": "post", 
//			"columns" : [[
//			    {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},
//				{field : 'paymentCode', title : '单据编号', align : 'center', sortable : true},
//				{field : 'venName', title : '供应商', align : 'center', sortable : true}, 
//				{field : 'clearingForm',title : '结算方式', align : 'center'}
//			]],
			"enable": true,
			"title": "修改", 
			"handler" : function() {
				var $tb  = $("#paymentSettlementView");
				var rows = $tb.datagrid("getSelections");
				//已提交不能修改
				var aStatus = rows[0].status;
				if( aStatus=='2' ){
					$.messager.alert("警告", "已提交，不能修改");
					return;
				}
				var redFlag = rows[0].redFlag;
//				var redFlagObj = $("input[name='redFlag']");
				/*var redFlagObj = $("#redFlag2");
				if( redFlag.val()=="on " ){//注意有空格
					redFlagObj.checked = true;
				}else{
					redFlagObj.checked = false;
				}*/
				comboboxVenNameUpdate();
			},
			"form": {"validate":true, "submitUrl": "../paymentSettlement/updatePaymentSettlement.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "新建", 
			"handler" : function() {
				comboboxVenName();
			},
			/*"form": {"validate":true, "submitUrl": "../paymentSettlement/create.do"}*/
			"form": {"validate":true,"title": "保存", "submitUrl": "../paymentSettlement/createPaymentSettlement.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看",
            "handler": function(){
				//是否活动显示
				var active = $(this).find("input[name='isActivity']");
				var val = active.val();
				if(!val || val=="Y"){
					active.val("是");
				}else {
					active.val("否");
				}
			}
		},
		"subHandler": {
			"enable": true,
			"title": "提交",
			"submitUrl": "../paymentSettlement/commitByIds.json", 
			"dataParams": "rowsData"
		}
	});
	
	function comboboxVenName(){
//		$(".abc").combobox({
			$("#venName1").combobox({ 
			url:"../paymentSettlement/paymentSettlementVenNameList.json",
//			valueField:'name',$("input[name='venName']")[1]
//			textField:'name',
			valueField:'venCode',
			textField:'venName',
			method:'post',
			onSelect:function(param){
				/*var v1 = $("#venName");
				var v2 = $("input[name='venName']");
				$("#venName").val(param.venName);*/
				/*var venname = $("input[name='venName']");
				var venCode = $("input[name='venCode']");
				venname[1].value = param.venName;
				venCode[1].value = param.venCode;*/
				$("#venName1").val(param.venName);
				$("#venCode1").val(param.venCode);
			},
			onUnselect:function(param){
				$("#venName").val("");
			}

		});
		$("#clearingForm1").combobox({ 
			//$("#venName1").combobox({ 
			url:"../paymentSettlement/querySettles.json",
//			valueField:'name',$("input[name='venName']")[1]
//			textField:'name',
			valueField:'cCode',
			textField:'cSSName',
			method:'post',
			onSelect:function(param){
				$("#clearingForm1").val(param.cSSName);
				var varSettlementSubject = $("input[name='settlementSubject1']");
				varSettlementSubject[0].value = param.cCode;
			},
			onUnselect:function(param){
				$("#clearingForm1").val("");
			}
			
		});
	}
	
	function comboboxVenNameUpdate(){
		$("#venName2").combobox({ 
			url:"../paymentSettlement/paymentSettlementVenNameList.json",
			valueField:'venCode',
			textField:'venName',
			method:'post',
			onSelect:function(param){
				$("#venName2").val(param.venName);
				$("#venCode2").val(param.venCode);
			},
			onUnselect:function(param){
				$("#venName2").val("");
			}
		});
		
		$("#clearingForm2").combobox({ 
			url:"../paymentSettlement/querySettles.json",
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
	
});

$(document).ready(function(){
	$("#button1").click(function(){
//		var vendorSelectVal = $("input[name='venName']").val();
		var vendorSelectVal = $("#venCode1").val();
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
							 	"title": '预付单列表',
					            "url":"../paymentSettlement/prepayment.json?vendorSelectVal="+vendorSelectVal,
					            "singleSelect": false, //多选
					            "method": "post", 
					            "columns" : [[
					                            
						      		            {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},  
						      					{field : 'paymentCode', title : '单据编号', align : 'center', sortable : true},
						      					{field : 'venCode', title : '供应商', align : 'center', sortable : true}, 
						      					{field : 'clearingForm',title : '结算方式', align : 'center'}, 
						      					{field : 'settlementSubject',title : '结算科目',align : 'center', sortable : true}, 
						      					{field : 'vendorBank',title : '供应商银行',align : 'center'},
						      					{field : 'vendorAccount',title : '供应商账户',align : 'center', sortable : true},
						      					{field : 'itemCode',title : '项目大类编码',align : 'center', sortable : true},
						      					{field : 'oppSubject',title : '对方科目',align : 'center', sortable : true},
						      					{field : 'oppItemCode',title : '对方项目编码',align : 'center', sortable : true},
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
						      					{field : 'comment',title : '入库单编号',align : 'center', sortable : true},
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
						      				var ids = '';
						      				var sums = 0;
						      				for(var i=0;i<rows.length;i++){
						      					id = rows[i].id;
						      					sum = rows[i].sum;
						      					ids = ids + rows[i].id + ",";
						      					sums += parseFloat(sum);
						      				}
						      				determine("pay",ids,sums);
						      			}
						      		}]
					     });
			}
		});
	$("#button3").click(function(){
		/*var vendorSelectVals = $("#venName").val();*/
//		var vendorSelect = $("input[name='venName']");
//		var vendorSelectVal = vendorSelect[1].value;
		var vendorSelectVal = $("#venCode2").val();
		//var vendorSelectVal = $("input[name='venName']").val();
		if(vendorSelectVal == null || vendorSelectVal ==""){
			alert("请先选择供应商！");
			return false;
		}else{
			$('#data_detail_dialog').dialog({
				title: '预付单列表',    
				width: 900,    
				height: 240,    
				closed: false,    
				cache: false,    
				href: '',    
				modal: true
				
			}); 
			$('#paymentSettlementDetailView').myDatagrid({  
				"title": '预付单列表',
				"url":"../paymentSettlement/prepayment.json?vendorSelectVal="+vendorSelectVal,
				"singleSelect": false, //多选
				"method": "post", 
				"columns" : [[
				              
				              {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},  
				              {field : 'paymentCode', title : '单据编号', align : 'center', sortable : true},
				              {field : 'venCode', title : '供应商', align : 'center', sortable : true}, 
				              {field : 'clearingForm',title : '结算方式', align : 'center'}, 
				              {field : 'settlementSubject',title : '结算科目',align : 'center', sortable : true}, 
				              {field : 'vendorBank',title : '供应商银行',align : 'center'},
				              {field : 'vendorAccount',title : '供应商账户',align : 'center', sortable : true},
				              {field : 'itemCode',title : '项目大类编码',align : 'center', sortable : true},
				              {field : 'oppSubject',title : '对方科目',align : 'center', sortable : true},
				              {field : 'oppItemCode',title : '对方项目编码',align : 'center', sortable : true},
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
				              {field : 'comment',title : '入库单编号',align : 'center', sortable : true},
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
				            		  var ids = '';
				            		  var sums = 0;
				            		  for(var i=0;i<rows.length;i++){
				            			  id = rows[i].id;
				            			  sum = rows[i].sum;
				            			  ids = ids + rows[i].id + ",";
				            			  sums += parseFloat(sum);
				            		  }
				            		  determine("payUpdate",ids,sums);
				            	  }
				              }]
			});
		}
	});
	
	$("#button2").click(function(){
//		var vendorSelectVal = $("#venName1").val();
//		var vendorSelectVal = $("input[name='venName']").val();
		var vendorSelectVal = $("#venCode1").val();
		if(vendorSelectVal == null || vendorSelectVal ==""){
			alert("请先选择供应商！");
			return false;
		}else{
			//根据供应商查询发票
			$('#data_detail_dialog').dialog({
				title: '发票列表',    
			    width: 900,    
			    height: 240,    
			    closed: false,    
			    cache: false,    
			    href: '',    
			    modal: true
			    
			});
			$('#paymentSettlementDetailView').myDatagrid({  
				"title": '发票列表',
	            "url":"../paymentSettlement/invoice.json?vendorSelectVal="+vendorSelectVal,
	            "singleSelect": false, //多选
	            "method": "post", 
	            "columns" : [[
	                            
		      		            {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},  
		      					{field : 'invoiceId', title : '单据号', align : 'center', sortable : true},
		      					{field : 'billingDate', title : '开票日期', align : 'center', sortable : true}, 
		      					{field : 'venCode',title : '供应商编码', align : 'center'}, 
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
		      					{field : 'modifier',title : '修改人',align : 'center', sortable : true},
		      					{field : 'updateTd',title : '修改时间',align : 'center', sortable : true},
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
		      					}}
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
		      				var ids = '';
		      				var sums = 0;
		      				for(var i=0;i<rows.length;i++){
		      					id = rows[i].id;
		      					sum = rows[i].sum;
		      					ids = ids + rows[i].id + ",";
		      					sums += parseFloat(sum);
		      				}
		      				determine("inv",ids,sums);
		      			}
		      		}]
			});
		}
	});
	$("#button4").click(function(){
//		var vendorSelectVal = $("#venName1").val();
//		var vendorSelect = $("input[name='venName']");
//		var vendorSelectVal = vendorSelect[1].value;
		var vendorSelectVal = $("#venCode2").val();
		if(vendorSelectVal == null || vendorSelectVal ==""){
			alert("请先选择供应商！");
			return false;
		}else{
			//根据供应商查询发票
			$('#data_detail_dialog').dialog({
				title: '发票列表',    
				width: 900,    
				height: 240,    
				closed: false,    
				cache: false,    
				href: '',    
				modal: true
				
			});
			$('#paymentSettlementDetailView').myDatagrid({  
				"title": '发票列表', 
				"url":"../paymentSettlement/invoice.json?vendorSelectVal="+vendorSelectVal,
				"singleSelect": false, //多选
				"method": "post", 
				"columns" : [[
				              
				              {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},  
				              {field : 'invoiceId', title : '单据号', align : 'center', sortable : true},
				              {field : 'billingDate', title : '开票日期', align : 'center', sortable : true}, 
				              {field : 'venCode',title : '供应商编码', align : 'center'}, 
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
				              {field : 'modifier',title : '修改人',align : 'center', sortable : true},
				              {field : 'updateTd',title : '修改时间',align : 'center', sortable : true},
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
							}}
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
				            		  var ids = '';
				            		  var sums = 0;
				            		  for(var i=0;i<rows.length;i++){
				            			  id = rows[i].id;
				            			  sum = rows[i].sum;
				            			  ids = ids + rows[i].id + ",";
				            			  sums += parseFloat(sum);
				            		  }
				            		  determine("invUpdate",ids,sums);
				            	  }
				              }]
			});
		}
	});
	});
	function determine(flag,ids,sums){
		$.messager.show({
			"title" : "操作成功"
		});
		if(flag == "inv"){
			$('#invoiceID1').val(ids);
			$('#sumInHidden').val(sums);
		}else if(flag == "invUpdate"){
//			$("input[name='invoiceID']")[1].value = ids;
			$('#invoiceID2').val(ids);
		}else if(flag == "pay"){
			$('#prePaymentID1').val(ids);
			$('#sumPreHidden').val(sums);
		}else{
//			$("input[name='prePaymentID']")[1].value = ids;
			$('#prePaymentID2').val(ids);
		}
		$('#data_detail_dialog').dialog('close');
	}
	

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


