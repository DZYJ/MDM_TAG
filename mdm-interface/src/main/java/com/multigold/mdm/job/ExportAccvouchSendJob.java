package com.multigold.mdm.job;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Component;

import com.multigold.common.util.mail.MailUtil;
import com.multigold.t6.service.api.accvouch.PoAccvouchService;
//import com.multigold.t6.service.api.accvouch.AccvouchService;

/**
 * 定时job
 * 发送消息到队列 T1.1商品信息（款式码） T6将商品主数据（款式码）发送给OMS
 * @author musaisai
 *
 */
@Component("exportAccvouchSendJob")  
public class ExportAccvouchSendJob extends BaseSendJob{
	private static final Log log = LogFactory.getLog(ExportAccvouchSendJob.class);

//	@Autowired
	private PoAccvouchService accvouchService;
	/**
	 * 导出交易凭证xml
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public void excute(){
		Map queryParam = new HashMap();
		//获取导入凭证的月份（上月）
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH);
		String toMail=null;
		String startDate =null;
		queryParam.put("iPeriod", month);
		toMail = MailUtil.getProperties().getProperty("toMail");
		queryParam.put("toMail", toMail);
		boolean isSettle = accvouchService.findIsSettle(month);
		if(isSettle){
			try {
				log.debug(month+"月已结账，"+"===========执行定时：ExportAccvouchSendJob.excute============");
				accvouchService.queryByCondition(queryParam);
			} catch (ApplicationException e) {
				// TODO 写入异常日志
				e.printStackTrace();
			}
		}else{
			log.debug(month+"月未结账！");
		}
	}
}
