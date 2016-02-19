package com.multigold.mdm.web.controller.sales;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.multigold.common.service.BaseService;
import com.multigold.common.util.Excel2007Utils;
import com.multigold.common.util.ExcelTxlUtils;
import com.multigold.common.util.ExportExcel;
import com.multigold.common.util.PropertiesUtils;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.mst.DataDictionary;
import com.multigold.mdm.service.api.mst.DataDictionaryService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.t6.service.api.sales.SalesService;
import com.multigold.t6.vo.sales.SaleTransactionVO;
import com.multigold.t6.vo.sales.SalesCheckVO;

/**
 *  
 * @author yangjun
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesProceeds")
public class SalesProceedsController extends BaseCRUDAction<SaleTransactionVO, String>{

	@Autowired
	DataDictionaryService<DataDictionary, Integer> dataDictionaryServive;
	
	
	@Autowired
	SalesService<SaleTransactionVO, String> salesService; 
	@Override
	public BaseService<SaleTransactionVO, String> getBaseService() {
		return salesService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request, SaleTransactionVO saleTransactionVO,
			String operateFlag) {
		saleTransactionVO.setInsertBy(UserSessionProvider.getUserSerssion(request).getAccount());
		saleTransactionVO.setModifiedBy(UserSessionProvider.getUserSerssion(request).getAccount());
	}
	
	/*
	 * @Title: salesCheck 
	 * @Description:  修改html名称与权限系统做关联
	 * @return String
	 */
	@RequestMapping(value = "salesProceeds", method = RequestMethod.GET)
	public String salesCheckList() {
		return viewName("salesProceeds");
	}
	
	/**
	 * 销售收款信息查询
	 * @param request
	 * @param paymentCode
	 * @param checkStatus
	 * @param timeFrom
	 * @param timeTo
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "salesProceedsLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSalesCheckLists(HttpServletRequest request, String paymentCode,String orderChannel, Date timeFrom, Date timeTo,Long page, Integer rows) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!StringUtils.isEmpty(paymentCode)) {
			map.put("paymentCode", "%"+paymentCode+"%");
		}
		if (!StringUtils.isEmpty(timeFrom)) {
			map.put("timeFrom", sdf.format(timeFrom));
		}
		if (!StringUtils.isEmpty(timeTo)) {
			map.put("timeTo", sdf.format(timeTo)+" 23:59:59");
		}
		if (!StringUtils.isEmpty(orderChannel)) {
			map.put("orderChannel", orderChannel);
		}
		map.put("page", page);
		map.put("rows", rows);
		resultMap= salesService.getSalesProceedsLists(map);
		return resultMap;
	}
	
	/**
	 * 导入第三方收款信息
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "importProceeds", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importProceeds(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "file", required = false) MultipartFile file) {
		final Map<String, Object> map = Maps.newHashMap();
		map.put("message", "error");
		SaleTransactionVO saleTransactionVO = null;
		List<Object> objList = new ArrayList<Object>();
		String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
		try {
			InputStream instream = file.getInputStream();
//			String[] headers = PropertiesUtils.getPropValue("").split(",");
			String[] headers = {"订单号","订单日期","总金额","优惠金额","实际收款","运费","销售渠道"};
			String[] paramNames = {"paymentCode","orderDate","totalAmount","discountAmount","sum","freight","channel"};
			String[] paramTypes = {"String","Date","BigDecimal","BigDecimal","Double","BigDecimal","String"};
			String entityPath ="com.multigold.t6.vo.sales.SaleTransactionVO";
				if(fileName.lastIndexOf(".")>1){
					String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
				if(fileType.equals("xls")){
					objList = ExcelTxlUtils.excelToEntity(headers, paramNames, paramTypes, entityPath, instream);
				}else if(fileType.equals("xlsx")){
					objList = Excel2007Utils.excelToEntity(headers, paramNames, paramTypes, entityPath, instream);
				}
				
			}
			List<SaleTransactionVO> tlhList = new ArrayList<SaleTransactionVO>();
			if(objList!=null && objList.size()>0){
				for (int i = 0; i < objList.size(); i++) {
					Object object  = objList.get(i);
					saleTransactionVO=(SaleTransactionVO)object;
					//写入录入人
					saleTransactionVO.setInputer(UserSessionProvider.getUserSerssion(request).getAccount());
					saleTransactionVO.setCreateTd(new Date());
					tlhList.add(saleTransactionVO);
					if((i!=0 && i%100==0) || i==objList.size()-1){
						salesService.insertSalesPaymentExcel(tlhList);
						tlhList = new ArrayList<SaleTransactionVO>();
					}
				}
				map.put("message", "success");
			}else{
				map.put("errorLog", "文件内容异常！");
			}
		} catch (IOException e) {
			map.put("errorLog", "数据读写异常！");
			e.printStackTrace();
		} catch (BiffException e) {
			map.put("errorLog", "Excel文件异常！");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			map.put("errorLog", "类名异常！");
			e.printStackTrace();
		} catch (InstantiationException e) {
			map.put("errorLog", "实例化异常！");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			map.put("errorLog", "安全权限异常！");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			map.put("errorLog", "方法异常！");
			e.printStackTrace();
		} catch (SecurityException e) {
			map.put("errorLog", "您没有权限！");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			map.put("errorLog", "不合法的参数异常！");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			map.put("errorLog", "调用方法或构造方法异常！");
			e.printStackTrace();
		} catch (ParseException e) {
			map.put("errorLog", "参数格式异常！");
			e.printStackTrace();
		} catch (Exception e) {
			map.put("errorLog", "数据库导入异常！");
			
			e.printStackTrace();
		} 
		return map;
	}
	
	/**
	 * 模板下载
	 * 
	 * @param
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "exportTemplate", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg="";
//	    InetAddress addr = InetAddress.getLocalHost();
//	    String ip = addr.getHostAddress();
//	    System.out.println(ip);
//		String filePath = "E:\\第三方收款信息导入模板.xls";
		String filePath = new String(PropertiesUtils.getPropValue("filePath").getBytes("iso-8859-1"),"utf8");
		File file = new File(filePath);
		String fileName=file.getName();
		response.setContentType("application/vnd.ms-excel;charset=utf8"); 
		response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("utf8"),"ISO_8859_1")); 
        response.setHeader("Pragma","No-cache");//设置头
        response.setHeader("Cache-Control","no-cache");//设置头
        response.setDateHeader("Expires", 0);//设置日期头
//        response.setCharacterEncoding("utf8");
        OutputStream outputStream = null;
        FileInputStream in = null;
		try {
			outputStream = response.getOutputStream();
			in = new FileInputStream(file);
			 byte[] buffer = new byte[1024];
             int bytesRead = -1;
             while ((bytesRead = in.read(buffer)) != -1) {
            	 outputStream.write(buffer, 0, bytesRead);
             }
             outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "导出文件失败！";
		}finally{
			if(in!=null)
			in.close();
		}
		return msg;
	}
	
	/*
	 * 加载下拉列表：销售渠道
	 * @return
	 */
	@RequestMapping(value = "salesProceedsChannelNameList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getChannelNameList(HttpServletResponse response) {
		List<Map<String, Object>> list = Lists.newArrayList();
		try {
			List<SaleTransactionVO> queryList = salesService.queryChannelNameList();
			if ((null != queryList) && (queryList.size() > 0)) {
				for (SaleTransactionVO systemModel : queryList) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("orderChannel", systemModel.getCorderChannl());
					map.put("cremark", systemModel.getCremark());
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
