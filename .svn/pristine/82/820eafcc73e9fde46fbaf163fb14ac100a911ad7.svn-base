package com.multigold.mdm.service.rest.tsix;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multigold.common.util.JsonUtil;
import com.multigold.common.util.LogTracking;
import com.multigold.t6.service.api.accvouch.HjbAccvouchService;
import com.multigold.t6.service.api.hjb.HjbService;
import com.multigold.t6.tsix.hjb.HjbGLReq;
import com.multigold.t6.tsix.hjb.HjbGLResp;
import com.multigold.t6.tsix.hjb.HjbGlList;
/**
 * 黄金币总账接入
 * @author zhanghua
 *
 */
@Path("hjbGLService")
@Component
public class HjbAccvouchResource {
	@Autowired
	public HjbAccvouchService hjbAccvouchService;
	@Autowired
	public HjbService  hjbService;
	
	/**
	 * 黄金币总账
	 * @param request
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/hjbGL")
	public Response recovery(HjbGLReq request){
		HjbGLResp resp = new HjbGLResp();
		String requestJSON = JsonUtil.objectToJSON(request);
		LogTracking.debugLog(this.getClass(), "<<<请求消息>>>", requestJSON);
		
		//step 1:插入黄金券接口日志表
		try{
			hjbService.insertHjbList(request);
		}catch(RuntimeException e){
			resp.setTransactionId(request.getTransactionId());
			resp.setResultCode("0");
			resp.setErrorMsg("生成凭证失败："+e.getMessage());
			return Response.status(Status.OK).entity(resp).build();
		}
		resp.setTransactionId(request.getTransactionId());
		resp.setResultCode("1");
		return Response.status(Status.OK).entity(resp).build();
	}
	
	/**
	 * 黄金币总账凭证重新生成
	 * @param request
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/profitAndLoss")
	public Response profitAndLoss(HjbGLReq request){
		HjbGLResp resp = new HjbGLResp();
		String requestJSON = JsonUtil.objectToJSON(request);
		LogTracking.debugLog(this.getClass(), "<<<请求消息>>>", requestJSON);
		
		//step 1:插入黄金券接口日志表
//		try{
//			hjbService.insertHjbList(request);
//		}catch(RuntimeException e){
//			resp.setTransactionId(request.getTransactionId());
//			resp.setResultCode("0");
//			resp.setErrorMsg("生成凭证失败："+e.getMessage());
//			return Response.status(Status.OK).entity(resp).build();
//		}
		
		return insertHjbAccvouch(request);
	}
	
	private  synchronized Response insertHjbAccvouch(HjbGLReq request){
		HjbGLResp resp = new HjbGLResp();
		//step 2:查询日志表，生成凭证
		try {
			List<Map<String,Object>> resultMap = (List<Map<String,Object>>)hjbService.queryByDateStr(request.getGlDate());
			
//			addHjbCl003(resultMap,request);
			Date dbill_date = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			Date digest_date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getGlDate());
			if(request.getTransactionId().contains("Den")){
				dbill_date=digest_date;
			}
			hjbAccvouchService.insertHjbAccvouch(resultMap,null,dbill_date,digest_date);
			resp.setTransactionId(request.getTransactionId());
			resp.setResultCode("1");
		} catch (Exception e) {
			e.printStackTrace();
			resp.setTransactionId(request.getTransactionId());
			resp.setResultCode("0");
			resp.setErrorMsg("生成凭证失败："+e.getMessage());
		}
		return Response.status(Status.OK).entity(resp).build();
	}
	
	/**
	 * 业务CL003强制贷款还款特殊处理
	 * @param resultMap
	 * @return
	 */
	private void addHjbCl003(List<Map<String,Object>> list,HjbGLReq request){
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("biz_code", "CL003");
		paramMap.put("transaction_Id", request.getTransactionId());
		List<HjbGlList> hjbGlList = hjbService.queryByBizCode(paramMap);
		LogTracking.debugLog(this.getClass(), "hjbGlList", hjbGlList!=null?hjbGlList.size()+"":"无数据");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getGlDate()));
		} catch (ParseException e) {
			
			throw new RuntimeException("日期格式异常！");
		}
		int month = calendar.get(Calendar.MONTH);
		BigDecimal cash = new BigDecimal(0);
		BigDecimal countAmout = new BigDecimal(0);
		for(HjbGlList hjbGl:hjbGlList){
			if(hjbGl.getAtomCode().equals("11401")){
				list.add(addAccvouchMap(hjbGl,"md","22410302","&date&强制还款",1,1,"2001","新",4,"140605"));
				list.add(addAccvouchMap(hjbGl,"mc","140605","&date&强制还款",1,2,"2001","新",4,"22410302"));
				hjbGl.setCnyAmount(hjbGl.getCnyAmount().multiply(new BigDecimal(-1)));
				list.add(addAccvouchMap(hjbGl,"md","640101","&date&强制还款",1,1,"2003","新",4,"140606"));
				list.add(addAccvouchMap(hjbGl,"mc","140606","&date&强制还款",1,2,"2003","新",4,"640101"));
			}else if(hjbGl.getAtomCode().equals("11407")){
				list.add(addAccvouchMap(hjbGl,"mc","22410301","&date&强制还款",1,1,"2002","新",4,""));
			}else if(hjbGl.getAtomCode().equals("11003")){
				countAmout=hjbGl.getCnyAmount();
				if(cash.doubleValue()>0){
					cash=hjbGl.getCnyAmount().subtract(cash);
				}else{
					cash=hjbGl.getCnyAmount();
				}
			}else if(hjbGl.getAtomCode().equals("11403")){
				if(cash.doubleValue()>0){
					cash=cash.subtract(hjbGl.getCnyAmount());
				}else{
					cash=hjbGl.getCnyAmount();
				}
			}
		}
		
		HjbGlList hjbGl2 = new HjbGlList();
		hjbGl2.setBizCode("CL003");
		hjbGl2.setCnyAmount(cash);
		list.add(addAccvouchMap(hjbGl2,"mc","22410304","&date&强制还款",1,2,"2002","新",4,""));
		HjbGlList hjbGl3 = new HjbGlList();
		hjbGl3.setBizCode("CL003");
		hjbGl3.setCnyAmount(countAmout.subtract(countAmout.divide(new BigDecimal(1.17),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(0.17)).setScale(2,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(-1)));
		list.add(addAccvouchMap(hjbGl3,"mc","600101","&date&强制还款",1,3,"2002","新",4,""));
		HjbGlList hjbGl4 = new HjbGlList();
		hjbGl4.setBizCode("CL003");
		hjbGl4.setCnyAmount(countAmout.divide(new BigDecimal(1.17),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(0.17)).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(-1)));
		list.add(addAccvouchMap(hjbGl4,"mc","22211205","&date&强制还款",1,4,"2002","新",4,""));
//		return list;
	}
	/**
	 * 业务CL004黄金券还贷款特殊处理
	 * @param resultMap
	 * @return
	 */
//	private void addHjbCl004(List<Map<String,Object>> list,String transaction_Id){
////		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		Map<String,String> paramMap = new HashMap<String,String>();
//		paramMap.put("biz_code", "CL004");
//		paramMap.put("transaction_Id", transaction_Id);
//		List<HjbGlList> hjbGlList = hjbService.queryByBizCode(paramMap);
//		LogTracking.debugLog(this.getClass(), "hjbGlList", hjbGlList!=null?hjbGlList.size()+"":"无数据");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		int month = calendar.get(Calendar.MONTH);
//		BigDecimal cash = new BigDecimal(0);
//		BigDecimal countAmout = new BigDecimal(0);
//		for(HjbGlList hjbGl:hjbGlList){
//			if(hjbGl.getAtomCode().equals("11401")){
//				
//				list.add(addAccvouchMap(hjbGl,"md","22410305","&date&黄金券还贷款",1,1,"2004","新",4,"140605"));
//				list.add(addAccvouchMap(hjbGl,"mc","140605","&date&黄金券还贷款",1,1,"2004","新",4,"22410305"));
//				hjbGl.setCnyAmount(hjbGl.getCnyAmount().multiply(new BigDecimal(-1)));
//				list.add(addAccvouchMap(hjbGl,"md","640101","&date&黄金券还贷款",1,1,"2006","新",4,"140606"));
//				list.add(addAccvouchMap(hjbGl,"mc","140606","&date&黄金券还贷款",1,1,"2006","新",4,"640101"));
//			}else if(hjbGl.getAtomCode().equals("11407")){
//				list.add(addAccvouchMap(hjbGl,"md","22410301","&date&黄金券还贷款",1,1,"2005","新",4,""));
//				countAmout=hjbGl.getCnyAmount();
//			}else if(hjbGl.getAtomCode().equals("11003")){
//				if(cash.doubleValue()>0){
//					cash=cash.subtract(hjbGl.getCnyAmount());
//				}else{
//					cash=hjbGl.getCnyAmount();
//				}
//			}else if(hjbGl.getAtomCode().equals("11403")){
//				if(cash.doubleValue()>0){
//					cash=hjbGl.getCnyAmount().subtract(cash);
//				}else{
//					cash=hjbGl.getCnyAmount();
//				}
//			}
//		}
//		
//		HjbGlList hjbGl2 = new HjbGlList();
//		hjbGl2.setBizCode("CL004");
//		hjbGl2.setCnyAmount(cash);
//		list.add(addAccvouchMap(hjbGl2,"mc","22410301","&date&黄金券还贷款",1,2,"2005","新",4,""));
//		HjbGlList hjbGl3 = new HjbGlList();
//		hjbGl3.setBizCode("CL004");
//		hjbGl3.setCnyAmount(countAmout.subtract(countAmout.divide(new BigDecimal(1.17),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(0.17)).setScale(2,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(-1)));
//		list.add(addAccvouchMap(hjbGl3,"mc","600101","&date&黄金券还贷款",1,3,"2005","新",4,""));
//		HjbGlList hjbGl4 = new HjbGlList();
//		hjbGl4.setBizCode("CL004");
//		hjbGl4.setCnyAmount(countAmout.divide(new BigDecimal(1.17),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(0.17)).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(-1)));
//		list.add(addAccvouchMap(hjbGl4,"mc","22211205","&date&黄金券还贷款",1,4,"2005","新",4,""));
////		return list;
//	}
	private Map<String,Object> addAccvouchMap(HjbGlList hjbGl,String md_mc,
			String code,String trade_desc,Integer ino_id,Integer inid,
			String count_manner,String csign,Integer isignseq,String cCode_equal){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ino_id", ino_id);
		map.put("inid", inid);
		map.put("sub_code", code);
		map.put("md_mc", md_mc);
		map.put("trade_desc", trade_desc);
		map.put("count_manner", count_manner);
		map.put("cCode_equal", cCode_equal);
		map.put("csign",csign);
		map.put("isignseq", isignseq);
		map.put("biz_code", hjbGl.getBizCode());
		map.put("atom_code", hjbGl.getAtomCode());
		map.put("cny_Amount", hjbGl.getCnyAmount());
		return map;
	}
}
