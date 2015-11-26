package com.multigold.mdm.service.api.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.BaseService;
import com.multigold.mdm.entity.mst.MstTransCapacity;
/**
 * 
 * @author mayanhu
 *
 * @param <T>
 * @param <ID>
 */
public interface MstTransCapacityService<T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID> {
	
	/**
	 * 取得时间规则（用于下拉列表框）
	 * 
	 * @return List<MstTransCapacity> 仓库名列表
	 */
	public List<MstTransCapacity> getLotSeqListCombobox();
	
	public MstTransCapacity getLotBySeq(String lotSeq);

}
