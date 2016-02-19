$(function() {
	// 销售渠道 下拉菜单
	/*$("#orderChannel").combobox({
		url:"../recordConfirm/getOrderChannelList.json",
		valueField:'cOrderChannel',
		textField:'remark',
		method:'post',
		onSelect:function(param){
			$("#orderChannel").val(param.cOrderChannel);
		},
		onUnselect:function(param){
			$("#orderChannel").val("");
		}
	});*/
	/*初始化入库单grid高度*/
	$("#recordConfirm_grid").height($(window).height()-150);
	/*入库单GRID--展示界面*/
	$('#recordConfirmView').myDatagrid({
		"title": "入库单列表",
		"url": "../recordConfirm/recordConfirmLists.json",
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
						}else{
							value="未知";
						}
						return value;
					}},
		  			{field : 'orderChannel',title : '销售渠道',align : 'center', sortable : true
						/*,formatter:function(value){
		  				if(value=="1000"){
			  				value="多边金都网站";
			  			}else if(value=="1001"){
			  				value="多边金都wap";
			  			}else if(value=="1002"){
			  				value="多边金都安卓app";
			  			}else if(value=="1003"){
			  				value="多边金都ios app";
			  			}else if(value=="1015"){
							value="京东";
						}else if(value=="1016"){
							value="国美在线3PP";
			  			}else if(value=="1017"){
			  				value="国美在线店铺平台";
			  			}else if(value=="1018"){
			  				value="微店";
			  			}else if(value=="2000"){
			  				value="天猫";
			  			}else {
			  				
			  			}
			  			if(value=="3001"){
			  				value="中塔";
			  			}else if(value=="3002"){
			  				value="十里堡";
			  			}else{
			  				
			  				value="未知";
			  			}
						return value;
					}*/
					},
		  			{field : 'recoveryNum',title : '入库单号',align : 'center', sortable : true},
		  			{field : 'orderDate',title : '入库日期',align : 'center', sortable : true, formatter:function(value){
						if(value=='' || value==null){
							//提醒....
						}else{
							var inputDate = new Date(value);
							return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()
//							+' '+inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds()
							;
						}
						return "录入时间有误";
					}},
		  			{field : 'whName',title : '仓库',align : 'center', sortable : true},
		  			{field : 'orderStatus',title : '付款状态',align : 'center', sortable : true,formatter:function(value){
						if(value=="1"){
							value="未付款";
						}else if(value=="9"){
							value="已付款";
						}else if(value=="3"){
							value="已充卡付款";
						}else if(value=="4"){
		  					value="已取消";
		  				}else{
							value="未知";
						}
						return value;
					}},
		  			{field : 'remark',title : '备注',align : 'center', sortable : true}
		]],
		"toolbar": [{
			id : 'data_form_detail',
			text : '查看明细',
			iconCls : 'icon-search',
			handler : function() {
				var $tb  = $("#recordConfirmView");
				var rows = $tb.datagrid("getSelections");
				if(!rows || rows.length<=0){
					$.messager.alert("提示", "请先选择要查看的单据。");
					return;
				}
				var row = rows[0];
				var $d = $('#recordConfirm_detail_dialog');
				$d.show().dialog({
					"title" : "详细信息",
					"closed" : false,
					"width": 900,    
				    "height": 380, 
					"modal" : true,
					"buttons" : [{
						"text" : "关闭",
						"iconCls" : 'icon-cancel',
						"handler" : function() {
							$d.dialog("close");
						}
					}]
				});
				if (rows.length > 1) {
					//bMany = true; // 用户选中多条数据
					multiRowMessge();
					var firstIndex = $tb.datagrid("getRowIndex", row);
					$tb.datagrid("unselectAll");
					$tb.datagrid("selectRow", firstIndex);
				}
				btnExt();
				/*var dataForm = $("#recordConfirmDetailView");
				dataForm.form("reset"); // 清空表单
				if (row) {
					dataForm.form("load", row); // 修改或查询，绑定数据到编辑表单
				}*/
				
				/*查看明细GRID*/
				$('#recordConfirmDetailView').myDatagrid(
						{
					"url": "../recordConfirm/getPOMainRecordsDetail.json?recoveryNum="+ rows[0].recoveryNum,
					"singleSelect": true, //多选
					"method": "get", 
					"columns" : [[
						{field : 'invCode', title : '存货编码', 	 width : 30, align : 'center' },          
						{field : 'invName', title : '存货名称', 	 width : 60, align : 'center'},
						{field : 'quantity', title : '主计量数量', 	 width : 30, align : 'center'},
						{field : 'price', title : '无税金额', 	     width : 25, align : 'center'},
						{field : 'free', title : '克重', 	      width : 20, align : 'center'}
					]]
				});
			},
			"permission": "mdm:queryRecordConfirm:recordConfirmDetail" //查看明细
		}],
		//"model": "recordConfirm", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改系统参数窗口，将自动匹配ID：sysParam_edit_dialog
		"dblClickHandler": "detailHandler" //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
	});
	
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
			"timeout" : 3000
		});
	}
	
	//入库日期只可选，不可手动输入
	$(".datebox :text").attr("readonly","readonly");
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#recordConfirmView').datagrid("load", $("#recordConfirm_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#recordConfirm_search_form").form("reset");
	});
	
});
