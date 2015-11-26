/**
 * 
 */
package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.mdm.dao.mst.MstLspPickupScheduleMapper;
import com.multigold.mdm.entity.mst.MstLspPickupSchedule;
import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.service.api.mst.MstLspPickupScheduleService;

/**
 * @author mayanhu
 *
 */
@Service
public class MstLspPickupScheduleServiceImpl<T extends BaseEntity, ID extends Serializable>
extends BaseServiceImpl<T, ID> implements MstLspPickupScheduleService<T, ID> {
	
	@Autowired
	MstLspPickupScheduleMapper<T, ID> pickupMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return pickupMapper;
	}
	
	/**
	 * 批量插入数据
	 * 
	 * @return 
	 */
	public int insertPickupList(List<MstLspPickupSchedule> pickupList){
		int result = 0;
		String lspCode = pickupList.get(0).getLspCode();
		if(null != lspCode && !lspCode.isEmpty()){
			pickupMapper.deleteByLspCode(lspCode);
			result= pickupMapper.insertPickupList(pickupList);
		}
		return result;
	}
	
	/**
	 * 上批量删除与上传的数据重复的数据
	 * 
	 * @return 
	 */
	public int deleteByLspCode(String lspCode){
		return pickupMapper.deleteByLspCode(lspCode);
	}

	@Override
	public List<MstLspPickupSchedule> queryListByLsp(String lspCode) {
		return pickupMapper.queryListByLsp(lspCode);
	}
}
