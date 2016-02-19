package com.multigold.mdm.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.multigold.common.util.LogTracking;
import com.multigold.t6.mq.model.bean.PodetailsBean;
import com.multigold.t6.mq.model.beanToXml.PurchaseOrderToXml;
import com.multigold.t6.mq.model.send.PodetailsEntity;
import com.multigold.t6.mq.model.send.ProductPoEntity;
import com.multigold.t6.mq.model.purchase_order.Purchase_order;
import com.multigold.t6.service.api.send.PodetailsService;

/**
 * 定时job
 * 发送消息到队列T1.3 采购订单、采购退货单 。 T6创建采购订单（或采购退货单）后，将采购订单（或退货单）信息传递到OMS。
 * @author musaisai
 *
 */
@Component("poSendJob")  
public class PoSendJob extends BaseSendJob{
	private static final Log log = LogFactory.getLog(PoSendJob.class);
	
	@Autowired
	private PodetailsService podetailsService;
	
	public void excute(){
		LogTracking.debugLog(VenSendJob.class, "excute", "entry...");
		try{
			System.out.println("Job>>>>");
			//TODO   需改队列名字
			List<String> poList = podetailsService.getPOAll();
			System.out.println("podetail BEAN TEST END!");
			if(poList!=null && !poList.isEmpty()){
				for(String poId :poList){
					// 商品信息（款式码）转换
					List<PodetailsEntity> pelist = podetailsService.getPODetailsById(poId);
					if(pelist.isEmpty() || pelist.size()<1) break;
					PodetailsBean pb = new PodetailsBean();
					Purchase_order pm = pb.setPodetails(pelist);
					String xml = PurchaseOrderToXml.PurchaseOrderToXml(pm);
					msgSend("queue.name.send.po", xml);
					//记录推送记录
					podetailsService.add(pelist.get(0));
					//更新项目
					podetailsService.updateFItemForPO(pelist.get(0));
				}
			}
			//委外订单
			List<String> omlist = podetailsService.getOMAll();
			if(omlist!=null && !omlist.isEmpty()){
				for(String poId :omlist){
					// 商品信息（款式码）转换
					List<ProductPoEntity> oelist = podetailsService.getOMDetailsById(poId);
					if(oelist.isEmpty() || oelist.size()<1) break;
					PodetailsBean pb = new PodetailsBean();
					Purchase_order pm = pb.setOmProductPo(oelist);
					String xml = PurchaseOrderToXml.PurchaseOrderToXml(pm);
					msgSend("queue.name.send.po", xml);
					//记录推送记录
					podetailsService.addProductPO(oelist.get(0));
					//更新项目
					podetailsService.updateFItemForOM(oelist.get(0));
				}
			}		
			
		}catch(Exception e){
			LogTracking.errorLog(PoSendJob.class, "excute", e);
		}
		LogTracking.debugLog(PoSendJob.class, "excute", "exit...");
	}
	

}
