package com.multigold.mdm.service.impl.system;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.system.SysParamConfigMapper;
import com.multigold.mdm.service.api.system.SysParamConfigService;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class SysParamConfigServiceImpl <T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements SysParamConfigService<T,ID>{

	@Autowired
	private SysParamConfigMapper<T, ID> sysParamConfigMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return sysParamConfigMapper;
	}
}
