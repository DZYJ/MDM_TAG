/**
 * 
 */
package com.multigold.mdm.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.system.OperationLog;
import com.multigold.mdm.service.api.system.OperationLogService;

/**
 * @author zhanghaiyang
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/operationLog")
public class OperationLogController extends BaseCRUDAction<OperationLog, Integer> {

	@Autowired
	OperationLogService<OperationLog,Integer> operationLogService;
	
	@Override
	public BaseService<OperationLog, Integer> getBaseService() {
		return operationLogService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, OperationLog t, String operateFlag) {
		
	}
	
}
