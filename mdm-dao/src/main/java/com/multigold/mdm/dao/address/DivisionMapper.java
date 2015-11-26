package com.multigold.mdm.dao.address;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.mdm.entity.address.Division;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
public interface DivisionMapper<T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID> {

	/**
	 * 获取所有一级区域（省）
	 * @return
	 */
	public List<Division> getFirstLevelList();
	
	/**
	 * 根据parent_division_code获取所有下级区域
	 * parent_division_code
	 * @return
	 */
	public List<Division> getChildListByPCode(String parent_division_code);
	
	/**
	 * 根据division_code查询区域信息
	 */
	public Division queryById(String division_code);
	
	public List<Division> queryListByEntity(Division division);
	
	/**
	 * 查询区域是否已存在
	 * @param divisionCode
	 * @return
	 */
	public int queryCountByDivisionCode(@Param("divisionCode") String divisionCode);
}
