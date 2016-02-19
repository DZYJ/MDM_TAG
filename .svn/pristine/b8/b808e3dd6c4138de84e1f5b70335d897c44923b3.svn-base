$(function() {
	/*初始化入库单grid高度*/
	$("#salesReturn_grid").height(500);
	$("#salesReturn_grid").width(900);
	var orderNum = getParameter("orderNum");
	getsalesReturnPrint(orderNum);
	getsalesReturnsPrint(orderNum);
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#salesReturnView').datagrid("load", $("#salesReturn_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#salesReturn_search_form").form("reset");
	});
});

//打印————主表内容
function getsalesReturnPrint(param){
	$.ajax({
		url: "../salesReturn/getSalesReturnPrintLists.json?orderNum="+param,
		dataType: "json",
		data: "param",
		type: "post",
		cache: false,
		success: function(data){
			if(data.success=='true'){
				$.each(data.data, function(i, item) { 
					
					 $("#orderNum").text(item.orderNum);
					 $("#originOrderNumber").text(item.originOrderNumber);
					 $("#originparentOrderNum").text(item.originparentOrderNum);
					 $("#customerName").text(item.customerName);
					 $("#paymentType").text(paymentType(item.paymentType));
					 $("#orderDate").text( FormatDate(item.orderDate));
					 $("#refundAmount").text(item.refundAmount);
					 $("#traceNum").text(item.traceNum);
				 });
			}
		}
	});
}

//打印————子表内容
function getsalesReturnsPrint(param){
	$.ajax({
		url: "../salesReturn/getSalesReturnsPrintDetail.json?orderNum="+param,
		dataType: "json",
		data: "param",
		type: "post",
		cache: false,
		success: function(data){
			if(data.success=='true'){
				var quantity=0;
				var sum=0;
				var freight=0;
				var number=1;
				$.each(data.data, function(i, item) { 
					 var htmlStr = "";
					 htmlStr="<tr><td>"+(number++)+"</td><td>"+item.styleNumber+"</td><td>"+item.partNumber+"</td><td>"+item.cinvName+"</td><td>"+item.partPrice+"</td><td>"+item.quantity+"</td><td>"+item.sum+"</td><td>"+item.freight+"</td></tr>";
					 quantity+=item.quantity;
					 sum+=item.sum;
					 freight+=item.freight;
					 $("#salesReturnView").append(htmlStr);
				});
				htmlStr="<tr><td>合计</td><td></td><td></td><td></td><td></td><td>"+quantity+"</td><td>"+sum+"</td><td>"+freight+"</td></tr>";
				$("#salesReturnView").append(htmlStr);
			}
		}
	});
}

function print(id){
	$("#"+id).printArea();
}

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

//支付方式
function paymentType(value){
		if(value=="1"){
			value="支付宝";
		}
		if(value=="3"){
			value="财付通";
		}
		if(value=="19"){
			value="余额支付";
		}
		if(value=="119"){
			value="银联快捷支付";
		}
		if(value=="120"){
			value="银联网银支付";
		}
		if(value=="128"){
			value="自提付款";
		}
		if(value=="150"){
			value="多边黄金钱包";
		}
		return value;
}

//格式化时间
function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}

function getParameter(param){
	var query = window.location.search;
	var iLen = param.length;
	var iStart = query.indexOf(param);
	if (iStart == -1){
		return "";
	}
	iStart += iLen + 1;
	var iEnd = query.indexOf("&", iStart);
	if (iEnd == -1){
		return query.substring(iStart);
	}
	return query.substring(iStart, iEnd);
}

