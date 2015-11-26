package com.multigold.mdm.web.controller.mst;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.MasLoc;
import com.multigold.mdm.service.api.mst.SiteService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;

/**
 * 
 * @author guoqiushi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/site")
public class SiteController extends BaseCRUDAction<MasLoc, Integer> {

	@Autowired
	SiteService<MasLoc, Integer> siteService;

	@Override
	public BaseService<MasLoc, Integer> getBaseService() {
		return siteService;
	}

	@RequestMapping(value = "getSiteByCode", method = RequestMethod.POST)
	@ResponseBody
	public MasLoc getSiteByCode(String mstMasLoc){
		return siteService.queryByCode(mstMasLoc);
	}
	
	@Override
	protected void setDefaultValue(HttpServletRequest request, MasLoc t, String operateFlag) {
		t.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		t.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
		
		if(operateFlag.equalsIgnoreCase("create")){
			StringBuffer siteCode = new StringBuffer();
			List<MasLoc> sites = siteService.queryList();
			if(sites!=null){
				if(sites.size()>0){
					String lastCode = sites.get(sites.size()-1).getMasLoc();
					String prefix = lastCode.substring(0,1);
					//0的位数
					char[] other = lastCode.substring(1).toCharArray();
					//0串
					StringBuffer zero = new StringBuffer();
					//数字串
					StringBuffer number = new StringBuffer();
					
					for(char c:other){
						if(c=='0'){
							zero.append(c);
						}else{
							number.append(c);
						}
					}
					int count = Integer.parseInt(number.toString())+1;
					siteCode.append(prefix).append(zero).append(count);
					t.setMasLoc(siteCode.toString());
				}else{
					t.setMasLoc("F001");
				}
			}
		}
	}
	
}
