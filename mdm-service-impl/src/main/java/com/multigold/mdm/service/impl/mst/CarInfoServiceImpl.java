package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.CarInfoMapper;
import com.multigold.mdm.service.api.mst.CarInfoService;

@Service
public class CarInfoServiceImpl <T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements CarInfoService<T, ID> {
	
	
	@Autowired
	CarInfoMapper<T, ID> carInfoMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return carInfoMapper;
	} 

	
	


}