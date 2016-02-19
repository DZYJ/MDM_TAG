package com.multigold.mdm.service.impl.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.multigold.common.exception.BusinessException;
import com.multigold.common.util.LogTracking;
import com.multigold.mdm.service.api.mq.MessageProduceService;

@Component
//@Service
public class MessageProduceServiceImpl implements MessageProduceService{
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(String queueName, String message, boolean pubSubDomain) {
		try {
			LogTracking.debugLog(MessageProduceServiceImpl.class, "sendMessage > message", message);
			
			jmsTemplate.setPubSubDomain(pubSubDomain);
			//设置消息
			jmsTemplate.send(queueName, this.createTxtMsg(message));
			
			LogTracking.debugLog(MessageProduceServiceImpl.class, "sendMessage", "end...");
		} catch (Exception e) {
			LogTracking.errorLog(MessageProduceServiceImpl.class, "sendMessage", e);
			throw new BusinessException(e);
		}

	}
	
	public void sendMessage(String queueName, String message) {
		this.sendMessage(queueName, message, false);
	}

	private MessageCreator createTxtMsg(final String msg) {
		return new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage();
				textMessage.setText(msg);
				return textMessage;
			}
		};
	}

}
