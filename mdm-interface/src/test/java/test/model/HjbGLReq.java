package test.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 黄金币总账请求数据结构
 * @author zhanghua
 *
 */

public class HjbGLReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8691116622442685697L;
	private String transactionId;		//交易ID
	private String glDate;				//总账日期	YYYYMMDD
	private String recordTotalCount;	//记录总数  交易ID的值由源系统负责产生，用于唯一标识该接口的每一次交易请求，格式：接口编号+下划线+yyyyMMdd+下划线+时间（毫秒）+下划线+sequenceID
	private List<HjbGlList> glList;		//记录
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getGlDate() {
		return glDate;
	}
	public void setGlDate(String glDate) {
//		try {
//			Date date = new SimpleDateFormat("yyyyMMdd").parse(glDate);
//			String dateStr = new SimpleDateFormat("yyyyMM-dd").format(date);
			this.glDate = glDate;
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
	}
	public String getRecordTotalCount() {
		return recordTotalCount;
	}
	public void setRecordTotalCount(String recordTotalCount) {
		this.recordTotalCount = recordTotalCount;
	}
	public List<HjbGlList> getGlList() {
		return glList;
	}
	public void setGlList(List<HjbGlList> glList) {
		this.glList = glList;
	}
	
}
