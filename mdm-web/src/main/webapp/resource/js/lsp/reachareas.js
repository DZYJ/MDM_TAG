$(function() {
	/*初始化配送区域grid高度*/
	$("#easyui-panel").height($(window).height()-123);
	
	$("#reachareas1_form_export").on("click", function(){
		var sValidate = $("#reachareas1_form").form("validate");
		if(!sValidate){
			return;
		}
		
		$('#reachareas1_form').form('submit', {
    		url: "../lsp/export.json",
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
	
	$("#reachareas1_form_import").on("click", function(){
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
	
	$.ajax({
		url:'../masLoc/getMasLocListCombobox.json',
		cache:false,
	    type:"get",
	    error: function () {//请求失败处理函数
		      $.messager.alert("提示", "仓库请求失败!");
		},
	    dataType:"json",
	    success:function(data){
	        $("#masLoc_id").combobox({
	        	data:data
	        });	
	    }
	});
	
	$.ajax({
		url:'../division/getFirstLevelList.json',
		cache:false,
	    type:"get",
	    error: function () {//请求失败处理函数
		      $.messager.alert("提示", "一级区域请求失败!");
		},
	    dataType:"json",
	    success:function(data){
	        $("#firstLevel").combobox({
	        	data:data,
	        	onChange:function(option){
	        		$.ajax({
	        			url:'../division/getChildListByPCode.json?parent_division_code='+option,
	        			cache:false,
	        		    type:"get",
	        		    error: function () {//请求失败处理函数
	        			      $.messager.alert("提示", "请求失败!");
	        			},
	        		    dataType:"json",
	        		    success:function(data){
	        		    	 $("#secondLevel").combobox({
	        			        	data:data,
	        			        	onChange:function(option){
	        			        		$.ajax({
	        			        			url:'../division/getChildListByPCode.json?parent_division_code='+option,
	        			        			cache:false,
	        			        		    type:"get",
	        			        		    error: function () {//请求失败处理函数
	        			        			      $.messager.alert("提示", "二级区域请求失败!");
	        			        			},
	        			        		    dataType:"json",
	        			        		    success:function(data){
	        			        		    	$("#thirdLevel").combobox({
	        		        			        	data:data,
	        		        			        	onChange:function(option){
	        		        			        		$.ajax({
	        		        			        			url:'../division/getChildListByPCode.json?parent_division_code='+option,
	        		        			        			cache:false,
	        		        			        		    type:"get",
	        		        			        		    error: function () {//请求失败处理函数
	        		        			        			      $.messager.alert("提示", "三级区域请求失败!");
	        		        			        			},
	        		        			        		    dataType:"json",
	        		        			        		    success:function(data){
	        		        			        		    	$("#fourthLevel").combobox({
	        		        		        			        	data:data
	        		        		        			        });	// end fourthLevel
	        		        			        		    }
	        		        			        		});
	        		        			        	}
	        		        			        });	// end thirdLevel
	        			        		    }
	        			        		});
	        			        	}
	        			        });	// end secondLevel
	        		      }
	        		});
	        	}// end first onchange
	        });	
	    }
	});
	
});
