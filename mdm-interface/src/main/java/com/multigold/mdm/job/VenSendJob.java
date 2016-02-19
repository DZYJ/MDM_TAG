package com.multigold.mdm.job;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.multigold.common.util.LogTracking;
import com.multigold.t6.mq.model.vendor.Vendor;
import com.multigold.t6.mq.model.bean.VendorBean;
import com.multigold.t6.mq.model.beanToXml.VendorToXml;
import com.multigold.t6.mq.model.send.VendorEntity;
import com.multigold.t6.service.api.send.VendorService;

/**
 * 定时job
 * 发送消息到队列 T1.2 供应商主数据 。 T6将供应商主数据发送给OMS。
 * @author musaisai
 *
 */
@Component("venSendJob")  
public class VenSendJob extends BaseSendJob{
	private static final Log log = LogFactory.getLog(VenSendJob.class);

	@Autowired
	private VendorService vendorService;
	
	public void excute(){
		LogTracking.debugLog(VenSendJob.class, "excute", "entry...");
		try{
			System.out.println("Job>>>>");
			//TODO   需改队列名字
			List<VendorEntity> vlist = vendorService.queryAll();
			System.out.println("vendoer BEAN TEST END!");
			if (vlist!=null && !vlist.isEmpty()) {
				// 商品信息（款式码）转换
				VendorBean vb = new VendorBean();
				Vendor pm = vb.setVendor(vlist);
				String xml = VendorToXml.VendorToXml(pm);
				msgSend("queue.name.send.vendor",xml);
//				ms.send(MQUtil.getProperties().getProperty("queue.name.send.vendor"), xml);

				for (VendorEntity ve : vlist) {
					vendorService.add(ve);
				}
			}
		}catch(Exception e){
			LogTracking.errorLog(VenSendJob.class, "excute", e);
		}
		LogTracking.debugLog(VenSendJob.class, "excute", "exit...");
	}
	

}
