package com.multigold.mdm.job;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.multigold.common.util.PropertiesUtils;
import com.multigold.common.util.common.WebAppContext;
import com.multigold.t6.mq.model.TblInterfaceHistory;
import com.multigold.t6.service.api.mq.LogService;
import com.multigold.t6.service.api.mq.TblInterfaceHistoryService;

/**
 * MqLog MQ的日志文件
 * @author bai_lu
 * 2010-11-16
 */
/**
 * MqLog MQ的日志文件
 * @author bai_lu
 * 2010-11-16
 */
public class MqLog {
	
	//protected Log log = LogFactory.getLog(this.getClass());
	public static Log log = LogFactory.getLog(MqLog.class);
	
	public static String saveUrl = PropertiesUtils.getPropValue("SAVE_URL");
 	
	//-----------------接口名称
	public static String DRG_TO_POR_EDD_NAME = "DRG给POR edd";
	public static String DRG_TO_POR_SO_NAME = "DRG给POR销售订单";
	public static String POR_TO_DRG_INVSS_NAME = "POR给DRG可卖数";
	public static String JL_TO_POR_TNT_NAME = "JL给POR订单执行状态";
	public static String POR_TO_DRG_TNT_NAME = "POR给DRG订单执行状态";
	public static String ACK_NAME = "ack";
	
	//-----------------接口编码
	public static String DRG_TO_POR_EDD_CD = "D4.2";
	public static String DRG_TO_POR_SO_CD = "D4.1";
	public static String POR_TO_DRG_INVSS_CD = "D4.3";
	public static String JL_TO_POR_TNT_CD = "D3.5";
	public static String POR_TO_DRG_TNT_CD = "D4.4";

	
	//-----------------目标和源
	public static String DRG = "DRG";
	public static String JL = "JL";
	public static String POR = "POR";
	
	//-----------------
//	public static String EDD = "edd";//"EDD";
//	public static String TNT = "track_n_trace";//"TNT";
//	public static String ATP = "atp";//"ATP";
//	public static String SN = "serial_num";//SN";
//	public static String SO = "ProductionOrder";//"SO";
//	public static String ACK = "ack";//"ACK";
	
	//-----------------错误信息代码
	public static String ERR_001 = "001";	//ec写入文件时失败
	public static String ERR_002 = "002";	//ec写入日志表是失败
	public static String ERR_003 = "003";	//对方传入的数据格式错误
	
	//-----------------XML文件名
	public static String XML_ERROR = "xmlError" ;	//错误的XML 文件夹
	public static String XML_RIGHT = "xmlRight" ;	//正确的 XML 文件夹

	
	/**
	 * 
	 * @param fileContent	文件内容
	 * @param xmlFlag
	 * @param moduleDir	文件目录
	 * @return
	 */
	public static String writeFile(String fileContent, String xmlFlag,String moduleDir) {
		String dir = saveUrl + File.separator + xmlFlag + File.separator + moduleDir + File.separator + getDateStr(new Date(), "yyyy-MM-dd") + File.separator;
		System.out.println(dir);
		
		File file = new File(dir);
		if(!file.exists()) {
			file.mkdirs();
		}
		String filePath = "";
		String fileName = System.currentTimeMillis()+""; 
		try {
			FileOutputStream fileoutputstream = new FileOutputStream(dir+fileName+".xml");
			byte tag_bytes[] = fileContent.getBytes("utf-8");
			fileoutputstream.write(tag_bytes);
		    fileoutputstream.close();
		    filePath = dir + fileName+".xml";
		    
		    log.debug("xml文件生成，生成路径为："+dir+fileName+".xml");
		} catch (Exception e) {
			log.debug("xml文件生成失败，错误原因："+e.getMessage());
		}
		return filePath;
	}
	/**
	 * 
	 * 方法功能：写入日志(根目录为项目工程下的MQXmlErr文件夹)
	 * @param transaction_id	报文ID
	 * @param fileContent	文件内容
	 * @param moduleDir		文件目录
	 * @param ifaceName		接口名称
	 * @param errorMessage	错误消息
	 * @author bai_lu 
	 * @time 2010-12-9
	 */
	public static String writeFile(String transaction_id,String fileContent, String xmlFlag,String moduleDir,String ifaceName,String errorMessage) {
		String filePath = writeFile(fileContent+errorMessage,xmlFlag,moduleDir);
		TblInterfaceHistory tblInterfaceHistory = new TblInterfaceHistory();
		tblInterfaceHistory.setTransaction_id(transaction_id);
	    tblInterfaceHistory.setCreate_date(new Date());
	    tblInterfaceHistory.setIfaceName(ifaceName);
	    tblInterfaceHistory.setSrcCd("OMS");
	    tblInterfaceHistory.setLog_path(filePath);
	    if(errorMessage!=null&&errorMessage.length()>500){
	    	errorMessage=errorMessage.substring(0,300);
	    }
	    if(xmlFlag.equals(XML_RIGHT)){
	    	tblInterfaceHistory.setLog_result("Y");
	    	tblInterfaceHistory.setDisErrorSource(null);
	    }else if(xmlFlag.equals(XML_ERROR)){
	    	tblInterfaceHistory.setLog_result("N");
	    	tblInterfaceHistory.setDisErrorSource(errorMessage);
	    }
		addMqLog(tblInterfaceHistory);
		return filePath;
	}
	
/*	*//**
	 * 
	 * 方法功能：写入日志(根目录为项目工程下的MQXmlErr文件夹)
	 * @param fileContent	文件内容
	 * @param moduleDir		文件目录
	 * @author bai_lu 
	 * @time 2010-12-9
	 *//*
	public static void writeFile(String fileContent, String moduleDir, String err) {
		String dir = WebAppContext.getInstance().getAppPathString() + "MQXmlErr" + File.separator + moduleDir + File.separator + getDateStr(new Date(), "yyyy-MM-dd") + File.separator;
		File file = new File(dir);
		if(!file.exists()) {
			file.mkdirs();
		}
		String fileName = System.currentTimeMillis()+""; 
		try {
			FileOutputStream fileoutputstream = new FileOutputStream(dir+fileName+".xml");
			byte tag_bytes[] = fileContent.getBytes("utf-8");
			fileoutputstream.write(tag_bytes);
			fileoutputstream.close();
			log.debug("xml文件生成，生成路径为："+dir+fileName+".xml");
		} catch (Exception e) {
			log.debug("xml文件生成失败，错误原因："+e.getMessage());
		}
	}*/
	
	/**
	 * 
	 * 方法功能：写入日志(根目录为项目工程下的MQEcAndDrgXml文件夹)
	 * @param fileContent	文件内容
	 * @param moduleDir		文件目录
	 * @param logDao		写入数据日志的接口
	 * @param hisId			要插入数据库中的ID
	 * @param interfaceCd	借口编号
	 * @param dest			目的地 
	 * @param state			记录状态 
	 * @param interfaceName	借口名称 
	 * @param src			源系统 
	 * @param param			参数(主要用来存放出入订单ID)
	 * @param stateDesc		状态描述（错误原因）
	 * @param disErrorSourc	区分错误信息来源（001ec导入文件时，002ec入库时，003对方传数据库有问题等）
	 * @param clientId		xmlID(订单交易ID)
	 * @author bai_lu 
	 * @time 2010-12-9
	 */
//	public static void writeFile(String fileContent, String moduleDir, TblInterfaceHistoryDAO tblInterfaceHistoryDAO, Long hisId, String interfaceCd, String dest, String state, String interfaceName, String src, String param, String stateDesc, String disErrorSourc, String clientId, String messageType) {
//		String dir = WebAppContext.getInstance().getAppPathString() + "MQXml" + File.separator + moduleDir + File.separator + getDateStr(new Date(), "yyyy-MM-dd") + File.separator;
//		File file = new File(dir);
//		if(!file.exists()) {
//			file.mkdirs();
//		}
//		String fileName = System.currentTimeMillis()+""; 
//		try {
//			FileOutputStream fileoutputstream = new FileOutputStream(dir+fileName+".xml");
//			byte tag_bytes[] = fileContent.getBytes("utf-8");
//			fileoutputstream.write(tag_bytes);
//		    fileoutputstream.close();
//		    log.debug("xml文件生成，路径为："+dir+fileName);
//		} catch (Exception e) {
//			log.debug("写入xml文件生成错误，错误原因："+e.getMessage());
//		} finally {
//			log.debug("开始写入数据库记录, 记录ID为："+hisId);
//			try {
//				TblInterfaceMqHistory history;
//				if(dest.equalsIgnoreCase("DRG")) {
//					history = new TblInterfaceMqHistory(hisId, interfaceCd, dest, state, interfaceName, src, new Date(), "", param, "", fileName, "", clientId, disErrorSourc);
//				} else {
//					history = new TblInterfaceMqHistory(hisId, interfaceCd, dest, state, interfaceName, src, new Date(), param, "", fileName, "", "", clientId, disErrorSourc);
//				}
//				if(messageType.equalsIgnoreCase("so") && moduleDir.equalsIgnoreCase("ack")) {
//					tblInterfaceHistoryDAO.updateTblInterfaceMqHistory(history);
//					tblInterfaceHistoryDAO.insertTblInterfaceMqHistory(history);
//				} else {
//					tblInterfaceHistoryDAO.insertTblInterfaceMqHistory(history);
//				}
//				log.debug("结束写入数据库记录");
//			} catch (Exception e) {
//				log.debug("记录数据库日志时出现异常:"+e.getMessage());
//			}
//		}
//	}
//	
	/**
	 * 
	 * 方法功能：			生成xml文件
	 * @param fileString 	文件内容
	 * @param moduleDir 	文件存放目录
	 * @param fileSuffix 	文件后缀名
	 * @author bai_lu 
	 * @time 2010-11-8
	 */
//	public static void writeFile(String fileContent, String moduleDir, String fileSuffix, String fileName) {
//		// writeUrl:最终日志文件存放的目录   (目录结构为：传入的moduleDir + yyyy-MM-dd)
//		String writeUrl = saveUrl +File.separator+ moduleDir + File.separator + getDateStr(new Date(), "yyyy-MM-dd") + File.separator;
//    	File file = new File(writeUrl);
//    	if(!file.exists()) {								//没有创建该目录
//    		file.mkdirs();
//    	}
//		fileName = writeUrl+fileName+"."+fileSuffix;		//文件名为系统毫秒数
//    	try {
//			FileOutputStream fileoutputstream = new FileOutputStream(fileName);
//			byte tag_bytes[] = fileContent.getBytes("utf-8");						//utf-8格式保存该文件
//			fileoutputstream.write(tag_bytes);
//		    fileoutputstream.close();
//		} catch (Exception e) {
//			e.getMessage();
//		}
//    }
	/**
	 * 
	 * 方法功能：		读取文件内容
	 * @param 			fileName文件名
	 * @param url	  	文件所在的文件夹名
	 * @return String 	文件内容
	 * @author bai_lu 
	 * @time 2010-11-8
	 */
	public static String readFile(String fileName, String moduleDir) {
//		String readUrl = MQListener.rootDir + File.separator + moduleDir + File.separator + fileName;
		String readUrl = "D:" + File.separator + "testMQ" + File.separator + fileName;
		StringBuffer tempSB = new StringBuffer("");
		try {
		FileInputStream fileInputStream = new FileInputStream(readUrl);
		InputStreamReader read = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader br = new BufferedReader(read);
		String line = br.readLine();
		while(line!= null){
			tempSB.append(line+"\n\r");											//读完一行后加换行符
			line = br.readLine();
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempSB.toString();
    }
	
	/**
	 * 
	 * 方法功能：将时间转换成字符串 date为null时返回	1900-01-01T00:00:00+08:00
	 * @return String
	 * @author bai_lu 
	 * @time 2010-11-8
	 */
	public static String getTimeStr(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+08:00'");
		if(null==date) {
			return "1900-01-01T00:00:00+08:00";
		} else {
			return f.format(date);

		}
	}
	
	/**
	 * 
	 * 方法功能：获取系统日期字符串 	
	 * @return String
	 * @param dateStyle
	 * @author bai_lu 
	 * @time 2010-11-8
	 */
	public static String getDateStr(Date date, String dateStyle) {
		SimpleDateFormat myFmt = new SimpleDateFormat(dateStyle);
		return myFmt.format(date);
	}
	
	/**
	 * 
	 * 方法功能：获取当前系统日期字符串 	
	 * @return String	yyyy-MM-dd'T'hh:mm:ss z
	 * @author bai_lu 
	 * @time 2010-11-8
	 */
	public static String getTimeStr() {
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss z");
		return myFmt.format(new Date());
	}
	/**
	 * 
	 * 方法功能：获取当前系统日期字符串 	
	 * @return String	yyyy-MM-dd'T'hh:mm:ss z
	 * @author bai_lu 
	 * @time 2010-11-8
	 */
	public static Date getDate(String date) {
		date = "2010-11-29T11:30:47+08:00";
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+08:00'");
		
		try {
			return myFmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date formatDate(String datestr) {
		if(datestr!=null) {
			datestr = datestr.substring(0, 22)+datestr.substring(23, datestr.length());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			try {
				Date date = sdf.parse(datestr);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}
		return null;
	}
	/**
	 * 
	 * 方法功能：获取日志序列ID
	 * @return
	 * @author yang_ziwen 
	 * @time 2010-11-16
	 */
	public static String findId() {
		WebAppContext webAppContext = WebAppContext.getInstance();
		TblInterfaceHistoryService tblInterfaceHistoryService = (TblInterfaceHistoryService)webAppContext.getBean("tblInterfaceHistoryMapper");
		String st = tblInterfaceHistoryService.findId();
		return st;
	}
	
//	/**
//	 * 
//	 * 方法功能：获取日志序列ID
//	 * @return
//	 * @author bai_lu 
//	 * @time 2010-11-16
//	 */
//	public String findId(JdbcTemplate jdbcTemplate2) {
//		String id = null;
//		try {
//			id = SeqNO.getSeqid(jdbcTemplate2, "ORDER", 10);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return id;
//	}
	/**
	 * 插入接口日志表
	 */
	public static void addMqLog(TblInterfaceHistory tblInterfaceHistory){
		LogService logServie = (LogService)WebAppContext.getInstance().getBean("logService");
		logServie.addTblInterfaceMqHistory(tblInterfaceHistory);
	}
	public static void main(String[] args) {
		 


	}
}