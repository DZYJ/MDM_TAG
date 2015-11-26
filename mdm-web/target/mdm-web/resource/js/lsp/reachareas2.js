$(function() {

	
	$.ajax({
		url:'../masLoc/getMasLocListCombobox.json',
		cache:false,
	    type:"get",
	    error: function () {//请求失败处理函数
		      $.messager.alert("提示", "仓库请求失败!");
		},
	    dataType:"json",
	    success:function(data){
	        $("#masLoc").combobox({
	        	data:data
	        });	
	    }
	});

	
	$("#divitionTree").tree({
		url:'../division/getTreeList.json',
		onBeforeExpand:function(node){
			if(node.divLevel<3){
				$('#divitionTree').tree('options').url = '../division/getChildList.json?childId='+node.id;
			}else{
				return false;
			}                  
	    }
	
	});
	
	$("#reachareas2_form_export").on("click", function(){
		var sValidate = $("#reachareas2_form").form("validate");
		if(!sValidate){
			return;
		}
		var masLoc = $("#reachareas2_form").find("input[name='masLoc']").val();
		var nodes = $('#divitionTree').tree('getChecked');
		var dataArray =[];
		if(nodes.length<1){
			$.messager.alert("提示", "请选择区域范围！");
			return;
		}
        for (var i = 0; i < nodes.length; i++) {
            dataArray.push(nodes[i].id+","+nodes[i].divLevel);
        }
        
        var str = jQuery.param({divisionCodes:dataArray});//jquery用这种方式传数组
        
        $('#reachareas2_form').form('submit', {
        	url: "../lsp/exportFromTree.json?"+str,
    		method: "post",
    		onSubmit: function(){
    			return true;
    		},
    		success: function(data){
    			var _data = $.parseJSON(data);
    			$.messager.alert("提示", _data);
    		}
    	});
		
	});
	
	
	$("#reachareas2_form_import").on("click", function(){
		var file = $("#file").val();
	    if (file == "") {
	        $.messager.alert('Excel批量导入', '请选择将要上传的文件!');        
	    }
	    
	    else {
	    	var strtype = file.substr(file.length-4,file.length-1);
	    	strtype = strtype.toUpperCase();
	        if (strtype != '.XLS' && strtype != 'XLSX') {
	            $.messager.alert('Excel批量导入','文件类型不正确，请选择EXCEL文件!'); 
	        }
	        else { 
	        	$('#import_form').form('submit', {
	        		url: "../lsp/import.json",
	        		method: "post",
	        		onSubmit: function(){
	        			return true;
	        		},
	        		success: function(data){
	        			var _data = $.parseJSON(data);
	        			$.messager.alert("提示", _data);
	        		}
	        	});
	            
	        }
	    }    
		
	});
	
});

