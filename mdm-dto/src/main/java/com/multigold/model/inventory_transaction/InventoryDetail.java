package com.multigold.model.inventory_transaction;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangbin 调出主库区
 */
public class InventoryDetail implements Serializable {

	public InventoryDetail() {
		super();
	}

	private static final long serialVersionUID = 1L;
	private String from_master_loc; // 调出主库区
	private String from_sub_loc; // 调出库区号
	private String from_master_loc_type; // 调出主库类型
	private String to_master_loc; // 调入主库区
	private String to_sub_loc; // 调入库区号
	private String to_master_loc_type; // 调入主库类型

	private List<Batch> batches;// 批次

	public String getFrom_master_loc() {
		return from_master_loc;
	}

	public void setFrom_master_loc(String fromMasterLoc) {
		from_master_loc = fromMasterLoc;
	}

	public String getFrom_sub_loc() {
		return from_sub_loc;
	}

	public void setFrom_sub_loc(String fromSubLoc) {
		from_sub_loc = fromSubLoc;
	}

	public String getFrom_master_loc_type() {
		return from_master_loc_type;
	}

	public void setFrom_master_loc_type(String fromMasterLocType) {
		from_master_loc_type = fromMasterLocType;
	}

	public String getTo_master_loc() {
		return to_master_loc;
	}

	public void setTo_master_loc(String toMasterLoc) {
		to_master_loc = toMasterLoc;
	}

	public String getTo_sub_loc() {
		return to_sub_loc;
	}

	public void setTo_sub_loc(String toSubLoc) {
		to_sub_loc = toSubLoc;
	}

	public String getTo_master_loc_type() {
		return to_master_loc_type;
	}

	public void setTo_master_loc_type(String toMasterLocType) {
		to_master_loc_type = toMasterLocType;
	}

	public List<Batch> getBatches() {
		return batches;
	}

	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}

}
