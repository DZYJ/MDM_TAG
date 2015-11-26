$(function() {
	//加载下拉框
	/*$("input[name='venName12']").combobox({ 
		//$("#venName1").combobox({ 
		url:"../paymentSettlement/paymentSettlementVenNameList.json",
		valueField:'name',
		textField:'name',
		method:'post'
	});*/
	
	/*初始化发票grid高度*/
	$("#invoice_grid").height($(window).height()-120);
	
	/*发票GRID*/
	$('#invoiceView').myDatagrid({
		"title": "发票信息列表",
		"url": "../invoice/invoice.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
		    {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},
			{field : 'invoiceId', title : '单据号', align : 'center', sortable : true},
			{field : 'billingDate', title : '开票日期', align : 'center', sortable : true
				, formatter:function(value){
				if(value=='' || value==null){
					//提醒....
				}else{
					var inputDate = new Date(value);
					return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate();
//					+' '+inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
//					return inputDate;
				}
				return "录入时间有误";
			}
			}, 
			{field : 'venCode',title : '供应商编码', align : 'center', sortable : true,hidden:true}, 
			{field : 'venName',title : '供应商名称',align : 'center', sortable : true}, 
			{field : 'businessType',title : '业务类型',align : 'center', sortable : true,formatter:function(value){
				if(value=="1"){
					value="采购发票";
				}else if(value=="2"){
					value="委外发票";
				}else if(value=="3"){
					value="采退发票";
				}
				return value;
			}},
			/*{field : 'isActivity',title : '是否活动', width : 40, align : 'center', sortable : true,formatter:function(value){
				if(value=="Y"){
					value="是";
				}else if(value=="N"){
					value="否";
				}
				return value;
			}},*/
			{field : 'sum',title : '无税金额（自动计算）',align : 'center', sortable : true},
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
			{field : 'updateTd',title : '修改时间',align : 'center', sortable : true, formatter:function(value){
				if(value=='' || value==null){
					//提醒....
				}else{
					var inputDate = new Date(value);
					return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
					inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
				}
				return "";
			}},
			{field : 'status',title : '状态',align : 'center', sortable : true,formatter:function(value){
				if(value=="1"){
					value="新建";
				}else if(value=="2"){
					value="提交并结算";
				}else if(value=="3"){
					value="作废";
				}
				return value;
			}},
			{field : 'storageId',title : '入库单',align : 'center', sortable : true,hidden:true},
			{field : 'ticketDate', title : '制单日期', align : 'center', sortable : true,hidden:true}, 
			{field : 'redFlag', title : '红字标识', align : 'center', sortable : true,hidden:true}
		]],
		"model": "invoice", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改数据字典窗口，将自动匹配ID：dataDictionary_edit_dialog
		//"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		/*增删改查配置*/
		"editHandlerTime": {
//			"queryUrl":"../invoice/queryInvoice.do",
			"enable": true,
			"title": "修改", 
			"handler" : function() {
				var $tb  = $("#invoiceView");
				var rows = $tb.datagrid("getSelections");
				//已提交不能修改
				var aStatus = rows[0].status;
				if( aStatus=='2' ){
					$.messager.alert("警告", "已提交，不能修改");
					return;
				}
				var redFlag = rows[0].redFlag;
				comboboxVenNameUpdate();
			},
			"form": {"validate":true, "submitUrl": "../invoice/updateInvoice.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "新建", 
			"handler" : function() {
				comboboxVenName();
			},
			/*"form": {"validate":true, "submitUrl": "../paymentSettlement/create.do"}*/
			"form": {"validate":true, "submitUrl": "../invoice/createInvoice.do"}
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
			"submitUrl": "../invoice/commitByIds.json", 
			"dataParams": "rowsData"
		}
	});
	
	function comboboxVenName(){
		$("#venName1").combobox({
			url:"../invoice/invoiceVenNameList.json",
			valueField:'venCode',
			textField:'venName',
			method:'post',
			onSelect:function(param){
				$("#venName1").val(param.venName);
				$("#venCode1").val(param.venCode);
			},
			onUnselect:function(param){
				$("#venName").val("");
			}
		});
	}
	
	function comboboxVenNameUpdate(){
		$("#venName2").combobox({ 
			url:"../invoice/invoiceVenNameList.json",
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
	  }
	
	function queryDate(){
		var billingDateId = $("#billingDate").val();
		alert(billingDateId);
		var billingDateName = $("input[name='billingDate']");
		billingDateName[1].value = billingDateId;
		alert(billingDateName[1].value);
	}
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#invoiceView').datagrid("load", $("#invoice_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#invoice_search_form").form("reset");
	});
	
});

$(document).ready(function(){
	$("#button1").click(function(){
		$('#data_detail_dialog').dialog({
			title: '入库单列表',    
			width: 900,    
			height: 240,    
			closed: false,    
			cache: false,    
			href: '',    
			modal: true
		});
		$('#invoiceDetailView').myDatagrid({
			"title": "入库单列表",
			"url": "../invoice/mgRdAndInv.json",
			"singleSelect": false, //多选
			"method": "post", 
	  		"columns":[[
	  					{field : 'rdId', title : '入库单', align : 'center', sortable : true}
	  					]],
			"toolbar": [{
	        	  id : 'data_form_detail',
	        	  text : '确定',
	        	  iconCls : 'icon-remove',
	        	  handler : function() {
	        		  var $tb  = $("#invoiceDetailView");
	        		  var rows = $tb.datagrid("getSelections");
	        		  var id ;
	        		  var ids = '';
	        		  for(var i=0;i<rows.length;i++){
	        			  id = rows[i].rdId;
	        			  ids = ids + id + ",";
	        		  }
	        		  determine("inv1",ids);
	        	  }
	         }]
		});
	}); 
	$("#button2").click(function(){
		$('#data_detail_dialog').dialog({
			title: '入库单列表',    
			width: 900,    
			height: 240,    
			closed: false,    
			cache: false,    
			href: '',    
			modal: true
		}); 
		$('#invoiceDetailView').myDatagrid({
			"title": "入库单列表",
			"url": "../invoice/mgRdAndInv.json",
			"singleSelect": false, //多选
			"method": "post", 
      		"columns":[[
      					{field : 'rdId', title : '入库单', align : 'center', sortable : true}
      					]],
			"toolbar": [{
            	  id : 'data_form_detail',
            	  text : '确定',
            	  iconCls : 'icon-remove',
            	  handler : function() {
            		  var $tb  = $("#invoiceDetailView");
            		  var rows = $tb.datagrid("getSelections");
            		  var id ;
            		  var ids = '';
            		  for(var i=0;i<rows.length;i++){
            			  id = rows[i].rdId;
            			  ids = ids + id + ",";
            		  }
            		  determine("inv2",ids);
            	  }
              }]
		});
	});
});

function determine(flag,ids){
	$.messager.show({
		"title" : "操作成功"
	});
	if(flag == "inv1"){
		$('#storageId1').val(ids);
	}else if(flag == "inv2"){
		$('#storageId2').val(ids);
	}
	$('#data_detail_dialog').dialog('close');
}

function checkOther(obj){
	var str = obj.value;
	var reg =/\d{4}-\d{2}-\d{2}/; 
	if(str!=null&&str!="")
   	{
		if (!reg.test(str)){
			alert("日期格式不正确！应为YYYY-MM-DD");
			obj.focus();
		}
   	}
}
