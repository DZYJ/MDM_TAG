package com.multigold.mdm.web.controller.mst;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multigold.common.constants.Constants;
import com.multigold.common.constants.TCConstants;
import com.multigold.common.exception.BaseException;
import com.multigold.common.service.BaseService;
import com.multigold.common.util.ExportExcel;
import com.multigold.common.util.ImportExcel;
import com.multigold.common.util.LogTracking;
import com.multigold.common.util.ReturnCode;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.common.web.springmvc.MessageResolver;
import com.multigold.mdm.entity.mst.MstSelfPickup;
import com.multigold.mdm.service.api.mst.MstSelfPickupService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.google.common.collect.Maps;

/**
 * 
 * @author guoqiushi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/mstSelfPickup")
public class MstSelfPickupController extends BaseCRUDAction<MstSelfPickup, Integer> {

	@Autowired
	MstSelfPickupService<MstSelfPickup, Integer> mstSelfPickupService;

	@Override
	public BaseService<MstSelfPickup, Integer> getBaseService() {
		return mstSelfPickupService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, MstSelfPickup t, String operateFlag) {
		t.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		t.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
		t.setBuid(Integer.parseInt(TCConstants.TNT.BUID));
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> create(final HttpServletRequest request, @ModelAttribute MstSelfPickup mstSelfPickup) { 
		LogTracking.debugLog(this.getClass(), "create", "create bean");
		final Map<String, Object> map = Maps.newHashMap();
		try {
			setDefaultValue(request, mstSelfPickup, "create");// 临时设置默认值
			
			int i = this.mstSelfPickupService.createEntity(mstSelfPickup);
			if (i > 0) {
				map.put("msg", MessageResolver.getMessage(request,Constants.Message.SUCCESS_SAVE));
				map.put("state", Constants.State.TRUE);
			} else if(i==0) {
				map.put("msg", ReturnCode.codeToMsg(ReturnCode.PICKUP_RECORD_EXIST));
				map.put("state", Constants.State.FALSE);
			}
			return map;
		} catch (BaseException e) {
			e.printStackTrace();
			map.put("msg", MessageResolver.getMessage(request,Constants.Message.FAILED_SAVE));
			map.put("state", Constants.State.FALSE);
			LogTracking.errorLog(this.getClass(), "create", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogTracking.debugLog(this.getClass(), "create", "create end");
		return map;
	}
	
	/**
	 * 导出报表
	 * @param  
	 * @return
	 */
	@RequestMapping(value = "export", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object export(@ModelAttribute MstSelfPickup mstSelfPickup, HttpServletRequest request , HttpServletResponse response) {
		String msg="";
		
		List<MstSelfPickup> exportList = mstSelfPickupService.getExportList(mstSelfPickup);

		if(null != exportList && exportList.size()>0){
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd-HH-mm-ss"); //格式不能带冒号，否则转码不成功会出现乱码导致在火狐下保存的文件无后缀名
			String nameDate = sdf.format(date);
	    	String fileName = "自提点信息"+nameDate+".xls";//设置下载时客户端Excel的名称 
			
			ExportExcel<MstSelfPickup> expor = new ExportExcel<MstSelfPickup>(fileName,exportList.get(0).getHeader(),
					exportList.get(0).getExportFieldNames(),exportList, "yyy-MM-dd HH:mm:ss");
			return expor;
		}else{
			response.setContentType("text/html");  
			msg = "无数据不能导出！";
		}
		return msg;
	}
	
	/**
	 * 模板下载
	 * 
	 * @param
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "exportTemplate", method = { RequestMethod.POST, RequestMethod.GET })
	public Object download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg="";
		try {
			List<MstSelfPickup> exportList = new ArrayList<MstSelfPickup>();
			exportList.add(new MstSelfPickup());
			String fileName = "自提点信息模板.xls";//设置下载时客户端Excel的名称 
			String[] header = new String[]{"自提点编号", "自提点名称", "承运商编码", "区域编码", 
		    "区域名称","自提点电话区号", "集团门店编码", "自提点联系人", "自提点电话","自提点电邮" ,"自提点邮编","自提点传真" };
			String[] exportFieldNames = new String[]{"selfSiteCode","selfSiteName","lsp","divisionCode",
			"divisionName","telZoneCode","companyStoreCode", "contact","telNum","email", "zipCode", "fax" };
			ExportExcel<MstSelfPickup> expor = new ExportExcel<MstSelfPickup>(fileName,header,
					exportFieldNames,exportList, "yyy-MM-dd HH:mm:ss");
			return expor;
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html");  
			msg = "导出文件失败！";
		}
		return msg;
	}
	
	/**
	 * 导入报表
	 * @return
	 */
	@RequestMapping(value = "import", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> imports(@RequestParam(value = "file", required = false) MultipartFile file, 
			HttpServletRequest request,HttpServletResponse response) {
		
		Map<String,String> map=Maps.newHashMap();
		map.put("state", "success");
		
		String fileName = file.getOriginalFilename(); 
		try {
			InputStream input = file.getInputStream();
		
			List<List<Object>> listobjectList  = ImportExcel.importExcel(fileName,input);
			List<MstSelfPickup> tlhList = new ArrayList<MstSelfPickup>();
			
			for (int i = 0; i < listobjectList.size(); i++) {
				List<Object> objects  = listobjectList.get(i);
				MstSelfPickup msp = new MstSelfPickup();
				
				msp.setBuid(8270);
				msp.setSelfSiteCode(objects.get(0).toString());
				msp.setSelfSiteName(objects.get(1).toString());
				msp.setLsp(objects.get(2).toString());
				msp.setDivisionCode(objects.get(3).toString());
				msp.setDivisionName(objects.get(4).toString());
				msp.setTelZoneCode(objects.get(5).toString());
				msp.setCompanyStoreCode(objects.get(6).toString());
				msp.setContact(objects.get(7).toString());
				msp.setTelNum(objects.get(8).toString());
				msp.setEmail(objects.get(9).toString());
				msp.setZipCode(objects.get(10).toString());
				msp.setFax(objects.get(11).toString());
				msp.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
				msp.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
				
				tlhList.add(msp);
			}

			mstSelfPickupService.importDataList(tlhList);
			map.put("msg", "导入成功！");
		} catch (Exception e) {
			map.put("msg", "导入失败！");
			e.printStackTrace();
		}
		return map;
	}
	
}
