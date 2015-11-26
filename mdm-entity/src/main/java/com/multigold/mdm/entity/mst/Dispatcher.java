package com.multigold.mdm.entity.mst;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.multigold.common.entity.BaseEntity;

public class Dispatcher extends BaseEntity {

	private static final long serialVersionUID = 6337475073048287298L;

	/**
	 * 身份证
	 */
	private String identification;

	/**
	 * 站点ID
	 */
	private String siteCode;

	/**
	 * 站点名称
	 */
	private String siteName;

	/**
	 * Id号
	 */
	private Long id;

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * email
	 */
	private String email;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 确认密码
	 */
	private String repassword;

	/**
	 * 是否是管理员
	 */
	private String adminFlag;

	/**
	 * 仓库
	 */
	private String masLoc;

	/**
	 * 需要排序字段 暂时只能对单字段排序，以后可以改为对多列排序，此字段为数据库中的字段，非对象.
	 */
	private String orderBy = "su_id";

	private String extension;
	private String segment;
	private String active;
	private String remark;
	private String lspCode;
	private String domain;
	private String roles;
	private String title;
	private String isEmployee;
	private String managerEmail;
	private Long enabled;
	private String timezone;
	private String language;
	private Long defaultBuid;
	private String countryCode;
	private String defaultAreaCode;
	@JSONField (format="yyyy-MM-dd hh:mm:ss")
	private Date lastLoginDate;
	private String hasChangedPswd;
	private Long invalidAttampts;
	@JSONField (format="yyyy-MM-dd hh:mm:ss")
	private Date lockoutTime;
	private Long rfLoginCount;
	private String loginStatus;
	private String telephoneNum;
	private Long sex;
	
	@JSONField (format="yyyy-MM-dd hh:mm:ss")
	private Date effectDate;
	@JSONField (format="yyyy-MM-dd hh:mm:ss")
	private Date expireDate;
	private String employeeNo;

	/**
	 * 系统id
	 */
	private long sysId;

	public long getSysId() {
		return sysId;
	}

	public void setSysId(long sysId) {
		this.sysId = sysId;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLockoutTime() {
		return lockoutTime;
	}

	public void setLockoutTime(Date lockoutTime) {
		this.lockoutTime = lockoutTime;
	}

	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLspCode() {
		return lspCode;
	}

	public void setLspCode(String lspCode) {
		this.lspCode = lspCode;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsEmployee() {
		return isEmployee;
	}

	public void setIsEmployee(String isEmployee) {
		this.isEmployee = isEmployee;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public Long getEnabled() {
		return enabled;
	}

	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getDefaultBuid() {
		return defaultBuid;
	}

	public void setDefaultBuid(Long defaultBuid) {
		this.defaultBuid = defaultBuid;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDefaultAreaCode() {
		return defaultAreaCode;
	}

	public void setDefaultAreaCode(String defaultAreaCode) {
		this.defaultAreaCode = defaultAreaCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}

	public String getMasLoc() {
		return masLoc;
	}

	public void setMasLoc(String masLoc) {
		this.masLoc = masLoc;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getRepassword() {
		if (StringUtils.isEmpty(repassword)) {
			return getPassword();
		} else {
			return repassword;
		}
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getHasChangedPswd() {
		return hasChangedPswd;
	}

	public void setHasChangedPswd(String hasChangedPswd) {
		this.hasChangedPswd = hasChangedPswd;
	}

	public Long getInvalidAttampts() {
		return invalidAttampts;
	}

	public void setInvalidAttampts(Long invalidAttampts) {
		this.invalidAttampts = invalidAttampts;
	}

	public Long getRfLoginCount() {
		return rfLoginCount;
	}

	public void setRfLoginCount(Long rfLoginCount) {
		this.rfLoginCount = rfLoginCount;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getTelephoneNum() {
		return telephoneNum;
	}

	public void setTelephoneNum(String telephoneNum) {
		this.telephoneNum = telephoneNum;
	}

	public Long getSex() {
		return sex;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	
}
