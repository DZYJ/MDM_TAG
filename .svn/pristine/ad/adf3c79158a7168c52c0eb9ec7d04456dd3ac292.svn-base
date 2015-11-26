package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.MstMasLocBucketMapper;
import com.multigold.mdm.service.api.mst.MstMasLocBucketService;

@Service
public class MstMasLocBucketServiceImpl <T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements MstMasLocBucketService<T, ID> {
	
	@Autowired
	MstMasLocBucketMapper<T, ID> mstMasLocBucketMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return mstMasLocBucketMapper;
	} 

	
	


}