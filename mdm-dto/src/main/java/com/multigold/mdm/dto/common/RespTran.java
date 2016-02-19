package com.multigold.mdm.dto.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyang
 * 
 */
public class RespTran implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String requestId;			//交易请求ID 

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
