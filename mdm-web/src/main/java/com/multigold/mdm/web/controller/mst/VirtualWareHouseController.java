package com.multigold.mdm.web.controller.mst;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.MstLocation;
import com.multigold.mdm.service.api.mst.MstLocationService;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value = "${adminPath}/virtualWareHouse")
public class VirtualWareHouseController extends BaseCRUDAction<MstLocation, Integer>{

	
	@Autowired(required=true)
	MstLocationService<MstLocation, Integer> mstLocationService;
	
	public BaseService<MstLocation, Integer> getBaseService() {
		return mstLocationService;
	}

	protected void setDefaultValue(HttpServletRequest request, MstLocation t, String operateFlag) {
		
	}
	

	/**
	 * 列表 GET 获取页面 list.htm
	 * 
	 * @return
	 */
	@RequestMapping(value = "virtualWareHouseList", method = RequestMethod.GET)
	public String list() {
		return viewName("virtualWareHouseList");
	}

	
	
	/**
	 * json列表 POST 获取数据 list.json
	 * 
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "virtualWareHouseList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(Long page, Integer rows, MstLocation t,
			String sort,String order) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			map = this.getBaseService().pageQueryEntity(page, rows, t, sort, order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
