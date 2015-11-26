package com.multigold.mdm.entity.mst;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.multigold.common.entity.BaseEntity;

/**
 * 车辆管理实体类
 * @author liangdingding
 *
 */
public class CarInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7147313672222442684L;
	/**
	 * 主键id
	 */
	private Integer carId;
	/**
	 * 车辆编号
	 */
	private String carNo;
	/**
	 *车牌号 
	 */
	private String carCardId;
	/**
	 * 车辆类型
	 */
	private String carType;
	/**
	 *承运商 
	 */
	private String carLsp;
	/**
	 * 车辆购买时间
	 */
	@JSONField (format="yyyy-MM-dd")
	private Date carBuyingTime;
	/**
	 * 车辆默认司机
	 */
	private String defaultDriver;
	/**
	 *车型 
	 */
	private String carModel;
	/**
	 * 车辆出厂日期
	 */
	@JSONField (format="yyyy-MM-dd")
	private Date carLeaveFactoryDate;
	/**
	 * 生产厂家
	 */
	private  String productionCompany;
	/**
	 * 箱体长度
	 */
	private float carBoxLength;
	/**
	 * 油耗
	 */
	private float oilConsumption;
	/**
	 * 核定体积
	 */
	private float carVolume;
	/**
	 * 使用性质
	 */
	private String useNature;
	/**
	 * 交强险投保公司
	 */
	private String trainstionInsuranceCompany;
	/**
	 * TRAINSTION_INSURANCE_OVERDATE
	 * 交强险截止时间
	 */
	@JSONField (format="yyyy-MM-dd")
	private Date trainstionInsuranceOverDate;
	/**
	 * BUSINESS_INSURANCE_COMPANY
	 * 商业险公司
	 */
	private String businessInsuranceCompany;
	
	/**
	 * BUSINESS_INSURANCE_OVERDATE
	 * 商业险截止时间
	 */
	@JSONField (format="yyyy-MM-dd")
	private Date bisinessInsuranceOverDate;
	/**
	 * 车辆引入方式
	 * CAR_IMPORT_WAY
	 */
	private String carImportWay;
	/**
	 * 发动机型号
	 * ENGINE_NO
	 */
	private String engineNo;
	/**
	 * 是否活动
	 * CAR_ISACTIVITY
	 */
	private Integer carIsActivity;
	/**
	 * 备注
	 */
	 private String comment;
	/**
	 * 默认排序
	 */
	 private String orderBy = "CAR_ID";
	 /**
	  * 按车辆购置时间查询开始时间
	  */
	 private Date starDate;
	 /**
	  * 按车辆购置时间查询结束时间
	  */
	 private Date endDate;
	 
	 
	 
	 public Date getStarDate() {
		return starDate;
	}
	public void setStarDate(Date starDate) {
		this.starDate = starDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getCarCardId() {
		return carCardId;
	}
	public void setCarCardId(String carCardId) {
		this.carCardId = carCardId;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarLsp() {
		return carLsp;
	}
	public void setCarLsp(String carLsp) {
		this.carLsp = carLsp;
	}
	public Date getCarBuyingTime() {
		return carBuyingTime;
	}
	public void setCarBuyingTime(Date carBuyingTime) {
		this.carBuyingTime = carBuyingTime;
	}
	public String getDefaultDriver() {
		return defaultDriver;
	}
	public void setDefaultDriver(String defaultDriver) {
		this.defaultDriver = defaultDriver;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public Date getCarLeaveFactoryDate() {
		return carLeaveFactoryDate;
	}
	public void setCarLeaveFactoryDate(Date carLeaveFactoryDate) {
		this.carLeaveFactoryDate = carLeaveFactoryDate;
	}
	public String getProductionCompany() {
		return productionCompany;
	}
	public void setProductionCompany(String productionCompany) {
		this.productionCompany = productionCompany;
	}
	public float getCarBoxLength() {
		return carBoxLength;
	}
	public void setCarBoxLength(float carBoxLength) {
		this.carBoxLength = carBoxLength;
	}
	public float getOilConsumption() {
		return oilConsumption;
	}
	public void setOilConsumption(float oilConsumption) {
		this.oilConsumption = oilConsumption;
	}
	public float getCarVolume() {
		return carVolume;
	}
	public void setCarVolume(float carVolume) {
		this.carVolume = carVolume;
	}
	public String getUseNature() {
		return useNature;
	}
	public void setUseNature(String useNature) {
		this.useNature = useNature;
	}
	public String getTrainstionInsuranceCompany() {
		return trainstionInsuranceCompany;
	}
	public void setTrainstionInsuranceCompany(String trainstionInsuranceCompany) {
		this.trainstionInsuranceCompany = trainstionInsuranceCompany;
	}
	public Date getTrainstionInsuranceOverDate() {
		return trainstionInsuranceOverDate;
	}
	public void setTrainstionInsuranceOverDate(Date trainstionInsuranceOverDate) {
		this.trainstionInsuranceOverDate = trainstionInsuranceOverDate;
	}
	public String getBusinessInsuranceCompany() {
		return businessInsuranceCompany;
	}
	public void setBusinessInsuranceCompany(String businessInsuranceCompany) {
		this.businessInsuranceCompany = businessInsuranceCompany;
	}
	public Date getBisinessInsuranceOverDate() {
		return bisinessInsuranceOverDate;
	}
	public void setBisinessInsuranceOverDate(Date bisinessInsuranceOverDate) {
		this.bisinessInsuranceOverDate = bisinessInsuranceOverDate;
	}
	public String getCarImportWay() {
		return carImportWay;
	}
	public void setCarImportWay(String carImportWay) {
		this.carImportWay = carImportWay;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public Integer getCarIsActivity() {
		return carIsActivity;
	}
	public void setCarIsActivity(Integer carIsActivity) {
		this.carIsActivity = carIsActivity;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
 }
