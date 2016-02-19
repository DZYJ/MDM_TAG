package com.multigold.mdm.listeners;

import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multigold.common.util.DateToString;
import com.multigold.common.util.LogTracking;
import com.multigold.mdm.dto.mq.MessageTransactions;
import com.multigold.model.Ack;
import com.multigold.model.AckToXml;
import com.multigold.t6.mq.model.TblInterfaceHistory;
import com.multigold.t6.mq.model.inventorytransaction.InventoryTransaction;
import com.multigold.t6.mq.model.inventorytransaction.InventoryTransactions;
import com.multigold.t6.mq.model.xmlToBean.XmlToInventoryTransaction;
import com.multigold.t6.service.api.mq.MqInvReceiverService;
import com.multigold.t6.util.MqLog;

@Component(value = "omsInvTransListener")
public class OMSInvTransListener extends BaseMessageListener{
	
	private MessageTransactions transaction;
	
	@Autowired
	MqInvReceiverService mqInvReceiverService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void processMessage(String xml) {
		try {
			String MQ_QUEUE_NAME = "库存移动";
			List<InventoryTransaction> inventory_transactions=null;
			InventoryTransactions inventoryTransactions = null;
			xml = xml.replace("&", "&amp;"); 
			XmlToInventoryTransaction xmlToInventoryTransaction = new XmlToInventoryTransaction();
			inventoryTransactions = xmlToInventoryTransaction
					.xmlToInventoryTransaction(xml); // 根据Xml生成InventoryTransactions
			inventory_transactions = inventoryTransactions
					.getInventory_transactions();// 库存交易
			TblInterfaceHistory tblInterfaceHistory = mqInvReceiverService.getTblInterfaceMqHistory(inventoryTransactions.getTransaction_id());
			if(tblInterfaceHistory==null){
				for (InventoryTransaction it : inventory_transactions) {
					mqInvReceiverService.insertRdrecord(it);
				}
				tblInterfaceHistory = MqLog.writeFile(inventoryTransactions.getTransaction_id(),xml, MqLog.XML_RIGHT, "inv_log",MQ_QUEUE_NAME,null);
				mqInvReceiverService.insertTblInterfaceMqHistory(tblInterfaceHistory);
			}else{
				tblInterfaceHistory = MqLog.writeFile(inventoryTransactions.getTransaction_id(),xml, MqLog.XML_ERROR, "inv_log",MQ_QUEUE_NAME,"报文重复发送！");
				mqInvReceiverService.insertTblInterfaceMqHistory(tblInterfaceHistory);
			}
		} catch (Exception e) {
			LogTracking.errorLog(OMSInvTransListener.class, "processMessage", e);
			transaction.setException(e.getMessage());
		}
	}

	@Override
	public void saveMessage(String msg) {
//		transaction.setMessage(msg);
		//存日志表
		//messageTransactionsService.saveMessageTransactions(transaction);
	}
	protected QueueConnection connection = null;
	protected QueueSession session = null;
	public void receive() throws Exception{
		boolean transacted = true;
 		connection = JMSResourceManager.getInstance().getQueueConnection();
 	
 		if(session==null) {
 			session = connection.createQueueSession(transacted, Session.AUTO_ACKNOWLEDGE);;
 		}
	}
	
	public void sendAck(String transaction_id,String mqName,String flag){
		if(connection==null){
			try {
				receive();
				AckSender ackSender = new AckSender();
				Ack ack = new Ack();
				ack.setTransaction_id(transaction_id);   //获取XML ID
				ack.setTransaction_date(new DateToString().dateToString(new Date())); //获取当前时间，字符串
				ack.setBuid("8270"); //设置国家代码
				ack.setPartner_code("PORTAL"); //合作商编号
				ack.setReceiver_id("DRG"); //接收方编号
				ack.setReceiver_name("DRAGON"); //接收方编号
				ack.setMessage_type("sales_order"); //XML 文件类型
				ack.setSuccess_flag(flag); //成功与否标志
				String ackXml = new AckToXml().ackToXml(ack); // ack转换成Xml
				ackSender.sendAck(connection,mqName, ackXml);
			} catch (Exception e) {
				LogTracking.errorLog(OMSInvTransListener.class, "建立MQ连接失败！", e);
			}finally{
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
	
}
