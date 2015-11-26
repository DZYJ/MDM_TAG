package com.multigold.mdm.web.controller.mst;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multigold.common.security.encoder.Md5PwdEncoder;
import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.Dispatcher;
import com.multigold.mdm.service.api.mst.DispatcherService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;

/**
 * 
 * @author guoqiushi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/dispatcher")
public class DispatcherController extends BaseCRUDAction<Dispatcher, Integer> {

	@Autowired
	DispatcherService<Dispatcher, Integer> DispatcherService;

	@Override
	public BaseService<Dispatcher, Integer> getBaseService() {
		return DispatcherService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, Dispatcher t, String operateFlag) {
		t.setPassword(Md5PwdEncoder.encodePassword(t.getPassword(),null));
		t.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		t.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}

}
