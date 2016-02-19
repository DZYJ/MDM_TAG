package com.multigold.mdm.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.multigold.common.util.LogTracking;
import com.multigold.t6.mq.model.DispatchEntity;
import com.multigold.t6.mq.model.Inventory;
import com.multigold.t6.mq.model.bean.DispatchBean;
import com.multigold.t6.mq.model.bean.PartMaterBean;
import com.multigold.t6.mq.model.beanToXml.PartMasterToXml;
import com.multigold.t6.mq.model.beanToXml.ProductionOrderToXml;
import com.multigold.mdm.job.MessageSender;
import com.multigold.t6.mq.model.part_master.Part_master;
import com.multigold.t6.mq.model.send.ProductionOrder;
import com.multigold.t6.service.api.send.DispatchService;
import com.multigold.t6.service.api.send.InventoryService;

/**
 * 定时job
 *  T6将商品主数据（款式码）发送给OMS
 * @author musaisai
 *
 */
@Component("inventorySendJob")  
public class InventorySendJob extends BaseSendJob{
	private static final Log log = LogFactory.getLog(InventorySendJob.class);

	@Autowired
	private InventoryService inventoryService;
	
	public void excute(){
		LogTracking.debugLog(InventorySendJob.class, "excute", "entry...");
		try{
			System.out.println("Job>>>>");
			inventoryService.insetProduct();
			//TODO   需改队列名字
			List<Inventory> itlist = inventoryService.queryAll();
			System.out.println("inventory BEAN TEST END!");
			if (itlist!=null && !itlist.isEmpty()) {
				PartMaterBean pmb = new PartMaterBean();

				Part_master pm = pmb.setPartMaster(itlist);

				MessageSender ms = new MessageSender();
				String xml = PartMasterToXml.InventoryToXml(pm);
				msgSend("queue.name.send.pm", xml);
				
				for (Inventory inv : itlist) {
					inventoryService.add(inv);
				}
}
		}catch(Exception e){
			LogTracking.errorLog(InventorySendJob.class, "excute", e);
		}
		LogTracking.debugLog(InventorySendJob.class, "excute", "exit...");
	}
	

}
