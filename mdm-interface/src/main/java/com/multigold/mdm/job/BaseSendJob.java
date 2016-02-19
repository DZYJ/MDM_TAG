package com.multigold.mdm.job;

import com.multigold.common.util.PropertiesUtils;
import com.multigold.common.util.mq.MessageSender;

/**
 * 发送MQ消息的通用类
 * @author musaisai
 *
 */
public class BaseSendJob {
	
	public void msgSend(String queue ,String msg){
		String manager = PropertiesUtils.getPropValue("mq.manager");
		String hostname = PropertiesUtils.getPropValue("mq.hostname");
		String channel = PropertiesUtils.getPropValue("mq.channel");
		int port = Integer.valueOf(PropertiesUtils.getPropValue("mq.port"));
		int ccsid = Integer.valueOf(PropertiesUtils.getPropValue("mq.ccsid"));
		String user = PropertiesUtils.getPropValue("mq.user");
		String pwd = PropertiesUtils.getPropValue("mq.pwd");
		
		String msgQueue = PropertiesUtils.getPropValue(queue);
		
		MessageSender ms = new MessageSender();
		ms.send(msgQueue, msg,manager,hostname,channel,port,ccsid,user,pwd);
	}
}
