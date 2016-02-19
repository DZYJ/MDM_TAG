package com.multigold.mdm.job;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.v6.jms.internal.JMSC;
import com.multigold.mdm.job.MQUtil;
import com.multigold.mdm.job.MqLog;

public class MessageSender {
	public static Log log = LogFactory.getLog(MqLog.class);
	public void send(String qName,String xml){
		String MQ_MANAGER = MQUtil.getProperties().getProperty("queue.manager.send");  		 
	    String MQ_HOST_NAME = MQUtil.getProperties().getProperty("queue.manager.host.send");
	    final String MQ_CHANNEL = MQUtil.getProperties().getProperty("app.mq.channel");
	    final int MQ_PROT = Integer.parseInt( MQUtil.getProperties().getProperty("app.mq.port")); 
	    final int MQ_CCSID = 1208;
	    String  user = MQUtil.getProperties().getProperty("app.mq.user"); 	
	    String  password = MQUtil.getProperties().getProperty("app.mq.pwd"); 	
	    
	    
	    QueueConnection connection = null;
	    QueueSession session = null;
	    
		MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
		try {
			factory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
			factory.setQueueManager(MQ_MANAGER);
			factory.setHostName(MQ_HOST_NAME);
			factory.setChannel(MQ_CHANNEL);
			factory.setPort(MQ_PROT);
			factory.setCCSID(MQ_CCSID);
			connection = factory.createQueueConnection(user,password);
			connection.start();
			boolean transacted = false;
			session = connection.createQueueSession(transacted,Session.AUTO_ACKNOWLEDGE);
			Queue ioQueue;
			ioQueue = session.createQueue( qName );
			
			TextMessage outMessage1 = session.createTextMessage();
			
			xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+xml;
			outMessage1.setText(xml);
			
			QueueSender queueSender = session.createSender(ioQueue);
			queueSender.send(outMessage1);
			System.out.println("send message is ");
			MqLog.writeFile(xml, MqLog.XML_RIGHT+qName, "send_log");
			//session.commit();
//			session.close();
//			connection.close();
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(session!=null)
				session.close();
				if(connection!=null)
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
		
}


