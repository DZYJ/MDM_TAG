/**
 * 
 */
package com.multigold.mdm.dao.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.mdm.entity.mst.MstLspPickupSchedule;

/**
 * @author mayanhu
 *
 */
public interface MstLspPickupScheduleMapper  <T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID> {
	
	/**
	 * 批量插入数据
	 * 
	 * @return 
	 */
	public int insertPickupList(List<MstLspPickupSchedule> pickupList);
	
	public List<MstLspPickupSchedule> queryListByLsp(String lspCode);
	
	/**
	 * 批量删除数据
	 * 
	 * @return 
	 */
	public int deleteByLspCode(String lspCode);

}
