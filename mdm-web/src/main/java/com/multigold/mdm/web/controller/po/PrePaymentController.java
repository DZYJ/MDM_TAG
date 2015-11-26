package com.multigold.mdm.web.controller.po;

import java.math.BigDecimal;
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

import com.multigold.common.constants.Constants;
import com.multigold.common.service.BaseService;
import com.multigold.common.util.JsonUtil;
import com.multigold.common.util.TimeUtil;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.common.web.springmvc.MessageResolver;


import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.t6.service.api.po.PrePaymentService;
import com.multigold.t6.vo.po.PaymentSettlementVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 
 * @author yangjun
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/po/prePayment")
public class PrePaymentController extends BaseCRUDAction<PaymentSettlementVO, String>{

	@Autowired
	PrePaymentService<PaymentSettlementVO, String> prePaymentService;
	
	@Override
	public BaseService<PaymentSettlementVO, String> getBaseService() {
		return prePaymentService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, PaymentSettlementVO paymentSettlementVO,
			String operateFlag) {
		paymentSettlementVO.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		paymentSettlementVO.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/*
	 * @Title: PrePayment 
	 * @Description: 采购预付单列表 修改html的名称
	 * @return String
	 */
	@RequestMapping(value = "prePayment", method = RequestMethod.GET)
	public String prePaymentList() {
		return viewName("prePayment");
	}
	
	/*
	 * 获取预付单列表 查询条件
	 * 
	 */
	@RequestMapping(value = "prePaymentLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPrePaymentLists(HttpServletRequest request, String paymentCode,  String redFlag,String venCode, String status, Long page, Integer rows) {
		//public List<PaymentSettlementVO> getPaymentSettlementLists(HttpServletRequest request, String paymentCode, String venCode, String status, Long page, Integer rows) {
		//List<RdrecordVO> rdrecordList = new ArrayList<RdrecordVO>();
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
		if (!StringUtils.isEmpty(redFlag)) {
			map.put("redFlag", redFlag);
		}
		map.put("type", 1);//预付款
		map.put("payType", 1);//采购
		map.put("page", page);
		map.put("rows", rows);
		resultMap= prePaymentService.getPrePaymentList(map);
		return resultMap;
		
	}
	
	
	
	/*
	 * @Title: PrePayment 
	 * @Description: 采购预付单列表 展示详情列表
	 * @return String
	 */
	@RequestMapping(value = "createPrePayment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createPrePayment(HttpServletRequest request, String paymentCode, String venCode, String venName,
			String oppSubject, String clearingForm, String settlementSubject, String vendorBank, String vendorAccount,
			String itemCode, String sum, String digest,String prePaymentID, String invoiceID,String createTd) {
		final Map<String, Object> map = Maps.newHashMap();
		PaymentSettlementVO paymentSettlementVO = new PaymentSettlementVO();
		if (!StringUtils.isEmpty(paymentCode)) {
			paymentSettlementVO.setPaymentCode(paymentCode);
		}
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!StringUtils.isEmpty(createTd)) {
			paymentSettlementVO.setCreateTd(TimeUtil.transferString2Date(createTd, "yyyy-MM-dd"));
			request.getSession(true).setAttribute("createTd",createTd);
		}
		
		if (!StringUtils.isEmpty(venCode)) {
			paymentSettlementVO.setVenCode(venCode);
		}
		if (!StringUtils.isEmpty(venName)) {
			paymentSettlementVO.setVenName(venName);
		}
		if (!StringUtils.isEmpty(oppSubject)) {
			paymentSettlementVO.setOppSubject(oppSubject);
		}
		if (!StringUtils.isEmpty(clearingForm)) {
			paymentSettlementVO.setClearingForm(clearingForm);
		}
		if (!StringUtils.isEmpty(settlementSubject)) {
			paymentSettlementVO.setSettlementSubject(settlementSubject);
		}
		if (!StringUtils.isEmpty(vendorBank)) {
			paymentSettlementVO.setVendorBank(vendorBank);
		}
		if (!StringUtils.isEmpty(vendorAccount)) {
			paymentSettlementVO.setVendorAccount(vendorAccount);
		}
		if (!StringUtils.isEmpty(itemCode)) {
			paymentSettlementVO.setItemCode(itemCode);
		}
		if (!StringUtils.isEmpty(sum)) {
			paymentSettlementVO.setSum(new BigDecimal(sum));
		}
		if (!StringUtils.isEmpty(digest)) {
			paymentSettlementVO.setDigest(digest);
		}
		
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		paymentSettlementVO.setInputer(strAccount);
		paymentSettlementVO.setCreateTd(new Date());
		paymentSettlementVO.setStatus(1);//新建
		paymentSettlementVO.setPayType(1);//采购
		paymentSettlementVO.setType(2);//应付款
		PaymentSettlementVO psvVenName = prePaymentService.queryVenName(paymentSettlementVO);
		paymentSettlementVO.setVenName(psvVenName.getcVenName());
		
		try {
			//获取最大ID
			int MaxPrePaymentNo = prePaymentService.getMaxPrePaymentNo();
			MaxPrePaymentNo++;
			paymentSettlementVO.setId(MaxPrePaymentNo);
			
			prePaymentService.createPrePayment(paymentSettlementVO);
			map.put("msg", MessageResolver.getMessage(request,
					Constants.Message.SUCCESS_SAVE));
			map.put("state", Constants.State.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", MessageResolver.getMessage(request,
					Constants.Message.FAILED_UPDATE));
			map.put("state", Constants.State.FALSE);
		}
		
		return map;
	}
	/*
	 * @Title: PrePayment 
	 * @Description: 更改
	 * 
	 */
	@RequestMapping(value = "updatePrePayment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePrePayment(HttpServletRequest request, String id, String paymentCode, String venCode, String venName,
			String oppSubject, String clearingForm, String settlementSubject, String vendorBank, String vendorAccount,
			String itemCode, String sum, String digest) {
		final Map<String, Object> map = Maps.newHashMap();
		PaymentSettlementVO paymentSettlementVO = new PaymentSettlementVO();
		if (!StringUtils.isEmpty(id)) {
			paymentSettlementVO.setId(Integer.parseInt(id));
		}
		if (!StringUtils.isEmpty(paymentCode)) {
			paymentSettlementVO.setPaymentCode(paymentCode);
		}
		if (!StringUtils.isEmpty(venCode)) {
			paymentSettlementVO.setVenCode(venCode);
		}
		if (!StringUtils.isEmpty(venName)) {
			paymentSettlementVO.setVenName(venName);
		}
		if (!StringUtils.isEmpty(oppSubject)) {
			paymentSettlementVO.setOppSubject(oppSubject);
		}
		if (!StringUtils.isEmpty(clearingForm)) {
			paymentSettlementVO.setClearingForm(clearingForm);
		}
		if (!StringUtils.isEmpty(settlementSubject)) {
			paymentSettlementVO.setSettlementSubject(settlementSubject);
		}
		if (!StringUtils.isEmpty(vendorBank)) {
			paymentSettlementVO.setVendorBank(vendorBank);
		}
		if (!StringUtils.isEmpty(vendorAccount)) {
			paymentSettlementVO.setVendorAccount(vendorAccount);
		}
		if (!StringUtils.isEmpty(itemCode)) {
			paymentSettlementVO.setItemCode(itemCode);
		}
		if (!StringUtils.isEmpty(sum)) {
			paymentSettlementVO.setSum(new BigDecimal(sum));
		}
		if (!StringUtils.isEmpty(digest)) {
			paymentSettlementVO.setDigest(digest);
		}
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		paymentSettlementVO.setModifier(strAccount);
		paymentSettlementVO.setCreateTd(new Date());
		paymentSettlementVO.setStatus(1);//新建
		paymentSettlementVO.setPayType(1);//采购
		paymentSettlementVO.setType(1);//预付款
		PaymentSettlementVO psvVenName = prePaymentService.queryVenName(paymentSettlementVO);
		paymentSettlementVO.setVenName(psvVenName.getcVenName());
		
		try {
			prePaymentService.updatePrePayment(paymentSettlementVO);
			map.put("msg", MessageResolver.getMessage(request,
					Constants.Message.SUCCESS_SAVE));
			map.put("state", Constants.State.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", MessageResolver.getMessage(request,
					Constants.Message.FAILED_UPDATE));
			map.put("state", Constants.State.FALSE);
		}
		return map;
	}
	
	
	
	/*
	 * 加载下拉列表：供应商表
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "prePaymentVenNameList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getPrePaymentVenNameList(HttpServletResponse response) {
		List<Map<String, Object>> list = Lists.newArrayList();
		try {
			List<PaymentSettlementVO> queryList = prePaymentService.queryVenList();
			if ((null != queryList) && (queryList.size() > 0)) {
				for (PaymentSettlementVO systemModel : queryList) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("venCode", systemModel.getcVenCode());
					map.put("venName", systemModel.getcVenName());
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/*
	 * 提交
	 *
	 */
	@RequestMapping(value = "commitByIds", method = RequestMethod.POST)
	@ResponseBody
	public void getCommitByIds(HttpServletRequest request,String rowsData) {
		List<PaymentSettlementVO> psvList  = new ArrayList<PaymentSettlementVO>();
		if(null!=rowsData && !("").equals(rowsData)){
			psvList = (List<PaymentSettlementVO>)JsonUtil.jsonToList(rowsData, PaymentSettlementVO.class);
		}
		for (PaymentSettlementVO p : psvList) {
			p.setStatus(1);
			prePaymentService.commitByIds(p);
		}
		
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
			List<PaymentSettlementVO> queryList = prePaymentService.getQuerySettles();
			if ((null != queryList) && (queryList.size() > 0)) {
				for (PaymentSettlementVO systemModel : queryList) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("cCode", systemModel.getcCode());
					map.put("cSSName", systemModel.getcSSName());
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	/*
	 * 获取制单日期
	 * @return
	 */
	@RequestMapping(value = "getDateFromSession", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDateFromSession(HttpServletRequest request) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		
		//resultMap= prePaymentService.getPrePaymentList(map);
		String ticketDate = (String) request.getSession(true).getAttribute("ticketDate");
		System.out.println("createTd>>>>>>"+ticketDate);
		resultMap.put("ticketDate" , ticketDate );
		return resultMap;
		
	}
}
