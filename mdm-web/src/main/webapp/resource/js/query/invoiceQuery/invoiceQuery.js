$(function() {

	
	/*初始化付款单grid高度*/
	$("#invoiceQuery_grid").height($(window).height()-120);
	/*付款单GRID*/
	$('#invoiceQueryView').myDatagrid({
		"title": "发票列表",
		"url": "../invoiceQuery/invoiceQueryLists.json",
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
		  					return inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate()+' '+
		  					inputDate.getHours()+':'+inputDate.getMinutes()+':'+inputDate.getSeconds();
		  				}
		  				return "录入时间有误";
		  			}}, 
		  			{field : 'venCode',title : '供应商编码', align : 'center', sortable : true}, 
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
		  			{field : 'rdId',title : '入库单',align : 'center', sortable : true,hidden:true}
		  		]],
		 
		  		
		  		
		  		
		  		
		  		
		  		
		  		
		  		"toolbar": [{
		  			id : 'data_form_detail',
					text : '查看明细',
					iconCls : 'icon-search',
					handler : function() {
						var $tb  = $("#invoiceQueryView");
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
//						btnExt();
						/*查看明细GRID*/
						$('#invoiceQueryDetailView').myDatagrid(
								{
							"url": "../invoiceQuery/queryRdRecordLists.json?id="+ rows[0].id+"&random="+new Date().getTime(),
//							"singleSelect": true, //多选
							"method": "get", 
							
							"columns" : [[
							
								{field : 'isum', title : '税率', 	     width : 15, align : 'center'},
								{field : 'iQuantity', title : '含税单价', 	 width : 20, align : 'center'},
								{field : 'iPrice', title : '税额', 	     width : 20, align : 'center'},
								{field : 'sum', title : '价税合计', 	     width : 20, align : 'center'},
								{field : 'cOrderCode', title : '主计量数量', 	 width : 30, align : 'center'},
								{field : 'cVenName', title : '无税单价', 	 width : 25, align : 'center'},
							
								
								{field : 'cItemCode', title : '项目编码', 	 width : 20, align : 'center'},
								{field : 'cName', title : '项目名称', 	     width : 30, align : 'center'},
								{field : 'cItem_Class', title : '项目大类编码', 	 width : 30, align : 'center'},
								
							]]
							
						});
						
					},
//					"permission": "mdm:pomainRecord:cataPass" //品类审核通过
				}],
		  		
		  		
		  		
		  		
		"model": "invoiceQuery", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改付款单窗口，将自动匹配ID：dataDictionary_edit_dialog
	
		/*"detailHandlerInvoiceQuery": {
			
			"queryUrl":"../invoiceQuery/rdRecordLists.do",
			
			"enable": true,
			"title": "查看入库单",
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
		}*/
		
	});
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#invoiceQueryView').datagrid("load", $("#invoiceQuery_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#invoiceQuery_search_form").form("reset");
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
			"timeout" : 5000
		});
	}
	
	
	
});








