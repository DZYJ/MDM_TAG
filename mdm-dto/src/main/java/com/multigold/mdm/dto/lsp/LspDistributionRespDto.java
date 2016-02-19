package com.multigold.mdm.dto.lsp;

import com.multigold.common.entity.BaseSerializable;

/**
 * @author wangweijin
 * 承运商分配结果反馈
 */
public class LspDistributionRespDto implements BaseSerializable{

	private static final long serialVersionUID = 4050709230526830104L;
	/** 支线承运商 */
	private String lspCode;
	/** 干线承运商 */
	private String lineHaul;
	/** 分拨中心仓库 */
	private String sortingCenter;
	/** 配送时长 */
	private Double deliveryLeadTime;
	
	public String getLspCode() {
		return lspCode;
	}
	public void setLspCode(String lspCode) {
		this.lspCode = lspCode;
	}
	public String getLineHaul() {
		return lineHaul;
	}
	public void setLineHaul(String lineHaul) {
		this.lineHaul = lineHaul;
	}
	public String getSortingCenter() {
		return sortingCenter;
	}
	public void setSortingCenter(String sortingCenter) {
		this.sortingCenter = sortingCenter;
	}
	public Double getDeliveryLeadTime() {
		return deliveryLeadTime;
	}
	public void setDeliveryLeadTime(Double deliveryLeadTime) {
		this.deliveryLeadTime = deliveryLeadTime;
	}
}
