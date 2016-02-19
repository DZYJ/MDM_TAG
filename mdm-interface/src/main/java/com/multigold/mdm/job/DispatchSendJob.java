package com.multigold.mdm.job;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.multigold.common.util.LogTracking;
import com.multigold.t6.mq.model.DispatchEntity;
import com.multigold.t6.mq.model.bean.DispatchBean;
import com.multigold.t6.mq.model.beanToXml.ProductionOrderToXml;
import com.multigold.mdm.job.MessageSender;
import com.multigold.t6.mq.model.send.ProductionOrder;
import com.multigold.t6.service.api.send.DispatchService;

/**
 * 定时job
 * 发送消息到队列T1.4 批发SO
 * @author musaisai
 *
 */
@Component("dispatchSendJob")  
public class DispatchSendJob extends BaseSendJob{
	private static final Log log = LogFactory.getLog(DispatchSendJob.class);

	@Autowired
	private DispatchService dispatchService;
	
	public void excute(){
		LogTracking.debugLog(DispatchSendJob.class, "excute", "entry...");
		try{
			System.out.println("Job>>>>");
			//TODO   需改队列名字
			List<String> dlist = dispatchService.selectDispatchAll();
			System.out.println("dispatch BEAN TEST END!");
			if (dlist!=null && !dlist.isEmpty()) {
				for(String soId :dlist){
					// 商品信息（款式码）转换
					List<DispatchEntity> solist = dispatchService.getDispatchById(soId);
					if(solist.isEmpty() || solist.size()<1) break;
					DispatchBean pb = new DispatchBean();
					ProductionOrder pm = pb.setProductionOrder(solist);
					String xml = ProductionOrderToXml.ProductionOrderToXml(pm);
					msgSend("queue.name.send.so", xml);
					//记录推送记录
					dispatchService.add(solist.get(0));
				}
}
		}catch(Exception e){
			LogTracking.errorLog(DispatchSendJob.class, "excute", e);
		}
		LogTracking.debugLog(DispatchSendJob.class, "excute", "exit...");
	}
	

}
