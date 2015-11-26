package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.MstTransCapacityMapper;
import com.multigold.mdm.entity.mst.MstTransCapacity;
import com.multigold.mdm.service.api.mst.MstTransCapacityService;

/**
 * 
 * @author mayanhu
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class MstTransCapacityServiceImpl<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements MstTransCapacityService<T,ID>{

	@Autowired
	MstTransCapacityMapper<T, ID> mstTransCapacityMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return mstTransCapacityMapper;
	}
	
	/**
	 * 取得时间规则（用于下拉列表框）
	 * 
	 * @return List<MstTransCapacity> 仓库名列表
	 */
	public List<MstTransCapacity> getLotSeqListCombobox(){
		return mstTransCapacityMapper.getLotSeqListCombobox();
	}
	
	public MstTransCapacity getLotBySeq(String lotSeq){
		return mstTransCapacityMapper.getLotBySeq(lotSeq);
	}
}
