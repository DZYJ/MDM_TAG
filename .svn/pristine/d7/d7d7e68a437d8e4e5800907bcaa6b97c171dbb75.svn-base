package com.multigold.mdm.dto.mq;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息日志
 * @author wangweijin
 *
 */
public class MessageTransactions implements Serializable{
	
	private static final long serialVersionUID = 7375383539077138445L;
	/** 编号 */
    private Long id;
    /** 源系统 */
    private String sourceCode;
    /** 目标系统 */
    private String targetCode;
    /** 交易ID */
    private String transactionId;
    /** MQ类型 */
    private String msgTypeCode;
    /** 检索值 */
    private String searchKey;
    /** 信息 */
    private String message;
    /** 异常信息 */
    private String exception;
    /** 重新执行标识 */
    private String reExecFlag;
    /**  */
    private String insertBy;

    private Date insertDate;

    private String modifiedBy;

    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode == null ? null : sourceCode.trim();
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode == null ? null : targetCode.trim();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public String getMsgTypeCode() {
        return msgTypeCode;
    }

    public void setMsgTypeCode(String msgTypeCode) {
        this.msgTypeCode = msgTypeCode == null ? null : msgTypeCode.trim();
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey == null ? null : searchKey.trim();
    }

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getReExecFlag() {
        return reExecFlag;
    }

    public void setReExecFlag(String reExecFlag) {
        this.reExecFlag = reExecFlag == null ? null : reExecFlag.trim();
    }

    public String getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(String insertBy) {
        this.insertBy = insertBy == null ? null : insertBy.trim();
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}