package com.multigold.mdm.dto.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Req  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String transactionId;	//讯息交易ID 
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
