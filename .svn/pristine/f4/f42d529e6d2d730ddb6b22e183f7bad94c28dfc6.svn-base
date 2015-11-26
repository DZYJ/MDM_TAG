package com.multigold.mdm.web.controller.mst;


import java.util.ArrayList;
import java.util.Date;
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
import com.multigold.mdm.entity.mst.MstLspPickupSchedule;
import com.multigold.mdm.service.api.mst.MstLspPickupScheduleService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.upms.entity.security.User;
import com.google.common.collect.Maps;

/**
 * 
 * @author guoqiushi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/pickuptime")
public class PickuptimeController extends BaseCRUDAction<MstLspPickupSchedule, Integer> {

	@Autowired
	MstLspPickupScheduleService<MstLspPickupSchedule, Integer> pickupService;

	@Override
	public BaseService<MstLspPickupSchedule, Integer> getBaseService() {
		return pickupService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, MstLspPickupSchedule t, String operateFlag) {
	}
	

	
	/**
	 * 保存取货时间
	 * 
	 * @return
	 */
	@RequestMapping(value = "savePickuptime", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> savePickuptime(@RequestParam("pickuptimeData") String pickuptimeData,@RequestParam("lspCode") String lspCode,
			HttpServletRequest request) {
		Map<String, Object> map = Maps.newHashMap();
		User user=UserSessionProvider.getUserSerssion(request);
		String insertBy = "";
		if(null!=user ){
			insertBy = user.getName();
		}
		
		if(pickuptimeData !=null && pickuptimeData.length()>0){
			String[] pickuptimes = pickuptimeData.split(";");
			if(pickuptimes!=null && pickuptimes.length>0){
				List<MstLspPickupSchedule> pickupList = new ArrayList<MstLspPickupSchedule>();
				for (int i = 0; i < pickuptimes.length; i++) {
					if(pickuptimes[i]!=null && pickuptimes[i].length()>0){
						String[] putItems = pickuptimes[i].split(",");
						MstLspPickupSchedule pickup = new MstLspPickupSchedule();
						pickup.setLspCode(lspCode);
						pickup.setMasLoc(putItems[0]);
						pickup.setDay(Integer.parseInt(putItems[1]));
						pickup.setTime(putItems[2]);
						pickup.setActive(putItems[3]);
						pickup.setInsertBy(insertBy);
						pickup.setInsertDate(new Date());
						pickup.setModifiedBy(insertBy);
						pickup.setModifiedDate(new Date());
						pickupList.add(pickup);
						
						
					}
				}
				pickupService.insertPickupList(pickupList);
			}
		}
		map.put("state", "success");
		map.put("msg", "保存取货时间成功！");
		return map;
	}
	
	/**
	 * 保存取货时间
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryPickuptime", method = RequestMethod.POST)
	@ResponseBody
	public List<MstLspPickupSchedule> queryPickuptime(@RequestParam("lspCode") String lspCode) {
		List<MstLspPickupSchedule>	pickupList = pickupService.queryListByLsp(lspCode);
		return pickupList;
	}

}
