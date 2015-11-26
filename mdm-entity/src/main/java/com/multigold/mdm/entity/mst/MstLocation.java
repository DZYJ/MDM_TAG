package com.multigold.mdm.entity.mst;

import com.multigold.common.entity.BaseEntity;

/**
 * 
 * @author liangdingding
 *
 */
public class MstLocation extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Mas_Loc
	 * 仓库
	 */
	private String masLoc;
	/**
	 * Bucket_Code
	 * 库区
	 */
	private String bucketCode;
	/**
	 * Location
	 * 承运商对应的SAP库区代码
	 */
	private String location;
	/**
	 * Lsp_Code
	 * 承运商
	 */
	private String lspCode;
	/**
	 * 承运商名称
	 * 
	 */
	private String lspName;
	/**
	 * PARENT_MAS_LOC
	 * 
	 * @return
	 */
	private String parentMasLoc;
	
	private String orderBy = "MML.MAS_LOC";
	
	
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getLspName() {
		return lspName;
	}
	public void setLspName(String lspName) {
		this.lspName = lspName;
	}
	public String getParentMasLoc() {
		return parentMasLoc;
	}
	public void setParentMasLoc(String parentMasLoc) {
		this.parentMasLoc = parentMasLoc;
	}
	public String getMasLoc() {
		return masLoc;
	}
	public void setMasLoc(String masLoc) {
		this.masLoc = masLoc;
	}
	public String getBucketCode() {
		return bucketCode;
	}
	public void setBucketCode(String bucketCode) {
		this.bucketCode = bucketCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLspCode() {
		return lspCode;
	}
	public void setLspCode(String lspCode) {
		this.lspCode = lspCode;
	}

	
	
	
	
	
}
