package com.multigold.mdm.web.controller.sales;

import java.text.SimpleDateFormat;
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
import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.t6.service.api.sales.SalesService;
import com.multigold.t6.vo.po.PaymentSettlementVO;
import com.multigold.t6.vo.sales.SalesCheckVO;

/**
 *  
 * @author yangjun
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesCheckAll")
public class SalesCheckAllController extends BaseCRUDAction<SalesCheckVO, String>{

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
	@RequestMapping(value = "salesCheckAll", method = RequestMethod.GET)
	public String salesCheckAllList() {
		return viewName("salesCheckAll");
	}
	
	/*
	 * 销售对账单   查询条件
	 * 
	 */
	@RequestMapping(value = "salesCheckAllLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSalesCheckAllLists(HttpServletRequest request, String paymentCode,String ccusName,String checkStatus, Date timeFrom, Date timeTo,Long page, Integer rows) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		request.getParameter("checkStatus");
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
		if (!StringUtils.isEmpty(ccusName)) {
			map.put("ccusName", ccusName);
		}
		map.put("page", page);
		map.put("rows", rows);
		resultMap= salesService.getSalesCheckAllList(map);
		return resultMap;
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
	 * 加载下拉列表：客戶名称
	 * @return
	 */
	@RequestMapping(value = "salesCheckAllCusNameList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getSalesCheckAllCusNameList(HttpServletResponse response,String index) {
		List<Map<String, Object>> list = Lists.newArrayList();
		try {
			List<SalesCheckVO> queryList = salesService.queryCusNameList();
			if ((null != queryList) && (queryList.size() > 0)) {
				for (SalesCheckVO systemModel : queryList) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("cusName", systemModel.getCcusName());
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
