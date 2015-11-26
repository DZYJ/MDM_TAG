$(function() {
	/*初始化承运商信息grid高度*/
	$("#pickuptime_grid").height($(window).height()-120);
	
	/*承运商信息GRID*/
	$('#pickuptimeView').myDatagrid({
		"title": "承运商信息列表1",
		"url": "../lspCarrier/lspCarrierList.json",
		"singleSelect": false, //多选
		"method": "post", 
		"columns" : [[
			{field : 'lspName', title : '承运商名称', 	 width : 40, align : 'center', sortable : true},
			{field : 'lspCode', title : '承运商编号', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'lspType',title : '承运商类型',  width : 60, align : 'center'}, 
			{field : 'enabled',title : '是否有效', width : 60, align : 'center', sortable : true,formatter:function(val){
				if(val=="1"){
					val="是";
				}else if(val=="0"){
					val="否";
				}
				return val;
			}}, 
			{field : 'codRate',	title : '代收费率',width : 60, align : 'center'},
			{field : 'insuranceRate',title : '保险费率', width : 40, align : 'center', sortable : true},
			{field : 'lspContact',title : '联系人', width : 40, align : 'center', sortable : true},
			{field : 'lspPhone',title : '电话', width : 40, align : 'center', sortable : true}
		]],
		"model": "pickuptime", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改系统参数窗口，将自动匹配ID：sysParam_edit_dialog
		"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		"toolbar": [{
			id : 'btnrole',
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				var selectRows = $("#pickuptimeView").datagrid("getSelections");//var selectRow = $(oGrid).datagrid("getSelected");
				if(!selectRows || selectRows.length<=0){
					$.messager.alert("提示", "&nbsp;&nbsp;请选择一行数据！");
					return;
				}
				if(selectRows.length>=2){
					$.messager.alert("提示", "&nbsp;&nbsp;不能选择多行数据！");
					return;
				}
				editPickuptime(selectRows);
			}
		},{
			id : 'btnrole',
			text : '查看',
			iconCls : 'icon-search',
			handler : function() {
				var selectRows = $("#pickuptimeView").datagrid("getSelections");//var selectRow = $(oGrid).datagrid("getSelected");
				if(!selectRows || selectRows.length<=0){
					$.messager.alert("提示", "&nbsp;&nbsp;请选择一行数据！");
					return;
				}
				if(selectRows.length>=2){
					$.messager.alert("提示", "&nbsp;&nbsp;不能选择多行数据！");
					return;
				}
				showPickuptime(selectRows);
			}
		}]
		
	});
	/*查询grid*/
	$("#search_form_submit").on("click", function(){
		$('#pickuptimeView').datagrid("load", $("#pickuptime_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#pickuptime_search_form").form("reset");
	});
});
var PICK_UP_TIME_DEFAULTS = {
		"masterLocations": null,
		"getMasterLocationUrl": "../masLoc/list.do", //查询仓库信息的URL
		"savePickuptimeUrl": "../pickuptime/savePickuptime.json", //保存收货时间的URL
		"getPickuptimeUrl": "../pickuptime/queryPickuptime.json",
		"saveParamName": "pickuptimeData" //保存收货时间时忘后台传递的参数名称
	};
function showPickuptime(selectRows){
	$("#pickuptime_dlg").find("tbody").html(""); //清空
	var $dialog = $("#pickuptime_dlg");
	/* 窗口按钮 */
	var buttons = [];
	buttons.push({
		"text" : "取消",
		"iconCls" : 'icon-cancel',
		"iconAlign": "right",
		"handler" : function() {
			$dialog.dialog("close");
		}
	});
	$dialog.show().dialog({
		"title" : "取货时间",
		"closed" : false,
		"modal" : true,
		"buttons" : buttons
	});
	var param = { "lspCode": selectRows[0].lspCode };
	$.ajax({
		"url" : PICK_UP_TIME_DEFAULTS.getPickuptimeUrl,
		"data": param,
		"dataType" : "json",
		"type" : "post",
		"cache" : false,
		"async": true, //异步
		"success" : function(result) {
			if(result && result.length>0){
				$.each(result, function(i, n){
					detailPickuptime(n);
				});
			}
		},
		"error" : function() {
			$.messager.alert("提示", "AJAX请求失败，请检查URL：" + PICK_UP_TIME_DEFAULTS.getPickuptimeUrl);
		}
	});
}
function editPickuptime(selectRows){
	$("#pickuptime_dlg").find("tbody").html(""); //清空
	var $dialog = $("#pickuptime_dlg");
	/* 窗口按钮 */
	var buttons = [];
	buttons.push({
		"text" : "添加取货时间",
		"id": "add_put_dlg_btn",
		"iconCls" : "icon-add",
		"iconAlign": "left",
		"handler" : function() {
			addPickuptime();
		}
	});
	buttons.push({
		"text" : "提交",
		"iconCls" : "icon-ok",
		"iconAlign": "right",
		"handler" : function() {
			savePickuptime();
		}
	});
	buttons.push({
		"text" : "取消",
		"iconCls" : 'icon-cancel',
		"iconAlign": "right",
		"handler" : function() {
			resetPickuptime();
			$dialog.dialog("close");
		}
	});
	$dialog.show().dialog({
		"title" : "取货时间",
		"closed" : false,
		"modal" : true,
		"buttons" : buttons
	});
	var param = { "lspCode": selectRows[0].lspCode };
	$.ajax({
		"url" : PICK_UP_TIME_DEFAULTS.getPickuptimeUrl,
		"data": param,
		"dataType" : "json",
		"type" : "post",
		"cache" : false,
		"async": true, //异步
		"success" : function(result) {
			if(result && result.length>0){
				$.each(result, function(i, n){
					addPickuptime(n);
				});
			} else {
				resetPickuptime();
			}
		},
		"error" : function() {
			$.messager.alert("提示", "AJAX请求失败，请检查URL：" + PICK_UP_TIME_DEFAULTS.getPickuptimeUrl);
		}
	});
}
/*同步获取仓库信息*/
function getMasterLocations(async){
	if(!PICK_UP_TIME_DEFAULTS.masterLocations){
		$.ajax({
			"url" : PICK_UP_TIME_DEFAULTS.getMasterLocationUrl,
//			"dataType" : "json",
			"type" : "post",
			"cache" : false,
			"async": async, //同步
			"success" : function(result) {
				if (result) {
					PICK_UP_TIME_DEFAULTS.masterLocations = result.rows;
				} 
			},
			"error" : function() {
				$.messager.alert("提示", "AJAX请求失败，请检查URL：" + PICK_UP_TIME_DEFAULTS.getMasterLocationUrl);
			}
		});
	}
	return PICK_UP_TIME_DEFAULTS.masterLocations;
}

/*添加取货时间*/
function detailPickuptime(queryData){
//	alert(JSON.stringify(queryData));
	var trClone = $("#pickuptime_tr_mirror").find(".pickuptime_tr").clone();
	trClone.appendTo($("#pickuptime_dlg").find("tbody"));
	var $master = trClone.find(".pickuptime_master");

	if(queryData && queryData.masLoc){
		$master.val(queryData.masName);
	}
	$master.attr("disabled", "disabled");
	
	var $day = trClone.find(".pickuptime_day");
	if(queryData && queryData.day){
		$day.val(queryData.day);
	}
	$day.attr("disabled", "disabled");
	
	var $time = trClone.find(".pickuptime_time");
	$time.mask("99:99"); //时间
	if(queryData && queryData.time){
		$time.val(queryData.time);
	} 
	$time.attr("disabled", "disabled");
	
	if(queryData && (queryData.active == 1 || queryData.active == "1")){
		trClone.find(".pickuptime_state")[0].checked = true;
	}
	trClone.find(".pickuptime_state")[0].disabled = true;
	
	$("#pickuptime_dlg").find("tbody tr:odd").addClass("tr_odd");
	//return trClone;
}
/*添加取货时间*/
function addPickuptime(queryData){
//	alert(JSON.stringify(queryData));
	var trClone = $("#pickuptime_tr_mirror").find(".pickuptime_tr").clone();
	trClone.appendTo($("#pickuptime_dlg").find("tbody"));
	var data = getMasterLocations(false);
	var $master = trClone.find(".pickuptime_master");
	$master.combobox({
		data: data,
		valueField: "masLoc",
		textField: "descr",
		panelHeight: "auto",
		loadFilter: function(_data){
			var ret = [{
				masLoc: "",
				descr: "--请选择--"
			}];
			$.merge(ret, _data);
			return ret;
		},
		onLoadSuccess: function(){
			if(queryData && queryData.masLoc){
				$master.combobox("setValue", queryData.masLoc);
			}
		}
	});
	var $day = trClone.find(".pickuptime_day");
	$day.combobox({
		width: 100,
		onLoadSuccess: function(){
			if(queryData && queryData.day){
				$day.combobox("setValue", queryData.day);
			}
		}
	});
	var $time = trClone.find(".pickuptime_time");
	$time.mask("99:99"); //时间
	if(queryData && queryData.time){
		$time.val(queryData.time);
	} //put_state
	if(queryData && (queryData.active == 1 || queryData.active == "1")){
		trClone.find(".pickuptime_state")[0].checked = true;
	}
	
	$("#pickuptime_dlg").find("tbody tr:odd").addClass("tr_odd");
	//return trClone;
}

/*提交取货时间*/
function savePickuptime(){
	
	var pickuptimes = $("#pickuptime_dlg").find("tbody tr");
	var dataStr = "";
	var flag = true;
	$.each(pickuptimes, function(i, n){
		if(!flag){
			return;
		}
		var $this = $(this);
		var putmaster = $this.find("input[name='put_master']").val();
		if(putmaster){
			var putday = $this.find("input[name='put_day']").val();
			if(!putday){
				flag = false;
				$.messager.alert("提示", "请选择星期！");
				return;
			}
			var puttime = $this.find("input[name='put_time']").val();
			if(!puttime){
				flag = false;
				$.messager.alert("提示", "请填写时间！");
				return;
			}
			var puttime = $this.find("input[name='put_time']").val();
			var arrys = puttime.split(":");
			if(arrys[0]>=24 || arrys[1]>59){
				flag = false;
				$.messager.alert("提示", "时间不能大于23:59！");
				return;
			}
			var putstate = $this.find("input[name='put_state']");
			if(putstate[0].checked){
				putstate = 1;
			}else{
				putstate = 0;
			}
			dataStr = dataStr + putmaster+","+putday+","+puttime+","+putstate+";";
			flag = true;
		}
	});
	if(!flag){
		return;
	}
	if(!dataStr){
		$.messager.alert("提示", "请至少填写一个取货时间！");
		return;
	}
	var param = {};
	param[PICK_UP_TIME_DEFAULTS.saveParamName] = dataStr;
	var lsp = $("#pickuptimeView").datagrid("getSelected").lspCode;
	param.lspCode = lsp;
	$.ajax({
		"url" : PICK_UP_TIME_DEFAULTS.savePickuptimeUrl,
		"data": param,
		"dataType" : "json",
		"type" : "post",
		"cache" : false,
		"async": true, //异步
		"success" : function(result) {
			$("#pickuptime_dlg").dialog("close");
			$.messager.show({
				"title" : "操作成功",
				"msg" : result.msg
			});
			resetPickuptime();
		},
		"error" : function() {
			$.messager.alert("提示", "AJAX请求失败，请检查URL：" + PICK_UP_TIME_DEFAULTS.savePickuptimeUrl);
		}
	});
}

/*重置取货时间*/
function resetPickuptime(){
	$("#pickuptime_dlg").find("tbody").html("");
	for(var i = 0; i<7; i++) { //默认有7个
		addPickuptime();
	}
}
