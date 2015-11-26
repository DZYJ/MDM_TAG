/**
 * 
 */
package com.multigold.mdm.dao.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.mdm.entity.mst.MasLoc;
import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;

/**
 * @author mayanhu
 *
 */
public interface MasLocMapper  <T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID> {
	/**
	 * 取得所有仓库名（用于下拉列表框）
	 * 
	 * @return List<MasLoc> 仓库名列表
	 */
	public List<MasLoc> getMasLocListCombobox();
	
	public List<String> getMasLoc(String lsp);
	
	
	/**
	 * 查询导出的数据 
	 * 
	 * @return 
	 */
	public List<MasLoc> queryListByEntity(MasLoc masLoc);

	/**
	 * 批量导入
	 * @param tempDDList
	 */
	public void insertMasLocList(List<MasLoc> tempMLList);
}
