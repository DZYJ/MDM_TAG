package com.multigold.mdm.entity.po;

import java.math.BigDecimal;

import com.multigold.common.entity.BaseEntity;

/**
 * 入库单主表
 * @author zhanghua
 *
 */
public class RdrecordEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5186384343725863925L;

	private String rdFlag;// 收发标志(1-收，0-发)
	private String vouchType;// 单据类型编码(01-采购入库单)
	private String busType;// 业务类型(【普通采购】)
	private String cBusCode;// 对应业务单号(本场景取对应【销售发货单号】)
	private String cSource;// 单据来源(【采购订单】，采购业务必有订单模式)
	private String whCode;// 仓库编码(取配置参数，商品采购入库仓库)
	private String dDate;// 单据日期(yyyy-mm-dd)
	private String coCode;// 单据编号(采购入库单的单据号，OMS系统可以自定义)
	private String cRdCode;// 入库类别(Select cRdCode from PurchaseType )
 
	private String cPTCode;// 采购类型编码(采购入库单对应的采购业务类型编码)
	private String venCode;// 供应商编码(采购入库单必填，【供应商编码】)
	private String orderCode;// 采购订单号(对应采购订单的单据编号【cPOID】)
 
	private String cMaker;// 制单人(必填，可配置参数，要求必须为用户系统操作员)
	private String VT_ID;// 单据模板号(采购入库单-27)
	private String iPurorderId;// 采购订单主表标识(对应采购订单的订单主表ID【POID】)
	private String cHandler;// 审核人(默认【NULL】审核人姓名， 可以设置为【demo】)
	private String handler;// 审核人(默认【NULL】审核人姓名， 可以设置为【demo】)
	private String veriDate;// 审核日期(默认【NULL】，若为审核状态，则填入审核日期)
	public String getRdFlag() {
		return rdFlag;
	}
	public void setRdFlag(String rdFlag) {
		this.rdFlag = rdFlag;
	}
	public String getVouchType() {
		return vouchType;
	}
	public void setVouchType(String vouchType) {
		this.vouchType = vouchType;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getcBusCode() {
		return cBusCode;
	}
	public void setcBusCode(String cBusCode) {
		this.cBusCode = cBusCode;
	}
	public String getcSource() {
		return cSource;
	}
	public void setcSource(String cSource) {
		this.cSource = cSource;
	}
	public String getWhCode() {
		return whCode;
	}
	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}
	public String getdDate() {
		return dDate;
	}
	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	
	public String getCoCode() {
		return coCode;
	}
	public void setCoCode(String coCode) {
		this.coCode = coCode;
	}
	public String getcRdCode() {
		return cRdCode;
	}
	public void setcRdCode(String cRdCode) {
		this.cRdCode = cRdCode;
	}
	public String getcPTCode() {
		return cPTCode;
	}
	public void setcPTCode(String cPTCode) {
		this.cPTCode = cPTCode;
	}
	public String getVenCode() {
		return venCode;
	}
	public void setVenCode(String venCode) {
		this.venCode = venCode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getcMaker() {
		return cMaker;
	}
	public void setcMaker(String cMaker) {
		this.cMaker = cMaker;
	}
	public String getVT_ID() {
		return VT_ID;
	}
	public void setVT_ID(String vT_ID) {
		VT_ID = vT_ID;
	}
	public String getiPurorderId() {
		return iPurorderId;
	}
	public void setiPurorderId(String iPurorderId) {
		this.iPurorderId = iPurorderId;
	}
	public String getcHandler() {
		return cHandler;
	}
	public void setcHandler(String cHandler) {
		this.cHandler = cHandler;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getVeriDate() {
		return veriDate;
	}
	public void setVeriDate(String veriDate) {
		this.veriDate = veriDate;
	}
	
	

}
