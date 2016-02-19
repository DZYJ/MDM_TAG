package com.multigold.mdm.dto.tsix.E74;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyang
 * 
 */
public class RecoveryResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String transactionId;
	private List<RecoveryRespTran> trans = new ArrayList<RecoveryRespTran>();
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public List<RecoveryRespTran> getTrans() {
		return trans;
	}
	public void setTrans(List<RecoveryRespTran> trans) {
		this.trans = trans;
	}
	
}
