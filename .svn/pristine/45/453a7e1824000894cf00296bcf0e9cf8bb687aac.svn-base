package com.multigold.mdm.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multigold.common.util.PropertiesUtils;
import com.multigold.t6.service.api.accvouch.HjbAccvouchService;
import com.multigold.t6.service.api.hjb.HjbService;
import com.multigold.t6.tsix.hjb.HjbGLReq;
import com.multigold.t6.tsix.hjb.HjbGLResp;

@Component("hjbAcc")  
public class HjbAccJob extends BaseSendJob{
	@Autowired
	public HjbAccvouchService hjbAccvouchService;
	@Autowired
	public HjbService  hjbService;
	public void createHjbAccvouch(){
			
		//step 2:查询日志表，生成凭证
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, -1);
			String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())+" 00:00:00";
//				List<Map<String,Object>> resultMap = (List<Map<String,Object>>)hjbService.queryByTransactionId(request.getTransactionId());
			//查询前一日黄金币总账信息
			List<Map<String,Object>> resultMap = (List<Map<String,Object>>)hjbService.queryByDateStr(dateStr);
//				addHjbCl003(resultMap,request);
			Date dbill_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
			hjbAccvouchService.insertHjbAccvouch(resultMap,null,dbill_date);
			
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("生成凭证失败!",e);
		}
	}
}
