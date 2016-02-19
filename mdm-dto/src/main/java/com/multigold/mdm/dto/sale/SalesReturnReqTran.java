package com.multigold.mdm.dto.sale;
import java.util.ArrayList;
import java.util.List;

import com.multigold.mdm.dto.common.RespTran;

public class SalesReturnReqTran extends RespTran {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4871321086884634790L;

	private List<SalesReturnReqDetails> details = new ArrayList<SalesReturnReqDetails>();

	public List<SalesReturnReqDetails> getDetails() {
		return details;
	}

	public void setDetails(List<SalesReturnReqDetails> details) {
		this.details = details;
	}
	
}
