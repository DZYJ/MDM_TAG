/**
 * 
 */
package com.multigold.mdm.web.controller.mst;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multigold.common.service.BaseService;
import com.multigold.common.util.ExportExcel;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.address.Division;
import com.multigold.mdm.entity.mst.HubProcessMatrix;
import com.multigold.common.util.ImportExcel;
import com.multigold.mdm.entity.mst.TempLspHub;
import com.multigold.mdm.service.api.address.DivisionService;
import com.multigold.mdm.service.api.mst.HubProcessMatrixService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.upms.entity.security.User;

/**
 * @author mayanhu
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/lsp")
public class ReachareasController extends BaseCRUDAction<HubProcessMatrix, String> {

	@Autowired
	HubProcessMatrixService<HubProcessMatrix, String> hubProcessMatrixService;
	
	@Autowired
	DivisionService<Division, Integer> divisionService;
	

	@Override
	public BaseService<HubProcessMatrix, String> getBaseService() {
		return hubProcessMatrixService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, HubProcessMatrix hubProcessMatrix, String operateFlag) {
		
	}

	@RequestMapping(value = "reachareas1", method = RequestMethod.GET)
	public String reachareas1() {
		return viewName("reachareas1");
	}
	
	@RequestMapping(value = "reachareas2", method = RequestMethod.GET)
	public String reachareas2() {
		return viewName("reachareas2");
	}
	
	@RequestMapping(value = "lspCarrierList", method = RequestMethod.GET)
	public String lspCarrierList() {
		return viewName("lspCarrierList");
	}
	@RequestMapping(value = "virtualWareHouseList", method = RequestMethod.GET)
	public String virtualWareHouse() {
		return viewName("virtualWareHouseList");
	}
	
	/**
	 * 列表 GET 获取页面 pickuptime.htm
	 * 
	 * @return
	 */
	@RequestMapping(value = "pickuptime", method = RequestMethod.GET)
	public String pickuptime() {
		return viewName("pickuptime");
	}
	
	/**
	 * 列表 GET 获取页面 lspDayHalfInfo.htm
	 * 
	 * @return
	 */
	@RequestMapping(value = "lspDayHalfInfo", method = RequestMethod.GET)
	public String lspDayHalfInfo() {
		return viewName("lspDayHalfInfo");
	}
	
	/**
	 * 列表 GET 获取页面运力页面 transport.htm
	 * 
	 * @return
	 */
	@RequestMapping(value = "transport", method = RequestMethod.GET)
	public String transport() {
		return viewName("transport");
	}
	
	/**
	 * 列表 GET 获取页面运力页面 transport.htm
	 * 
	 * @return
	 */
	@RequestMapping(value = "transCapacity", method = RequestMethod.GET)
	public String transCapacity() {
		return viewName("transCapacity");
	}
	
	/**
	 * 导出报表
	 * @param  
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.POST)
	@ResponseBody
	public Object export(@RequestParam("masLoc") String masLoc,@RequestParam("fourthLevel") String fourthLevel,
			HttpServletResponse response) {
		String msg="";
		String[] fourthLevelArr = fourthLevel.split(",");
		
		List<String> listDivisionCode = Arrays.asList(fourthLevelArr);
		
		List<HubProcessMatrix> exportList = hubProcessMatrixService.getExportList(masLoc, listDivisionCode);

		if(null != exportList && exportList.size()>0){
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd-HH-mm-ss"); //格式不能带冒号，否则转码不成功会出现乱码导致在火狐下保存的文件无后缀名
			String nameDate = sdf.format(date);
	    	String fileName = "区域配置报表"+nameDate+".xls";//设置下载时客户端Excel的名称 
			
			ExportExcel<HubProcessMatrix> expor = new ExportExcel<HubProcessMatrix>(fileName,exportList.get(0).getHeader(),
					exportList.get(0).getExportFieldNames(),exportList, "yyy-MM-dd HH:mm:ss");
			return expor;
		}else{
			response.setContentType("text/html");  
			msg = "无数据不能导出！";
		}
		return msg;
	}
	
	
	/**
	 * 通过树形区域导出报表
	 * @param  
	 * @return
	 */
	@RequestMapping(value = "exportFromTree", method = RequestMethod.POST)
	@ResponseBody
	public Object exportFromTree(@RequestParam("masLoc") String masLoc,
			@RequestParam("divisionCodes[]") String divisionCodes[],HttpServletResponse response) {
		
		String msg="";
		List<Division> childList = new ArrayList<Division>();
		
		if(divisionCodes!=null && divisionCodes.length>0){
			List<Division> divisionList = new ArrayList<Division>();
			if(divisionCodes[0].contains(",")){
				for (int i = 0; i < divisionCodes.length; i++) {
					if(divisionCodes[i]!=null && divisionCodes[i].length()>0){
						
						String[] putItems = divisionCodes[i].split(",");
						Division division = new Division();
						division.setDivisionCode(putItems[0]);
						division.setDivLevel(Integer.parseInt(putItems[1]));
						divisionList.add(division);
					}
				}
			}else{
					Division division = new Division();
					division.setDivisionCode(divisionCodes[0]);
					division.setDivLevel(Integer.parseInt(divisionCodes[1]));
					divisionList.add(division);
			}
			childList = divisionService.recursiveTree(divisionList,childList);
		}
		
		List<String> codeList = new ArrayList<>();
		for (int i = 0; i < childList.size(); i++) {
			codeList.add(childList.get(i).getDivisionCode());
		}

		List<HubProcessMatrix> exportList = hubProcessMatrixService.getExportList(masLoc, codeList);
		
		if(null != exportList && exportList.size()>0){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd-HH-mm-ss"); //格式不能带冒号，否则转码不成功会出现乱码导致在火狐下保存的文件无后缀名
			String nameDate = sdf.format(date);
	    	String fileName = "区域配置报表"+nameDate+".xls";//设置下载时客户端Excel的名称
			
			ExportExcel<HubProcessMatrix> expor = new ExportExcel<HubProcessMatrix>(fileName,exportList.get(0).getHeader(),
					exportList.get(0).getExportFieldNames(),exportList, "yyy-MM-dd HH:mm:ss");
			return expor;
		}else{
			response.setContentType("text/html");  
			msg = "无数据不能导出！";
		}
		return msg;
	}
	
	
	/**
	 * 导入报表
	 * @param tempLspHubList 
	 * @return
	 */
	@RequestMapping(value = "import", method = RequestMethod.POST)
	@ResponseBody
	public String imports(@RequestParam(value = "file", required = false) MultipartFile file, 
			HttpServletRequest request,HttpServletResponse response) {
		
		response.setContentType("text/html");  
		
		String msg ="导入成功！";
		
		String fileName = file.getOriginalFilename(); 
		try {
			InputStream input = file.getInputStream();
		
			List<List<Object>> listobjectList  = ImportExcel.importExcel(fileName,input);
			List<TempLspHub> tlhList = new ArrayList<>();
			for (int i = 0; i < listobjectList.size(); i++) {
				List<Object> objects  = listobjectList.get(i);
				TempLspHub tlh = new TempLspHub();
				
				tlh.setMasLoc(objects.get(0).toString());
				tlh.setDivisionCode(objects.get(1).toString());
				tlh.setLineHaul(objects.get(2).toString());
				tlh.setLspCode(objects.get(3).toString());
				tlh.setShipMethod(objects.get(4).toString());
				tlh.setTradeInIdentifier(objects.get(5).toString());
				tlh.setPaymentTerm(objects.get(6).toString());
				tlh.setSupportDirection(objects.get(7).toString());
				tlh.setSelfPickup(objects.get(8).toString());
				tlh.setGoodsCollectFlag(objects.get(9).toString());
				if(null != objects.get(10) && !objects.get(10).toString().isEmpty())
				tlh.setDeliveryLeadTime(Float.parseFloat(objects.get(10).toString()));
				
				tlhList.add(tlh);
			}

			User user=UserSessionProvider.getUserSerssion(request);
			String insertBy = "";
			if(null!=user ){
				insertBy = user.getName();
			}
			msg = hubProcessMatrixService.importHubProcessMatrix(tlhList,insertBy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

}
