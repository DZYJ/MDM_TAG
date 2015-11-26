/**
 * 
 */
package com.multigold.mdm.service.api.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.BaseService;
import com.multigold.mdm.entity.mst.MasLoc;

/**
 * @author mayanhu
 *
 */
public interface MasLocService <T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID> {
	/**
	 * 取得所有仓库名（用于下拉列表框）
	 * 
	 * @return List<MasLoc> 仓库名列表
	 */
	public List<MasLoc> getMasLocListCombobox();

	
	/**
	 * 导入数据集合
	 * @param tempMLList
	 * @param name
	 * @return
	 */
	public void importDataList(List<MasLoc> tempMLList);
	
	/**
	 * 查询导出的数据 
	 * 
	 * @return 
	 */
	public List<MasLoc> getExportList(MasLoc masLoc);
}
