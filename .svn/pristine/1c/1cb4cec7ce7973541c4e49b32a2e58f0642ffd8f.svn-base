package com.multigold.mdm.listeners;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.QueueConnection;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.v6.jms.internal.JMSC;
//import com.multigold.util.mq.MQUtil;
import com.multigold.t6.util.MqUtil;

/**
 * 
 * @author wangzhengwu
 * @version 1.0
 * @date 2010-12-02
 * 
 */
public class JMSResourceManager {

	public static Properties props = MqUtil.getProperties();
	private static JMSResourceManager instance = null;
	private static MQQueueConnectionFactory factory = null;
	private static QueueConnection queueConn = null;
	public final static Boolean noAutoCommit = true;
	private Boolean isReConnection = true;

	private JMSResourceManager(){
		
	}
	
	public static JMSResourceManager getInstance(){
		if(null == instance){
			instance = new JMSResourceManager();
		}
		return instance;
	}
	
	public MQQueueConnectionFactory getMQQueueConnectionFactory() throws JMSException {
		if (null == factory) {
			factory = new MQQueueConnectionFactory();
			factory.setQueueManager(props.getProperty("queue.manager.get"));
			factory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
			factory.setHostName(props.getProperty("queue.manager.host.get"));
			factory.setChannel(props.getProperty("app.mq.channel"));
			factory.setPort(Integer.valueOf(props.getProperty("app.mq.port")));
			factory.setCCSID(Integer.valueOf(props.getProperty("queue.manager.ccsid")));
//			factory.setQueueManager("QM_multigold01");
//			factory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
//			factory.setHostName("10.57.8.22");
//			factory.setChannel("C_SVR_multigold01");
//			factory.setPort(1416);
//			factory.setCCSID(1208);
		}
		return factory;
	}

	/**
	 * @param mqParms
	 * @return
	 * @throws JMSException
	 */
	public QueueConnection getQueueConnection()  {
		try {
			if(null==factory) {
				factory = getMQQueueConnectionFactory();
			}
			if (null == queueConn) {
				queueConn = factory.createQueueConnection(props.getProperty("app.mq.user"), props.getProperty("app.mq.pwd"));
				//queueConn = factory.createQueueConnection("GMQ", "multigold_mqm");
				queueConn.start();
			}
		} catch (JMSException e) {
			isReConnection = false;
			e.printStackTrace();
		}
		System.out.println("queueConn============"+queueConn);
		return queueConn;
	}
	
	public void reQueueConnection()  {
		if(null==queueConn && isReConnection==false){
			try {
				wait(2000);
				getQueueConnection();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

//	public QueueSender createQueueSender(String queueName, boolean autoCommit)
//			throws JMSException {
//		if (autoCommit) {
//			session = queueConn.createQueueSession(autoCommit, Session.AUTO_ACKNOWLEDGE);
//		} else {
//			session = queueConn.createQueueSession(autoCommit, Session.CLIENT_ACKNOWLEDGE);
//		}
//		Queue queue = session.createQueue(queueName);
//		return session.createSender(queue);
//	}

//	public QueueReceiver createQueueReceiver(String queueName, boolean autoCommit) throws JMSException {
//		QueueSession session = null;
//		if (autoCommit) {
//			session = queueConn.createQueueSession(autoCommit, Session.AUTO_ACKNOWLEDGE);
//		} else {
//			session = queueConn.createQueueSession(autoCommit, Session.CLIENT_ACKNOWLEDGE);
//		}
//		Queue queue = session.createQueue(queueName);
//		return session.createReceiver(queue);
//	}

//	/**
//	 * 
//	 * @param queueName
//	 * @param autoCommit �Զ��ύ true
//	 * @param msg
//	 * @throws JMSException
//	 */
//	public void sendMessage(String queueName, boolean autoCommit, Message msg){
//		QueueSender queueSender = null;
//		try {
//			queueSender = this.createQueueSender(queueName, autoCommit);
//		} catch (JMSException e1) {
//			e1.printStackTrace();
//		}
//		if(! autoCommit){
//			try {
//				queueSender.send(msg);
//				session.commit();
//			} catch (JMSException e) {
//				try {
//					session.rollback();
//				} catch (JMSException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}		
//	}

//	/**
//	 * @param queueName
//	 * @param autoCommit �Զ��ύ true
//	 * @return
//	 * @throws JMSException 
//	 */
//	public Message receiveMessage(String queueName, boolean autoCommit) throws JMSException {
//		QueueReceiver queueReceiver = null;
//		Message msg = null;
//		queueReceiver = this.createQueueReceiver(queueName, autoCommit);
//		msg = queueReceiver.receive();
//		return msg;
//	}
//	
//	public void closeSession() throws JMSException{
//		if(null != session)
//		session.close();
//	}
	
	public void closeConnection() throws JMSException{
		if(null != queueConn)
		queueConn.close();
		queueConn=null;
	}
	
	public void destoryFactory(){
		try {
			closeConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		factory = null;
	}
}