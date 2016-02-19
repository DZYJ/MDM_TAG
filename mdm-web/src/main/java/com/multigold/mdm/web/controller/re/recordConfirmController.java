package com.multigold.mdm.web.controller.re;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.util.logging.resources.logging;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.multigold.common.constants.Constants;
import com.multigold.common.service.BaseService;
import com.multigold.common.util.GUIDGenerator;
import com.multigold.common.util.HttpRequestClient;
import com.multigold.common.util.LogTracking;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.common.web.springmvc.MessageResolver;
import com.multigold.mdm.dto.common.GenericResponse;
import com.multigold.mdm.service.api.interfaceConfig.InterfaceService;
//import com.multigold.mdm.dto.tsix.E7_4.RecoveryReqDetail;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.model.interfaceConfig.InterfaceEntity;
import com.multigold.t6.entity.accvouch.AccvouchEntity;
import com.multigold.t6.service.api.accvouch.PoAccvouchService;
import com.multigold.t6.service.api.po.RdrecordService;
import com.multigold.t6.service.api.re.RecordConfirmService;
import com.multigold.t6.tsix.E74.RecoveryResp;
import com.multigold.t6.tsix.E74.RecoveryRespDetail;
import com.multigold.t6.tsix.E74.RecoveryRespTran;
import com.multigold.t6.vo.po.RdrecordVO;
import com.multigold.t6.vo.po.RdrecordsVO;
import com.multigold.t6.vo.po.RecordConfirmVO;

/**
 * 
 * @author yangjun
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/re/recordConfirm")
public class recordConfirmController extends BaseCRUDAction<RecordConfirmVO, String>{

	@Autowired
	RecordConfirmService<RecordConfirmVO, String> recordConfirmService; 
	@Autowired
	RdrecordService <RdrecordVO, String> rdrecordService;
	@Autowired
	PoAccvouchService<AccvouchEntity, String> poAccvouchService;
	@Autowired
	InterfaceService<InterfaceEntity, String> interfaceService;
	@Override
	public BaseService<RecordConfirmVO, String> getBaseService() {
		return recordConfirmService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, RecordConfirmVO recordConfirmVO,
			String operateFlag) {
		recordConfirmVO.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		recordConfirmVO.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/*
	 * @Title: recordConfirm 
	 * @Description:  修改html名称与权限系统做关联
	 * @return String
	 */
	@RequestMapping(value = "recordConfirm", method = RequestMethod.GET)
	public String recordConfirmList() {
		return viewName("recordConfirm");
	}
	
	/*
	 * @Title: recordConfirm 
	 * @Description:  修改html名称与权限系统做关联
	 * @return String
	 */
	@RequestMapping(value = "queryRecordConfirm", method = RequestMethod.GET)
	public String queryRecordConfirmList() {
		return viewName("queryRecordConfirm");
	}
	
	/*
	 * 获取预付单列表 查询条件
	 * 
	 */
	@RequestMapping(value = "recordConfirmLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPrePaymentLists(HttpServletRequest request, String customerName,String orderStatus,String tradeType,String recoveryNum, Date timeFrom, Date timeTo, String productType, String orderChannel,Long page, Integer rows) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!StringUtils.isEmpty(customerName)) {
			map.put("customerName", "%" + customerName + "%");
		}
		if (!StringUtils.isEmpty(tradeType)) {
			map.put("tradeType", tradeType);
		}
		if (!StringUtils.isEmpty(recoveryNum)) {
			map.put("recoveryNum", "%" + recoveryNum + "%");
		}
		if (!StringUtils.isEmpty(timeFrom)) {
			map.put("timeFrom", sdf.format(timeFrom));
		}
		if (!StringUtils.isEmpty(timeTo)) {
			map.put("timeTo", sdf.format(timeTo)+" 23:59:59");
		}
		if (!StringUtils.isEmpty(productType)) {
			map.put("productType", productType);
		}
		if (!StringUtils.isEmpty(orderStatus)) {
			map.put("orderStatus", orderStatus);
		}
		if (!StringUtils.isEmpty(orderChannel)) {
			map.put("orderChannel", orderChannel);
		}
		map.put("page", page);
		map.put("rows", rows);
		resultMap= recordConfirmService.getRecordConfirmList(map);
		return resultMap;
		
	}

	/*
	 * @Title: PrePayment 
	 * @Description: 入库单付款单确认--录入详情
	 * @return String
	 */
	@RequestMapping(value = "createRecordConfirm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createRecordConfirm(HttpServletRequest request, int id1, String traceNum1,String customerName1, String customerBank1,
			String oppSubject1, String clearingForm1, String settlementSubject1,String customerAccount1,String totalAmount1, Date ticketDate1) {
		final Map<String, Object> map = Maps.newHashMap();
		//新增时保存选择的日期
		request.getSession().setAttribute("ticketDate", new SimpleDateFormat("yyyy-MM-dd").format(ticketDate1));
		RecordConfirmVO recordConfirmVO = new RecordConfirmVO();
		if (!StringUtils.isEmpty(id1)) {
			recordConfirmVO.setId(id1);
		}
		if (!StringUtils.isEmpty(traceNum1)) {
			recordConfirmVO.setTraceNum(traceNum1);
		}
		if (!StringUtils.isEmpty(customerName1)) {
			recordConfirmVO.setCustomerName(customerName1);
		}
		if (!StringUtils.isEmpty(customerAccount1)) {
			recordConfirmVO.setCustomerAccount(customerAccount1);
		}
		if (!StringUtils.isEmpty(customerBank1)) {
			recordConfirmVO.setCustomerBank(customerBank1);
		}
		if (!StringUtils.isEmpty(oppSubject1)) {
			recordConfirmVO.setOppSubject(oppSubject1);
		}
		if (!StringUtils.isEmpty(clearingForm1)) {
			recordConfirmVO.setClearingForm(clearingForm1);
		}
		if (!StringUtils.isEmpty(settlementSubject1)) {
			recordConfirmVO.setSettlementSubject(settlementSubject1);
		}
		if (!StringUtils.isEmpty(totalAmount1)) {
			recordConfirmVO.setTotalAmount(new BigDecimal(totalAmount1));
		}
		if (!StringUtils.isEmpty(ticketDate1)) {
			recordConfirmVO.setTicketDate(ticketDate1);
		}
		
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		recordConfirmVO.setOperator(strAccount);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		recordConfirmVO.setOperationTime(new Date());
		try {
			recordConfirmService.insertRecordConfirm(recordConfirmVO);
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
	 * @Title: recordConfirm 
	 * @Description: 入库单确认付款单--修改部分
	 * 
	 */
	@RequestMapping(value = "updateRecordConfirm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePrePayment(HttpServletRequest request, String traceNum2, String id2,String customerName2, String customerBank2,
			String oppSubject2, String clearingForm2, String settlementSubject2,String customerAccount2,String totalAmount2, Date ticketDate2) {
		final Map<String, Object> map = Maps.newHashMap();
		RecordConfirmVO recordConfirmVO = new RecordConfirmVO();
		if (!StringUtils.isEmpty(id2)) {
			recordConfirmVO.setId(Integer.parseInt(id2));
		}
		if (!StringUtils.isEmpty(traceNum2)) {
			recordConfirmVO.setTraceNum(traceNum2);
		}
		if (!StringUtils.isEmpty(customerName2)) {
			recordConfirmVO.setCustomerName(customerName2);
		}
		if (!StringUtils.isEmpty(customerAccount2)) {
			recordConfirmVO.setCustomerAccount(customerAccount2);
		}
		if (!StringUtils.isEmpty(customerBank2)) {
			recordConfirmVO.setCustomerBank(customerBank2);
		}
		if (!StringUtils.isEmpty(oppSubject2)) {
			recordConfirmVO.setOppSubject(oppSubject2);
		}
		if (!StringUtils.isEmpty(clearingForm2)) {
			recordConfirmVO.setClearingForm(clearingForm2);
		}
		if (!StringUtils.isEmpty(settlementSubject2)) {
			recordConfirmVO.setSettlementSubject(settlementSubject2);
		}
		if (!StringUtils.isEmpty(totalAmount2)) {
			recordConfirmVO.setTotalAmount(new BigDecimal(totalAmount2));
		}
		if (!StringUtils.isEmpty(ticketDate2)) {
			recordConfirmVO.setTicketDate(ticketDate2);
		}
		
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		recordConfirmVO.setOperator(strAccount);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		recordConfirmVO.setOperationTime(new Date());
		try {
			recordConfirmService.updateRecordConfirm(recordConfirmVO);
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
	 * 确认付款单，修改打款状态
	 * @return
	 */
	@RequestMapping(value = "commitByIds", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCommitByIds(HttpServletRequest request, String id, String orderStatus) {
		final Map<String, Object> map = Maps.newHashMap();
		List<String> ids = new ArrayList<String>();
		ids.add(id);
		RecordConfirmVO recordConfirmVO = new RecordConfirmVO();
		recordConfirmVO.setId(Integer.parseInt(id));
//		recordConfirmVO.setOrderStatus(Constants.RecordConfirmAdmin.RECORDCONFIRM_ORDERSTATUS_2);
		try {
			LogTracking.debugLog(this.getClass(), "<<<请求消息>>>", request.toString());
			String returnInfo = getPostRdrecordInterface(id);// 先调用接口
			if(returnInfo.equals("100")){ // 调用接口成功   对本库状态进行修改
				recordConfirmService.commitByIds(recordConfirmVO);
				map.put("msg", MessageResolver.getMessage(request,
						Constants.Message.SUCCESS_SAVE));
				map.put("state", Constants.State.TRUE);
			}else{
				map.put("msg", MessageResolver.getMessage(request,
						Constants.Message.FAILED_UPDATE));
				map.put("state", Constants.State.FALSE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", MessageResolver.getMessage(request,
					Constants.Message.FAILED_UPDATE));
			map.put("state", Constants.State.FALSE);
		}
		return map;
	}
	
	/*
	 * 获取入库单明细
	 * @return
	 */
	@RequestMapping(value = "getRecordConfirmDetail", method = RequestMethod.GET)
	@ResponseBody
	public List<RecordConfirmVO> getRecordConfirmDetail(HttpServletRequest request, String recoveryNum) {
		List<RecordConfirmVO> rdrecordList = new ArrayList<RecordConfirmVO>();
		final Map<String, Object> map = Maps.newHashMap();
		if ( recoveryNum != null && recoveryNum != ""){
			map.put("define25", recoveryNum);
			rdrecordList = recordConfirmService.getRdrecordDetail(map);	
		}
		
		return rdrecordList;
		
	}
	
	/*
	 * 加载下拉列表：销售渠道
	 * 
	 * @param response
	 * 
	 */
	@RequestMapping(value = "getOrderChannelList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getOrderChannelList(HttpServletResponse response) {
		List<Map<String, Object>> list = Lists.newArrayList();
		/*try {
			List<RecordConfirmVO> queryList = recordConfirmService.getOrderChannelList();
			if ((null != queryList) && (queryList.size() > 0)) {
				for (RecordConfirmVO systemModel : queryList) {
					Map<String, Object> map = Maps.newHashMap();
					//map.put("cCode", systemModel.getcCode());
					//map.put("cSSName", systemModel.getcSSName());
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return list;
	}
	
	/*
	 * 获取入库单明细
	 * @return
	 */
	@RequestMapping(value = "getPOMainRecordsDetail", method = RequestMethod.GET)
	@ResponseBody
	public List<RdrecordsVO> getPOMainRecordsDetail(HttpServletRequest request, String recoveryNum) {
		List<RdrecordsVO> rdrecordList = new ArrayList<RdrecordsVO>();

		if ( recoveryNum != null && !recoveryNum.equals("")){
		  rdrecordList = rdrecordService.getRdrecordWithCcodeDetail(recoveryNum);	
		}	
		return rdrecordList;
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
			List<RecordConfirmVO> queryList = recordConfirmService.getQuerySettles();
			if ((null != queryList) && (queryList.size() > 0)) {
				for (RecordConfirmVO systemModel : queryList) {
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
	
	
	/*
	 * 提供服务接口
	 */
	private String getPostRdrecordInterface(String id) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("id", id);
		HttpRequestClient client = new HttpRequestClient();
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("interface_code", "ec_order_confirm");
		InterfaceEntity entity = interfaceService.queryUrlConfig(paramMap);
		String url = entity.getUrl();//sit
//		String url = "http://10.129.61.217:8080/api/buyBackInfoResponse";//本地
//		String url = "http://10.129.62.116:8080/api/buyBackInfoResponse";
//		String url = PropertiesUtils.getEcResourceValue("url", "ecResource");
		//1.根据id查询RecordConfirmVO对象集  付款确认时，或许提供的不止一个id,而是ids
		List<RecordConfirmVO> list = (List<RecordConfirmVO>) recordConfirmService.queryRecordConfirmVOById(map);
//		RecordConfirmVO recordConfirmVO = recordConfirmService.queryById(id+"");
		
		//2-前处理： 组装请求报文
		RecoveryRespDetail recoveryRespDetail 	= new RecoveryRespDetail();
		List<RecoveryRespDetail> details 		= new ArrayList<RecoveryRespDetail>();
		RecoveryRespTran reqTran 				= new RecoveryRespTran();
		List<RecoveryRespTran> reqTrans 		= new ArrayList<RecoveryRespTran>();
		RecoveryResp resp 						= new RecoveryResp();
		SimpleDateFormat sdf 					= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (RecordConfirmVO recordConfirmVO : list) {
			recoveryRespDetail.setRecoveryNum(recordConfirmVO.getRecoveryNum());
//			recoveryRespDetail.setRecoveryNum("111");
			recoveryRespDetail.setRecoveryStatus("9");//打款状态 9.已打款
			recoveryRespDetail.setTraceNum(recordConfirmVO.getTraceNum());
//			recoveryRespDetail.setOperator(recordConfirmVO.getOperator());
//			recoveryRespDetail.setOperationTime(sdf.format(recordConfirmVO.getOperationTime()));
			recoveryRespDetail.setOperator(recordConfirmVO.getOperator());
			recoveryRespDetail.setOperationTime(sdf.format(recordConfirmVO.getOperationTime()));
			recoveryRespDetail.setRemark(recordConfirmVO.getRemark());
			details.add(recoveryRespDetail);
			reqTran.setDetails(details);
//			now.getTimeInMillis();
			reqTran.setRequestId(new Date().getTime()+"");
			reqTrans.add(reqTran);
		}
		resp.setTrans(reqTrans);
		
		String transID = GUIDGenerator.getSimpleUUID();
		System.out.println("transID:"+transID);
		resp.setTransactionId(transID);
		//3-主处理：发送报文
		GenericResponse reps = null;
		try {
			reps = client.post(url, resp, GenericResponse.class);
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("<<<请求失败>>>", e);
		}
		//4-后处理：处理反映
		System.out.println(reps.getHeader().getResponseCode());
		System.out.println(reps.getHeader().getRemark());
		String returnInfo = reps.getHeader().getResponseCode();
		return returnInfo;
	}
}
