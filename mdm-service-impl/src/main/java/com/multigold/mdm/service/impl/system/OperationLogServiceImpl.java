/**
 * 
 */
package com.multigold.mdm.service.impl.system;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.system.OperationLogMapper;
import com.multigold.mdm.service.api.system.OperationLogService;

/**
 * @author zhanghaiyang
 *
 */
@Service
public class OperationLogServiceImpl<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements OperationLogService<T, ID> {

	@Autowired
	private OperationLogMapper<T, ID> operationLogMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return operationLogMapper;
	}
	
}
