package com.multigold.mdm.web.controller.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multigold.common.service.BaseService;
import com.multigold.common.util.JsonUtil;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.t6.service.api.query.PaymentSettmentQueryService;
import com.multigold.t6.vo.po.PaymentSettlementVO;
import com.google.common.collect.Maps;

/**
 * 
 * @author yangjun
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/query/paymentSettmentQuery")
public class PaymentSettmentQueryController extends BaseCRUDAction<PaymentSettlementVO, String>{

	@Autowired
	PaymentSettmentQueryService<PaymentSettlementVO, String> paymentSettmentQueryService;
	
	@Override
	public BaseService<PaymentSettlementVO, String> getBaseService() {
		return paymentSettmentQueryService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, PaymentSettlementVO paymentSettlementVO,
			String operateFlag) {
		paymentSettlementVO.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		paymentSettlementVO.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/*
	 * @Title: paymentSettmentQuery 
	 * @Description: 采购付款单列表
	 * @return String
	 */
	@RequestMapping(value = "paymentSettmentQuery", method = RequestMethod.GET)
	public String paymentSettmentQueryList() {
		return viewName("paymentSettmentQuery");
	}
	
	/*
	 * 获取付款单列表查询条件
	 * @return
	 */
	@RequestMapping(value = "paymentSettmentQueryLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPaymentSettmentQueryLists(HttpServletRequest request, String paymentCode, String venCode, String status, Long page, String type,String payType,Integer rows) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		if (!StringUtils.isEmpty(paymentCode)) {
			map.put("paymentCode", paymentCode);
		}
		if (!StringUtils.isEmpty(venCode)) {
			map.put("venCode", venCode);
		}
		if (!StringUtils.isEmpty(status)) {
			map.put("status", status);
		}
		if (!StringUtils.isEmpty(payType)) {
			map.put("payType", payType);
		}
		if (!StringUtils.isEmpty(type)) {
			map.put("type", type);
		}
		
		map.put("page", page);
		map.put("rows", rows);

		resultMap= paymentSettmentQueryService.getPaymentSettmentQueryList(map);
		return resultMap;
	}
	
	@RequestMapping(value = "commitByIds", method = RequestMethod.POST)
	@ResponseBody
	public void getCommitByIds(HttpServletRequest request,String rowsData) {

		List<PaymentSettlementVO> psvList  = new ArrayList<PaymentSettlementVO>();
		if(null!=rowsData && !("").equals(rowsData)){
			psvList = (List<PaymentSettlementVO>)JsonUtil.jsonToList(rowsData, PaymentSettlementVO.class);
		}
		for (PaymentSettlementVO p : psvList) {
			p.setStatus(1);
			paymentSettmentQueryService.commitByIds(p);
		}
		
		
	}
	
	@RequestMapping(value = "queryInvoiceLists", method = RequestMethod.GET)
	@ResponseBody
	public List<PaymentSettlementVO> queryInvoiceLists(HttpServletRequest request,int id) {

		PaymentSettlementVO paymentSettlementVO = new PaymentSettlementVO();
		paymentSettlementVO.setId(id);
		List<PaymentSettlementVO> list = paymentSettmentQueryService.getPaymentSettmentQuery(paymentSettlementVO);
		
		return list;
		
	}
}
