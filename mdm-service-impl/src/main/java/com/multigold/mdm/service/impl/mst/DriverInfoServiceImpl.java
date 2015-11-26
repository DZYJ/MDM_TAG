package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.DriverInfoMapper;
import com.multigold.mdm.service.api.mst.DriverInfoService;
/**
 * 
 * @author liangdingding
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class DriverInfoServiceImpl<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements DriverInfoService<T, ID> {
	
	
	@Autowired
	DriverInfoMapper<T, ID> driverInfoMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return driverInfoMapper;
	} 

	
	


}