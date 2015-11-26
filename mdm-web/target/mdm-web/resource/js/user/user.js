$(function() {
	/*初始化用户grid高度*/
	$("#user_grid").height($(window).height()-120);
	
	/*用户GRID*/
	$('#userview').myDatagrid({
		"title": "管理员列表",
		"url": "../user/list.json",
		"singleSelect": false, //多选
		"method": "post", //默认为get,此处是因为后台处理是POST方式
		"columns" : [[
			{field : 'id', 		title : '编号', 	 width : 40, align : 'center', sortable : true},
			{field : 'account', title : '账户', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'name',	title : '用户名',  width : 60, align : 'center'}, 
			{field : 'password',title : '密码', 	 width : 60, align : 'center', sortable : true}, 
			{field : 'email',	title : 'E-Mail',width : 60, align : 'center'},
			{field : 'masLoc',	title : '仓库', 	 width : 40, align : 'center', sortable : true}
		]],
		"toolbar": [{
			id : 'btnrole',
			text : '用户角色管理',
			iconCls : 'icon-filter',
			handler : function() {
				userRoleHandler();
			}
		}],
		"model": "user", //当不指定form、dialog的ID，插件会根据该属性来自动匹配页面元素，如修改用户窗口，将自动匹配ID：user_edit_dialog
		"dblClickHandler": "detailHandler", //双击行时进行的操作(当定义了onDblClickRow时，此参数将失效)
		/*增删改查配置*/
		"editHandler": {
			"enable": true,
			"title": "修改用户", 
			"form": {"validate":true, "submitUrl": "../user/update.do"}
			//,"handler": function(){alert("回调方法");}
		},
		"addHandler": {
			"enable": true,
			"title": "新增用户", 
			"form": {"validate":true, "submitUrl": "../user/create.do"}
		},
		"detailHandler": {
			"enable": true,
			"title": "查看用户",
			"queryUrl": "../user/edit.json"
		},
		"removeHandler": {
			"enable": true,
			"title": "删除用户",
			"removeUrl": "../user/deleteByIds.json", 
			"idField": "id",
			"idParams": "ids"
		}
	});
	
	/*查询grid*/
	$("#user_search_form").find("#account")[0].disabled = false;
	$("#search_form_submit").on("click", function(){
		$('#userview').datagrid("load", $("#user_search_form").form("formToJson"));
	});
	$("#search_form_reset").on("click", function(){
		$("#user_search_form").form("reset");
	});
	
	/*用户角色管理弹出窗口*/
	$("#role_manage").show().dialog({    
	    title: "用户角色管理",    
	    width: 750,  
	    height: 500,    
	    closed: true,    
	    modal: true,
	    buttons:[{
			text:'提交',
			iconCls:'icon-save',
			handler:function(){
	    		getSelectedRoles();
			}
		}]
	});
	$("#add_user_roles").on("click", function(){
		var cks = $("#new_roles").find(".user_role_ck:checked");
		$.each(cks, function(){
			$("#own_roles").append($(this).parent().parent());
		});
	});
	$("#del_user_roles").on("click", function(){
		var cks = $("#own_roles").find(".user_role_ck:checked");
		$.each(cks, function(){
			$("#new_roles").append($(this).parent().parent());
		});
	});
});

/**
 * 用户角色管理FN
 */
function userRoleHandler(){
	var dataGrid = $('#userview');
	var selectRow = dataGrid.datagrid("getSelected");
	var bMany = false;
	var selectRows = dataGrid.datagrid("getSelections");// var selectRow = $(oGrid).datagrid("getSelected");
	if (!selectRows || selectRows.length <= 0) {
		$.messager.alert("提示", "&nbsp;&nbsp;请选择一行数据！");
		return;
	}
	selectRow = selectRows[0]; // 默认对选中的第一条数据进行操作
	if (selectRows.length > 1) {
		bMany = true;
		var firstIndex = dataGrid.datagrid("getRowIndex", selectRow);
		dataGrid.datagrid("unselectAll");
		dataGrid.datagrid("selectRow", firstIndex);
	}
	roleManager(selectRow, bMany);
}
function roleManager(userInfo, bMany){
	$("#role_user_info").html("账号："+userInfo.account+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名：" + userInfo.name 
			+"<input type='hidden' id='current_user_id' value='"+ userInfo.id+"'/>");
	$("#role_manage").show().dialog("open");
	$("#own_roles").html("");
	$("#new_roles").html("");
	if (bMany) {/* 当用户选中多条数据时，提示用户将默认对其选择的第一条数据进行操作 */
		msg.topCenter("温馨提示", "<span style='color:red;'>*</span>由于您选择了多条数据，系统将默认对您选择的第一条数据进行操作。");
	}
	$.ajax({
		url: "../userRole/roles.json?t="+Math.random(),
		data: {"userId": userInfo.id},
		dataType: "json",
		type: "get",
		cache: false,
		success: function(data){
			//alert(JSON.stringify(data.ownRoles));
			if(data){
				roleInit(data.ownRoles, data.newRoles);
			}
		}
	});
}
function roleInit(ownRoles, newRoles){
	var ownStrs = "";
	var newStrs = "";
	$.each(ownRoles, function(i1, n1){
		ownStrs += "<div class='role_list'><div class='role_list_l'><input type='checkbox' class='user_role_ck'/></div>"
			+ "<div class='role_list_c'>"+n1.name+"<input type='hidden' class='user_role_val' value='"+n1.id+"'/></div></div>";
	});
	$.each(newRoles, function(i2, n2){
		newStrs += "<div class='role_list'><div class='role_list_l'><input type='checkbox' class='user_role_ck'/></div>"
			+ "<div class='role_list_c'>"+n2.name+"<input type='hidden' class='user_role_val' value='"+n2.id+"'/></div></div>";
	});
	var $ownRoles = $("#own_roles");
	var $newRoles = $("#new_roles");
	$ownRoles.children(".role_list").remove();//$("#own_roles").html(ownStrs);
	$ownRoles.append($(ownStrs));
	$newRoles.children(".role_list").remove();//$("#new_roles").html(newStrs);
	$newRoles.append($(newStrs));
	$('.role_list_c').on("dblclick", function(){
		var $target = $ownRoles;
		if($(this).parent().parent().attr("id")=="own_roles"){
			$target = $newRoles;
		}
		$(this).parent().appendTo($target);
	});
	$('.role_list_c').draggable({
		proxy:'clone',
		revert:true,
		cursor:'auto',
		onStartDrag:function(){
			$(this).draggable('options').cursor='not-allowed';
			$(this).draggable('proxy').addClass('dp');
		},
		onStopDrag:function(){
			$(this).draggable('options').cursor='auto';
		}
	});
	$('#new_roles, #own_roles').droppable({
		onDragEnter:function(e, source){
			$(source).draggable('options').cursor='auto';
			$(source).draggable('proxy').css('border','1px solid red');
			$(this).addClass('over');
		},
		onDragLeave:function(e, source){
			$(source).draggable('options').cursor='not-allowed';
			$(source).draggable('proxy').css('border','1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop:function(e, source){
			if($(this).attr("id")!=$(source).parent().parent().attr("id")){
				$(this).append($(source).parent());
			}
			$(this).removeClass('over');
		}
	});
}
/**
 *获取所选角色（ID）
 */
function getSelectedRoles(){
	var userId = $("#current_user_id").val();
	var ownRoles = $("#own_roles").find("input.user_role_val");
	var ownStrs = "";
	$.each(ownRoles, function(i, n){
		ownStrs += $(n).val() + ",";
	});
	if(ownStrs.length > 0){
		ownStrs = ownStrs.substring(0, ownStrs.length-1);
	}
	$.ajax({
		url: "../userRole/roleManager.json",
		data: {"userId": userId, "roleIds":ownStrs},
		dataType: "json",
		type: "get",
		success: function(data){
			msg.bottomRight("提示信息",data.msg);
			$("#role_manage").dialog("close");
		}
	});
}
/**
 *全选、反选
 */
function selectRoles(sType, bChecked){
	if(sType=="new"){
		checkboxSelector($("#new_roles").find(".user_role_ck"), bChecked);
	}else if(sType=="own"){
		checkboxSelector($("#own_roles").find(".user_role_ck"), bChecked);
	}
}
