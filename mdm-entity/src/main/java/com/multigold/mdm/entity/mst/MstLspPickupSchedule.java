/**
 * 
 */
package com.multigold.mdm.entity.mst;

import com.multigold.common.entity.BaseEntity;

/**
 * @author mayanhu
 * 取货时间
 *
 */
public class MstLspPickupSchedule extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    
	private int    id;  
	private String masLoc;           
	private String masName;           
	private String lspCode;           
	private int    day;      
	private String time;
	private String active;
	
	public String getMasLoc() {
		return masLoc;
	}
	public void setMasLoc(String masLoc) {
		this.masLoc = masLoc;
	}
	public String getLspCode() {
		return lspCode;
	}
	public void setLspCode(String lspCode) {
		this.lspCode = lspCode;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getMasName() {
		return masName;
	}
	public void setMasName(String masName) {
		this.masName = masName;
	}

}