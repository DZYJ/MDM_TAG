package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.MstLspDaShareMapper;
import com.multigold.mdm.service.api.mst.MstLspDaShareService;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class MstLspDaShareServiceImpl <T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements MstLspDaShareService<T,ID>{

	@Autowired
	private MstLspDaShareMapper<T, ID> mstLspDaShareMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return mstLspDaShareMapper;
	}
	
}
