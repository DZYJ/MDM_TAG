package com.multigold.mdm.web.controller.wo;

import java.math.BigDecimal;
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

import com.multigold.common.constants.Constants;
import com.multigold.common.service.BaseService;
import com.multigold.common.util.JsonUtil;
import com.multigold.common.util.TimeUtil;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.common.web.springmvc.MessageResolver;


import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.t6.entity.accvouch.AccvouchEntity;
import com.multigold.t6.service.api.accvouch.OmAccvouchService;
import com.multigold.t6.service.api.po.PrePaymentService;
import com.multigold.t6.vo.AccvouchConfig;
import com.multigold.t6.vo.accvouch.AccMessageCode;
import com.multigold.t6.vo.po.PaymentSettlementVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 
 * @author yangjun
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/wo/woPrePayment")
public class WoPrePaymentController extends BaseCRUDAction<PaymentSettlementVO, String>{

	@Autowired
	PrePaymentService<PaymentSettlementVO, String> prePaymentService;
	@Autowired
	OmAccvouchService<AccvouchEntity, String> omAccvouchService;
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
	 * @Description: 委外预付单列表 修改html的名称
	 * @return String
	 */
	@RequestMapping(value = "woPrePayment", method = RequestMethod.GET)
	public String prePaymentList() {
		return viewName("woPrePayment");
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
		map.put("type", Constants.PaymentAdmin.TYPE_1);//预付
		map.put("payType", Constants.PaymentAdmin.PAY_TYPE_2);//委外
		map.put("page", page);
		map.put("rows", rows);
		resultMap= prePaymentService.getPrePaymentList(map);
		return resultMap;
		
	}
	
	
	
	/*
	 * @Title: PrePayment 
	 * @Description: 委外预付单列表  新增详情列表
	 * @return String
	 */
	@RequestMapping(value = "createPrePayment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createPrePayment(HttpServletRequest request, String paymentCode1, String venCode1, String venName1,
			String oppSubject1, String clearingForm1, String settlementSubject1, String vendorBank1, String vendorAccount1,
			String itemCode1, String sum1, String digest1,String prePaymentID1, String invoiceID1,String createTd1,Date ticketDate1, String redFlag1) {
		final Map<String, Object> map = Maps.newHashMap();
		//新增时保存选择的日期
		request.getSession().setAttribute("ticketDate", new SimpleDateFormat("yyyy-MM-dd").format(ticketDate1));
		PaymentSettlementVO paymentSettlementVO = new PaymentSettlementVO();
		if (!StringUtils.isEmpty(paymentCode1)) {
			paymentSettlementVO.setPaymentCode(paymentCode1);
		}
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!StringUtils.isEmpty(createTd1)) {
			paymentSettlementVO.setCreateTd(TimeUtil.transferString2Date(createTd1, "yyyy-MM-dd"));
			request.getSession(true).setAttribute("createTd",createTd1);
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
		if (!StringUtils.isEmpty(redFlag1)) {
			paymentSettlementVO.setRedFlag(redFlag1);
		}
		if (!StringUtils.isEmpty(ticketDate1)) {
			paymentSettlementVO.setTicketDate(ticketDate1);
		}
		
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		paymentSettlementVO.setInputer(strAccount);
		paymentSettlementVO.setCreateTd(new Date());
		paymentSettlementVO.setStatus(Constants.PaymentAdmin.PAYMENT_STATUS_1);
		paymentSettlementVO.setPayType(Constants.PaymentAdmin.PAY_TYPE_2);
		paymentSettlementVO.setType(Constants.PaymentAdmin.TYPE_1);
		
		
		try {
			//获取最大ID
			int MaxPrePaymentNo = prePaymentService.getMaxPrePaymentNo();
			MaxPrePaymentNo++;
			paymentSettlementVO.setId(MaxPrePaymentNo);
			
			prePaymentService.insertPrePayment(paymentSettlementVO);
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
	 * @Description: 更改详情列表
	 * 
	 */
	@RequestMapping(value = "updatePrePayment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePrePayment(HttpServletRequest request, String id, String paymentCode2, String venCode2, String venName2,
			String oppSubject2, String clearingForm2, String settlementSubject2, String vendorBank2, String vendorAccount2,
			String itemCode2, String sum2, String digest2,  Date ticketDate2, String redFlag2) {
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
		if (!StringUtils.isEmpty(redFlag2)) {
			paymentSettlementVO.setRedFlag(redFlag2);
		}
		if (!StringUtils.isEmpty(ticketDate2)) {
			paymentSettlementVO.setTicketDate(ticketDate2);
		}
		
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		paymentSettlementVO.setModifier(strAccount);
		paymentSettlementVO.setCreateTd(new Date());
		paymentSettlementVO.setStatus(Constants.PaymentAdmin.PAYMENT_STATUS_1);
		paymentSettlementVO.setPayType(Constants.PaymentAdmin.PAY_TYPE_2);
		paymentSettlementVO.setType(Constants.PaymentAdmin.TYPE_1);
		
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
	 * 提交
	 * @return
	 */
	@RequestMapping(value = "commitByIds", method = RequestMethod.POST)
	@ResponseBody
//	public Map<String, Object> getCommitByIds(HttpServletRequest request, String rowsData) {
		public Map<String, Object> getCommitByIds(HttpServletRequest request, String id, String status) {
		final Map<String, Object> map = Maps.newHashMap();
		List<String> ids = new ArrayList<String>();
		ids.add(id);
		PaymentSettlementVO paymentSettlementVO = new PaymentSettlementVO();
		paymentSettlementVO.setId(Integer.parseInt(id));
		paymentSettlementVO.setStatus(Integer.parseInt(status));
		try {
			String message = omAccvouchService.insertAccvouch(ids, AccvouchConfig.OM_PREPAYMENT);
			if(message.equals(AccMessageCode.ACC_SUCCESS)){
				paymentSettlementVO.setId(Integer.parseInt(id));
				paymentSettlementVO.setStatus(Integer.parseInt(status));
				prePaymentService.commitByIds(paymentSettlementVO);
				map.put("msg", MessageResolver.getMessage(request,
						Constants.PaymentAdmin.PAYMENT_SUCCESS_COMMIT));
				map.put("state", Constants.State.TRUE);
			}else{
				String errMsg = "生成凭证失败";
				if(!StringUtils.isEmpty(Constants.RdAuditStatus.accResultMapping.get(message))){
					errMsg = Constants.RdAuditStatus.accResultMapping.get(message);
				}
				map.put("state", Constants.State.FALSE);
				map.put("msg", errMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			String message = e.getMessage();
		    map.put("msg", MessageResolver.getMessage(request, 
					AccMessageCode.getAccMessage(message)));
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
	public List<Map<String, Object>> getPrePaymentVenNameList(HttpServletResponse response,String index) {
		List<Map<String, Object>> list = Lists.newArrayList();
		try {
			List<PaymentSettlementVO> queryList = prePaymentService.queryVenList();
			if ((null != queryList) && (queryList.size() > 0)) {
				for (PaymentSettlementVO systemModel : queryList) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("venCode"+index, systemModel.getcVenCode());
					map.put("venName"+index, systemModel.getcVenName());
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
		if(ticketDate==null){
			ticketDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		System.out.println("createTd>>>>>>"+ticketDate);
		resultMap.put("ticketDate" , ticketDate );
		return resultMap;
		
	}
}
