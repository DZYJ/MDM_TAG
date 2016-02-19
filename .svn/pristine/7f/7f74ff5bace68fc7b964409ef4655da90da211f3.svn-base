package com.multigold.mdm.web.controller.accvouch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.t6.entity.accvouch.AccvouchEntity;
import com.multigold.t6.service.api.accvouch.PoAccvouchService;

@Controller
@RequestMapping(value = "${adminPath}/accvouch")
public class AccvouchMangerController extends BaseCRUDAction<AccvouchEntity, String> {

	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	PoAccvouchService<AccvouchEntity, String> poAccvouchService;
	
	@Override
	public BaseService<AccvouchEntity, String> getBaseService() {
		return poAccvouchService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, AccvouchEntity accvouchEntity,
			String operateFlag) {
		accvouchEntity.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		accvouchEntity.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/*
	 * 获取凭证列表
	 * @return
	 */
	@RequestMapping(value = "accvouchList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAccvouchLists(HttpServletRequest request, Date startDate,Date endDate,String period,String ino_id, String signseq, Long page, Integer rows) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		if (!StringUtils.isEmpty(startDate)) {
			map.put("startDate", startDate);
		}
		if (!StringUtils.isEmpty(endDate)) {
			map.put("endDate", endDate);
		}
		if (!StringUtils.isEmpty(period)) {
			map.put("iPeriod", period);
		}
		if (!StringUtils.isEmpty(ino_id)) {
			map.put("iNo_id", ino_id);
		}
		if (!StringUtils.isEmpty(signseq)) {
			map.put("isignseq", signseq);
		}
		
		map.put("page", page);
		map.put("rows", rows);
		resultMap= poAccvouchService.queryAccCollect(map);
		return resultMap;
		
	}
	
	/**
	 * 获取凭证信息
	 * @param request
	 * @param iperiod
	 * @param isignseq
	 * @param ino_id
	 * @return
	 */
	@RequestMapping(value = "accvouchDetail", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> accvouchDetail(HttpServletRequest request,Integer iperiod,Integer isignseq,Integer ino_id){
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		System.out.println("---"+request.getParameter("iperiod"));
		System.out.println("++++"+request.getAttribute("iperiod"));
//		iperiod = Integer.parseInt(request.getParameter("iperiod"));
//		isignseq = Integer.parseInt(request.getParameter("isignseq"));
//		ino_id = Integer.parseInt(request.getParameter("ino_id"));
		if (!StringUtils.isEmpty(iperiod)) {
			map.put("iperiod", iperiod);
		}
		if (!StringUtils.isEmpty(isignseq)) {
			map.put("isignseq", isignseq);
		}
		if (!StringUtils.isEmpty(ino_id)) {
			map.put("ino_id", ino_id);
		}
//		map.put("page", page);
//		map.put("rows", rows);
		resultMap= poAccvouchService.accvouchDetail(map);
		return resultMap;
	}
	/**
	 * 获取凭证信息
	 * @param request
	 * @param iperiod
	 * @param isignseq
	 * @param ino_id
	 * @return
	 */
	@RequestMapping(value = "exportNcAcc", method = RequestMethod.GET)
	@ResponseBody
	public String exportNcAcc(HttpServletRequest request,HttpServletResponse response,String iperiod,String isignseq,String ino_id,String ino_id_start,String ino_id_end,Date startDate,Date endDate) throws Exception{
		Map<String, Object> map = Maps.newHashMap();
		if (!StringUtils.isEmpty(iperiod)) {
			map.put("iPeriod", iperiod);
		}
		if (!StringUtils.isEmpty(isignseq)) {
			map.put("isignseq", isignseq);
		}
		if (!StringUtils.isEmpty(ino_id)) {
			map.put("iNo_id", ino_id);
		}
		if (!StringUtils.isEmpty(startDate)) {
			map.put("startDate", startDate);
		}
		if (!StringUtils.isEmpty(endDate)) {
			map.put("endDate", endDate);
		}
		if (!StringUtils.isEmpty(ino_id_start)) {
			map.put("ino_id_start", ino_id_start);
		}
		if (!StringUtils.isEmpty(ino_id_end)) {
			map.put("ino_id_end", ino_id_end);
		}
		String message=null;
		try {
			message = poAccvouchService.queryByCondition(map);
			if(message.equals("error")){
				throw new Exception("凭证导出失败！");
			}else{
				String downloadfFileName = "accvouch_"+new SimpleDateFormat("yyyy_MMddHHmmss").format(new Date())+".xml";
	            downloadfFileName = new String(downloadfFileName.getBytes("iso-8859-1"),"utf-8");
	            String fileName = downloadfFileName.substring(downloadfFileName.indexOf("_")+1);
	            String userAgent = request.getHeader("User-Agent");
	            byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8"); 
	            fileName = new String(bytes, "ISO-8859-1");
	            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
	            ServletOutputStream out = response.getOutputStream();
	            try {
	                FileInputStream in = new FileInputStream(new File(message));
	                
	                byte[] buffer = new byte[1024];
	                int bytesRead = -1;
	                while ((bytesRead = in.read(buffer)) != -1) {
	                    out.write(buffer, 0, bytesRead);
	                }
	                out.flush();
	            } catch (FileNotFoundException e) {
	            	log.error("导出凭证异常：", e);
	            } catch (IOException e) {
	            	log.error("导出凭证异常：", e);
	            }   
				return null;
			}
			
		} catch (ApplicationException e) {
			message=e.getMessage();
			log.error("导出凭证异常：", e);
		}
		return message;
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
	
	@RequestMapping(value = "getCsign", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<Integer,String>> getCsign(HttpServletRequest request){
		List<Map<Integer,String>> signList = poAccvouchService.queryCsign();
		return signList;
	}
}
