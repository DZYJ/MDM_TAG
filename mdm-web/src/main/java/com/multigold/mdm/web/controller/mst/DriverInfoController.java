package com.multigold.mdm.web.controller.mst;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.DriverInfo;
import com.multigold.mdm.service.api.mst.DriverInfoService;
/**
 * 
 * @author liangdingding
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/driverInfo")
public class DriverInfoController extends BaseCRUDAction<DriverInfo, Integer> {

	
	@Autowired(required=false)
	DriverInfoService<DriverInfo, Integer> driverInfoService;
	
	@Override
	public DriverInfoService<DriverInfo, Integer> getBaseService() {
		return driverInfoService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, DriverInfo t, String operateFlag) {
		
	}









}
