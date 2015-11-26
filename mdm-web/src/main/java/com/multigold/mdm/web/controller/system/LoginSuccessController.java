package com.multigold.mdm.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.system.LoginSuccessLog;
import com.multigold.mdm.service.api.system.LoginSuccessService;
/**
 * 
 * @author guoqiushi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/loginSuccess")
public class LoginSuccessController extends BaseCRUDAction<LoginSuccessLog, Integer> {

	@Autowired
	LoginSuccessService<LoginSuccessLog, Integer> loginSuccessService;
	
	@Override
	public BaseService<LoginSuccessLog, Integer> getBaseService() {
		return loginSuccessService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, LoginSuccessLog t, String operateFlag) {
		
	}
}
