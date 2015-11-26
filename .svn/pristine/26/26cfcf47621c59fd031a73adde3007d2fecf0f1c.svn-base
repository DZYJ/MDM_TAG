package com.multigold.mdm.service.api.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.BaseService;
import com.multigold.mdm.entity.mst.MstSelfPickup;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
public interface MstSelfPickupService <T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID> {
	
	/**
	 * 导入数据集合
	 * @param tempLspHubList
	 * @param name
	 * @return
	 */
	public void importDataList(List<MstSelfPickup> tempMSPList);
	
	/**
	 * 查询导出的数据 
	 * 
	 * @return 
	 */
	public List<MstSelfPickup> getExportList(MstSelfPickup mstSelfPickup);
	
}
