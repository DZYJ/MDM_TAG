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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.multigold.common.constants.Constants;
import com.multigold.common.service.BaseService;
import com.multigold.common.util.JsonUtil;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.common.web.springmvc.MessageResolver;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.t6.service.api.po.PaymentSettlementService;
import com.multigold.t6.vo.po.InvoiceVO;
import com.multigold.t6.vo.po.MgPayAndInvVO;
import com.multigold.t6.vo.po.MgRdAndInvVO;
import com.multigold.t6.vo.po.PaymentSettlementVO;
import com.multigold.upms.entity.security.User;

@Controller
@RequestMapping(value = "${adminPath}/po/paymentSettlement")
public class PaymentSettlementController extends BaseCRUDAction<PaymentSettlementVO, String>{

	@Autowired
	PaymentSettlementService<PaymentSettlementVO, String> paymentSettlementService;
	
	@Override
	public BaseService<PaymentSettlementVO, String> getBaseService() {
		return paymentSettlementService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, PaymentSettlementVO paymentSettlementVO,
			String operateFlag) {
		paymentSettlementVO.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		paymentSettlementVO.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/*
	 * @Title: paymentSettlement 
	 * @Description: 付款结算列表
	 * @return String
	 */
	@RequestMapping(value = "paymentSettlementList", method = RequestMethod.GET)
	public String paymentSettlementList() {
		return viewName("list");
	}
	
	/*
	 * 获取付款结算列表
	 * @return
	 */
	@RequestMapping(value = "paymentSettlementLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPaymentSettlementLists(HttpServletRequest request, String paymentCode, String venCode, String status, String redFlag, Long page, Integer rows) {
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
		map.put("type", Constants.PaymentAdmin.TYPE_2);
		map.put("payType", Constants.PaymentAdmin.PAY_TYPE_1);//采购
		map.put("page", page);
		map.put("rows", rows);
		resultMap= paymentSettlementService.getPaymentSettlementList(map);
		return resultMap;
	}
	/*
	 * 获取付款结算列表
	 * @return
	 */
	@RequestMapping(value = "updateQueryPaymentSettlementLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateQueryPaymentSettlementLists(HttpServletRequest request, int id) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		resultMap= paymentSettlementService.updateQueryPaymentSettlementLists(id);
		return resultMap;
	}
	
	/*
	 * @Title: paymentSettlement 
	 * @Description: 插入付款结算
	 * @return String
	 */
	@RequestMapping(value = "createPaymentSettlement", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createPaymentSettlement(HttpServletRequest request, String paymentCode1, String venCode1, String venName1,
			String oppSubject1, String clearingForm1, String settlementSubject1, String vendorBank1, String vendorAccount1,
			String itemCode1, String sum1, String digest1,String prePaymentID1, String invoiceID1, Date ticketDate1, String redFlag1) {
		final Map<String, Object> map = Maps.newHashMap();
		PaymentSettlementVO paymentSettlementVO = new PaymentSettlementVO();
		if (!StringUtils.isEmpty(paymentCode1)) {
			paymentSettlementVO.setPaymentCode(paymentCode1);
		}
		if (!StringUtils.isEmpty(venCode1)) {
			paymentSettlementVO.setVenCode(venCode1);
		}
		if (!StringUtils.isEmpty(venName1)) {
			paymentSettlementVO.setVenName(venName1);
		}
		if (!StringUtils.isEmpty(oppSubject1)) {
			paymentSettlementVO.setOppSubject(oppSubject1);
		}
		if (!StringUtils.isEmpty(clearingForm1)) {
			paymentSettlementVO.setClearingForm(clearingForm1);
		}
		if (!StringUtils.isEmpty(settlementSubject1)) {
			paymentSettlementVO.setSettlementSubject(settlementSubject1);
		}
		if (!StringUtils.isEmpty(vendorBank1)) {
			paymentSettlementVO.setVendorBank(vendorBank1);
		}
		if (!StringUtils.isEmpty(vendorAccount1)) {
			paymentSettlementVO.setVendorAccount(vendorAccount1);
		}
		if (!StringUtils.isEmpty(itemCode1)) {
			paymentSettlementVO.setItemCode(itemCode1);
		}
		if (!StringUtils.isEmpty(sum1)) {
			paymentSettlementVO.setSum(new BigDecimal(sum1));
		}
		if (!StringUtils.isEmpty(digest1)) {
			paymentSettlementVO.setDigest(digest1);
		}
		if (!StringUtils.isEmpty(prePaymentID1)) {
			paymentSettlementVO.setPrePaymentID(prePaymentID1);
		}
		if (!StringUtils.isEmpty(invoiceID1)) {
			paymentSettlementVO.setInvoiceID(invoiceID1);
		}
		if (!StringUtils.isEmpty(redFlag1)) {
			paymentSettlementVO.setRedFlag(redFlag1);
		}
		if (!StringUtils.isEmpty(ticketDate1)) {
			paymentSettlementVO.setTicketDate(ticketDate1);
		}
		if (!StringUtils.isEmpty(invoiceID1)) {
			paymentSettlementVO.setInvoiceID(invoiceID1);
		}
		if (!StringUtils.isEmpty(prePaymentID1)) {
			paymentSettlementVO.setPrePaymentID(prePaymentID1);
		}
		/*User user = new User();
		user.setTimezone(timezone);
		UserSessionProvider.setUserSerssion(request, user);*/
		/*String aa = UserSessionProvider.getUserSerssion(request).getName();*/
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		paymentSettlementVO.setInputer(strAccount);
		paymentSettlementVO.setCreateTd(new Date());
		paymentSettlementVO.setStatus(Constants.PaymentAdmin.PAYMENT_STATUS_1);
		paymentSettlementVO.setPayType(Constants.PaymentAdmin.PAY_TYPE_1);
		paymentSettlementVO.setType(Constants.PaymentAdmin.TYPE_2);
		
		try {
			//插入付款表信息
			paymentSettlementService.insertPaymentSettlement(paymentSettlementVO);
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
	 * @Title: paymentSettlement 
	 * @Description: 更改
	 * @return 
	 */
	@RequestMapping(value = "updatePaymentSettlement", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePaymentSettlement(HttpServletRequest request, String id, String paymentCode2, String venCode2, String venName2,
			String oppSubject2, String clearingForm2, String settlementSubject2, String vendorBank2, String vendorAccount2,
			String itemCode2, String sum2, String digest2, String prePaymentID2, String invoiceID2, Date ticketDate2, String redFlag2) {
		final Map<String, Object> map = Maps.newHashMap();
		PaymentSettlementVO paymentSettlementVO = new PaymentSettlementVO();
		if (!StringUtils.isEmpty(id)) {
			paymentSettlementVO.setId(Integer.parseInt(id));
		}
		if (!StringUtils.isEmpty(paymentCode2)) {
			paymentSettlementVO.setPaymentCode(paymentCode2);
		}
		if (!StringUtils.isEmpty(venCode2)) {
			paymentSettlementVO.setVenCode(venCode2);
		}
		if (!StringUtils.isEmpty(venName2)) {
			paymentSettlementVO.setVenName(venName2);
		}
		if (!StringUtils.isEmpty(oppSubject2)) {
			paymentSettlementVO.setOppSubject(oppSubject2);
		}
		if (!StringUtils.isEmpty(clearingForm2)) {
			paymentSettlementVO.setClearingForm(clearingForm2);
		}
		if (!StringUtils.isEmpty(settlementSubject2)) {
			paymentSettlementVO.setSettlementSubject(settlementSubject2);
		}
		if (!StringUtils.isEmpty(vendorBank2)) {
			paymentSettlementVO.setVendorBank(vendorBank2);
		}
		if (!StringUtils.isEmpty(vendorAccount2)) {
			paymentSettlementVO.setVendorAccount(vendorAccount2);
		}
		if (!StringUtils.isEmpty(itemCode2)) {
			paymentSettlementVO.setItemCode(itemCode2);
		}
		if (!StringUtils.isEmpty(sum2)) {
			paymentSettlementVO.setSum(new BigDecimal(sum2));
		}
		if (!StringUtils.isEmpty(digest2)) {
			paymentSettlementVO.setDigest(digest2);
		}
		if (!StringUtils.isEmpty(prePaymentID2)) {
			paymentSettlementVO.setPrePaymentID(prePaymentID2);
		}
		if (!StringUtils.isEmpty(invoiceID2)) {
			paymentSettlementVO.setInvoiceID(invoiceID2);
		}
		if (!StringUtils.isEmpty(redFlag2)) {
			paymentSettlementVO.setRedFlag(redFlag2);
		}
		if (!StringUtils.isEmpty(ticketDate2)) {
			paymentSettlementVO.setTicketDate(ticketDate2);
		}
		if (!StringUtils.isEmpty(invoiceID2)) {
			paymentSettlementVO.setInvoiceID(invoiceID2);
		}else{
			paymentSettlementVO.setInvoiceID("");
		}
		if (!StringUtils.isEmpty(prePaymentID2)) {
			paymentSettlementVO.setPrePaymentID(prePaymentID2);
		}else{
			paymentSettlementVO.setPrePaymentID("");
		}
		/*String[] ss = null;
		if (!StringUtils.isEmpty(invoiceID2)) {
			ss = invoiceID2.split(",");
		}else{
			paymentSettlementVO.setInvoiceID("");
		}
		String[] pp = null;
		if (!StringUtils.isEmpty(prePaymentID2)) {
			pp = prePaymentID2.split(",");
		}else{
			paymentSettlementVO.setPrePaymentID("");
		}*/
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		paymentSettlementVO.setModifier(strAccount);
		paymentSettlementVO.setCreateTd(new Date());
		paymentSettlementVO.setStatus(Constants.PaymentAdmin.PAYMENT_STATUS_1);
		paymentSettlementVO.setPayType(Constants.PaymentAdmin.PAY_TYPE_1);
		paymentSettlementVO.setType(Constants.PaymentAdmin.TYPE_2);
		try {
			//更改或删除  发票、付款单关联信息表 并更改预付单与付款单之间的关联关系
			paymentSettlementService.deleteMgPayAndInvOrUpdatePrePayment(paymentSettlementVO);
			
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
	 * @Title: paymentSettlement 
	 * @Description: 选择列表 预付单
	 * @return list
	 */
	@RequestMapping(value = "prepayment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryPrepayment(HttpServletRequest request, Long page, Integer rows, String vendorSelectVal){
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		map.put("page", page);
		map.put("rows", rows);
		map.put("venCode", vendorSelectVal);
		map.put("status", Constants.InvoiceAdmin.AUDIT_STATUS_3);
		map.put("payType", Constants.PaymentAdmin.PAY_TYPE_1);
		map.put("type", Constants.PaymentAdmin.TYPE_1);
		resultMap = paymentSettlementService.getPrepayment(map);
		return resultMap;
	}
	
	/*
	 * @Title: paymentSettlement 
	 * @Description: 选择列表 发票
	 * @return list
	 */
	@RequestMapping(value = "invoice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryInvoice(HttpServletRequest request, Long page, Integer rows, String vendorSelectVal){
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		map.put("page", page);
		map.put("rows", rows);
		map.put("venCode", vendorSelectVal);
		map.put("status", Constants.InvoiceAdmin.AUDIT_STATUS_3);
		map.put("businessType", Constants.InvoiceAdmin.BUSINESS_TYPE_1);
		resultMap = paymentSettlementService.getInvoice(map);
		return resultMap;
	}
	
	/*
	 * 加载下拉列表：供应商表
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "paymentSettlementVenNameList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getPaymentSettlementVenNameList(HttpServletResponse response) {
		List<Map<String, Object>> list = Lists.newArrayList();
		try {
			List<PaymentSettlementVO> queryList = paymentSettlementService.queryVenList();
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
	 * 加载下拉列表：结算科目表
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "querySettles", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getQuerySettles(HttpServletResponse response) {
		List<Map<String, Object>> list = Lists.newArrayList();
		try {
			List<PaymentSettlementVO> queryList = paymentSettlementService.getQuerySettles();
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
	 * 提交
	 * @return
	 */
	@RequestMapping(value = "commitByIds", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCommitByIds(HttpServletRequest request,String rowsData) {
		final Map<String, Object> map = Maps.newHashMap();
		List<PaymentSettlementVO> psvList  = new ArrayList<PaymentSettlementVO>();
		if(null!=rowsData && !("").equals(rowsData)){
			psvList = (List<PaymentSettlementVO>)JsonUtil.jsonToList(rowsData, PaymentSettlementVO.class);
		}
		try {
			for (PaymentSettlementVO p : psvList) {
				p.setStatus(Constants.PaymentAdmin.PAYMENT_STATUS_2);
				paymentSettlementService.commitByIds(p);
			}
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
	
}
