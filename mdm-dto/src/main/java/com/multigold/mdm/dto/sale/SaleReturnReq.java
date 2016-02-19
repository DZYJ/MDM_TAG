package com.multigold.mdm.dto.sale;

import java.util.List;

import com.multigold.mdm.dto.common.Req;

public class SaleReturnReq extends Req {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6157826871244349702L;
	private List<SalesReturnReqTran> trans;
	public List<SalesReturnReqTran> getTrans() {
		return trans;
	}
	public void setTrans(List<SalesReturnReqTran> trans) {
		this.trans = trans;
	}
	
	
}
