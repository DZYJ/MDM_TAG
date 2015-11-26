/**
 * 
 */
package com.multigold.mdm.dao.mst;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.mdm.entity.mst.HubProcessMatrix;

/**
 * @author mayanhu
 *
 */
public interface HubProcessMatrixMapper  <T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID> {

	/**
	 * 上批量删除与上传的数据重复的数据
	 * 
	 * @return 
	 */
	public void deleteRepeat();
	
	/**
	 * 查询导出的数据 
	 * 
	 * @return 
	 */
	public List<HubProcessMatrix> getExportList(Map<String, Object> map);
	/**
	 * 查询要插入历史表的数据
	 * 
	 * @return 
	 */
	public List<HubProcessMatrix> getInsetHistoryList();
	
	public long getCount(Map<String, Object> map);
}
