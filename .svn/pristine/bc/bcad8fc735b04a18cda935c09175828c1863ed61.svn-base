$(function() {
	/*初始化入库单grid高度*/
	$("#salesCheck_grid").height($(window).height()-120);
	//下拉框
	comboboxCusName("");
	/*入库单GRID--展示界面*/
	//loadSearch("");
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$("#clean").remove();
		loadSearch("../salesCheckAll/salesCheckAllLists.json");
		$('#salesCheckView').datagrid("load", $("#salesCheck_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#salesCheck_search_form").form("reset");
	});
	
});

function loadSearch(url){
	$('#salesCheckView').myDatagrid({
		"title": "销售对账单",
		"singleSelect": false, //多选
		"method": "post", 
		"url":url,
		"columns" : [[
			            {field : 'id', title : 'id', align : 'center', sortable : true,hidden:true},
			            {field : 'cdlCode', title : '应收单号', align : 'center', sortable : true},
			  			{field : 'isum', title : '应收金额', align : 'center', sortable : true}, 
			  			{field : 'ddate',title : '发货日期', align : 'center',sortable : true, formatter:function(value){
							if(value=='' || value==null){
								//提醒....
							}else{
								var inputDate = new Date(value);
								return inputDate.getFullYear()+'-'+inputDate.getMonth()+'-'+inputDate.getDate();
							}
							return "录入时间有误";
						}},		
						{field : 'paymentCode', title : '实收单号', align : 'center', sortable : true},
						{field : 'sum', title : '实收金额', align : 'center', sortable : true}, 
						{field : 'ticketDate',title : '收款日期', align : 'center',sortable : true, formatter:function(value){
							if(value=='' || value==null){
								//提醒....
							}else{
								var inputDate = new Date(value);
								return inputDate.getFullYear()+'-'+inputDate.getMonth()+'-'+inputDate.getDate();
							}
							return "录入时间有误";
						}},		
						{field : 'ccusName', title : '客户', align : 'center', sortable : true}, 
			  			{field : 'digest', title : '摘要', align : 'center', sortable : true}, 
			  	 		{field : 'checkStatus',title : '核销状态', align : 'center',sortable : true,formatter:function(value){
			  				if(value=="1"||value==null||value==""){
			  					value="未核销";
			  				}
			  				if(value=="2"){
			  					value="已核销";
			  				}
			  				return value;}}
			]],
		"toolbar": [
		    {
		    }],
		"dblClickHandler": "detailHandler" //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
	});
}

/*//加载下拉框
function comboboxCusName(index){
	$("#ccusName"+index).combobox({ 
		url:"../salesCheckAll/salesCheckAllCusNameList.json?index="+index,
		valueField:'ccusName'+index,
		textField:'ccusName'+index,
		method:'post',
	});
}*/
function comboboxCusName(index){
	$("#ccusName").combobox({ 
		url:"../salesCheckAll/salesCheckAllCusNameList.json",
		valueField:'cusName',
		textField:'cusName',
		method:'post'
	});
}
