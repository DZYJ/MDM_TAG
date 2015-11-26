/**
 * 
 */
package com.multigold.mdm.service.api.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.BaseService;
import com.multigold.mdm.entity.mst.MstLspPickupSchedule;

/**
 * @author mayanhu
 *
 */
public interface MstLspPickupScheduleService <T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID> {
	
	
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
