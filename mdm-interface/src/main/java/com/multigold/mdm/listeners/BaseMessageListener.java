package com.multigold.mdm.listeners;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.multigold.common.util.LogTracking;

public abstract class BaseMessageListener implements MessageListener {
	

	@Override
	public void onMessage(Message message) {
		String msg = "";
		try {
			if (message instanceof TextMessage) {
				msg = ((TextMessage) message).getText();
				LogTracking.debugLog(this.getClass(), "onMessage", msg);
				// 处理消息
				processMessage(msg);

			} else {
				LogTracking.logWarn("Message is not fitable Type...",this.getClass());
			}
		} catch (Exception e) {
			LogTracking.errorLog(this.getClass(), "onMessage", e);
		} finally {
			// 备份报文
//			saveMessage(msg);
		}
	}

	/**
	 * 备份报文
	 * 
	 * @param msg
	 * @throws
	 */
	public abstract void saveMessage(String msg); 
	/**
	 * 消息处理
	 * 
	 * @param msg
	 * @throws
	 */
	public abstract void processMessage(String msg);
	
}
