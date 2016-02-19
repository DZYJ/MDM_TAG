package com.multigold.mdm.dto.sale;

import java.util.List;

import com.multigold.mdm.dto.common.Req;

/**
 * 销售交易接口
 * @author zhanghua
 *
 */
public class SaleTransactionReq extends Req{

	private static final long serialVersionUID = -2209649418685010636L;
	private List<SaleTransactionTran> trans;
	public List<SaleTransactionTran> getTrans() {
		return trans;
	}
	public void setTrans(List<SaleTransactionTran> trans) {
		this.trans = trans;
	}
	
}
