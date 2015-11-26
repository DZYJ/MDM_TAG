package com.multigold.mdm.service.impl.system;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.system.LoginSuccessMapper;
import com.multigold.mdm.service.api.system.LoginSuccessService;

/**
 * 
 * @author guoqiushi
 *
 */
@Service
public class LoginSuccessServiceImpl<T extends BaseEntity, ID extends Serializable>
extends BaseServiceImpl<T, ID> implements LoginSuccessService<T, ID>{

	@Autowired
	LoginSuccessMapper<T, ID> loginSuccessMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return loginSuccessMapper;
	}
	
}
