/**
 * 
 */
package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.MstLspMapper;
import com.multigold.mdm.service.api.mst.MstLspService;

/**
 * @author zhanghaiyang
 *
 */
@Service
public class MstLspServiceImpl<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements MstLspService<T, ID> {
	
	
	@Autowired
	MstLspMapper<T, ID> mstLspMapper;
	
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return mstLspMapper;
	}
}
