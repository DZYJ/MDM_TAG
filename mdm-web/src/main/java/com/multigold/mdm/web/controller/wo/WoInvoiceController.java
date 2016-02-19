package com.multigold.mdm.web.controller.wo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.multigold.common.util.LogTracking;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.common.web.springmvc.MessageResolver;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.t6.vo.AccvouchConfig;
import com.multigold.t6.vo.accvouch.AccMessageCode;
import com.multigold.t6.vo.po.InvoiceVO;
import com.multigold.t6.entity.accvouch.AccvouchEntity;
import com.multigold.t6.service.api.accvouch.OmAccvouchService;
import com.multigold.t6.service.api.accvouch.PoAccvouchService;
import com.multigold.t6.service.api.po.InvoiceService;
import com.multigold.t6.service.api.po.RdrecordService;

@Controller
@RequestMapping(value = "${adminPath}/wo/woInvoice")
public class WoInvoiceController extends BaseCRUDAction<InvoiceVO, String> {

	@Autowired
	InvoiceService<InvoiceVO, String> invoiceService;
	
	@Autowired
	RdrecordService<InvoiceVO, String> rdrecordService;
	
	@Autowired
	PoAccvouchService<AccvouchEntity, String> poAccvouchService;
	
	@Autowired
	OmAccvouchService<AccvouchEntity, String> omAccvouchService;
	
	@Override
	public BaseService<InvoiceVO, String> getBaseService() {
		return invoiceService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, InvoiceVO invoiceVO,
			String operateFlag) {
		invoiceVO.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		invoiceVO.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/*
	 * 获取发票单列表
	 * @return
	 */
	@RequestMapping(value = "invoice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getInvoiceLists(HttpServletRequest request, String invoiceId, String venCode, String status, String redFlag, Long page, Integer rows) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		if (!StringUtils.isEmpty(invoiceId)) {
			map.put("invoiceId", "%" + invoiceId + "%");
		}
		if (!StringUtils.isEmpty(venCode)) {
			map.put("venCode", "%" + venCode + "%");
		}
		if (!StringUtils.isEmpty(status)) {
			map.put("status", status);
		}
		if (!StringUtils.isEmpty(redFlag)) {
			map.put("redFlag", redFlag);
		}
		map.put("businessType", Constants.InvoiceAdmin.BUSINESS_TYPE_2);
		map.put("page", page);
		map.put("rows", rows);
		resultMap= invoiceService.getInvoiceLists(map);
		return resultMap;
		
	}
	
	/*
	 * 新增发票
	 * @return
	 */
	@RequestMapping(value = "createInvoice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createInvoice(HttpServletRequest request, String invoiceId1, Date billingDate1, String venCode1, String venName1,
			String businessType1, String sum1, String tax1, String taxRate1, String price1,String storageId1, Date ticketDate1, String redFlag1,
			String coCode1,String storageIdMoneyTotal1) {
//		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		InvoiceVO invoiceVO = new InvoiceVO();
		//新增时保存选择的日期
		request.getSession().setAttribute("billingDate1", new SimpleDateFormat("yyyy-MM-dd").format(billingDate1));
		request.getSession().setAttribute("ticketDate1", new SimpleDateFormat("yyyy-MM-dd").format(ticketDate1));
		if (!StringUtils.isEmpty(invoiceId1)) {
			invoiceVO.setInvoiceId(invoiceId1);
		}
		if (!StringUtils.isEmpty(venCode1)) {
			invoiceVO.setVenCode(venCode1);
		}
		if (!StringUtils.isEmpty(billingDate1)) {
			invoiceVO.setBillingDate(billingDate1);
		}
		if (!StringUtils.isEmpty(venName1)) {
			invoiceVO.setVenName(venName1);
		}
		if (!StringUtils.isEmpty(sum1)) {
			invoiceVO.setSum(new BigDecimal(sum1));
		}
		if (!StringUtils.isEmpty(tax1)) {
			invoiceVO.setTax(new BigDecimal(tax1));
		}
		if (!StringUtils.isEmpty(taxRate1)) {
			invoiceVO.setTaxRate(new BigDecimal(taxRate1));
		}
		if (!StringUtils.isEmpty(price1)) {
			invoiceVO.setPrice(new BigDecimal(price1));
		}
		if (!StringUtils.isEmpty(redFlag1)) {
			invoiceVO.setRedFlag(redFlag1);
		}
		if (!StringUtils.isEmpty(ticketDate1)) {
			invoiceVO.setTicketDate(ticketDate1);
		}
		if (!StringUtils.isEmpty(storageId1)) {
			invoiceVO.setStorageId(storageId1);
		}
		if (!StringUtils.isEmpty(coCode1)) {
			invoiceVO.setCoCode(coCode1);
		}
		if (!StringUtils.isEmpty(storageIdMoneyTotal1)) {
			invoiceVO.setStorageIdMoneyTotal(new BigDecimal(storageIdMoneyTotal1));
		}
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		invoiceVO.setInputer(strAccount);
		invoiceVO.setCreateTd(new Date());
		invoiceVO.setStatus(Constants.InvoiceAdmin.INVOICE_STATUS_1);
		invoiceVO.setBusinessType(Constants.InvoiceAdmin.BUSINESS_TYPE_2);
		try {
			//插入发票表信息
			invoiceService.insertInvoice(invoiceVO);
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
	 * @Title: Invoice 
	 * @Description: 更改
	 * @return 
	 */
	@RequestMapping(value = "updateInvoice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateInvoice(HttpServletRequest request, String id, String invoiceId2, Date billingDate2, String venCode2, String venName2,
			String businessType2, String sum2, String tax2, String taxRate2, String price2,String storageId2, Date ticketDate2, String redFlag2,
			String coCode2,String storageIdMoneyTotal2) {
		final Map<String, Object> map = Maps.newHashMap();
		InvoiceVO invoiceVO = new InvoiceVO();
		if (!StringUtils.isEmpty(id)) {
			invoiceVO.setId(Integer.parseInt(id));
		}
		if (!StringUtils.isEmpty(invoiceId2)) {
			invoiceVO.setInvoiceId(invoiceId2);
		}
		if (!StringUtils.isEmpty(venCode2)) {
			invoiceVO.setVenCode(venCode2);
		}
		if (!StringUtils.isEmpty(billingDate2)) {
			invoiceVO.setBillingDate(billingDate2);
		}
		if (!StringUtils.isEmpty(venName2)) {
			invoiceVO.setVenName(venName2);
		}
		if (!StringUtils.isEmpty(sum2)) {
			invoiceVO.setSum(new BigDecimal(sum2));
		}
		if (!StringUtils.isEmpty(tax2)) {
			invoiceVO.setTax(new BigDecimal(tax2));
		}
		if (!StringUtils.isEmpty(taxRate2)) {
			invoiceVO.setTaxRate(new BigDecimal(taxRate2));
		}
		if (!StringUtils.isEmpty(price2)) {
			invoiceVO.setPrice(new BigDecimal(price2));
		}
		if (!StringUtils.isEmpty(redFlag2)) {
			invoiceVO.setRedFlag(redFlag2);
		}else{
			invoiceVO.setRedFlag(null);
		}
		if (!StringUtils.isEmpty(ticketDate2)) {
			invoiceVO.setTicketDate(ticketDate2);
		}
		if (!StringUtils.isEmpty(storageId2)) {
			invoiceVO.setStorageId(storageId2);
		}else{
			invoiceVO.setStorageId("");
		}
		if (!StringUtils.isEmpty(coCode2)) {
			invoiceVO.setCoCode(coCode2);
		}else{
			invoiceVO.setCoCode("");
		}
		if (!StringUtils.isEmpty(storageIdMoneyTotal2)) {
			invoiceVO.setStorageIdMoneyTotal(new BigDecimal(storageIdMoneyTotal2));
		}else{
			invoiceVO.setStorageIdMoneyTotal(new BigDecimal(0));
		}
		String strAccount = UserSessionProvider.getUserSerssion(request).getAccount();
		invoiceVO.setModifier(strAccount);
		invoiceVO.setUpdateTd(new Date());
		invoiceVO.setStatus(Constants.InvoiceAdmin.INVOICE_STATUS_1);
		invoiceVO.setBusinessType(Constants.InvoiceAdmin.BUSINESS_TYPE_2);
		try {
			//准备删除并更新发票、入库单关联信息表
			invoiceService.deleteMgRdAndInvOrUpdateInvoice(invoiceVO);
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
	
	@RequestMapping(value = "commitByIds", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCommitByIds(HttpServletRequest request, String id, String rowsData) {
		final Map<String, Object> map = Maps.newHashMap();
		boolean isAllowed = true;
		InvoiceVO invoiceVO = new InvoiceVO();
		invoiceVO.setId(Integer.parseInt(id));
			
		try{
			List<String> ids = new ArrayList<String>();
			ids.add(id);
			String accResult = omAccvouchService.insertAccvouch(ids, AccvouchConfig.OM_PAYABLE);
			
			if(!StringUtils.isEmpty(accResult)){
				if(AccMessageCode.ACC_SUCCESS.equals(accResult)){
					//map.put("state", Constants.State.TRUE);
					//map.put("msg", Constants.RdAuditStatus.SUCCESS);
				}else{
					String errMsg = "生成发票单凭证失败";
					if(!StringUtils.isEmpty(Constants.InvoiceAdmin.accResultMapping.get(accResult))){
						errMsg = Constants.InvoiceAdmin.accResultMapping.get(accResult);
					}
					map.put("state", Constants.State.FALSE);
					map.put("msg", errMsg);
					isAllowed = false;
				}
			}else{
				map.put("state", Constants.State.FALSE);
				map.put("msg", Constants.InvoiceAdmin.ACC_FAILED);
				LogTracking.debugLog(this.getClass(), "OmWoInvoice-commitByIds", "凭证生成结果>>>>："+accResult);
			}
			
		}catch(Exception e){
			isAllowed = false;
			e.printStackTrace();
			map.put("state", Constants.State.FALSE);
			map.put("msg", Constants.InvoiceAdmin.ACC_FAILED);
			LogTracking.errorLog(this.getClass(), "OmWoInvoiceController-commitByIds", e.getMessage());
		}
		
		if(isAllowed){
			try {
				invoiceService.commitByIds(invoiceVO);
				map.put("msg", MessageResolver.getMessage(request,
						Constants.InvoiceAdmin.INVOICE_SUCCESS_COMMIT));
				map.put("state", Constants.State.TRUE);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				map.put("msg", MessageResolver.getMessage(request,
						Constants.InvoiceAdmin.FAILED_UPDATE_INVOICE_INFO));
				map.put("state", Constants.State.FALSE);
			}catch (Exception e) {
				e.printStackTrace();
				map.put("msg", MessageResolver.getMessage(request,
						Constants.Message.FAILED_UPDATE));
				map.put("state", Constants.State.FALSE);
			}
		}
		return map;
	}
	
	/*
	 * @Title: mg_rd_and_inv 
	 * @Description: 选择列表 关联关系表
	 * @return list
	 */
	@RequestMapping(value = "mgRdAndInv", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryMgRdAndInv(HttpServletRequest request, Long page, Integer rows,String vendorSelectVal, String invoiceId, String redFlag){
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		//MgRdAndInvVO mgRdAndInvVO = new MgRdAndInvVO();
		//String strVendorSelectVal = vendorSelectVal;
		map.put("page", page);
		map.put("rows", rows);
		map.put("venCode", vendorSelectVal);
		map.put("invoiceId", invoiceId);
		map.put("redFlag", redFlag);
		map.put("cvouchType", Constants.InvoiceAdmin.INVOICE_CVOUCHTYPE_10);
//		map.put("aStatus", "N");
		resultMap = rdrecordService.pageQueryRdrecordForMRAIList(map);
		
//		List<MgRdAndInvVO> list = invoiceService.queryMgRdAndInv();
		return resultMap;
	}
	
	/*
	 * @Title: mg_rd_and_inv 
	 * @Description: 删除列表 关联关系表
	 * @return list
	 */
	@RequestMapping(value = "deleteMgRdAndInv", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteMgRdAndInv(HttpServletRequest request, String id, String storageId, String businessType){
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		map.put("id", id);
		map.put("storageId", storageId);
		map.put("businessType", businessType);
		
		try {
			invoiceService.deleteMgRdAndInv(map);
			resultMap.put("msg", MessageResolver.getMessage(request,
					"操作成功"));
			resultMap.put("state", Constants.State.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("msg", MessageResolver.getMessage(request,
					Constants.Message.FAILED_UPDATE));
			resultMap.put("state", Constants.State.FALSE);
		}
		return resultMap;
	}
	
	/*
	 * @Description: 加载下拉列表：供应商表
	 * @return list
	 */
	@RequestMapping(value = "invoiceVenNameList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> queryInvoiceVenNameList(HttpServletRequest request, String index){
		List<Map<String, Object>> list = Lists.newArrayList();
		try {
			List<InvoiceVO> queryList = invoiceService.queryInvoiceVenNameList();
			if ((null != queryList) && (queryList.size() > 0)) {
				for (InvoiceVO systemModel : queryList) {
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
	 * 获取制单日期
	 * @return
	 */
	@RequestMapping(value = "getDateFromSession", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDateFromSession(HttpServletRequest request) {
		Map<String, Object> resultMap = Maps.newHashMap();
		
		//resultMap= prePaymentService.getPrePaymentList(map);
		String billingDate = (String) request.getSession(true).getAttribute("billingDate1");
		String ticketDate = (String) request.getSession(true).getAttribute("ticketDate1");
		
		if(billingDate==null){
			billingDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		if(ticketDate==null){
			ticketDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		
		System.out.println("createTd>>>>>>"+billingDate);
		resultMap.put("billingDate1" , billingDate );
		System.out.println("createTd>>>>>>"+ticketDate);
		resultMap.put("ticketDate1" , ticketDate );
		return resultMap;
		
	}

}
