package com.multigold.mdm.web.controller.sales;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.multigold.common.util.GUIDGenerator;
import com.multigold.common.util.HttpRequestClient;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.common.web.springmvc.MessageResolver;
import com.multigold.mdm.dto.common.GenericResponse;
import com.multigold.mdm.dto.sale.SaleReturnStatusReq;
import com.multigold.mdm.dto.sale.SalesReturnStatusReqDetails;
import com.multigold.mdm.dto.sale.SalesReturnStatusReqTran;
import com.multigold.mdm.service.api.interfaceConfig.InterfaceService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.model.interfaceConfig.InterfaceEntity;
import com.multigold.t6.service.api.sales.SalesService;
import com.multigold.t6.vo.sales.SalesReturnVO;
import com.multigold.t6.vo.sales.SalesReturnsVO;

/**
 *  
 * @author yangjun
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesReturn")
public class SalesReturnController extends BaseCRUDAction<SalesReturnVO, String>{

	@Autowired
	SalesService<SalesReturnVO, String> salesService; 
	@Autowired
	InterfaceService<InterfaceEntity, String> interfaceService;
	
	@Override
	public BaseService<SalesReturnVO, String> getBaseService() {
		return salesService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, SalesReturnVO salesReturnVO,
			String operateFlag) {
		salesReturnVO.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		salesReturnVO.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/*
	 * @Title: salesCheck 
	 * @Description:  修改html名称与权限系统做关联
	 * @return String
	 */
	@RequestMapping(value = "salesReturn", method = RequestMethod.GET)
	public String salesReturnList() {
		return viewName("salesReturn");
	}
	
	/*
	 * @Title: salesCheck 
	 * @Description:  修改html名称与权限系统做关联
	 * @return String
	 */
	@RequestMapping(value = "salesReturnPrint", method = RequestMethod.GET)
	public String salesReturnPrintList() {
		return viewName("salesReturnPrint");
	}
	
	/*
	 * 退款单管理   查询条件
	 * 
	 */
	@RequestMapping(value = "salesReturnLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPrePaymentLists(HttpServletRequest request, String customerName,String orderNum, Date timeFrom, Date timeTo,Long page, Integer rows) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!StringUtils.isEmpty(customerName)) {
			map.put("customerName", customerName);
		}
		if (!StringUtils.isEmpty(timeFrom)) {
			map.put("timeFrom", sdf.format(timeFrom));
		}
		if (!StringUtils.isEmpty(timeTo)) {
			map.put("timeTo", sdf.format(timeTo)+" 23:59:59");
		}
		if (!StringUtils.isEmpty(orderNum)) {
			map.put("orderNum", orderNum);
		}
		map.put("page", page);
		map.put("rows", rows);
		resultMap= salesService.getSalesReturnList(map);
		return resultMap;
	}

	/*
	 * @Title: salesReturn 
	 * @Description: 退款单管理审核通过--录入详情
	 * @return String
	 */
	@RequestMapping(value = "createSalesReturn", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createSalesReturn(HttpServletRequest request, int id1, String traceNum1,String customerName1, String customerBank1,
		String clearingForm1, String settlementSubject1,String itemCode1,String customerAccount1,String remark1, Date ticketDate1) {
		final Map<String, Object> map = Maps.newHashMap();
		//新增时保存选择的日期
		request.getSession().setAttribute("ticketDate", new SimpleDateFormat("yyyy-MM-dd").format(ticketDate1));
		SalesReturnVO salesReturnVO = new SalesReturnVO();
		if (!StringUtils.isEmpty(id1)) {
			salesReturnVO.setId(id1);
		}
		if (!StringUtils.isEmpty(traceNum1)) {
			salesReturnVO.setTraceNum(traceNum1);
		}
		if (!StringUtils.isEmpty(itemCode1)) {
			salesReturnVO.setItemCode(itemCode1);
		}
		if (!StringUtils.isEmpty(remark1)) {
			salesReturnVO.setRemark(remark1);
		}
		if (!StringUtils.isEmpty(customerName1)) {
			salesReturnVO.setCustomerName(customerName1);
		}
		if (!StringUtils.isEmpty(customerAccount1)) {
			salesReturnVO.setCustomerAccount(customerAccount1);
		}
		if (!StringUtils.isEmpty(customerBank1)) {
			salesReturnVO.setCustomerBank(customerBank1);
		}
		if (!StringUtils.isEmpty(clearingForm1)) {
			salesReturnVO.setClearingForm(clearingForm1);
		}
		if (!StringUtils.isEmpty(settlementSubject1)) {
			salesReturnVO.setSettlementSubject(settlementSubject1);
		}
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		salesReturnVO.setInputer(strAccount);
		salesReturnVO.setCreateTd(new Date());
		salesReturnVO.setStatus(Constants.SalesReturnAdmin.SALESRETURN_STATUS_02);
		try {
			salesService.insertSalesReturn(salesReturnVO);
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
	 * @Title: salesReturn 
	 * @Description: 退款单管理审核通过--修改部分
	 * 
	 */
	@RequestMapping(value = "updateSalesReturn", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateSalesReturn(HttpServletRequest request, String traceNum2, String id2,String customerName2, String customerBank2,
		String oppSubject2, String clearingForm2, String settlementSubject2, String remark2,String itemCode2,String customerAccount2,String totalAmount2, Date ticketDate2) {
		final Map<String, Object> map = Maps.newHashMap();
		SalesReturnVO salesReturnVO = new SalesReturnVO();
		if (!StringUtils.isEmpty(id2)) {
			salesReturnVO.setId(Integer.parseInt(id2));
		}
		if (!StringUtils.isEmpty(traceNum2)) {
			salesReturnVO.setTraceNum(traceNum2);
		}
		if (!StringUtils.isEmpty(remark2)) {
			salesReturnVO.setRemark(remark2);
		}
		if (!StringUtils.isEmpty(customerName2)) {
			salesReturnVO.setCustomerName(customerName2);
		}
		if (!StringUtils.isEmpty(itemCode2)) {
			salesReturnVO.setItemCode(itemCode2);
		}
		if (!StringUtils.isEmpty(customerAccount2)) {
			salesReturnVO.setCustomerAccount(customerAccount2);
		}
		if (!StringUtils.isEmpty(customerBank2)) {
			salesReturnVO.setCustomerBank(customerBank2);
		}
		if (!StringUtils.isEmpty(clearingForm2)) {
			salesReturnVO.setClearingForm(clearingForm2);
		}
		if (!StringUtils.isEmpty(settlementSubject2)) {
			salesReturnVO.setSettlementSubject(settlementSubject2);
		}
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		salesReturnVO.setModifier(strAccount);
		salesReturnVO.setUpdateTd(new Date());
		try {
			salesService.updateSalesReturn(salesReturnVO);
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
	 * 退款单审核
	 * @salesreturn
	 */
	@RequestMapping(value = "commitByIds", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCommitByIds(HttpServletRequest request, String id, String orderStatus,String orderNum) {
		final Map<String, Object> map = Maps.newHashMap();
		List<String> ids = new ArrayList<String>();
		ids.add(id);
		SalesReturnVO salesReturnVO = new SalesReturnVO();
		salesReturnVO.setId(Integer.parseInt(id));
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		salesReturnVO.setModifier(strAccount);
		salesReturnVO.setUpdateTd(new Date());
		salesReturnVO.setStatus(Constants.SalesReturnAdmin.SALESRETURN_STATUS_03);
		
		try {
			
			String message = getPostSaleReturnStatusInterface(orderNum);
			if(!message.equals("error")){
				salesService.commitByIds(salesReturnVO);
				map.put("msg", MessageResolver.getMessage(request,
						Constants.Message.SUCCESS_SAVE));
				map.put("state", Constants.State.TRUE);
			}else{
				map.put("msg", MessageResolver.getMessage(request,
						Constants.Message.FAILED_UPDATE));
				map.put("state", Constants.State.FALSE);
			}
		}catch (Exception e) {
			Logger.getLogger(this.getClass()).error("更新失败",e);
			map.put("msg", MessageResolver.getMessage(request,
					Constants.Message.FAILED_UPDATE));
			map.put("state", Constants.State.FALSE);
		}
		return map;
	}
	
	/*
	 * 从子表获取退款单单明细
	 * @salesreturn
	 */
	@RequestMapping(value = "getSalesReturnsDetail", method = RequestMethod.GET)
	@ResponseBody
	public List<SalesReturnsVO> SalesReturn(HttpServletRequest request, String orderNum) {
		List<SalesReturnsVO> salesReturnsList = new ArrayList<SalesReturnsVO>();
		final Map<String, Object> map = Maps.newHashMap();
		if ( orderNum != null && orderNum != ""){
			map.put("orderNum", orderNum);
			salesReturnsList = salesService.getSalesReturnDetail(map);	
		}
		return salesReturnsList;
	}

	/*
	 * 从子表获取退款单单明细--打印
	 * @salesreturn
	 */
	@RequestMapping(value = "getSalesReturnsPrintDetail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getSalesReturnsPrintDetail(HttpServletRequest request,HttpServletResponse response,String orderNum){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<SalesReturnsVO> list =SalesReturn(request,orderNum);
		resultMap.put("data", list);
		resultMap.put("success", "true");  
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

	/*
	 * 打印主表详情
	 * 
	 */
	@RequestMapping(value = "getSalesReturnPrintLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getSalesReturnPrintLists(HttpServletResponse response,String orderNum){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<SalesReturnVO> list = new ArrayList<SalesReturnVO>();
		final Map<String, Object> map = Maps.newHashMap();
		if ( orderNum != null && orderNum != ""){
			map.put("orderNum", orderNum);
		   list = salesService.getSalesReturnPrintLists(map);	
		}
		resultMap.put("data", list);
		resultMap.put("success", "true");  
		return resultMap;
	}


	/*
	 * 提供服务接口
	 */
	private String getPostSaleReturnStatusInterface(String orderNum) {
		HttpRequestClient client = new HttpRequestClient();
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("interface_code", "ec_refund_status");
		InterfaceEntity entity = interfaceService.queryUrlConfig(paramMap);
		String url = entity.getUrl();//sit
//		String url = "http://10.129.61.217:8080/api/buyBackInfoResponse";//本地
//		String url = "http://10.129.62.116:8080/api/buyBackInfoResponse";
//		String url = PropertiesUtils.getEcResourceValue("url", "ecResource");
		//1.根据id查询RecordConfirmVO对象集  付款确认时，或许提供的不止一个id,而是ids
		SalesReturnVO vo = salesService.queryById(orderNum);
		String returnInfo = null;
		SaleReturnStatusReq req = new SaleReturnStatusReq();
		List<SalesReturnStatusReqTran> trans = new ArrayList<SalesReturnStatusReqTran>();
		SalesReturnStatusReqTran tran = new SalesReturnStatusReqTran();
		List<SalesReturnStatusReqDetails> details = new ArrayList<SalesReturnStatusReqDetails>();
		SalesReturnStatusReqDetails detail = new SalesReturnStatusReqDetails();
		detail.setOrderNum(vo.getOrderNum());
		String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getModifiedDate());
		detail.setOperationTime(operationTime);
		detail.setTraceNum(vo.getTraceNum());
		detail.setRemark(vo.getRemark());
		detail.setRefundStatus("4");
		details.add(detail);
		tran.setDetails(details);
		tran.setRequestId(new Date().getTime()+"");
		trans.add(tran);
		req.setTrans(trans);
		String transID = GUIDGenerator.getSimpleUUID();
		req.setTransactionId(transID);
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			Logger.getLogger(this.getClass()).debug(mapper.writeValueAsString(req));
		} catch (JsonGenerationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GenericResponse reps = null;
		try {
			reps = client.post(url, req, GenericResponse.class);
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("接口调用失败", e);
		}
		//4-后处理：处理反映
		returnInfo = reps.getHeader().getResponseCode();
		
//		
		return returnInfo;
	}
}
