package com.multigold.mdm.web.controller.mst;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.upms.entity.security.Department;
import com.multigold.upms.service.api.security.DepartmentService;

/**
 * @author guoqiushi
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/department")
public class  DepartmentController extends BaseCRUDAction<Department, Integer>{

	@Autowired
	DepartmentService<Department, Integer> departmentService;
	
	@Override
	public BaseService<Department, Integer> getBaseService() {
		return departmentService;
	}
	
	@Override
	protected void setDefaultValue(HttpServletRequest request, Department t, String operateFlag) {
		
	}

}