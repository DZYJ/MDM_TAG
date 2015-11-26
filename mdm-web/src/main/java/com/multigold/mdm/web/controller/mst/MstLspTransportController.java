package com.multigold.mdm.web.controller.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.address.Division;
import com.multigold.mdm.entity.mst.MstLspTransport;
import com.multigold.mdm.service.api.address.DivisionService;
import com.multigold.mdm.service.api.mst.MstLspTransportService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.upms.entity.security.User;
import com.google.common.collect.Maps;
/**
 * 
 * @author mayanhu
 * Date:2014/8/11
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/transport")
public class MstLspTransportController extends BaseCRUDAction<MstLspTransport, Integer> {
	
	@Autowired
	MstLspTransportService<MstLspTransport, Integer> mstLspTransportService;
	
	@Autowired
	DivisionService<Division, Integer> divisionService;
	

	@Override
	public BaseService<MstLspTransport, Integer> getBaseService() {
		return mstLspTransportService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request,MstLspTransport mstLspTransport, String operateFlag) {
		if(operateFlag.equalsIgnoreCase("update")){
			User user=UserSessionProvider.getUserSerssion(request);
			String modifiedBy = "";
			if(null!=user ){
				modifiedBy = user.getName();
			}
			mstLspTransport.setModifiedBy(modifiedBy);
		}
	}
	
	/**
	 * 批量添加运力
	 * 
	 * @return
	 */
	@RequestMapping(value = "insertTransportList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertTransportList(@RequestParam("lspCode") String lspCode,@RequestParam("priority") String priority,@RequestParam("maxOrdQty") String maxOrdQty,
			@RequestParam("minDivisor") String minDivisor,@RequestParam("minAmount") String minAmount,@RequestParam("maxAmount") String maxAmount,
			@RequestParam("orderDownloadBeginTime") String orderDownloadBeginTime,@RequestParam("orderDownloadEndTime") String orderDownloadEndTime,
			@RequestParam("divisionCodes[]") String divisionCodes[],HttpServletRequest request) {
		List<Division> childList = new ArrayList<Division>();
		
		if(divisionCodes!=null && divisionCodes.length>0){
			List<Division> divisionList = new ArrayList<Division>();
			if(divisionCodes[0].contains(",")){
				for (int i = 0; i < divisionCodes.length; i++) {
					if(divisionCodes[i]!=null && divisionCodes[i].length()>0){
						
						String[] putItems = divisionCodes[i].split(",");
						Division division = new Division();
						division.setDivisionCode(putItems[0]);
						division.setDivLevel(Integer.parseInt(putItems[1]));
						divisionList.add(division);
					}
				}
			}else{
					Division division = new Division();
					division.setDivisionCode(divisionCodes[0]);
					division.setDivLevel(Integer.parseInt(divisionCodes[1]));
					divisionList.add(division);
			}
			childList = divisionService.recursiveTree3(divisionList,childList);
		}
		
		List<MstLspTransport> transportList = new ArrayList<>();
		for (int i = 0; i < childList.size(); i++) {
			MstLspTransport transport = new MstLspTransport();
			transport.setLsp(lspCode);
			transport.setPriority(Integer.parseInt(priority));
			transport.setMaxOrdQty(Integer.parseInt(maxOrdQty));
			transport.setMinDivisor(Integer.parseInt(minDivisor));
			transport.setMinAmount(Double.parseDouble(minAmount));
			transport.setMaxAmount(Double.parseDouble(maxAmount));
			transport.setOrderDownloadBeginTime(orderDownloadBeginTime);
			transport.setOrderDownloadEndTime(orderDownloadEndTime);
			transport.setDivisionCode(childList.get(i).getDivisionCode());
			transport.setDaId("DA"+childList.get(i).getDivisionCode());
			User user=UserSessionProvider.getUserSerssion(request);
			String insertBy = "";
			if(null!=user ){
				insertBy = user.getName();
			}
			transport.setInsertBy(insertBy);
			
			transportList.add(transport);
		}
		
		mstLspTransportService.insertTransportList(transportList);
		Map<String, Object> map = Maps.newHashMap();
		map.put("state", "success");
		map.put("msg", "保存成功！");
		return map;
	}
	
}
