package com.multigold.mdm.service.impl.interfaceConfig;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.interfaceConfig.InterfaceMapper;
import com.multigold.mdm.service.api.interfaceConfig.InterfaceService;
import com.multigold.model.interfaceConfig.InterfaceEntity;
@Service
public class InterfaceServiceImpl <T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements InterfaceService<T, ID> {
	@Autowired
	InterfaceMapper<T,ID> interfaceMapper;
	@Override
	public BaseMapper<T, ID> getMapper() {
		// TODO Auto-generated method stub
		return interfaceMapper;
	}
	public InterfaceEntity queryUrlConfig(Map<String,String> map){
		List<InterfaceEntity> list = interfaceMapper.queryUrlConfig(map);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
