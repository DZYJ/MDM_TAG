/**
 * 
 */
package com.multigold.mdm.service.api.mst;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.BaseService;
import com.multigold.mdm.entity.mst.HubProcessMatrix;

/**
 * @author mayanhu
 *
 */
public interface HubProcessMatrixHistoryService <T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID> {
	/**
	 * 把HubProcessMatrix表中删除的数据备份到HubProcessMatrixHistory中
	 * 
	 * @return 
	 */
	public void insertHubProcessMatrixHistoryList(List<HubProcessMatrix> hubProcessMatrixHistoryList,String name,Date dt);

}
