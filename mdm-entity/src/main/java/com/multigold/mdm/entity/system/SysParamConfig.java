package com.multigold.mdm.entity.system;

import com.multigold.common.entity.BaseEntity;

/**
 * 
 * @author guoqiushi
 *
 */
public class SysParamConfig  extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Id号
	 */
    private Integer id;
    /**
     * 参数代码
     */
    private String code;
    /**
     * 参数描述
     */
    private String description;
    /**
     * 参数类型
     */
    private String param_type;
    /**
     * 备注
     */
    private String remark;
     
    private String orderBy = "ID";
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParam_type() {
		return param_type;
	}
	public void setParam_type(String param_type) {
		this.param_type = param_type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
