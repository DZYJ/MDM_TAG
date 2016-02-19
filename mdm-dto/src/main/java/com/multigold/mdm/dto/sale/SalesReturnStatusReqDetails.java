package com.multigold.mdm.dto.sale;

import java.io.Serializable;
import java.util.Date;

public class SalesReturnStatusReqDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5518382838862424338L;
	private String orderNum;		//退单订单号	
	private String refundStatus;	//退款状态		
	private String traceNum;		//交易流水号
	private String operator;		//操作人
	private String operationTime;		//操作时间
	private String remark;			//备注
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getTraceNum() {
		return traceNum;
	}
	public void setTraceNum(String traceNum) {
		this.traceNum = traceNum;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
