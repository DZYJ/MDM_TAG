package test.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class HjbGlList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5391388459013211370L;
	private String bizCode;		//业务代码
	private String atomCode;	//原子交易代码
	private String memo;		//附加信息--1.对于买金,还贷为支付方式;2.针对消费为商户号
	private BigDecimal cnyAmount;	//金额
	private BigDecimal gram;		//克重
	private Integer status;		//状态
	private String transactionId;	//交易ID
	private String dateStr;
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getAtomCode() {
		return atomCode;
	}
	public void setAtomCode(String atomCode) {
		this.atomCode = atomCode;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public BigDecimal getCnyAmount() {
		return cnyAmount;
	}
	public void setCnyAmount(BigDecimal cnyAmount) {
		this.cnyAmount = cnyAmount;
	}
	public BigDecimal getGram() {
		return gram;
	}
	public void setGram(BigDecimal gram) {
		this.gram = gram;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	
}
