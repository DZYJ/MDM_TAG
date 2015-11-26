package com.multigold.mdm.web.controller.address;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multigold.common.constants.Constants;
import com.multigold.common.exception.BaseException;
import com.multigold.common.model.DivisionTree;
import com.multigold.common.service.BaseService;
import com.multigold.common.util.ExportExcel;
import com.multigold.common.util.LogTracking;
import com.multigold.common.util.ReturnCode;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.common.web.springmvc.MessageResolver;
import com.multigold.mdm.entity.address.Division;
import com.multigold.mdm.service.api.address.DivisionService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.google.common.collect.Maps;

/**
 * 
 * @author guoqiushi
 * @param <T>
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/division")
public class DivisionController extends BaseCRUDAction<Division, String> {

	@Autowired
	DivisionService<Division, String> divisionService;

	@Override
	public BaseService<Division, String> getBaseService() {
		return divisionService;
	}

	/**
	 * 获取所有一级区域
	 */
	@RequestMapping(value = "getFirstLevelList", method = RequestMethod.GET)
	@ResponseBody
	public List<Division> getFirstLevelList() {
		return divisionService.getFirstLevel();
	}

	/**
	 * 根据父区域编码获取下级区域
	 * @param parent_division_code
	 * @return
	 */
	@RequestMapping(value = "getChildListByPCode", method = RequestMethod.GET)
	@ResponseBody
	public List<Division> getChildListByPCode(String parent_division_code) {
		return divisionService.getChildListByPCode(parent_division_code);
	}

	/**
	 * 根据区域编码查询
	 * @param divisionCode
	 * @return
	 */
	@RequestMapping(value = "queryByCode", method = RequestMethod.GET)
	@ResponseBody
	public Division queryByCode(String divisionCode) {
		return divisionService.queryByCode(divisionCode);
	}

	@RequestMapping(value = "getTreeList", method = RequestMethod.GET)
	@ResponseBody
	public List<DivisionTree> getTreeList(){
		return divisionService.getTreeList();
	}
	
	@RequestMapping(value = "getChildList", method = RequestMethod.GET)
	@ResponseBody
	public List<DivisionTree> getChildList(String childId){
		return divisionService.getChildList(childId);
	}
	
	@Override
	protected void setDefaultValue(HttpServletRequest request, Division t,String operateFlag) {
		t.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		t.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/**
	 * 导出报表
	 * @param  
	 * @return
	 */
	@RequestMapping(value = "export", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object export(@ModelAttribute Division division, HttpServletRequest request , HttpServletResponse response) {
		String msg="";
		
		List<Division> exportList = divisionService.getExportList(division);

		if(null != exportList && exportList.size()>0){
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd-HH-mm-ss"); //格式不能带冒号，否则转码不成功会出现乱码导致在火狐下保存的文件无后缀名
			String nameDate = sdf.format(date);
	    	String fileName = "数据字典报表"+nameDate+".xls";//设置下载时客户端Excel的名称 
			
			ExportExcel<Division> expor = new ExportExcel<Division>(fileName,exportList.get(0).getHeader(),
					exportList.get(0).getExportFieldNames(),exportList, "yyy-MM-dd HH:mm:ss");
			return expor;
		}else{
			response.setContentType("text/html");  
			msg = "无数据不能导出！";
		}
		return msg;
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> create(final HttpServletRequest request, @ModelAttribute Division division) { 
		LogTracking.debugLog(this.getClass(), "create", "create bean");
		final Map<String, Object> map = Maps.newHashMap();
		try {
			setDefaultValue(request, division, "create");// 临时设置默认值
			
			int i = this.divisionService.createEntity(division);
			if (i > 0) {
				map.put("msg", MessageResolver.getMessage(request,Constants.Message.SUCCESS_SAVE));
				map.put("state", Constants.State.TRUE);
			} else if(i==0) {
				map.put("msg", ReturnCode.codeToMsg(ReturnCode.DIVISION_EXIST));
				map.put("state", Constants.State.FALSE);
			}
			return map;
		} catch (BaseException e) {
			e.printStackTrace();
			map.put("msg", MessageResolver.getMessage(request,Constants.Message.FAILED_SAVE));
			map.put("state", Constants.State.FALSE);
			LogTracking.errorLog(this.getClass(), "create", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogTracking.debugLog(this.getClass(), "create", "create end");
		return map;
	}
}
