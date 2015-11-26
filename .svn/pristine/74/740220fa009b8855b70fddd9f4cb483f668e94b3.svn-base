/**
 * 
 */
package com.multigold.mdm.dao.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.mdm.entity.mst.MstTransCapacity;

/**
 * @author mayanhu
 *
 */
public interface MstTransCapacityMapper<T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID> {
	
	public int insertTransCapacity(MstTransCapacity transCapacity);
	
	public int deleteByObject(MstTransCapacity transCapacity);
	
	/**
	 * 取得时间规则（用于下拉列表框）
	 * 
	 * @return List<MstTransCapacity> 仓库名列表
	 */
	public List<MstTransCapacity> getLotSeqListCombobox();
	
	public MstTransCapacity getLotBySeq(String lotSeq);
}
