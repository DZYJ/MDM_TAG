/**
 * 
 */
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
import com.multigold.mdm.entity.mst.LspDayHalfInfo;
import com.multigold.mdm.service.api.mst.LspDayHalfInfoService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.upms.entity.security.User;
import com.google.common.collect.Maps;

/**
 * @author mayanhu
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/lspDayHalfInfo")
public class LspDayHalfInfoController extends BaseCRUDAction<LspDayHalfInfo, String> {

	@Autowired
	LspDayHalfInfoService<LspDayHalfInfo, String> lspDayHalfInfoService;
	

	@Override
	public BaseService<LspDayHalfInfo, String> getBaseService() {
		return lspDayHalfInfoService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, LspDayHalfInfo lspDayHalfInfo, String operateFlag) {
		
	}

	/**
	 * 保存取货时间
	 * 
	 * @return
	 */
	@RequestMapping(value = "saveDayHalf", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveDayHalf(@RequestParam("dayHalfData") String dayHalfData,
			@RequestParam("lspCode") String lspCode,HttpServletRequest request) {
		User user=UserSessionProvider.getUserSerssion(request);
		String insertBy = "";
		if(null!=user ){
			insertBy = user.getName();
		}
		Map<String, Object> map = Maps.newHashMap();
		if(dayHalfData !=null && dayHalfData.length()>0){
			String[] dayHalfs = dayHalfData.split(";");
			if(dayHalfs!=null && dayHalfs.length>0){
				List<LspDayHalfInfo> lspDayHalfInfoList = new ArrayList<LspDayHalfInfo>();
				for (int i = 0; i < dayHalfs.length; i++) {
					if(dayHalfs[i]!=null && dayHalfs[i].length()>0){
						String[] putItems = dayHalfs[i].split(",");
						
						LspDayHalfInfo dayHalf = new LspDayHalfInfo();
						dayHalf.setBuid(8270);
						dayHalf.setLspCode(lspCode);
						dayHalf.setExFromDate(putItems[0]);
						dayHalf.setExToDate(putItems[1]);
						dayHalf.setDlDate(putItems[2]);
						dayHalf.setInterDayFlg(putItems[3]);
						dayHalf.setNextDayFlg(putItems[4]);
						dayHalf.setIsEffectiveFlg(putItems[5]);
						dayHalf.setId(Integer.parseInt(putItems[6]));
						dayHalf.setInsertBy(insertBy);
						dayHalf.setInsertDate(new Date());
						dayHalf.setModifiedBy(insertBy);
						dayHalf.setModifiedDate(new Date());
						
						lspDayHalfInfoList.add(dayHalf);
					}
				}
				if(null != lspDayHalfInfoList && lspDayHalfInfoList.size()>0)
				lspDayHalfInfoService.insertLspDayHalfList(lspDayHalfInfoList);
			}
		}
		map.put("state", "success");
		map.put("msg", "保存半日达信息成功！");
		return map;
	}
	
	/**
	 * 保存取货时间
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryDayHalf", method = RequestMethod.POST)
	@ResponseBody
	public List<LspDayHalfInfo> queryListByLsp(@RequestParam("lspCode") String lspCode) {
		List<LspDayHalfInfo> dayHalfList = lspDayHalfInfoService.queryListByLsp(lspCode);
		return dayHalfList;
	}

}
