package com.multigold.mdm.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.system.SysParamConfig;
import com.multigold.mdm.service.api.system.SysParamConfigService;
/**
 * 
 * @author guoqiushi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sysParam")
public class SysParamConfigController extends BaseCRUDAction<SysParamConfig, Integer>{
	
	@Autowired
	SysParamConfigService<SysParamConfig, Integer> sysParamConfigService;

	@Override
	public BaseService<SysParamConfig, Integer> getBaseService() {
		return sysParamConfigService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, SysParamConfig t, String operateFlag) {
		
	}
}
