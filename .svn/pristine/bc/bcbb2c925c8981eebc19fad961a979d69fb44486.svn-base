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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multigold.common.service.BaseService;
import com.multigold.common.util.ExportExcel;
import com.multigold.common.util.ImportExcel;
import com.multigold.common.util.StringUtil;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.MasLoc;
import com.multigold.mdm.service.api.mst.MasLocService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.google.common.collect.Maps;
/**
 * 
 * @author musaisai
 * Date:2014/7/18
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/masLoc")
public class MasLocController extends BaseCRUDAction<MasLoc, String> {
	
	@Autowired(required=false)
	MasLocService<MasLoc, String> masLocService;

	@Override
	public BaseService<MasLoc, String> getBaseService() {
		return masLocService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, MasLoc masloc, String operateFlag) {
		masloc.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		masloc.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/**
	 * 获取仓库下拉列表
	 * @return
	 */
	@RequestMapping(value = "getMasLocListCombobox", method = RequestMethod.POST)
	@ResponseBody
	public List<MasLoc> getMasLocListCombobox() {
		
		return masLocService.getMasLocListCombobox();
	}
	
	/**
	 * 导出报表
	 * @param  
	 * @return
	 */
	@RequestMapping(value = "export", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object export(@ModelAttribute MasLoc masLoc, HttpServletRequest request , HttpServletResponse response) {
		String msg="";
		
		List<MasLoc> exportList = masLocService.getExportList(masLoc);

		if(null != exportList && exportList.size()>0){
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd-HH-mm-ss"); //格式不能带冒号，否则转码不成功会出现乱码导致在火狐下保存的文件无后缀名
			String nameDate = sdf.format(date);
	    	String fileName = "仓库信息报表"+nameDate+".xls";//设置下载时客户端Excel的名称 
			
			ExportExcel<MasLoc> expor = new ExportExcel<MasLoc>(fileName,exportList.get(0).getHeader(),
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
			List<MasLoc> exportList = new ArrayList<MasLoc>();
			exportList.add(new MasLoc());
	    	String fileName = "仓库信息报表模板.xls";//设置下载时客户端Excel的名称 
	    	String[] header = new String[]{ "仓库编码", "承运商编码","仓库名称", "中心坐标", "主库类型", "配船编码","区域编码","时区",
	    	"库区类型","国家代码","父节点仓库编码","联系人名称","联系人电话","地址","邮编","可控类型","可控标识","传送方类型","城市" };
	    	String[] exportFieldNames = new String[]{"masLoc","lsp","descr","centralLocation","hubType","ccnCode","areaCode","timezone",
	    	"bucketType","countryCode","parentMasLoc","contactName","contactPhone","address","zipCode","manageableType","manageableFlag","sendHubType","city" }; 
			ExportExcel<MasLoc> expor = new ExportExcel<MasLoc>(fileName,header,
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
		StringBuffer msg = new StringBuffer();
		String masLoc = null;
		String descr = null;
		String fileName = file.getOriginalFilename(); 
		try {
			InputStream input = file.getInputStream();
		
			List<List<Object>> listobjectList  = ImportExcel.importExcel(fileName,input);
			List<MasLoc> tlhList = new ArrayList<MasLoc>();
			for (int i = 0; i < listobjectList.size(); i++) {
				List<Object> objects  = listobjectList.get(i);
				if (objects.size() != 19) {
					msg.append("第" + (i+1) + "行，数据无法识别。原因可能为：有数据列数据未填写，请复制excel模板表格中有边框的行进行修改！");
					break;
				}
				MasLoc ml = new MasLoc();
				
				//加入对仓库编码和仓库名称的判定
				masLoc = objects.get(0).toString();
				if (StringUtils.isEmpty(masLoc)) {
					msg.append("第" + (i+1) + "行，仓库编码不能为空！");
					break;
				}
				descr = objects.get(2).toString();
				if (StringUtils.isEmpty(descr)) {
					msg.append("第" + (i+1) + "行，仓库名称不能为空！");
					break;
				}
				ml.setMasLoc(objects.get(0).toString());
				ml.setLsp(objects.get(1).toString());
				ml.setDescr(objects.get(2).toString());
				ml.setEnabled(1);
				ml.setCentralLocation(objects.get(3).toString());
				ml.setHubType(objects.get(4).toString());
				//ml.setCcnCode(objects.get(5).toString());
				ml.setAreaCode(objects.get(6).toString());
				ml.setTimezone(objects.get(7).toString());
				ml.setBucketType(objects.get(8).toString());
				ml.setCountryCode(objects.get(9).toString());
				ml.setParentMasLoc(objects.get(10).toString());
				ml.setContactName(objects.get(11).toString());
				ml.setContactPhone(objects.get(12).toString());
				ml.setAddress(objects.get(13).toString());
				ml.setZipCode(objects.get(14).toString());
				ml.setManageableType(objects.get(15).toString());
				ml.setManageableFlag(objects.get(16).toString());
				ml.setSendHubType(objects.get(17).toString());
				ml.setCity(objects.get(18).toString());
				ml.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
				ml.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
				
				tlhList.add(ml);
			}

			if (!tlhList.isEmpty() && msg.toString().isEmpty()) {
				masLocService.importDataList(tlhList);
				msg.append("导入成功！");
			} else {
				msg.append("导入数据为空！");
			}
		} catch (Exception e) {
			if (e.toString().contains("for key 'PRIMARY'")) {
				msg.append("仓库编码已存在！");
			}
			e.printStackTrace();
		}
		map.put("msg", msg.toString());
		return map;
	}
	
}
