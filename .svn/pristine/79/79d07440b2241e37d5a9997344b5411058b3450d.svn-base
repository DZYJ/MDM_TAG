package com.multigold.mdm.web.controller.mst;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.HubProcessMatrix;
import com.multigold.mdm.service.api.mst.HubProcessMatrixService;
/**
 * 
 * @author mayanhu
 * Date:2014/7/25
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/hubProcessMatrix")
public class HubProcessMatrixController extends BaseCRUDAction<HubProcessMatrix, String> {
	
	@Autowired
	HubProcessMatrixService<HubProcessMatrix, String> hubProcessMatrixService;
	

	@Override
	public BaseService<HubProcessMatrix, String> getBaseService() {
		return hubProcessMatrixService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, HubProcessMatrix hubProcessMatrix, String operateFlag) {
		
	}
	
}
