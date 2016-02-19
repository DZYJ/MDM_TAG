
/**
 * yang_ziwen
 * 2010-11-23
 */

package com.multigold.model;

import java.io.Serializable;
import java.util.List;


/**
 * @author multigold_bai
 *
 */
public class Ack implements Serializable {

	public Ack() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Ack(String transactionId, String transactionDate, String buid,
			String partnerCode, String receiverId, String receiverName,
			String messageType, String successFlag,
			List<ExceptionDetail> exceptionDetails) {
		super();
		transaction_id = transactionId;
		transaction_date = transactionDate;
		this.buid = buid;
		partner_code = partnerCode;
		receiver_id = receiverId;
		receiver_name = receiverName;
		message_type = messageType;
		success_flag = successFlag;
		exception_details = exceptionDetails;
	}



	private String transaction_id;
	private String transaction_date;
	private String buid;
	private String partner_code;
	private String receiver_id;
	private String receiver_name;
	private String message_type;
	private String success_flag;
	private List<ExceptionDetail> exception_details;
	
	
	
	public String getTransaction_id() {
		return transaction_id;
	}



	public void setTransaction_id(String transactionId) {
		transaction_id = transactionId;
	}



	public String getTransaction_date() {
		return transaction_date;
	}



	public void setTransaction_date(String transactionDate) {
		transaction_date = transactionDate;
	}



	public String getBuid() {
		return buid;
	}



	public void setBuid(String buid) {
		this.buid = buid;
	}



	public String getPartner_code() {
		return partner_code;
	}



	public void setPartner_code(String partnerCode) {
		partner_code = partnerCode;
	}



	public String getReceiver_id() {
		return receiver_id;
	}



	public void setReceiver_id(String receiverId) {
		receiver_id = receiverId;
	}



	public String getReceiver_name() {
		return receiver_name;
	}



	public void setReceiver_name(String receiverName) {
		receiver_name = receiverName;
	}



	public String getMessage_type() {
		return message_type;
	}



	public void setMessage_type(String messageType) {
		message_type = messageType;
	}



	public String getSuccess_flag() {
		return success_flag;
	}



	public void setSuccess_flag(String successFlag) {
		success_flag = successFlag;
	}



	public List<ExceptionDetail> getException_details() {
		return exception_details;
	}



	public void setException_details(List<ExceptionDetail> exceptionDetails) {
		exception_details = exceptionDetails;
	}





	
	
}
