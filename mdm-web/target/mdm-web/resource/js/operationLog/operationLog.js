$(function() {
	$("#bb").height($(window).height()-$("#bb").outerHeight(true)-185);
	$('#operationLogview').datagrid({
		title : '操作日志列表',
		collapsible : true,//是否可折叠的
		rownumbers : true,//行号
		multiple : true,
		multiSort : true,//排序
		pagination : true,//分页控件
		singleSelect : true,//是否单选
		remoteSort : false,
		fitColumns : true,//自动大小
		url : '../operationLog/list.json',
		fit: true,
		columns : [ [ {
			field : 'id',
			title : '编号',
			width : 40,
			sortable : true,
			align : 'center'
		}, {
			field : 'title',
			title : '标题',
			width : 60,
			sortable : true,
			align : 'center'
		}, {
			field : 'username',
			title : '用户名',
			width : 60,
			sortable : true,
			align : 'center'
		},{
			field : 'ip',
			title : 'IP地址',
			width : 60,
			sortable : true,
			align : 'center'
		},{
			field : 'insertDate',
			title : '操作时间',
			width : 60,
			sortable : true,
			align : 'center',
			formatter : function(val, rec){
				var d = new Date(val);
				return UI.Date.dateStr(d, "yyyy-mm-dd hh:mm:ss");
			}
		}
		] ],

		loadMsg : '数据为您加载中,请您稍后……'
	});
	//设置分页控件
	var p = $('#operationLogview').datagrid('getPager');
	$(p).pagination({
		//每页显示的记录条数，默认为10
		pageSize : 10,
		//可以设置每页记录条数的列表
		pageList : [ 5, 10, 15, 20 ],
		//页数文本框前显示的汉字
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});
	
	
	/*
	*点击“查询”后提交查询
	*/
	function submitQuery() {
		
		$('#fm').form('submit', {
			url :'../operationLog/list.json',
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				 var map = $.parseJSON(result);
					$('#operationLogview').datagrid('load',{
					    title: $('#title').val(),
					    username: $('#username').val(),
					    ip:$('#ip').val(),
					    insertDate:$('#insertDate').val()  
					});
				  $('#operationLogview').datagrid({ url: '../operationLog/list.json' });
			}
		});
	}