package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.MstLspDaAmountMapper;
import com.multigold.mdm.service.api.mst.MstLspDaAmountService;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class MstLspDaAmountServiceImpl <T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements MstLspDaAmountService<T,ID>{

	@Autowired
	private MstLspDaAmountMapper<T, ID> mstLspDaAmountMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return mstLspDaAmountMapper;
	}
	
}
