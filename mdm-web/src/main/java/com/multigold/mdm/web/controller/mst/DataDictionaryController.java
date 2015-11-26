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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multigold.common.service.BaseService;
import com.multigold.common.util.ExportExcel;
import com.multigold.common.util.ImportExcel;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.DataDictionary;
import com.multigold.mdm.service.api.mst.DataDictionaryService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.google.common.collect.Maps;

/**
 * 
 * @author guoqiushi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/dataDictionary")
public class DataDictionaryController extends BaseCRUDAction<DataDictionary, Integer>{

	@Autowired
	DataDictionaryService<DataDictionary, Integer> dataDictionaryServive;
	
	@Override
	public BaseService<DataDictionary, Integer> getBaseService() {
		return dataDictionaryServive;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, DataDictionary t, String operateFlag) {
		t.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		t.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/**
	 * 导出报表
	 * @param  
	 * @return
	 */
	@RequestMapping(value = "export", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object export(HttpServletRequest request , HttpServletResponse response) {
		String msg="";
		List<DataDictionary> exportList = dataDictionaryServive.getExportList(reference(request));

		if(null != exportList && exportList.size()>0){
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd-HH-mm-ss"); //格式不能带冒号，否则转码不成功会出现乱码导致在火狐下保存的文件无后缀名
			String nameDate = sdf.format(date);
	    	String fileName = "数据字典报表"+nameDate+".xls";//设置下载时客户端Excel的名称 
			
			ExportExcel<DataDictionary> expor = new ExportExcel<DataDictionary>(fileName,exportList.get(0).getHeader(),
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
			List<DataDictionary> exportList = new ArrayList<DataDictionary>();
			exportList.add(new DataDictionary());
			String fileName = "数据字典报表模板.xls";//设置下载时客户端Excel的名称 
			String[] header = new String[]{"英文名称", "中文名称", "字段标识名称", "字段值", "是否活动","备注" };
			String[] exportFieldNames = new String[]{"englishName","chineseName","indicateName",
							"columnValue","isActivity","comment" };
			ExportExcel<DataDictionary> expor = new ExportExcel<DataDictionary>(fileName,header,
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
	@RequestMapping(value = "import", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, String> imports(@RequestParam(value = "file", required = false) MultipartFile file, 
			HttpServletRequest request,HttpServletResponse response) {
		
		Map<String,String> map=Maps.newHashMap();
		map.put("state", "success");
		
		String fileName = file.getOriginalFilename(); 
		try {
			InputStream input = file.getInputStream();
		
			List<List<Object>> listobjectList  = ImportExcel.importExcel(fileName,input);
			List<DataDictionary> tlhList = new ArrayList<DataDictionary>();
			
			for (int i = 0; i < listobjectList.size(); i++) {
				List<Object> objects  = listobjectList.get(i);
				DataDictionary dd = new DataDictionary();
				
				dd.setEnglishName(objects.get(0).toString());
				dd.setChineseName(objects.get(1).toString());
				dd.setIndicateName(objects.get(2).toString());
				dd.setColumnValue(objects.get(3).toString());
				dd.setIsActivity(objects.get(4).toString());
				dd.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
				dd.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
				if(null != objects.get(5) && !objects.get(5).toString().isEmpty())
					dd.setComment(objects.get(5).toString());
				
				tlhList.add(dd);
			}

			dataDictionaryServive.importDataList(tlhList);
			map.put("msg", "导入成功！");
		} catch (Exception e) {
			map.put("msg", "导入失败！");
			e.printStackTrace();
		}
		
		return map;
	}
	
	private DataDictionary reference(HttpServletRequest request) {
		DataDictionary dataDictionary = new DataDictionary();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			dataDictionary.setId(Integer.parseInt(id));
		}
		dataDictionary.setEnglishName(request.getParameter("englishName"));
		dataDictionary.setChineseName(request.getParameter("chineseName"));
		dataDictionary.setIndicateName(request.getParameter("indicateName"));
		dataDictionary.setColumnValue(request.getParameter("columnValue"));
		dataDictionary.setIsActivity(request.getParameter("isActivity"));
		dataDictionary.setComment(request.getParameter("comment"));
		return dataDictionary;
	}
	

}
