package com.multigold.mdm.job;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.multigold.common.util.LogTracking;
import com.multigold.t6.mq.model.beanToXml.TNTToXml;
import com.multigold.mdm.job.MessageSender;
import com.multigold.t6.mq.model.send.TntEntity;
import com.multigold.t6.service.api.send.TntService;

/**
 * 定时job
 * 发送消息到队列T1.5 批发SO取消
 * @author musaisai
 *
 */
@Component("tntSendJob")  
public class TntSendJob extends BaseSendJob{
	private static final Log log = LogFactory.getLog(TntSendJob.class);

	@Autowired
	private TntService tntService;
	
	public int excute(){
		LogTracking.debugLog(TntSendJob.class, "excute", "entry...");
		List<String> idList = tntService.selectALLSOClosed();
		try{
			System.out.println("Job>>>>");
			//TODO   需改队列名字
			System.out.println("tnt BEAN TEST END!");
			if (idList!=null && !idList.isEmpty()) {
				for(String id :idList){
				TntEntity tntEn = new TntEntity();
				tntEn.setId(id);
				String xml = TNTToXml.getTntXml(tntEn);
				msgSend("queue.name.send.tnt", xml);
				tntService.updateInfSOClosed(tntEn);
			}
				}
		}catch(Exception e){
			LogTracking.errorLog(TntSendJob.class, "excute", e);
		}
		LogTracking.debugLog(TntSendJob.class, "excute", "exit...");
		return idList.size();
	}
	

}
