$(function() {
	/*初始化入库单grid高度*/
	$("#salesCheck_grid").height($(window).height()-120);
	/*入库单GRID--展示界面*/
	$('#salesCheckView').myDatagrid({
		"title": "销售对账单",
		"url": "../salesCheck/salesCheckLists.json?tradeType= s"+1,
		"singleSelect": false, //多选
		"method": "post", 
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
			id : 'bt_cataPass',
			text : '对账单核销',
			iconCls : 'icon-remove',
			handler : function() {
				var $tb  = $("#salesCheckView");
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
				//没有收款单，不能核销
				var paymentCode = selectRows[0].paymentCode;
				if(paymentCode==null||paymentCode==''){
					$.messager.alert("提示", "&nbsp;&nbsp;未收款，不允许核销！");
					return;
				}
				//已核销，不能再次核销
				var checkStatus = selectRows[0].checkStatus;
				if( checkStatus=='2' ){
					$.messager.alert("提示", "&nbsp;&nbsp;已核销，不允许进行再次核销！");
					return;
				}
				//自动生成凭证提示
				$.messager.confirm("操作提示", "提交后，您确定要执行操作吗？", function (data) {
		            if (data) {
		            	updateStatus(selectRows[0].id,checkStatus,"0");
		            }
		            else {
		            }
		        });
			},
			"permission": "mdm:salesCheck:commit" //审核
		}],
		"dblClickHandler": "detailHandler" //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
	});
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#salesCheckView').datagrid("load", $("#salesCheck_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#salesCheck_search_form").form("reset");
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

/**
 * 对账单核销
 */
function updateStatus(id,checkStatus){
	$.ajax({
		url: "../salesCheck/checkAndConfirm.json",
		data: {"id":id,"checkStatus":checkStatus},
		dataType: "json",
		type: "post",
		cache: false,
		error:function(data){
		},
		success: function(result){
			 // 返回需要修改的数据信息
			if(result && (result.state == true || result.state == "true")){
				$.messager.show({
					"title" : "核销对账单成功",
					"msg" : result.msg
				});
				$('#salesCheckView').datagrid("load", $("#salesCheck_search_form").form("formToJson"));// 重绘表格
				
			} else {
				$.messager.alert("核销对账单失败", result.msg);
			}
		}
	});
}
