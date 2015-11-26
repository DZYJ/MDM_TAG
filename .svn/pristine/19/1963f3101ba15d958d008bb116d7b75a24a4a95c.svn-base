/**
 * 
 */
package com.multigold.mdm.entity.system;

import java.util.Date;

import com.multigold.common.entity.BaseEntity;

/**
 * @author zhanghaiyang
 *
 */
public class OperationLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String title;

	private String username;

	private String content;

	private String ip;

	/**
	 * 需要排序字段 暂时只能对单字段排序，以后可以改为对多列排序，此字段为数据库中的字段，非对象.
	 */
	private String orderBy = "id";

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public OperationLog() {
		super();
	}

	public OperationLog(long id, String title, String username, String content,
			String ip) {
		super();
		this.id = id;
		this.title = title;
		this.username = username;
		this.content = content;
		this.ip = ip;
	}

	public OperationLog(long id, String title, String username, String content,
			String ip, Date insertDate) {
		super();
		this.id = id;
		this.title = title;
		this.username = username;
		this.content = content;
		this.ip = ip;
		this.insertDate = insertDate;
	}

	public OperationLog(String title, String username, String content,
			String ip, Date insertDate) {
		super();
		this.title = title;
		this.username = username;
		this.content = content;
		this.ip = ip;
		this.insertDate = insertDate;
	}
}
