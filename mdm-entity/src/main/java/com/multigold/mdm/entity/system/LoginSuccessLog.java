package com.multigold.mdm.entity.system;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.util.JsonUtil;



/**
 * 
 * @author guoqiushi
 *
 */
public class LoginSuccessLog extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private long id;
	/**
	 * 用户名
	 */
    private String username;
    /**
     * IP
     */
    private String ip;;
	/**
	 * 需要排序字段 暂时只能对单字段排序，以后可以改为对多列排序，此字段为数据库中的字段，非对象.
	 */
	private String orderBy = "id";
    
	public LoginSuccessLog() {
	}

	public LoginSuccessLog(long id, String username, String ip) {
		this.id = id;
		this.username = username;
		this.ip = ip;
		
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return JsonUtil.objectToJSON(this);
	}
	
} 
