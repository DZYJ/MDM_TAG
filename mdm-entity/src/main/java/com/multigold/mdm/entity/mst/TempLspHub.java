/**
 * 
 */
package com.multigold.mdm.entity.mst;

import com.multigold.common.entity.BaseEntity;

/**
 * @author mayanhu
 * 调度配置上传虚拟表
 *
 */
public class TempLspHub extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    
	private String masLoc;            //仓库编码
	private String lspCode;           //支线承运商编码
	private String divisionCode;      //区域编码
	private String lineHaul;          //干线承运商编码
	private String shipMethod;        //货运方式
	private String tradeInIdentifier; //特殊承运商分配标识
	private String paymentTerm;       //支付方式
	private String supportDirection;  //物流方向
	private String selfPickup;        //是否自提
	private String goodsCollectFlag;  //退货时是否支持上门取货
	private float  deliveryLeadTime;  //配送时长预估
	
	private String hubType;
	private String daId;
	private String parentDivisionCode;
	
	
	/**
	 * 需要排序字段 暂时只能对单字段排序，以后可以改为对多列排序，此字段为数据库中的字段，非对象.
	 */
	private String orderBy = "mas_Loc";

	public String getMasLoc() {
		return masLoc;
	}

	public void setMasLoc(String masLoc) {
		this.masLoc = masLoc;
	}

	public String getLspCode() {
		return lspCode;
	}

	public void setLspCode(String lspCode) {
		this.lspCode = lspCode;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getLineHaul() {
		return lineHaul;
	}

	public void setLineHaul(String lineHaul) {
		this.lineHaul = lineHaul;
	}

	public String getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(String shipMethod) {
		this.shipMethod = shipMethod;
	}

	public String getTradeInIdentifier() {
		return tradeInIdentifier;
	}

	public void setTradeInIdentifier(String tradeInIdentifier) {
		this.tradeInIdentifier = tradeInIdentifier;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getSupportDirection() {
		return supportDirection;
	}

	public void setSupportDirection(String supportDirection) {
		this.supportDirection = supportDirection;
	}

	public String getSelfPickup() {
		return selfPickup;
	}

	public void setSelfPickup(String selfPickup) {
		this.selfPickup = selfPickup;
	}

	public String getGoodsCollectFlag() {
		return goodsCollectFlag;
	}

	public void setGoodsCollectFlag(String goodsCollectFlag) {
		this.goodsCollectFlag = goodsCollectFlag;
	}

	public float getDeliveryLeadTime() {
		return deliveryLeadTime;
	}

	public void setDeliveryLeadTime(float deliveryLeadTime) {
		this.deliveryLeadTime = deliveryLeadTime;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getHubType() {
		return hubType;
	}

	public void setHubType(String hubType) {
		this.hubType = hubType;
	}

	public String getDaId() {
		return daId;
	}

	public void setDaId(String daId) {
		this.daId = daId;
	}

	public String getParentDivisionCode() {
		return parentDivisionCode;
	}

	public void setParentDivisionCode(String parentDivisionCode) {
		this.parentDivisionCode = parentDivisionCode;
	}

}