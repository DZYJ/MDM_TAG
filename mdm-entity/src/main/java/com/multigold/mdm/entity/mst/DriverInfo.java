package com.multigold.mdm.entity.mst;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.multigold.common.entity.BaseEntity;
/**
 * 司机信息
 * @author liangdingding
 *
 */
public class DriverInfo extends BaseEntity{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 7376035232014278443L;
	/**
	 * 主键id
	 * DRIVER_ID
	 */
	private Integer driverId;
	/**
	 * 司机编号
	 * DRIVER_NO
	 */
	private Integer driverNo;
	/**
	 *司机姓名
	 * DRIVER_NAME
	 */
	private String driverName;
	/**
	 * 承运商
	 * DRIVER_LSP
	 */
	private String driverLsp;
	/**
	 * 所属部门
	 * DRIVER_DEPARTMENT
	 */
	private Integer driverDepartment;
	/**
	 * 驾驶证号
	 * DRIVER_CARD
	 */
	private String driverCard;
	/**
	 * 出生日期
	 * DRIVER_BRITHDAY
	 */
	@JSONField (format="yyyy-MM-dd")
	private Date driverBrithDay;
	/**
	 * 身份证
	 * DRIVER_IDCARD
	 */
	private String driverIdCard;
	/**
	 * 联系电话
	 * DRIVER_PHONE
	 */
	private String driverPhone;
	/**
	 * 司机联系地址
	 * DRIVER_ADDRESS
	 */
	private String driverAddress;
	/**
	 * 员工评级 1.高级  2.中级  3.初级
	 * DRIVER_LEVEL
	 */
	private Integer driverLevel;
	/**
	 * 参加工作的时间
	 * ENTER_WORK_DATE
	 */
	@JSONField (format="yyyy-MM-dd")
	private Date enterWorkDate;
	/**
	 * 驾照年审日期
	 * DRIVER_CARD_CHECKDATE
	 */
	@JSONField (format="yyyy-MM-dd")
	private Date driverCardCheck;
	/**
	 * 驾照日期
	 * DRIVER_CARD_DATE
	 */
	@JSONField (format="yyyy-MM-dd")
	private Date driverCardDate;
	/**
	 * 从业证号
	 * ENTER_WORK_ID
	 */
	private String enterWorkId;
	/**
	 * 技能等级 1-高级 2-中级 3-低级
	 * DRIVER_SKILL_LEVEL
	 */
	private int driverSkillLevel;
	/**
	 * 驾驶车型
	 * DRIVER_CAR_TYPE
	 */
	private String driverCarType;
	/**
	 * 是否活动 1||0 是或者不是
	 * DRIVER_ISACTIVITY
	 */
	private Integer driverIsActivity;
	/**
	 * 备注
	 * COMMENT
	 */
	private String comment;
	/**
	 * 排序
	 */
	private String orderBy="ENTER_WORK_DATE";
	 /**
	  * 出生起始时间
	  */
	@JSONField (format="yyyy-MM-dd")
	 private Date starDate;
	 /**
	  * 出生结束时间
	  */
	@JSONField (format="yyyy-MM-dd")
	 private Date endDate;
	
	
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public Integer getDriverNo() {
		return driverNo;
	}
	public void setDriverNo(Integer driverNo) {
		this.driverNo = driverNo;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverLsp() {
		return driverLsp;
	}
	public void setDriverLsp(String driverLsp) {
		this.driverLsp = driverLsp;
	}
	public Integer getDriverDepartment() {
		return driverDepartment;
	}
	public void setDriverDepartment(Integer driverDepartment) {
		this.driverDepartment = driverDepartment;
	}
	public String getDriverCard() {
		return driverCard;
	}
	public void setDriverCard(String driverCard) {
		this.driverCard = driverCard;
	}
	public Date getDriverBrithDay() {
		return driverBrithDay;
	}
	public void setDriverBrithDay(Date driverBrithDay) {
		this.driverBrithDay = driverBrithDay;
	}
	public String getDriverIdCard() {
		return driverIdCard;
	}
	public void setDriverIdCard(String driverIdCard) {
		this.driverIdCard = driverIdCard;
	}
	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	public String getDriverAddress() {
		return driverAddress;
	}
	public void setDriverAddress(String driverAddress) {
		this.driverAddress = driverAddress;
	}
	public Integer getDriverLevel() {
		return driverLevel;
	}
	public void setDriverLevel(Integer driverLevel) {
		this.driverLevel = driverLevel;
	}
	public Date getEnterWorkDate() {
		return enterWorkDate;
	}
	public void setEnterWorkDate(Date enterWorkDate) {
		this.enterWorkDate = enterWorkDate;
	}
	public Date getDriverCardCheck() {
		return driverCardCheck;
	}
	public void setDriverCardCheck(Date driverCardCheck) {
		this.driverCardCheck = driverCardCheck;
	}

	public Date getDriverCardDate() {
		return driverCardDate;
	}
	public void setDriverCardDate(Date driverCardDate) {
		this.driverCardDate = driverCardDate;
	}
	public String getEnterWorkId() {
		return enterWorkId;
	}
	public void setEnterWorkId(String enterWorkId) {
		this.enterWorkId = enterWorkId;
	}
	public int getDriverSkillLevel() {
		return driverSkillLevel;
	}
	public void setDriverSkillLevel(int driverSkillLevel) {
		this.driverSkillLevel = driverSkillLevel;
	}
	public String getDriverCarType() {
		return driverCarType;
	}
	public void setDriverCarType(String driverCarType) {
		this.driverCarType = driverCarType;
	}
	public Integer getDriverIsActivity() {
		return driverIsActivity;
	}
	public void setDriverIsActivity(Integer driverIsActivity) {
		this.driverIsActivity = driverIsActivity;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStarDate() {
		return starDate;
	}
	public void setStarDate(Date starDate) {
		this.starDate = starDate;
	}
	
	
	
}
