/**
 * 
 */
package com.multigold.mdm.entity.mst;

import com.multigold.common.entity.BaseEntity;

/**
 * @author mayanhu
 * 配送区域配置
 *
 */
public class HubProcessMatrix extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    
	private double id;          
	private String ruleName;           //
	private String column1;      //主库
	private String column2;      //区域代码
	private String column3;      //
	private String column4;      //物流方向
	private String column5;      //
	private String column6;      //
	private String column7;      //货运方式
	private String column8;      //是否自提 
	private String column9;      //支付方式 
	private String column10;     //特殊承运商分配标识
	private String column11;     //
	private String column12;     //
	private String column13;     //
	private String column14;     //退货时是否支持上门取货
	private String column15;     //
	private String operationCode;//干线承运商
	private String hubCode;      //
	private String masLoc;       //
	private String lineHaul;     //直线承运商
	private String carrierCode;  //
	private int    priority;
	private String sortingCenterCode;//配送仓库
	private String daId;
	private String selfPickupFlag;
	private String lineHaul2;
	private String deliverymanId;
	private int    enabled;
	private double deliveryLeadTime; //配送时长预估
	
	private String[] header = new String[]{ "出库主库", "区域代码", "干线-承运商代码", "支线-承运商代码", "货运方式  ALL：支持所有方式  A：仅支持空运  G：仅支持公路陆运  T：仅支持铁路陆运",
			"特殊承运商分配标识   ALL：全部支持    N：正常SMI订单    WRT：纯延保订单     12：指定日订单（运能）    10：超大超重订单    14：天猫订单   MOBILE89：电信89套餐    CM：移动0元购机    CU：联通0元购机    CT：电信0元购机    GCU：极信-联通    GCM：极信-移动    GCT：极信-电信",
			"支付方式   ALL：都支持   COD：货到付款   PAID：已付款",
			"物流方向   SMI、PKP、GFT、3FM、USE：SMI仓库出发支持正逆向   SMI、GFT、3FM、USE：SMI仓库出发仅支持单程正向   PKP：SMI仓库出发仅支持单程逆向   3PP、GFT、3FM、USE、3PKP：3PP仓库出发支持正逆向",
			"是否自提   ALL：都支持   Y：支持自提点   N：不支持自提","退货时是否支持上门取货   ALL：都支持   N：不支持上门取货   Y：支持上门取货（仅针对单程逆向场景）",
			"配送时长预估   单位：小时"};
	private String[] exportFieldNames = new String[]{"column1","column2","operationCode","lineHaul",
					"column7","column10","column9","column4","column8","column14","deliveryLeadTime"}; 
	
	
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	public String getColumn3() {
		return column3;
	}
	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	public String getColumn4() {
		return column4;
	}
	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	public String getColumn5() {
		return column5;
	}
	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	public String getColumn6() {
		return column6;
	}
	public void setColumn6(String column6) {
		this.column6 = column6;
	}
	public String getColumn7() {
		return column7;
	}
	public void setColumn7(String column7) {
		this.column7 = column7;
	}
	public String getColumn8() {
		return column8;
	}
	public void setColumn8(String column8) {
		this.column8 = column8;
	}
	public String getColumn9() {
		return column9;
	}
	public void setColumn9(String column9) {
		this.column9 = column9;
	}
	public String getColumn10() {
		return column10;
	}
	public void setColumn10(String column10) {
		this.column10 = column10;
	}
	public String getColumn11() {
		return column11;
	}
	public void setColumn11(String column11) {
		this.column11 = column11;
	}
	public String getColumn12() {
		return column12;
	}
	public void setColumn12(String column12) {
		this.column12 = column12;
	}
	public String getColumn13() {
		return column13;
	}
	public void setColumn13(String column13) {
		this.column13 = column13;
	}
	public String getColumn14() {
		return column14;
	}
	public void setColumn14(String column14) {
		this.column14 = column14;
	}
	public String getColumn15() {
		return column15;
	}
	public void setColumn15(String column15) {
		this.column15 = column15;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getHubCode() {
		return hubCode;
	}
	public void setHubCode(String hubCode) {
		this.hubCode = hubCode;
	}
	public String getMasLoc() {
		return masLoc;
	}
	public void setMasLoc(String masLoc) {
		this.masLoc = masLoc;
	}
	public String getLineHaul() {
		return lineHaul;
	}
	public void setLineHaul(String lineHaul) {
		this.lineHaul = lineHaul;
	}
	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getSortingCenterCode() {
		return sortingCenterCode;
	}
	public void setSortingCenterCode(String sortingCenterCode) {
		this.sortingCenterCode = sortingCenterCode;
	}
	public String getDaId() {
		return daId;
	}
	public void setDaId(String daId) {
		this.daId = daId;
	}
	public String getSelfPickupFlag() {
		return selfPickupFlag;
	}
	public void setSelfPickupFlag(String selfPickupFlag) {
		this.selfPickupFlag = selfPickupFlag;
	}
	public String getLineHaul2() {
		return lineHaul2;
	}
	public void setLineHaul2(String lineHaul2) {
		this.lineHaul2 = lineHaul2;
	}
	public String getDeliverymanId() {
		return deliverymanId;
	}
	public void setDeliverymanId(String deliverymanId) {
		this.deliverymanId = deliverymanId;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public double getDeliveryLeadTime() {
		return deliveryLeadTime;
	}
	public void setDeliveryLeadTime(double deliveryLeadTime) {
		this.deliveryLeadTime = deliveryLeadTime;
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