package com.multigold.mdm.service.rest.tsix;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multigold.common.util.JsonUtil;
import com.multigold.mdm.dto.common.GenericResponse;
import com.multigold.mdm.dto.common.GenericResponseBodyDto;
import com.multigold.mdm.dto.common.GenericResponseHeader;
import com.multigold.mdm.dto.common.GenericResponseTranDto;
import com.multigold.mdm.dto.sale.SaleReturnReq;
import com.multigold.mdm.dto.sale.SaleReturnStatusReq;

import com.multigold.mdm.dto.sale.SaleTransactionDetail;
import com.multigold.mdm.dto.sale.SaleTransactionReq;

import com.multigold.mdm.dto.sale.SaleTransactionTran;
import com.multigold.mdm.dto.sale.SalesReturnReqDetails;
import com.multigold.mdm.dto.sale.SalesReturnReqOrderDetails;
import com.multigold.mdm.dto.sale.SalesReturnReqTran;
import com.multigold.mdm.dto.sale.SalesReturnStatusReqDetails;
import com.multigold.mdm.dto.sale.SalesReturnStatusReqTran;
import com.multigold.mdm.dto.tsix.E74.RecoveryCancelReq;
import com.multigold.mdm.dto.tsix.E74.RecoveryCancelReqDetail;
import com.multigold.mdm.dto.tsix.E74.RecoveryCancelReqTran;
import com.multigold.t6.service.api.sales.SalesService;
import com.multigold.t6.service.api.tsix.E74.EcResourceService;
import com.multigold.t6.tsix.E74.RecoveryReq;
import com.multigold.t6.tsix.E74.RecoveryReqDetail;
import com.multigold.t6.tsix.E74.RecoveryReqTran;
import com.multigold.t6.vo.sales.SaleTransactionVO;
import com.multigold.t6.vo.sales.SalesReturnVO;
import com.multigold.t6.vo.sales.SalesReturnsVO;

@Path("tsixservice")
@Component
public class ECResource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4582469012158116034L;
	@Autowired
	EcResourceService ecResourceService;
	@Autowired
	SalesService<SalesReturnVO, String> salesService; 
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * 旧金回收交易信息
	 * @param request
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/recovery")
	public Response recovery(RecoveryReq request){
		String requestJSON = JsonUtil.objectToJSON(request);
		log.debug("<<<tsixservice/recovery请求消息>>>"+requestJSON);
		//1-前处理：解析请求对象
		//2-主处理：调用接口，处理业务
		List<RecoveryReqTran> trans = request.getTrans();
		GenericResponse resp = new GenericResponse();
		GenericResponseHeader header = new GenericResponseHeader();
		GenericResponseBodyDto body = new GenericResponseBodyDto();
		
		List<GenericResponseTranDto> respTrans = new ArrayList<GenericResponseTranDto>();
		StringBuffer recoveryNums = new StringBuffer("");
		for (RecoveryReqTran recoveryReqTran : trans) {
			String requestId = recoveryReqTran.getRequestId();
			GenericResponseTranDto respTran = new GenericResponseTranDto();
			respTran.setRequestId(requestId);
			Boolean isSuccess=false;
			List<RecoveryReqDetail> detail = recoveryReqTran.getDetails();
			for (RecoveryReqDetail recoveryReqDetail : detail) {
				try{
					ecResourceService.insertDetail(recoveryReqDetail);
					recoveryNums.append(recoveryReqDetail.getRecoveryNum()+",");
					isSuccess=true;
				}catch(Exception e){
					log.error("<<<处理异常，异常单号："+recoveryReqDetail.getRecoveryNum()+">>>", e);
				}
			}
			respTran.getRequestId();
			respTran.setStatus(isSuccess?"Y":"N");
			respTrans.add(respTran);
		}
		//3-组装返回报文
		header.setResponseCode("100");
		header.setRemark(recoveryNums.toString());
		body.setTrans(respTrans);
		resp.setHeader(header);
		resp.setBody(body);
		
		return Response.status(Status.OK).entity(resp).build();
	}
	

	
	//EC销售交易信息
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/paymentInf")
	public Response saleTransaction(SaleTransactionReq req){
		String requestJSON = JsonUtil.objectToJSON(req);
		log.debug("<<<tsixservice/saleTransaction请求消息>>>"+requestJSON);
		//TODO:存入收款信息表
		//1.接受信息，解析报文
		//2.裸机操作，返回信息
//			List<SaleTransactionReqTran> trans = req.getTrans();//上面没有的
		GenericResponse resp = new GenericResponse();
		GenericResponseHeader header = new GenericResponseHeader();
		GenericResponseBodyDto body = new GenericResponseBodyDto();
		List<GenericResponseTranDto> respTrans = new ArrayList<GenericResponseTranDto>();
		StringBuffer saleTransactionNums = new StringBuffer("");
		for(SaleTransactionTran tran:(List<SaleTransactionTran>)req.getTrans()){
			String requestId = tran.getRequestId();
			GenericResponseTranDto respTran = new GenericResponseTranDto();
			respTran.setRequestId(requestId);
			Boolean isSuccess=false;
			for(SaleTransactionDetail details:(List<SaleTransactionDetail>)tran.getDetails()){
				SaleTransactionVO vo = new SaleTransactionVO();
				vo.setParentOrderNum(details.getParentOrderNum());
				vo.setOrderNum(details.getOrderNum());
				vo.setOrderChannel(details.getOrderChannel());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					vo.setOrderDate( sdf.parse(details.getOrderDate()));
				} catch (ParseException e) {
					log.error("<<<时间格式异常："+details.getOrderDate()+">>>", e);
				}
				
				vo.setCustomerName(details.getCustomerName());
				vo.setPaymentType(details.getPaymentType());
				vo.setCustomerBank(details.getCustomerBank());
				vo.setCustomerAccount(details.getCustomerAccount());
				vo.setSum(details.getAmount());
				vo.setOrderStatus(details.getOrderStatus());
				vo.setRemark(details.getRemark());
				
				String num =  salesService.queryOrderNum(vo);
				System.out.println(num);
				
				if(num == null){
				try{
					salesService.createSaleTransactionEntity(vo);
					saleTransactionNums.append(vo.getOrderNum()+",");
					isSuccess=true;
					System.out.println("success");
				}catch(RuntimeException e){
					log.error("<<<处理异常，异常单号："+vo.getOrderNum()+">>>", e);
				}
				}
				else{
					log.error("<<<处理异常，异常单号："+vo.getOrderNum()+">>>");
				}
			}
			respTran.getRequestId();
			respTran.setStatus(isSuccess?"Y":"N");
			respTrans.add(respTran);
		}
		body.setTrans(respTrans);
		header.setResponseCode("100");
		header.setRemark(saleTransactionNums.toString());
		resp.setBody(body);
		resp.setHeader(header);
		return Response.status(Status.OK).entity(resp).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/recoveryCancel")
	public Response recoveryCancel(RecoveryCancelReq req){
		String requestJSON = JsonUtil.objectToJSON(req);

		
		log.debug("<<<tsixservice/recoveryCancel请求消息>>>"+requestJSON);
		StringBuffer recoveryNums = new StringBuffer("");
		GenericResponse resp = new GenericResponse();
		GenericResponseHeader header = new GenericResponseHeader();
		GenericResponseBodyDto body = new GenericResponseBodyDto();
		List<GenericResponseTranDto> respTrans = new ArrayList<GenericResponseTranDto>();
		for(RecoveryCancelReqTran tran:req.getTrans()){
			String requestId = tran.getRequestId();
			GenericResponseTranDto respTran = new GenericResponseTranDto();
			respTran.setRequestId(requestId);
			Boolean isSuccess=false;
			for(RecoveryCancelReqDetail detail:tran.getDetails()){
				//根据回购单号取出旧金回收信息
				try{
					RecoveryReqDetail recoveryDetail = ecResourceService.queryDetailByRecoverNum(detail.getRecoveryNum());
					//判断旧金回收是否已录入或已付款
					String traceNum = recoveryDetail.getTraceNum();
					if((traceNum==null || !traceNum.equals("")) && !recoveryDetail.getOrderStatus().equals("9")){
						
						if(detail.getRecoveryStatus().equals("4")){
							
							recoveryDetail.setRecoveryNum("T"+detail.getRecoveryNum());
							BigDecimal minus = new BigDecimal(-1);
							recoveryDetail.setTotalAmount(minus.multiply(new BigDecimal(recoveryDetail.getTotalAmount())).toString());
							recoveryDetail.setCount(minus.multiply(new BigDecimal(recoveryDetail.getCount())).toString());
							recoveryDetail.setWeight(minus.multiply(new BigDecimal(recoveryDetail.getWeight())).toString());
							recoveryDetail.setRecoveryDate(recoveryDetail.getDateStr());
							recoveryDetail.setOrderStatus(detail.getRecoveryStatus());
							String recoveryNum = ecResourceService.insertDetail(recoveryDetail);
							if(recoveryNum!=null){
								recoveryNums.append(detail.getRecoveryNum()+",");
								isSuccess=true;
							}else{
								log.error("<<<旧金回收取消状态异常，异常单号："+detail.getRecoveryNum()+">>>");
							}
							
						}else{
							log.error("<<<旧金回收取消状态异常，异常单号："+detail.getRecoveryNum()+">>>");
						}
					}else{
						log.error("<<<旧金回收已付款，不允许回购，异常单号："+detail.getRecoveryNum()+">>>");
					}
				}catch(Exception e){
					log.error("<<<处理异常，异常单号："+detail.getRecoveryNum()+">>>", e);
				}
			}
			respTran.getRequestId();
			respTran.setStatus(isSuccess?"Y":"N");
			respTrans.add(respTran);
		}
		body.setTrans(respTrans);
		header.setResponseCode("100");
		header.setRemark(recoveryNums.toString());
		resp.setBody(body);
		resp.setHeader(header);
		return Response.status(Status.OK).entity(resp).build();
	}
	

	//EC销售退款申请
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/refund")
	public Response saleReturnTransaction(SaleReturnReq req){
		Map<String,String> map = new HashMap<String,String>();
		map.put("257", "0");
		map.put("258", "1");
		String requestJSON = JsonUtil.objectToJSON(req);
		log.debug("<<<tsixservice/saleReturn请求消息>>>"+requestJSON);
//		List<SalesReturnVO> saleReturnList = new ArrayList<SalesReturnVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer saleReturnNums = new StringBuffer("");
		GenericResponse resp = new GenericResponse();
		GenericResponseHeader header = new GenericResponseHeader();
		GenericResponseBodyDto body = new GenericResponseBodyDto();
		List<GenericResponseTranDto> respTrans = new ArrayList<GenericResponseTranDto>();
		//TODO:存入退款申请表
		for(SalesReturnReqTran tran:(List<SalesReturnReqTran>)req.getTrans()){
			String requestId = tran.getRequestId();
			GenericResponseTranDto respTran = new GenericResponseTranDto();
			respTran.setRequestId(requestId);
			Boolean isSuccess=false;
			for(SalesReturnReqDetails details:(List<SalesReturnReqDetails>)tran.getDetails()){
				SalesReturnVO vo = new SalesReturnVO();
				vo.setOriginOrderNumber (details.getOriginOrderNumber ());
				vo.setOriginparentOrderNum(details.getOriginparentOrderNum());
				vo.setOrderNum(details.getOrderNum());
				vo.setOrderChannel(details.getOrderChannel());
				Date orderDate=new Date();
				try {
					orderDate = sdf.parse(details.getOrderDate());
				} catch (ParseException e1) {
					log.debug("<<<tsixservice/saleReturn时间格式异常>>>"+details.getOrderDate());
				}
				vo.setOrderDate (orderDate);
				vo.setCustomerName(details.getCustomerName());
				vo.setPaymentType(details.getPaymentType());
				vo.setCustomerBank(details.getCustomerBank());
				vo.setCustomerAccount(details.getCustomerAccount());
				vo.setRefundAmount(new BigDecimal(details.getRefundAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
				vo.setOrderAmount(new BigDecimal(details.getOrderAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
				vo.setPartyDiscountAmount(new BigDecimal(details.getPartyDiscountAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
				vo.setMulitDiscountAmount(new BigDecimal(details.getMulitDiscountAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
				vo.setRefundReason(details.getRefundReason());
				
				vo.setOrderStatus(details.getOrderStatus());
				vo.setValidateFlag(details.getValidateFlag());
				vo.setRemark(details.getRemark());
				vo.setStatus("0");
				List<SalesReturnsVO> salesReturnsList = new ArrayList<SalesReturnsVO>();
				for(SalesReturnReqOrderDetails orderDetails:details.getOrderDetails()){
					SalesReturnsVO vos = new SalesReturnsVO();
					vos.setOrderNum(details.getOrderNum());
					vos.setStyleNumber(orderDetails.getStyleNumber());
					vos.setPartNumber(orderDetails.getPartNumber());
					vos.setPartPrice(new BigDecimal(orderDetails.getPartPrice()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
					vos.setQuantity(orderDetails.getQuantity());
					vos.setSum(new BigDecimal(orderDetails.getSum()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
					vos.setFreight(new BigDecimal(orderDetails.getFreight()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
					salesReturnsList.add(vos);

				}
				try{
					vo.setSalesReturns(salesReturnsList);
					salesService.createEntity(vo);
					saleReturnNums.append(vo.getOrderNum()+",");
					isSuccess=true;
				}catch(RuntimeException e){
					log.error("<<<处理异常，异常单号："+vo.getOriginOrderNumber()+">>>", e);
				}
			}
			respTran.getRequestId();
			respTran.setStatus(isSuccess?"Y":"N");
			respTrans.add(respTran);
		}
		body.setTrans(respTrans);
		header.setResponseCode("100");
		header.setRemark(saleReturnNums.toString());
		resp.setBody(body);
		resp.setHeader(header);
		return Response.status(Status.OK).entity(resp).build();
	}
	
	//EC销售退款状态
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/refundStatus")
	public Response saleReturnStatus(SaleReturnStatusReq req){
		GenericResponse resp = new GenericResponse();
		GenericResponseHeader header = new GenericResponseHeader();
		GenericResponseBodyDto body = new GenericResponseBodyDto();
		List<GenericResponseTranDto> respTrans = new ArrayList<GenericResponseTranDto>();
		String requestJSON = JsonUtil.objectToJSON(req);
		StringBuffer saleReturnNums = new StringBuffer("");
		log.debug("<<<tsixservice/saleReturnStatus请求消息>>>"+requestJSON);
		for(SalesReturnStatusReqTran tran:(List<SalesReturnStatusReqTran>)req.getTrans()){
			String requestId = tran.getRequestId();
			GenericResponseTranDto respTran = new GenericResponseTranDto();
			respTran.setRequestId(requestId);
			Boolean isSuccess=true;
			for(SalesReturnStatusReqDetails details:(List<SalesReturnStatusReqDetails>)tran.getDetails()){
				SalesReturnVO vo = salesService.queryById(details.getOrderNum());
				vo.setTraceNum(details.getTraceNum());
				if(details.getRefundStatus().equals("1")){
					vo.setStatus("01");
					saleReturnNums.append(details.getOrderNum()+",");
				}else if(details.getRefundStatus().equals("4")){
					vo.setStatus("03");
					saleReturnNums.append(details.getOrderNum()+",");
				}else{
					isSuccess=false;
				}
				if(isSuccess){
					Date operationTime=null;
					try {
						operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(details.getOperationTime());
					} catch (ParseException e) {
						log.error("日期格式化异常，异常日期："+details.getOperationTime(),e);
					}
					vo.setModifiedDate(operationTime);
					salesService.insertSalesReturn(vo);
				}
			}
			
			respTran.setRequestId(tran.getRequestId());
			respTran.setStatus(isSuccess?"Y":"N");
			respTrans.add(respTran);
			
		}
		body.setTrans(respTrans);
		header.setResponseCode("100");
		header.setRemark(saleReturnNums.toString());
		resp.setBody(body);
		resp.setHeader(header);
		return Response.status(Status.OK).entity(resp).build();
	}
	
//	//EC销售交易信息
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/refundStatus")
//	public Response saleReturnStatus(SaleTransactionReq req){
//		GenericResponse resp = new GenericResponse();
//		GenericResponseHeader header = new GenericResponseHeader();
//		GenericResponseBodyDto body = new GenericResponseBodyDto();
//		List<GenericResponseTranDto> respTrans = new ArrayList<GenericResponseTranDto>();
//		for(SaleTransactionTran tran:req.getTrans()){
//			//写入收款信息表
//			
//			
//		}
//		
//		body.setTrans(respTrans);
//		header.setResponseCode("100");
////			header.setRemark(saleReturnNums.toString());
//		return Response.status(Status.OK).entity(resp).build();
//	}
}
