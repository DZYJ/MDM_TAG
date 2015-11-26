/**
 * 
 */
package com.multigold.mdm.dao.mst;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.mdm.entity.mst.HubProcessMatrix;

/**
 * @author mayanhu
 *
 */
public interface HubProcessMatrixHistoryMapper  <T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID> {

	/**
	 * 把HubProcessMatrix表中删除的数据备份到HubProcessMatrixHistory中
	 * 
	 * @return 
	 */
	public void insertHubProcessMatrixHistoryList(Map<String, Object> map);
}
