package com.multigold.mdm.web.controller.mst;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multigold.common.service.BaseService;
import com.multigold.common.util.LogTracking;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.Vessel;
import com.multigold.mdm.service.api.mst.VesselService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.google.common.collect.Maps;

/**
 * 容器
 * @author ouyang
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/vessel")
public class VesselController extends BaseCRUDAction<Vessel, Integer> {

	@Autowired
	VesselService<Vessel, Integer> vesselService;

	@Override
	public BaseService<Vessel, Integer> getBaseService() {
		return vesselService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, Vessel t, String operateFlag) {
		
	}
	
	/**
	 * 获取容器信息列表
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(Long page, Integer rows, Vessel t,
			String sort, String order) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			map = this.getBaseService().pageQueryEntity(page, rows, t, sort,
					order);
		} catch (Exception e) {
			e.printStackTrace();
			LogTracking.errorLog(this.getClass(), "list", e.getMessage());
		}

		//	修改时间格式  方便前台接收
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		@SuppressWarnings("unchecked")
		List<Vessel> list = (List<Vessel>) map.get("rows");
		for (Vessel vesselInfo : list) {
			
			/*if(vesselInfo.getInsertDate()!=null){
				vesselInfo.setInsertDateStr(dft.format(vesselInfo.getInsertDate()));
			}
			if(vesselInfo.getModifiedDate()!=null){
				vesselInfo.setModifiedDateStr(dft.format(vesselInfo.getModifiedDate()));
			}*/
		}
		map.put("rows", list);
		
		return map;
	}
	

}
