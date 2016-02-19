package com.multigold.mdm.dto.lsp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.multigold.common.entity.BaseSerializable;

/**
 * @author wangweijin
 * 承运商分配传入头参数
 */
public class LspDistributionReqDto implements BaseSerializable{

	private static final long serialVersionUID = 3755711175189944691L;
	/** column1 仓库 */
	private String masLoc;
	/** column2 4级CODE*/
	private String shipToStateCode;//2
	/** column3 顾客VIP标识，目前固定为N*/
	private String vipFlag;//3
	/** column5 当前状态时间*/
	private Date currentStatusFromDate;//5
	/** column6 当前状态时间*/
	private Date currentStatusToDate;//6
	/** column7 货运方式A:Air;S:Sea;G:Ground;T:Train*/
	private String shipMethod;//7
	/** column8 顾客自提标识，默认为N：顾客不自提*/
	private String selfPickupFlag;//8
	/** column9 支付方式PAID,COD*/
	private String paymentCode;//9
	/** column11*/
	private String orderEstExpiredDate;//11
	/** column12*/
	private String sortingCenter;//12
	/** column13 3级DAID */
	private String daId;//13
	
	/** column10 以旧换新编码（特殊承运商标识），N，空（也是N），MOBILE89(电信89套餐），10（大件商品），12（指定送达日订单）*/
	private String tradeInFlag;
	/** column4订单大类型 */
	private String productType;
	/** column14上门取货标识(N（退货邮寄），Y（退货上门取货）) */
	private String goodsCollectFlag;
	/** column15商品分组,有多种值（目前十多个）需要在MST_PARAMETER上加主数据 */
	private String partGroup;
	
	private String orderNum;
	/** */
	private String hubType;
	/** 指定日送达标识 */
	private String fddFlag;
	/** 指定日期 */
	private String fddDate;
	/** 订单类型 */
	private String orderType;	
	/** 原订单承运商 */
	private String orginalLsp;
	/** 订单价格 */
	private Double totalPrice;
		
	/** column13 3级DAID */
	private String deliveryTimesLot;

	public String getFddFlag() {
		return fddFlag;
	}

	public void setFddFlag(String fddFlag) {
		this.fddFlag = fddFlag;
	}

	public String getFddDate() {
		return fddDate;
	}

	public void setFddDate(String fddDate) {
		this.fddDate = fddDate;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getShipToStateCode() {
		return shipToStateCode;
	}

	public void setShipToStateCode(String shipToStateCode) {
		this.shipToStateCode = shipToStateCode;
	}

	public String getOrginalLsp() {
		return orginalLsp;
	}

	public void setOrginalLsp(String orginalLsp) {
		this.orginalLsp = orginalLsp;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getMasLoc() {
		return masLoc;
	}

	public void setMasLoc(String masLoc) {
		this.masLoc = masLoc;
	}

	public String getDaId() {
		return daId;
	}

	public void setDaId(String daId) {
		this.daId = daId;
	}

	public String getVipFlag() {
		return vipFlag;
	}

	public void setVipFlag(String vipFlag) {
		this.vipFlag = vipFlag;
	}

	public Date getCurrentStatusFromDate() {
		return currentStatusFromDate;
	}

	public void setCurrentStatusFromDate(Date currentStatusFromDate) {
		this.currentStatusFromDate = currentStatusFromDate;
	}

	public Date getCurrentStatusToDate() {
		return currentStatusToDate;
	}

	public void setCurrentStatusToDate(Date currentStatusToDate) {
		this.currentStatusToDate = currentStatusToDate;
	}

	public String getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(String shipMethod) {
		this.shipMethod = shipMethod;
	}

	public String getSelfPickupFlag() {
		return selfPickupFlag;
	}

	public void setSelfPickupFlag(String selfPickupFlag) {
		this.selfPickupFlag = selfPickupFlag;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getOrderEstExpiredDate() {
		return orderEstExpiredDate;
	}

	public void setOrderEstExpiredDate(String orderEstExpiredDate) {
		this.orderEstExpiredDate = orderEstExpiredDate;
	}

	public String getSortingCenter() {
		return sortingCenter;
	}

	public void setSortingCenter(String sortingCenter) {
		this.sortingCenter = sortingCenter;
	}

	public String getTradeInFlag() {
		return tradeInFlag;
	}

	public void setTradeInFlag(String tradeInFlag) {
		this.tradeInFlag = tradeInFlag;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getGoodsCollectFlag() {
		return goodsCollectFlag;
	}

	public void setGoodsCollectFlag(String goodsCollectFlag) {
		this.goodsCollectFlag = goodsCollectFlag;
	}

	public String getPartGroup() {
		return partGroup;
	}

	public void setPartGroup(String partGroup) {
		this.partGroup = partGroup;
	}

	public String getDeliveryTimesLot() {
		return deliveryTimesLot;
	}

	public void setDeliveryTimesLot(String deliveryTimesLot) {
		this.deliveryTimesLot = deliveryTimesLot;
	}

	public String getHubType() {
		return hubType;
	}

	public void setHubType(String hubType) {
		this.hubType = hubType;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
}
