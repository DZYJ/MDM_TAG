package com.multigold.mdm.listeners;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.multigold.t6.mq.model.TblInterfaceHistory;
import com.multigold.t6.service.api.mq.LogService;
import com.multigold.t6.util.MqLog;


public class AckSender {
	@Autowired
	LogService logService;
	
	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param connection 连接
	 * @param queName 队列名称
	 * @param ackxml 消息内容
	 */
	public void sendAck(QueueConnection connection, String queName,
			String ackxml) {
		QueueSession session = null;

		try {

			boolean transacted = false;
			session = connection.createQueueSession(transacted,
					Session.AUTO_ACKNOWLEDGE);

			Queue ioQueue;
			ioQueue = session.createQueue(queName);

			TextMessage outMessage = session.createTextMessage();

			QueueSender queueSender = session.createSender(ioQueue);
			outMessage.setText(ackxml);
			queueSender.send(outMessage);
			System.out.println("message is sended");
			System.out.println(ackxml);
			
			MqLog.writeFile(ackxml, MqLog.XML_RIGHT, "send_ack_log");
			// session.commit();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
