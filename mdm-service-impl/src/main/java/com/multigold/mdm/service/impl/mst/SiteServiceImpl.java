package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.MstMasLocMapper;
import com.multigold.mdm.entity.mst.MasLoc;
import com.multigold.mdm.service.api.mst.SiteService;


/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class SiteServiceImpl<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements SiteService<T, ID> {
	
	@Autowired
	MstMasLocMapper<T, ID> mstMasLocMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return mstMasLocMapper;
	}

	@Override
	public MasLoc queryByCode(String siteCode) {
		return mstMasLocMapper.queryByCode(siteCode);
	}
}
