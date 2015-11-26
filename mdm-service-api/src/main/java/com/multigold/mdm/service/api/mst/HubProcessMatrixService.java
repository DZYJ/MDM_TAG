/**
 * 
 */
package com.multigold.mdm.service.api.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.BaseService;
import com.multigold.mdm.entity.mst.HubProcessMatrix;
import com.multigold.mdm.entity.mst.TempLspHub;

/**
 * @author mayanhu
 *
 */
public interface HubProcessMatrixService <T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID> {
	
	
	public String importHubProcessMatrix(List<TempLspHub> tempLspHubList,String name);

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
	public List<HubProcessMatrix> getExportList(String masLoc,List<String> divisionList);
	/**
	 * 查询要插入历史表的数据
	 * 
	 * @return 
	 */
	public List<HubProcessMatrix> getInsetHistoryList();

}
