package com.multigold.mdm.job;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Maps;
import com.multigold.common.util.LogTracking;
import com.multigold.t6.service.api.sales.SalesService;
import com.multigold.t6.vo.sales.SalesCheckVO;

/**
 * 定时job
 * 定时更新对账单核销状态
 * @author yangjun
 *
 */
@Component("updateSalesCheckStstusJob")  
public class UpdateSalesCheckStstusJob {
	private static final Log log = LogFactory.getLog(UpdateSalesCheckStstusJob.class);

	@Autowired
	private SalesService<SalesCheckVO, String> salesService;
	
	public void excute( ){
		LogTracking.debugLog(UpdateSalesCheckStstusJob.class, "excute", "entry...");
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		//查询符合更新条件的数据
		//resultMap = salesService.getSalesCheckList(map);
//		resultMap.put("checkStatus", 02);
		try{
			System.out.println("updateSalesCheckStstusJob>>>>");
			System.out.println("updateSalesCheckStstus BEAN TEST END!");
//			调用更新状态的方法(包含查询了)
			salesService.updateSalesCheckStatusAuto(resultMap);
		}catch(Exception e){
			LogTracking.errorLog(UpdateSalesCheckStstusJob.class, "excute", e);
		}
		LogTracking.debugLog(UpdateSalesCheckStstusJob.class, "excute", "exit...");
	}
	

}
