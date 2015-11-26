/**
 * 
 */
package com.multigold.mdm.web.controller.mst;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.MstLsp;
import com.multigold.mdm.service.api.mst.MstLspService;

/**
 * @author zhanghaiyang
 * 承运商基础信息维护
 */
@Controller
@RequestMapping(value = "${adminPath}/mstLsp")
public class MstLspController extends BaseCRUDAction<MstLsp, Integer> {

	@Autowired
	MstLspService<MstLsp, Integer> mstLspService;
	
	@Override
	public BaseService<MstLsp, Integer> getBaseService() {
		return mstLspService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, MstLsp mstLsp, String operateFlag) {
		
	}

}
