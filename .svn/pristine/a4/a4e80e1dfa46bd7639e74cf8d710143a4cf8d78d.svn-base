$(function() {

	
	/*初始化预付单grid高度*/
	$("#prePayment_grid").height($(window).height()-120);
	
	/*预付单GRID*/
	$('#prePaymentView').myDatagrid({
		"title": "预付单列表",
		"url": "../prePayment/prePaymentLists.json",
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
		"model": "prePayment", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改预付单窗口，将自动匹配ID：dataDictionary_edit_dialog
		//"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改", 
			"handler" : function() {
				var $tb  = $("#prePaymentView");
				var rows = $tb.datagrid("getSelections");
				//已提交不能修改
				var aStatus = rows[0].status;
				if( aStatus=='2' ){
					$.messager.alert("警告", "已提交，不能修改");
					return;
				}
				comboboxVenNameUpdate();
			},
			"form": {"validate":true, "submitUrl": "../prePayment/updatePrePayment.do"}
		},
		"addHandler": {
			"enable": true,
			"title": "新建", 
			"handler" : function() {
				comboboxVenNameUpdate();
				getDateFromSession();
			},
			/*"form": {"validate":true, "submitUrl": "../paymentSettlement/create.do"}*/
			"form": {"validate":true, "submitUrl": "../prePayment/createPrePayment.do"}
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
			"submitUrl": "../prePayment/commitByIds.json", 
			"dataParams": "rowsData"
		}
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#prePaymentView').datagrid("load", $("#prePayment_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#prePayment_search_form").form("reset");
	});
	
});



function comboboxVenName(){
		$("#venName1").combobox({ 
		url:"../prePayment/prePaymentVenNameList.json",
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
	$("#clearingForm1").combobox({ 
		url:"../prePayment/querySettles.json",
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
		url:"../prePayment/prePaymentVenNameList.json",
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
		url:"../prePayment/querySettles.json",
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
	$('#prePaymentView').datagrid("load", $("#prePayment_search_form").form("formToJson"));
});
$("#search_form_reset").on("click", function(){
	$("#prePayment_search_form").form("reset");
});

$("#redFlag").on("click",function(){
	var str = $("#redFlag").checked;
	if(str!=null&&str!="")
	{
		if(str){
			$("#sum").attr("validType","hedgeCurrency");
		}else{
			$("#sum").attr("validType","currency");
		}
	}
});




function checkOther(obj){
    var str = obj.value;
   	if(str!=null&&str!="")
   	{
	   	var reg = /^([0-9]|(\.))*$/;
		if (reg.test(str)) 
		{
			if(str.length>10)
	   		{
				$.messager.alert("提示", "&nbsp;&nbsp;长度不能超过10！");
				return;
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
	if(str!=null&&str!="")
	{
		if(str){
			$("#sum1").attr("validType","hedgeCurrency");
		}
	}
}




function getDateFromSession(){
	$.ajax({  
		   type: "POST",  
		   url: "../prePayment/getDateFromSession.json",  
		   dataType:"json",  
		   success: function(data){
			   $("#createTd").val(data.createTd);
		   }  
		 });
	//$('#user_add_form').form('clear');
}
