package com.multigold.mdm.web.controller.sales;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.multigold.common.constants.Constants;
import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.common.web.springmvc.MessageResolver;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.t6.service.api.sales.SalesService;
import com.multigold.t6.vo.sales.SalesCheckVO;
import com.multigold.t6.vo.sales.SalesReturnVO;

/**
 *  
 * @author yangjun
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesCheck")
public class SalesCheckController extends BaseCRUDAction<SalesCheckVO, String>{

	@Autowired
	SalesService<SalesCheckVO, String> salesService; 
	@Override
	public BaseService<SalesCheckVO, String> getBaseService() {
		return salesService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, SalesCheckVO salesCheckVO,
			String operateFlag) {
		salesCheckVO.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		salesCheckVO.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/*
	 * @Title: salesCheck 
	 * @Description:  修改html名称与权限系统做关联
	 * @return String
	 */
	@RequestMapping(value = "salesCheck", method = RequestMethod.GET)
	public String salesCheckList() {
		return viewName("salesCheck");
	}
	
	/*
	 * 销售对账单   查询条件
	 * 
	 */
	@RequestMapping(value = "salesCheckLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSalesCheckLists(HttpServletRequest request, String paymentCode,String checkStatus, Date timeFrom, Date timeTo,Long page, Integer rows) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!StringUtils.isEmpty(paymentCode)) {
			map.put("paymentCode", paymentCode);
		}
		if (!StringUtils.isEmpty(timeFrom)) {
			map.put("timeFrom", sdf.format(timeFrom));
		}
		if (!StringUtils.isEmpty(timeTo)) {
			map.put("timeTo", sdf.format(timeTo)+" 23:59:59");
		}
		if (!StringUtils.isEmpty(checkStatus)) {
			map.put("checkStatus", checkStatus);
		}
		map.put("page", page);
		map.put("rows", rows);
		resultMap= salesService.getSalesCheckList(map);
		return resultMap;
	}
	
	/*
	 * 对账单核销
	 * @sales
	 */
	@RequestMapping(value = "checkAndConfirm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkAndConfirm(HttpServletRequest request, String id, String checkStatus) {
		final Map<String, Object> map = Maps.newHashMap();
		List<String> ids = new ArrayList<String>();
		ids.add(id);
		SalesCheckVO salesCheckVO = new SalesCheckVO();
		salesCheckVO.setId(Integer.parseInt(id));
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		salesCheckVO.setModifier(strAccount);
		salesCheckVO.setUpdateTd(new Date());
		salesCheckVO.setStatus(Constants.SalesAdmin.SALESCHECK_STATUS_02);
		try {
		salesService.updateSalesCheckStatus(salesCheckVO);
		map.put("msg", MessageResolver.getMessage(request,
				Constants.Message.SUCCESS_SAVE));
		map.put("state", Constants.State.TRUE);}
		catch (Exception e) {
			e.printStackTrace();
			map.put("msg", MessageResolver.getMessage(request,
					Constants.Message.FAILED_UPDATE));
			map.put("state", Constants.State.FALSE);
		}
		return map;
	}
	
	/*
	 * 获取制单日期
	 * @return
	 */
	@RequestMapping(value = "getDateFromSession", method = RequestMethod.POST)  
	@ResponseBody 
	public Map<String, Object> getDateFromSession(HttpServletRequest request) {
		Map<String, Object> resultMap = Maps.newHashMap();
		String ticketDate = (String) request.getSession(true).getAttribute("ticketDate");
		if(ticketDate==null){
			ticketDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		System.out.println("createTd>>>>>>"+ticketDate);
		resultMap.put("ticketDate" , ticketDate );
		return resultMap;
	}
	/*
	 * 加载下拉列表：结算科目表 结算方式分部现金等
	 * 
	 * @param response
	 * 
	 */
	@RequestMapping(value = "querySettles", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getQuerySettles(HttpServletResponse response) {
		List<Map<String, Object>> list = Lists.newArrayList();
		try {
			List<SalesReturnVO> queryList = salesService.getQuerySettles();
			if ((null != queryList) && (queryList.size() > 0)) {
				for (SalesReturnVO systemModel : queryList) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("cCode", systemModel.getCcode());
					map.put("cSSName", systemModel.getCsSName());
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
