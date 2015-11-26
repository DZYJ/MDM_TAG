package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.MstLspTransportMapper;
import com.multigold.mdm.entity.mst.MstLspTransport;
import com.multigold.mdm.service.api.mst.MstLspTransportService;

/**
 * 
 * @author mayanhu
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class MstLspTransportServiceImpl<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements MstLspTransportService<T,ID>{

	@Autowired
	MstLspTransportMapper<T, ID> mstLspTransportMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return mstLspTransportMapper;
	}

	@Override
	public long getCount(Map<String, Object> map) {
		return mstLspTransportMapper.getCount(map);
	}

	@Override
	public int insertTransportList(List<MstLspTransport> transportList) {
		mstLspTransportMapper.deleteByObjectList(transportList);
		return mstLspTransportMapper.insertTransportList(transportList);
	}
	
}
