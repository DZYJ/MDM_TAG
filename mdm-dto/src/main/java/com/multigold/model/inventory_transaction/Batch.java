package com.multigold.model.inventory_transaction;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangbin 批次
 */
public class Batch implements Serializable {

	public Batch() {
		super();
	}

	private static final long serialVersionUID = 1L;
	private String batch_id; // 批次号码
	private String vendor_code; // 商品供应商
	private String vendor_loc; // 供应商地点

	private List<Detail> details; // 销售订单

	public String getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(String batchId) {
		batch_id = batchId;
	}

	public String getVendor_code() {
		return vendor_code;
	}

	public void setVendor_code(String vendorCode) {
		vendor_code = vendorCode;
	}

	public String getVendor_loc() {
		return vendor_loc;
	}

	public void setVendor_loc(String vendorLoc) {
		vendor_loc = vendorLoc;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}
}
