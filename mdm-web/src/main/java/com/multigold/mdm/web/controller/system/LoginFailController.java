package com.multigold.mdm.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.system.LoginFailLog;
import com.multigold.mdm.service.api.system.LoginFailService;

/**
 * 
 * @author guoqiushi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/loginFail")
public class LoginFailController extends BaseCRUDAction<LoginFailLog, Integer>{

	@Autowired
	LoginFailService<LoginFailLog, Integer> loginFailService;
	
	@Override
	public BaseService<LoginFailLog, Integer> getBaseService() {
		return loginFailService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, LoginFailLog t, String operateFlag) {
	}
}
