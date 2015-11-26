package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.VesselMapper;
import com.multigold.mdm.service.api.mst.VesselService;

@Service
public class VesselServiceImpl<T extends BaseEntity, ID extends Serializable>
		extends BaseServiceImpl<T, ID> implements VesselService<T, ID> {

	@Autowired
	VesselMapper<T, ID> vesselMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return vesselMapper;
	}
}
