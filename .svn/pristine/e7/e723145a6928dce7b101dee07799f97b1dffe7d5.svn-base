/**
 * 
 */
package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.mdm.dao.mst.HubProcessMatrixHistoryMapper;
import com.multigold.mdm.entity.mst.HubProcessMatrix;
import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.service.api.mst.HubProcessMatrixHistoryService;

/**
 * @author mayanhu
 *
 */
@Service
public class HubProcessMatrixHistoryServiceImpl<T extends BaseEntity, ID extends Serializable>
extends BaseServiceImpl<T, ID> implements HubProcessMatrixHistoryService<T, ID> {
	
	@Autowired
	HubProcessMatrixHistoryMapper<T, ID> hubProcessMatrixHistoryMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return hubProcessMatrixHistoryMapper;
	}
	
	/**
	 * 把HubProcessMatrix表中删除的数据备份到HubProcessMatrixHistory中
	 * name  操作人
	 * dt    操作时间
	 * @return 
	 */
	public void insertHubProcessMatrixHistoryList(List<HubProcessMatrix> hubProcessMatrixHistoryList,String name,Date dt){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mapList", hubProcessMatrixHistoryList);
		params.put("name", name);
		params.put("dt", dt);
		
		hubProcessMatrixHistoryMapper.insertHubProcessMatrixHistoryList(params);
	}
	
}
