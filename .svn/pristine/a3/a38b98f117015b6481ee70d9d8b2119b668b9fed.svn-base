package com.multigold.mdm.web.controller.wo;

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

import com.google.common.collect.Maps;
import com.multigold.common.constants.Constants;
import com.multigold.common.service.BaseService;
import com.multigold.common.util.LogTracking;
import com.multigold.common.util.TimeUtil;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.t6.service.api.accvouch.AccvouchService;
import com.multigold.t6.service.api.common.CommonService;
import com.multigold.t6.service.api.po.RdrecordService;
import com.multigold.t6.vo.AccvouchConfig;
import com.multigold.t6.vo.common.MstAuditStatusVO;
import com.multigold.t6.vo.po.RdrecordVO;
import com.multigold.t6.vo.po.RdrecordsVO;
/**
 * 委外入库单审核
 * @author musaisai
 * Date:2015/06/29
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/wo/womainRecord")
public class WOmainRecordController extends BaseCRUDAction<RdrecordVO, String> {
	
	@Autowired
	RdrecordService <RdrecordVO, String> rdrecordService;
	@Autowired
	CommonService <MstAuditStatusVO, String> commonService;
	
	@Autowired
	private AccvouchService    accvouchService;
	
	@Override
	public BaseService<RdrecordVO, String> getBaseService() {
		return rdrecordService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, RdrecordVO rdrecordVO, String operateFlag) {
	}
	/**
	 * @Title: pomainRecord 
	 * @Description: 委外入库单列表跳转到页面
	 * @return String
	 */
	@RequestMapping(value = "womainRecord", method = RequestMethod.GET)
	public String pomainRecord() {
		return viewName("womainRecord");
	}
	
	/**
	 * 委外-获取入库单列表
	 * @return
	 */
	@RequestMapping(value = "getPOMainRecords", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPOMainRecords(HttpServletRequest request, String coCode, 
			String cOrderCode, Date timeFrom, Date timeTo,String cVenName,Long page, Integer rows,
			String auditStatus,String redFlag,String wareHouse) {
		//List<RdrecordVO> rdrecordList = new ArrayList<RdrecordVO>();
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		map.put("page", page);
		map.put("rows", rows);
		//map.put("count", rows*(page-1));
		map.put("aStatus", auditStatus );
		//采购cvouchType=01 and cwhcode<>1001
		//委外cvouchType=10 and cwhcode<>1001
		map.put("cvouchType", "10");
		if (!StringUtils.isEmpty(auditStatus)) {
			map.put("aStatus", auditStatus );
		}
		
		if (!StringUtils.isEmpty(coCode)) {
			map.put("coCode", "%" + coCode + "%");
		}
		if (!StringUtils.isEmpty(cOrderCode)) {
			map.put("cOrderCode", "%" + cOrderCode + "%");
		}
		if (!StringUtils.isEmpty(cVenName)) {
			map.put("cVenName", "%" + cVenName + "%");
		}
		if (!StringUtils.isEmpty(timeFrom)) {
			/**
			 * Date转化为String
			 */
			String str = TimeUtil.transferDate2String(timeFrom, TimeUtil.formatter_type_ymd); 
			/**
			 * 增加当天开始时刻
			 */
			str = str+" 00:00:00";
			timeFrom = TimeUtil.transferString2Date(str, TimeUtil.formatter_type_ymdhms);
			
			map.put("timeFrom", timeFrom);
		}
		if (!StringUtils.isEmpty(timeTo)) {
			/**
			 * Date转化为String
			 */
			String str = TimeUtil.transferDate2String(timeTo, TimeUtil.formatter_type_ymd); 
			/**
			 * 增加当天最后时刻
			 */
			str = str+" 23:59:59";
			timeTo = TimeUtil.transferString2Date(str, TimeUtil.formatter_type_ymdhms);
			map.put("timeTo", timeTo);
		}
		if (!StringUtils.isEmpty(redFlag)) {
			map.put("redFlag", redFlag );
		}else{
			map.put("redFlag", "0" );
		}
		if (!StringUtils.isEmpty(wareHouse)) {
			map.put("wareHouse", wareHouse );
		}
		
		resultMap = rdrecordService.pageQueryRdrecordList(map);
		
		return resultMap;
		
	}
	
	/**
	 * 获取入库单明细
	 * @return
	 */
	@RequestMapping(value = "getPOMainRecordsDetail", method = RequestMethod.GET)
	@ResponseBody
	public List<RdrecordsVO> getPOMainRecordsDetail(HttpServletRequest request, Integer id) {
		List<RdrecordsVO> rdrecordList = new ArrayList<RdrecordsVO>();
		if ( id != 0){
		  rdrecordList = rdrecordService.getRdrecordDetail(id);	
		}
		
		return rdrecordList;
		
	}
	/**
	 * 更新入库单状态
	 * @return
	 */
	@RequestMapping(value = "updateRecordStatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateRecordStatus( HttpServletRequest request, String coCode, String auditStatus,String role) {
		boolean isAllowed = true;
		Map<String, Object> mapResult = Maps.newHashMap();
		//1.1前处理，根据当前登录用户，查询角色
		//paymentSettlementVO.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		//2.1主处理，设定需要更新的审核状态
		Map<String, Object> map = Maps.newHashMap();
		
		if( !StringUtils.isEmpty(role) ){
			if("0".equals(role)){
				map.put("category_auditor",UserSessionProvider.getUserSerssion(request).getAccount());   //品类操作员
				map.put("category_audit_td", new Date());
			}
			if("1".equals(role)){
				map.put("finance_auditor",UserSessionProvider.getUserSerssion(request).getAccount());   //财务操作员
				map.put("finance_audit_td", new Date());
			}
		}
		
		if (!StringUtils.isEmpty(coCode)) {
			map.put("coCode", coCode);
		}
		
		map.put("status",auditStatus);  
		//2.2更新的审核状态
		//调用生成凭证的接口,财务审核通过时调用
		if( !StringUtils.isEmpty(auditStatus) ){
			if("3".equals(auditStatus)){
				try{
					List<String> ids = new ArrayList<String>();
					ids.add(coCode);
					accvouchService.insertAccvouch(ids, AccvouchConfig.PO_TENTATIVE);
					
				}catch(Exception e){
					isAllowed = false;
					e.printStackTrace();
					mapResult.put("state", Constants.State.FALSE);
					mapResult.put("msg", Constants.RdAuditStatus.ACC_FAILED);
					LogTracking.errorLog(this.getClass(), "POmainRecordController-updateRecordStatus", e.getMessage());
				}
				
			}
		}
		if(isAllowed){
			mapResult = rdrecordService.updateRecordStatus(map);
		}
		
		return mapResult;
	}
	/**
	 * 下拉列表显示审核状态
	 * @param parentCode
	 * @return
	 */
	@RequestMapping(value = "getAuditStatusList", method = RequestMethod.POST)
	@ResponseBody
	public List<MstAuditStatusVO> getStatusList() {
		List<MstAuditStatusVO> list = commonService.getAuditStatusList();
		return list;
	}
}
