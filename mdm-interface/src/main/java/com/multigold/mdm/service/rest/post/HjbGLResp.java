package com.multigold.mdm.service.rest.post;

import java.io.Serializable;
import java.util.List;

/**
 * 黄金币总账请求数据结构
 * @author zhanghua
 *
 */

public class HjbGLResp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8961830228714072705L;
	private String transactionId;	//交易ID
	private String resultCode;		//处理结果Code--1:成功;0:失败
	private String errorMsg;		//错误描述 --当“处理结果”为0（失败）时，该字段包含错误描述
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
