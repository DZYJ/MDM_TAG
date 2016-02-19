package com.multigold.mdm.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multigold.common.util.LogTracking;
import com.multigold.t6.entity.accvouch.AccvouchEntity;
import com.multigold.t6.entity.mq.model.RdrecordEntity;
import com.multigold.t6.service.api.accvouch.OmAccvouchService;
import com.multigold.t6.service.api.accvouch.PoAccvouchService;
import com.multigold.t6.service.api.po.RdrecordService;
import com.multigold.t6.vo.AccvouchConfig;

/**
 * 定时job
 * 发送消息到队列T1.3 采购订单、采购退货单 。 T6创建采购订单（或采购退货单）后，将采购订单（或退货单）信息传递到OMS。
 * @author musaisai
 *
 */
@Component("createRdrecordAccvouch")  
public class RdrecordAccvouchJob extends BaseSendJob{
	private static final Log log = LogFactory.getLog(RdrecordAccvouchJob.class);
	
	@Autowired
	PoAccvouchService<AccvouchEntity,String> poAccvouchService;
	@Autowired
	OmAccvouchService<AccvouchEntity,String> omAccvouchService;
	@Autowired
	RdrecordService<RdrecordEntity,String> rdrecordService; 
	public void excute(){
		LogTracking.debugLog(VenSendJob.class, "excute", "createRdrecordAccvouch...");
		//采购
		
		try {
			List<String> ids = queryByAccId("01");
			if(ids!=null && ids.size()>0){
				poAccvouchService.insertAccvouch(ids, AccvouchConfig.PO_TENTATIVE);
			}
		} catch (Exception e) {
			log.error("生成采购凭证失败",e);
		}
		//委外
		try {
			List<String> ids = queryByAccId("10");
			if(ids!=null && ids.size()>0){
				omAccvouchService.insertAccvouch(ids, AccvouchConfig.OM_TENTATIVE);
			}
		} catch (Exception e) {
			log.error("生成委外凭证失败",e);
		}
	}
	private List<String> queryByAccId(String type){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("cVouchType", type);
		List<Integer> ids = rdrecordService.queryByAccId(paramMap);
		List<String> idsStr = new ArrayList<String>();
		for(Integer id:ids){
			idsStr.add(id+"");
		}
		return idsStr;
	}

}
