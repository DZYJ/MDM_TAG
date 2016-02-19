$(function() {
	//加载下拉框
	comboboxVenName();
	
	/*初始化发票grid高度*/
	$("#accvouch_grid").height($(window).height()-120);
	
	/*发票GRID*/
	$('#accvouchView').myDatagrid({
		"title": "凭证列表",
		"url": "../accvouch/accvouchList.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
		    {field : 'iperiod', title : '会计期间', align : 'center',hidden:true, sortable : true},
		    {field : 'isignseq', title : '凭证类别', align : 'center', hidden:true,sortable : true},
			{field : 'dbill_date', width : 10,title : '制单日期', align : 'center', sortable : true},
			{field : 'csign',width : 10, title : '凭证类别', align : 'center', sortable : true},
			{field : 'ino_id', width : 10, title : '凭证号', align : 'center', sortable : true},
			{field : 'cdigest',width : 30,title : '摘要', align : 'center', sortable : true}, 
			{field : 'md',width : 10, title : '借方金额合计',align : 'center', sortable : true}, 
			{field : 'mc',width : 10, title : '贷方金额合计',align : 'center', sortable : true},
		]],
		"toolbar": [
			{
				id : 'bt_cataDetail',
				text : '查看',
				iconCls : 'icon-search',
				handler : function() {
					var $tb  = $("#accvouchView");
					var rows = $tb.datagrid("getSelections");
					
					if(!rows || rows.length<=0){
						$.messager.alert("提示", "请先选择凭证。");
						return;
					} 
					if(rows.length>1){
						multiRowMessge();
					}
					var row = rows[0];
					var $d = $('#data_detail_dialog');
					$d.show().dialog({
						"title" : "详细信息",
						"closed" : false,
						"width": 1150,    
					    "height": 400, 
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
					$('#accvouchDetailView').myDatagrid(
							{
						"url": "../accvouch/accvouchDetail.json?iperiod="+ row.iperiod+"&isignseq="+row.isignseq+"&ino_id="+row.ino_id+"&startDate=''&endDate=''",
						"singleSelect": true, //多选
						"method": "get", 
						"columns" : [[
							{field : 'cdigest', title : '摘要', width : 100, align : 'center' },          
							{field : 'ccode', title : '科目',width : 30, align : 'center'},
							{field : 'codeName', title : '科目名称',width : 30, align : 'center'},
							{field : 'md', title : '借方金额',width : 20, align : 'center'},
							{field : 'mc', title : '贷方金额',width : 20, align : 'center'},
							{field : 'citem_id', title : '项目核算',width : 20, align : 'center'},
							{field : 'cdept_id', title : '部门核算',width : 30, align : 'center'},
							{field : 'cperson_id', title : '个人往来',width : 25, align : 'center'},
							{field : 'ccus_id', title : '客户往来',width : 25, align : 'center'},
							{field : 'csup_id', title : '供应商往来',width : 30, align : 'center'}
						]]
					});
					
				},
			     "permission": "mdm:accvouch:query" //查看
			},
		    {
				id : 'bt_cataCommit',
				text : '导出',
				iconCls : 'icon-filter',
				handler : function() {
					var $tb  = $("#accvouchView");
					var rows = $tb.datagrid("getSelections");
					
					if(!rows || rows.length<=0){
						$.messager.alert("提示", "请先选择凭证!");
						return;
					} 
					if(rows.length>1){
						multiRowMessge();
					}
					var row=rows[0];
					var dataForm  = $("#accvouch_export_form");//form
					dataForm.form("reset"); // 清空表单
					$("#periodExport").val(row.iperiod);
					$("#signseqExport").val(row.isignseq);
					$("#ino_idExport").val(row.ino_id);
					dataForm.submit();
//					$.ajax({
//						url: "../accvouch/exportNcAcc.json?iperiod="+ row.iperiod+"&isignseq="+row.isignseq+"&ino_id="+row.ino_id,
//						dataType: "json",
//						type: "get",
//						cache: false,
//						error:function(data){
//							$.messager.alert("失败", "凭证导出失败！");
//						},
//						success: function(result){
//							 // 返回需要修改的数据信息
//							if(result  == "suc"){
//								$.messager.alert("提示", "凭证导出成功！");
//							}
//						}
//					});
				},
				"permission": "mdm:accvouch:export" //导出
			},
		    {
				id : 'bt_cataexport',
				text : '批量导出',
				iconCls : 'icon-filter',
				handler : function() {
					var dataForm  = $("#accvouch_export_form");//form
					dataForm.form("reset"); // 清空表单
					var dataDialog = $("#accvouch_export_dialog");
					comboboxVenName("Export");
					var buttons = [];
			//		if (true) {
						/* 编辑或新增的时候的提交按钮 */
					buttons.push({
						"text" : "导出",
						"iconCls" : "icon-filter",
						"handler" : function() {
							dataForm.submit();
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
						"title" : "批量导出凭证 ",
						//"width": handler.dialog.width,
						//"height": dh,
						"closed" : false,
						"modal" : true,
						"buttons" : buttons
					});
				},
				"permission": "mdm:accvouch:export" //导出
			}
		],
		"model": "accvouch", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改数据字典窗口，将自动匹配ID：dataDictionary_edit_dialog
		"detailHandler": {
			"enable": false,
			"title": "查看",
            "handler": function(){
				//是否活动显示
//				var billingDate = $("#billingDate2");
////				var billingDate = $(this).find("input[name='billingDate2']");
//				var val = billingDate.val();
//				var inputDate = new Date(val);
//				inputDate = inputDate.getFullYear()+'-'+(inputDate.getMonth()+1)+'-'+inputDate.getDate();
//				billingDate.val(inputDate);
			}
		}
		/*"subHandlerPo": {
			"enable": true,
			"title": "提交",
			"submitUrl": "../accvouch/commitByIds.json", 
			"dataParams": "rowsData"
		}*/
	});
	
	function comboboxVenName(name){
		if(name==null){
			name="";
		}
		$("#signseq"+name).combobox({
			url:"../accvouch/getCsign.json",
			valueField:'isignseq',
			textField:'ctext',
			method:'post',
		});
	}
	
//	$(".datebox :text").attr("readonly","readonly");
	
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#accvouchView').datagrid("load", $("#accvouch_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#accvouch_search_form").form("reset");
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
