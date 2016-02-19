package com.multigold.model.interfaceConfig;

import java.io.Serializable;

import com.multigold.common.entity.BaseEntity;

public class InterfaceEntity extends BaseEntity{
	/**
	 * 接口配置表
	 */
	private static final long serialVersionUID = -3971263868607467599L;
	private Integer id;	
	private String url;	//请求地址
	private String systemName;	//相关系统名称
	private String interfaceName;	//接口名称
	private String interfaceCode;	//接口代码
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getInterfaceCode() {
		return interfaceCode;
	}
	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}
	
	
}
