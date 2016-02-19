//package com.multigold.mdm.rest.request;
//
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import com.multigold.common.rest.response.GenericResponse;
//import com.multigold.common.util.JsonUtil;
//import com.multigold.mdm.dto.sale.SaleReturnReq;
//import com.multigold.mdm.dto.sale.SalesReturnReqDetails;
//import com.multigold.mdm.dto.sale.SalesReturnReqOrderDetails;
//import com.multigold.mdm.dto.sale.SalesReturnReqTran;
//
//public class HttpPostDemo {
//	public static void main(String[] args) throws Exception {
//		HttpRequestClient client = new HttpRequestClient();
////	    String url ="https://10.129.161.79:8443/api/buyBackInfoResponse";
////		String url ="http://10.129.61.216:8085/mdm-interface/tsixservice/refund";
//		String url = "http://10.129.62.237:8084/api/orderRefundStatus";
//		//String request = "{\"hello\":\"hello\",\"values\":{\"key4\":\"value4\",\"key3\":\"value3\",\"key2\":\"value2\",\"key1\":\"value1\"}}";
//		SaleReturnReq req = new SaleReturnReq();
//		req.setTransactionId("tr9525546");
//		
//		List<SalesReturnReqTran> trans = new ArrayList<SalesReturnReqTran>();
//		SalesReturnReqTran tran = new SalesReturnReqTran();
//		tran.setRequestId("r956254513");
//		List<SalesReturnReqDetails> detailsList = new ArrayList<SalesReturnReqDetails>();
//		SalesReturnReqDetails details = new SalesReturnReqDetails();
//		details.setOriginOrderNumber("P586524326");
//		details.setCustomerAccount("1");
//		details.setRefundAmount(888.33);
//		details.setCustomerName("张三");
//		details.setOrderChannel("1000");
//		details.setOrderAmount(4154.33);
//		details.setCustomerBank("工商银行");
//		details.setPaymentType("0");
//		details.setRefundReason("不想要了");
//		details.setOrderStatus("0");
//		details.setPartyDiscountAmount(878.645);
//		details.setOriginparentOrderNum("1115553335555");
//		details.setMulitDiscountAmount(7885484.66);
//		details.setOrderNum("T545123517");
//		details.setRemark("");
//		details.setOrderDate("2015-09-22 22:22:22");
//		details.setValidateFlag("Y");
//		
//		List<SalesReturnReqOrderDetails> orderDetailsList = new ArrayList<SalesReturnReqOrderDetails>();
//		SalesReturnReqOrderDetails orderDetails = new SalesReturnReqOrderDetails();
//		orderDetails.setPartNumber("1000446");
//		orderDetails.setStyleNumber("11HJ545223O");
//		orderDetails.setSum(4855.6565);
//		orderDetails.setFreight(8780d);
//		orderDetails.setPartPrice(89.66);
//		orderDetails.setQuantity(1);
//		orderDetailsList.add(orderDetails);
//		details.setOrderDetails(orderDetailsList);
//		detailsList.add(details);
//		tran.setDetails(detailsList);
//		trans.add(tran);
//		req.setTrans(trans);
//		String requestJSON = JsonUtil.objectToJSON(req);
//		System.out.println("-----请求报文-----："+requestJSON);
//		//2-主处理：发送报文
//		Object o = client.post(url, req, GenericResponse.class);
//		System.out.println("Object:--------------"+o);
//		//3-后处理：处理反映\
////		System.out.println(reps.getTransactionId());
////		System.out.println(reps.getResultCode());
////		System.out.println(reps.getErrorMsg());
//	}
//}
