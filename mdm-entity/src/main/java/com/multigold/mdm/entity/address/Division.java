package com.multigold.mdm.entity.address;

import com.multigold.common.entity.BaseEntity;

/**
 * 
 * @author guoqiushi
 *
 */
public class Division extends BaseEntity{

	private static final long serialVersionUID = 1L;
	 
	// 业务需求模拟构造的字段
	/**
	 * 四级地址编码
	 */
	private String divisionCode4;
	/**
	 * 四级地址名称
	 */
	private String divisionName4;
	/**
	 * 一级区域编码
	 */
	private String divisionCode1;
	
	/**
	 * 一级区域名称
	 */
	private String divisionName1;
	/**
	 * 二级区域编码
	 */
	private String divisionCode2;
	/**
	 * 二级区域名称
	 */
	private String divisionName2;
	/**
	 * 三级区域编码
	 */
	private String divisionCode3;
	/**
     *  三级区域名称
     */
    private String divisionName3;
    
    //表本身结构的字段
    /**
     * 区域代码
     */
    private String divisionCode;
    
    /**
     * 父区域编码
     */
    private String parentDivisionCode;
    
    /**
     * 区域名称
     */
    private String divisionName;
    
    /**
     * 父区域名称
     */
    private String parentDivisionName;
    
	/**
	 * 区域级别
	 */
	private int divLevel; 
	/**
	 * 可卖数编码
	 */
    private String daId;
 
    /**
     * 子区域
     */
    private String subDivisionFlag;
    
    /**
     * 是否活动
     */
    private String isActivity;
    
    private String orderBy = "division_code4";
    
    private String[] header = new String[]{ "一级区域编码", "一级区域名称", "二级区域编码", "二级区域名称", "三级区域编码", "三级区域名称",  
    		"四级区域编码", "四级区域名称", "区域级别" };
	private String[] exportFieldNames = new String[]{"divisionCode1","divisionName1","divisionCode2","divisionName2","divisionCode3",
			"divisionName3","divisionCode4","divisionName4","divLevel" }; 
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getIsActivity() {
		return isActivity;
	}

	public void setIsActivity(String isActivity) {
		this.isActivity = isActivity;
	}

	public String getDivisionCode1() {
		return divisionCode1;
	}

	public void setDivisionCode1(String divisionCode1) {
		this.divisionCode1 = divisionCode1;
	}

	public String getDivisionCode2() {
		return divisionCode2;
	}

	public void setDivisionCode2(String divisionCode2) {
		this.divisionCode2 = divisionCode2;
	}

	public String getDivisionCode4() {
		return divisionCode4;
	}

	public void setDivisionCode4(String divisionCode4) {
		this.divisionCode4 = divisionCode4;
	}

	public String getDivisionCode3() {
		return divisionCode3;
	}

	public void setDivisionCode3(String divisionCode3) {
		this.divisionCode3 = divisionCode3;
	}

	public String getDivisionName4() {
		return divisionName4;
	}

	public void setDivisionName4(String divisionName4) {
		this.divisionName4 = divisionName4;
	}

	public String getDivisionName1() {
		return divisionName1;
	}

	public void setDivisionName1(String divisionName1) {
		this.divisionName1 = divisionName1;
	}

	public String getDivisionName2() {
		return divisionName2;
	}

	public void setDivisionName2(String divisionName2) {
		this.divisionName2 = divisionName2;
	}

	public String getDivisionName3() {
		return divisionName3;
	}

	public void setDivisionName3(String divisionName3) {
		this.divisionName3 = divisionName3;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getParentDivisionCode() {
		return parentDivisionCode;
	}

	public void setParentDivisionCode(String parentDivisionCode) {
		this.parentDivisionCode = parentDivisionCode;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getParentDivisionName() {
		return parentDivisionName;
	}

	public void setParentDivisionName(String parentDivisionName) {
		this.parentDivisionName = parentDivisionName;
	}

	public int getDivLevel() {
		return divLevel;
	}

	public void setDivLevel(int divLevel) {
		this.divLevel = divLevel;
	}

	public String getDaId() {
		return daId;
	}

	public void setDaId(String daId) {
		this.daId = daId;
	}

	public String getSubDivisionFlag() {
		return subDivisionFlag;
	}

	public void setSubDivisionFlag(String subDivisionFlag) {
		this.subDivisionFlag = subDivisionFlag;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public String[] getExportFieldNames() {
		return exportFieldNames;
	}

	public void setExportFieldNames(String[] exportFieldNames) {
		this.exportFieldNames = exportFieldNames;
	}
	
}
