package com.multigold.mdm.dto.sale;

import java.util.List;

import com.multigold.mdm.dto.common.Req;

public class SaleReturnStatusReq extends Req{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6157826871244349702L;
	private List<SalesReturnStatusReqTran> trans;
	public List<SalesReturnStatusReqTran> getTrans() {
		return trans;
	}
	public void setTrans(List<SalesReturnStatusReqTran> trans) {
		this.trans = trans;
	}
	
	
}
