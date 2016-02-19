package com.multigold.mdm.job;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sun.net.ftp.FtpProtocolException;

import com.multigold.common.util.Excel2007Utils;
import com.multigold.common.util.FtpUtils;
import com.multigold.common.util.PropertiesUtils;
import com.multigold.t6.entity.a9.A9DaytimeOutBoundEntity;
import com.multigold.t6.service.api.mginf.MgInfService;
import com.multigold.t6.service.api.po.RdrecordService;
/**
 * 
 * @author zhanghua
 * A9接口
 */
@Component("ftpToXmlA9")  
public class A9FtpJob extends BaseSendJob {
	@Autowired
	RdrecordService<A9DaytimeOutBoundEntity,String> rdrecordService;
	@Autowired
	MgInfService<A9DaytimeOutBoundEntity,String> mgInfService;
	/**
	 * 日间出库
	 */
	public void daytimeOutBound(){
		//SIT 
//		String path = "/A9/srcdata/txn/T6/TXN_";
		//UAT
		String path = PropertiesUtils.getPropValue("txn_path");//"/A9/srcdata/txn/A3/TXN_";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		//查询数据生成EXCEL文件
		//开始时间
		String startDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		//结束时间
		String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate",endDate);
		List<Object> objList = new ArrayList<Object>();
		List<A9DaytimeOutBoundEntity> list = rdrecordService.daytimeOutBound(paramMap);
		if(list!=null && list.size()>0){
			List<String> mgInfA9List = new ArrayList<String>();
			//写入日志表
			
			List<String> saveList = new ArrayList<String>();
			
			
			String dateStr = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
			OutputStream out=null;
			//连接FTP服务器
			
			String[] header = {"系统编号","批次号","业务单号","原业务单号","交易单号","原交易单号","交易日期","交易时间",
					"入库日期","账号","记账方向","商品种类","业务类型","交易数量","物品克重","交易货币对","黄金买入价","加权平均价","交易牌价","交易状态"};
			String entityPath = "com.multigold.t6.entity.a9.A9DaytimeOutBoundEntity";
			String[] paramName = {"&T6","batch","busCode","corCode",null,null,"outDate","&00:00:00","inDate",null,"returnFlag","invCCode","&SOLD","quantity","weight","&GLDCNY","costAmount",null,"salesAmount","&00"};
			String[] paramType = {"string","string","string","string","string","string","string","string","string","string","string","string","string","string","string","string","string","string","string","string"};
			String sheetName = "日间出库";
			//写入FTP服务器
			
			int count =1;
		
			for(int i=0;i<list.size();i++){
				objList.add(list.get(i));
				mgInfA9List.add(list.get(i).getBusCode());
				if((i>0 && i%500==0) || i==list.size()-1){
					try {
						out=getConnect(path+dateStr+"_"+(count++)+".xlsx");
						Excel2007Utils.exportExcel(out, sheetName, header, paramName, paramType, objList, entityPath);
						objList=new ArrayList<Object>();
						Logger.getLogger(Excel2007Utils.class).debug("生成Excel成功！");
					} catch (FtpProtocolException e) {
						Logger.getLogger(this.getClass()).error("FTP服务器连接失败！",e);
					}catch (IOException e) {
						Logger.getLogger(this.getClass()).error("FTP服务器文件创建失败！",e);
					} catch (Exception e) {
						Logger.getLogger(Excel2007Utils.class).error("生成Excel文件异常！",e);
					}finally{
						try {
							out.close();
						} catch (IOException e) {
							Logger.getLogger(this.getClass()).error("IOException异常！");
						}
						//关闭FTP连接
						FtpUtils.closeFtp();
					}
				}
			}
			for(int i=0;i<mgInfA9List.size();i++){
				saveList.add(mgInfA9List.get(i));
				if((i>0 && i%100==0) || i==mgInfA9List.size()-1){
					mgInfService.createEntityList(saveList);
					saveList = new ArrayList<String>();
				}
			}
		}
	}
	
	/**
	 * 日终库存
	 */
	public void dayEndInventory(){
		//SIT 
//		String path = "/A9/srcdata/txn_batch/T6/batch_TXN_";
		//UAT
		String path = PropertiesUtils.getPropValue("txn_batch_path");//"/A9/srcdata/txn_batch/A3/batch_TXN_";
		List<Object> objList = new ArrayList<Object>();
		List<A9DaytimeOutBoundEntity> list = rdrecordService.dayEndInventory();
		for(A9DaytimeOutBoundEntity entity:list){
			objList.add(entity);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		//查询数据生成EXCEL文件
		//开始时间
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		OutputStream out=null;
		//连接FTP服务器
		try {
			out=getConnect(path+dateStr+".xlsx");
			String[] header = {"日终余额ID","源系统ID","账户类型","账户/库存余额（克）","库存日期","买入价","加权平均价","总价"};
			String[] paramName = {"$ID","&T6","invCCode","weight","&"+dateStr,null,null,"costAmount"};
			String[] paramType = {"string","string","string","string","string","string","string","string"};
			String entityPath = "com.multigold.t6.entity.a9.A9DaytimeOutBoundEntity";
			//写入FTP服务器
			try {
				Excel2007Utils.exportExcel(out, "日终库存", header, paramName, paramType, objList, entityPath);
				
				Logger.getLogger(Excel2007Utils.class).error("生成Excel文件成功！");
			} catch (Exception e) {
				Logger.getLogger(Excel2007Utils.class).error("生成Excel文件异常！",e);
			} 
		} catch (FtpProtocolException e) {
			Logger.getLogger(this.getClass()).error("FTP服务器连接失败！",e);
		}catch (IOException e) {
			Logger.getLogger(this.getClass()).error("FTP服务器文件创建失败！",e);
		}finally{
			
			try {
				out.close();
			} catch (IOException e) {
				Logger.getLogger(this.getClass()).error("IOException异常！");
			}
			//关闭FTP连接
			FtpUtils.closeFtp();
		}
	}
	
	public OutputStream getConnect(String path) throws FtpProtocolException, IOException {
		String ip = PropertiesUtils.getPropValue("ip");
		int port = Integer.parseInt(PropertiesUtils.getPropValue("port"));
		String userName = PropertiesUtils.getPropValue("userName");
		String password = PropertiesUtils.getPropValue("password");
		return FtpUtils.connect(ip, path, port, userName, password);
	}
}
