$(function() {
	var param_id = 0; //定义全局变量param_id
	/*初始化系统参数grid高度*/
	$("#pomainRecord_grid").height($(window).height()-120);
	//加载审核状态下拉框
	$("#auditStatus").combobox({ 
		url:"../womainRecord/getAuditStatusList.json",
		valueField:'statusCode',
		textField:'statusDesc',
		method:'post',
		value:''  //默认选中value指定的选项
	});
	/*系统参数GRID*/
	$('#pomainRecordView').myDatagrid(
			{
		"title": "入库单信息列表",
		"url": "../womainRecord/getPOMainRecords.json",
		"system": "mdm",
		"singleSelect": false, //多选
		"method": "post", 
		"model": "womainRecord",
		"fitColumns":true,
		
		"columns" : [[
            {field : 'id', title : 'ID',            align : 'center', sortable : false, hidden:true},
            {field : 'coCode', title : '入库单号', 	  align : 'center'},          
			{field : 'orderDate', title : '入库日期',width : 40, 	 align : 'center' },
			{field : 'whName', title : '仓库', 	       align : 'center' },
			{field : 'orderCode', title : '订单号', 	width : 30,  align : 'center'},
			{field : 'busType', title : '业务类型', 	  align : 'center'},
			{field : 'cptName', title : '采购类型', 	  align : 'center'},
			{field : 'venName', title : '委外商', width : 40,	  align : 'center'},
			{field : 'cptName', title : '入库类别', 	  align : 'center'},
			{field : 'statusDesc', title : '审核状态',  formatter: function(value,row,index){
				  if (value == null ){
					return '待品类审核';
				  }else{
					return value;
				  }
				},align : 'center'},
			{field : 'auditStatus', title : '审核状态代码',   align : 'center', hidden:true}
		]],
		/*入库单审核按钮*/
		"toolbar": [{
			id : 'bt_cataPass',
			text : '品类审核通过',
			iconCls : 'icon-edit',
			handler : function() {
				var $tb  = $("#pomainRecordView");
				var rows = $tb.datagrid("getSelections");
				if(!rows || rows.length<=0){
					$.messager.alert("提示", "请先选择所要审核的单据。");
					return;
				} 
				if (rows.length > 1) {
					multiRowMessge();
				}
				//已审核的不能重审
				var aStatus = rows[0].auditStatus;
				if( aStatus=='2' ){
					$.messager.alert("警告", "该单品类已审核通过，不能重审");
					return;
				}
				if( aStatus=='3' ){
					$.messager.alert("警告", "该单财务已审核通过，不能重审");
					return;
				}
				if( aStatus=='4' ){
					$.messager.alert("警告", "该单品类审核不通过，不能重审");
					return;
				}
				if( aStatus=='5' ){
					$.messager.alert("警告", "该单财务审核不通过，不能重审");
					return;
				}
				if(aStatus==null){
					aStatus = 2;
				}
				updateRecordStatus(rows[0].coCode,"2","0");
			},
			"permission": "mdm:womainRecord:cataPass" //品类审核通过
		},{
			id : 'bt_cataNoPass',
			text : '品类审核不通过',
			iconCls : 'icon-edit',
			handler : function() {
				var $tb  = $("#pomainRecordView");
				var rows = $tb.datagrid("getSelections");
				if(!rows || rows.length<=0){
					$.messager.alert("提示", "请先选择所要审核的单据。");
					return;
				} 
				if (rows.length > 1) {
					multiRowMessge();
				}
				//已审核的不能重审
				var aStatus = rows[0].auditStatus;
				if( aStatus=='3' ){
					$.messager.alert("警告", "该单财务已审核通过，不能重审");
					return;
				}
				if( aStatus=='2' ){
					$.messager.alert("警告", "该单品类已审核通过，不能重审");
					return;
				}
				if( aStatus=='4' ){
					$.messager.alert("警告", "该单品类审核不通过，不能重审");
					return;
				}
				if( aStatus=='5' ){
					$.messager.alert("警告", "该单财务审核不通过，不能重审");
					return;
				}
				if(aStatus==null){
					aStatus = 4;
				}
				updateRecordStatus(rows[0].coCode,"4","0");
			},
			"permission": "mdm:womainRecord:cataNoPass" //品类审核不通过
		},{
			id : 'bt_finaPass',
			text : '财务审核通过',
			iconCls : 'icon-edit',
			handler : function() {
				var $tb  = $("#pomainRecordView");
				var rows = $tb.datagrid("getSelections");
				if(!rows || rows.length<=0){
					$.messager.alert("提示", "请先选择所要审核的单据。");
					return;
				} 
				if (rows.length > 1) {
					multiRowMessge();
				}
				//已审核的不能重审
				var aStatus = rows[0].auditStatus;
				
				if( aStatus=='3' ){
					$.messager.alert("警告", "该单财务已审核通过，不能重审");
					return;
				}
				if( aStatus=='4' ){
					$.messager.alert("警告", "该单品类审核不通过，不能重审");
					return;
				}
				if( aStatus=='5' ){
					$.messager.alert("警告", "该单财务审核不通过，不能重审");
					return;
				}
				if(aStatus==null){
					$.messager.alert("警告", "该单需要品类先审核");
					return;
				}
				//自动生成凭证提示
				$.messager.confirm("操作提示", "财务审核通过将自动生成凭证，您确定要执行操作吗？", function (data) {
		            if (data) {
		            	updateRecordStatus(rows[0].coCode,"3","1");
		            }
		            else {
		            }
		        });
				
			},
			"permission": "mdm:womainRecord:FinaPass" //品类审核通过
		},{

			id : 'bt_finaNoPass',
			text : '财务审核不通过',
			iconCls : 'icon-edit',
			handler : function() {
				var $tb  = $("#pomainRecordView");
				var rows = $tb.datagrid("getSelections");
				if(!rows || rows.length<=0){
					$.messager.alert("提示", "请先选择所要审核的单据。");
					return;
				} 
				if (rows.length > 1) {
					multiRowMessge();
				}
				//已审核的不能重审
				var aStatus = rows[0].auditStatus;
				if( aStatus=='3' ){
					$.messager.alert("警告", "该单财务已审核通过，不能重审");
					return;
				}
				if( aStatus=='4' ){
					$.messager.alert("警告", "该单品类审核不通过，不能重审");
					return;
				}
				if( aStatus=='5' ){
					$.messager.alert("警告", "该单财务审核不通过，不能重审");
					return;
				}
				if(aStatus==null){
					$.messager.alert("警告", "该单需要品类先审核");
					return;
				}
				updateRecordStatus(rows[0].coCode,"5","1");
			},
			"permission": "mdm:womainRecord:FinaNoPass" //品类审核通过
		
		},{

			id : 'data_form_detail',
			text : '查看明细',
			iconCls : 'icon-search',
			handler : function() {
				var $tb  = $("#pomainRecordView");
				var rows = $tb.datagrid("getSelections");
				if(!rows || rows.length<=0){
					$.messager.alert("提示", "请先选择要查看的单据。");
					return;
				} 
				if (rows.length > 1) {
					//bMany = true; // 用户选中多条数据
					multiRowMessge();
					//if (bMany) {}
				}
				var $d = $('#data_detail_dialog');
				$d.show().dialog({
					"title" : "详细信息",
					"closed" : false,
					"width": 1150,    
				    "height": 240, 
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
				$('#pomainRecordDetailView').myDatagrid(
						{
					"url": "../womainRecord/getPOMainRecordsDetail.json?id="+ rows[0].id,
					"singleSelect": true, //多选
					"method": "get", 
					
					"columns" : [[
						{field : 'invCode', title : '存货编码', 	 width : 30, align : 'center' },          
						{field : 'invName', title : '存货名称', 	 width : 60, align : 'center'},
						{field : 'taxRate', title : '税率', 	     width : 15, align : 'center'},
						{field : 'taxCost', title : '含税单价', 	 width : 20, align : 'center'},
						{field : 'taxPrice', title : '税额', 	     width : 20, align : 'center'},
						{field : 'sum', title : '价税合计', 	     width : 20, align : 'center'},
						{field : 'quantity', title : '主计量数量', 	 width : 30, align : 'center'},
						{field : 'unitCost', title : '无税单价', 	 width : 25, align : 'center'},
						{field : 'price', title : '无税金额', 	     width : 25, align : 'center'},
						{field : 'batch', title : '批号', 	     width : 30, align : 'center'},
						{field : 'cItemCode', title : '项目编码', 	 width : 20, align : 'center'},
						{field : 'cName', title : '项目名称', 	     width : 30, align : 'center'},
						{field : 'cItem_Class', title : '项目大类编码', 	 width : 30, align : 'center'},
						{field : 'cItemCName', title : '项目大类名称', 	 width : 30, align : 'center'},
						{field : 'cFree1', title : '克重', 	      width : 20, align : 'center'}
					]]
					
				});
				
			},
			"permission": "mdm:womainRecord:poDetail" //查看明细
		
		}],
		
		//"model": "womainRecord", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改系统参数窗口，将自动匹配ID：sysParam_edit_dialog
		"dblClickHandler": "detailHandler" //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		
	});
	
	/*查询grid*/
	//$("#sysParam_search_form").find("#account")[0].disabled = false;
	$("#search_form_submit").on("click", function(){
		$('#pomainRecordView').datagrid("load", $("#pomainRecord_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#pomainRecord_form").form("reset");
	});
	
	/**
	 * 审核提交
	 */
	function updateRecordStatus(coCode,auditStatus,role){
		$.ajax({
			url: "../womainRecord/updateRecordStatus.json",
			data: {"coCode":coCode,"auditStatus":auditStatus,"role":role},
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
					$('#pomainRecordView').datagrid("load", $("#pomainRecord_form").form("formToJson"));// 重绘表格
					
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
