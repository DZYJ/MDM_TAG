$(function() {
	/*初始化承运商信息grid高度*/
	$("#carrierInfo_grid").height($(window).height()-120);
	
	$.ajax({
		url:'../lspCarrier/getLspListCombobox.json',
		cache:false,
	    type:"get",
	    error: function () {//请求失败处理函数
		      $.messager.alert("提示", "获取承运商信息失败！");
		},
		dataType:"json",
	    success:function(data){
	    	
	        $("#lspCode").combobox({
	        	data:data,
	        	onChange:function(option){
	        		$.ajax({
	        			url:'../lspDayHalfInfo/queryDayHalf.json?lspCode='+option,
	        			cache:false,
	        		    type:"post",
	        		    error: function () {//请求失败处理函数
	        		    	$.messager.alert("提示", "半日达信息查询失败！");
	        			},
	        		    dataType:"json",
	        		    success:function(data){
	        		    	if(data.length>0){
	            		    	$("#exFromDate1").val(data[0].exFromDate);
	            		    	$("#exToDate1").val(data[0].exToDate);
	            		    	$("#dlDate1").val(data[0].dlDate);
	            		    	if(data[0].interDayFlg=='Y'){
	            		    		$("#interDayFlg1").attr("checked", true);
	            		    	}
	            		    	if(data[0].nextDayFlg=='Y'){
	            		    		$("#nextDayFlg1").attr("checked", true);
	            		    	}
	            		    	if(data[0].isEffectiveFlg=='Y'){
	            		    		$("#isEffectiveFlg1").attr("checked", true);
	            		    	}
	            		    	
	            		    	$("#exFromDate2").val(data[1].exFromDate);
	            		    	$("#exToDate2").val(data[1].exToDate);
	            		    	$("#dlDate2").val(data[1].dlDate);
	            		    	if(data[1].interDayFlg=='Y'){
	            		    		$("#interDayFlg2").attr("checked", true);
	            		    	}
	            		    	if(data[1].nextDayFlg=='Y'){
	            		    		$("#nextDayFlg2").attr("checked", true);
	            		    	}
	            		    	if(data[1].isEffectiveFlg=='Y'){
	            		    		$("#isEffectiveFlg2").attr("checked", true);
	            		    	}
	        		    	}else{
	        		    		$("#dayHalf_form").form("clear");
	        		    		//$("#dayHalf_form").find("input[type=checkbox]").prop("checked", false);
	        		    		$.messager.alert("提示", "无数据请配置！");
	        		    	}
	        		    }
	        		});
	        	}
	        });	
	    }
	});
	
	
	$("#exFromDate1").mask("99:99"); //时间
	$("#exToDate1").mask("99:99");
	$("#dlDate1").mask("99:99");
	$("#exFromDate2").mask("99:99"); //时间
	$("#exToDate2").mask("99:99");
	$("#dlDate2").mask("99:99");

	/*查询grid*/
	$("#dayHalf_submit").on("click", function(){
		
		var sValidate = $("#dayHalf_serch_form").form("validate");
		if(!sValidate){
			return;
		}
		var bValidate = $("#dayHalf_form").form("validate");
		if(!bValidate){
			return;
		}
		var arrays="";
		var exFromDate1 = $("#exFromDate1").val();
		arrays = exFromDate1.split(":");
		if(arrays[0]>=24 || arrays[1]>59){
			$.messager.alert("提示", "时间不能大于23:59！");
			return false;
		}
		
		var exToDate1 = $("#exToDate1").val();
		arrays = exToDate1.split(":");
		if(arrays[0]>=24 || arrays[1]>59){
			$.messager.alert("提示", "时间不能大于23:59！");
			return false;
		}
		
		var dlDate1 = $("#dlDate1").val();
		arrays = dlDate1.split(":");
		if(arrays[0]>=24 || arrays[1]>59){
			$.messager.alert("提示", "时间不能大于23:59！");
			return false;
		}
		
		var interDayFlg1 = 'Y';
		var nextDayFlg1 = 'Y';
		var isEffectiveFlg1 = 'Y';
		if (!$('#interDayFlg1').attr('checked')) {
			interDayFlg1='N';
		}
		if (!$('#nextDayFlg1').attr('checked')) {
			nextDayFlg1='N';
		}
		if (!$('#isEffectiveFlg1').attr('checked')) {
			isEffectiveFlg1='N';
		}
		
		var exFromDate2 = $("#exFromDate2").val();
		arrays = exFromDate2.split(":");
		if(arrays[0]>=24 || arrays[1]>59){
			$.messager.alert("提示", "时间不能大于23:59！");
			return false;
		}
		
		var exToDate2 = $("#exToDate2").val();
		arrays = exToDate2.split(":");
		if(arrays[0]>=24 || arrays[1]>59){
			$.messager.alert("提示", "时间不能大于23:59！");
			return false;
		}
		
		var dlDate2 = $("#dlDate2").val();
		arrays = dlDate2.split(":");
		if(arrays[0]>=24 || arrays[1]>59){
			$.messager.alert("提示", "时间不能大于23:59！");
			return false;
		}
		
		var interDayFlg2 = 'Y';
		var nextDayFlg2 = 'Y';
		var isEffectiveFlg2 = 'Y';
		if (!$('#interDayFlg2').attr('checked')) {
			interDayFlg2='N';
		}
		if (!$('#nextDayFlg2').attr('checked')) {
			nextDayFlg2='N';
		}
		if (!$('#isEffectiveFlg2').attr('checked')) {
			isEffectiveFlg2='N';
		}
		
		var dataStr = exFromDate1+','+exToDate1+','+dlDate1+','+interDayFlg1+','+nextDayFlg1+','+isEffectiveFlg1+',1;'
		+exFromDate2+','+exToDate2+','+dlDate2+','+interDayFlg2+','+nextDayFlg2+','+isEffectiveFlg2+',2;';
		
		var lspCode = $('#lspCode').combobox('getValue');
		
		$.ajax({
			url: "../lspDayHalfInfo/saveDayHalf.json",
			data: {"dayHalfData":dataStr,"lspCode": lspCode},
			dataType: "json",
			type: "post",
			success: function(data){
				$.messager.alert("提示", data.msg);
			}
		});
		
		
	});
	
	$("#dayHalf_reset").on("click", function(){
		$("#dayHalf_form").form("clear");
	});
});

