package com.multigold.mdm.web.controller.mst;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.CarInfo;
import com.multigold.mdm.service.api.mst.CarInfoService;


@Controller
@RequestMapping(value = "${adminPath}/carInfo")
public class CarInfoController extends BaseCRUDAction<CarInfo, Integer> {

	
	@Autowired(required=true)
	CarInfoService<CarInfo, Integer> carInfoService;
	
	@Override
	public BaseService<CarInfo, Integer> getBaseService() {
		return carInfoService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, CarInfo t, String operateFlag) {
		
	}

}
