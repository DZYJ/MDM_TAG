package com.multigold.mdm.rest.request;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.multigold.common.util.JsonUtil;
import com.multigold.mdm.dto.common.GenericResponse;
import com.multigold.mdm.dto.sale.SaleTransactionDetail;
import com.multigold.mdm.dto.sale.SaleTransactionReq;
import com.multigold.mdm.dto.sale.SaleTransactionTran;

public class TestSaleTransactionInf {

	public static void main(String[] args) {
		HttpRequestClient client = new HttpRequestClient();
//		String url = "http://localhost:8085/mdm-interface/tsixservice/paymentInf";
		String url = "https://10.129.161.21:9005/mdm-interface/tsixservice/paymentInf";
		
		SaleTransactionReq req = new SaleTransactionReq();
		List<SaleTransactionTran> trans = new ArrayList<SaleTransactionTran>();
		
		req.setTrans(trans);
		
		req.setTransactionId("4545645645");
		
		SaleTransactionTran tran = new SaleTransactionTran();
		tran.setRequestId("584515");
		List<SaleTransactionDetail> details = new ArrayList<SaleTransactionDetail>();
		SaleTransactionDetail detail = new SaleTransactionDetail();
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
//		BigDecimal amount = new BigDecimal(100.5555).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		  String a="1467000000";
		  double aa=Double.parseDouble(a);
		detail.setAmount(aa);
		detail.setCustomerAccount("11");
		detail.setCustomerBank("detail");
		detail.setCustomerName("11");
		detail.setOrderChannel("11");
		detail.setOrderDate(dateStr);
		detail.setOrderNum("11");
		detail.setOrderStatus("1");
		detail.setParentOrderNum("111");
		detail.setPaymentType("1");
		detail.setRemark("111");
		details.add(detail);
		tran.setDetails(details);
		trans.add(tran);
		System.out.println(JsonUtil.objectToJSON(req));
		GenericResponse resp = new GenericResponse();
		try {
			resp=client.post(url, req, GenericResponse.class);
			System.out.println(resp.getBody().getTrans().get(0).getStatus());
//			client.requestPost(url, "req", null);
			
//			client.requestPost(url, "{    \"trans\": [        {            \"details\": [                {                    \"parentOrderNum\": \"115040100011\",                    \"orderNum\": \"115040100011\",                    \"orderChannel\": \"1000\",                    \"orderDate\": \"2015-04-01 16:08:58\",                    \"customerName\": \"张三\",                    \"PaymentType\": \"10\",                    \"customerBank\": \"工商银行\",                    \"customerAccount\":\"2205065445512545555\",                    \"amount\":\"1002\",                    \"orderStatus\":\"DL\",                    \"remark\":\"\"                },                {                   \"parentOrderNum\": \"115040100012\",                    \"orderNum\": \"115040100012\",                    \"orderChannel\": \"1000\",                    \"orderDate\": \"2015-04-01 16:08:58\",                    \"customerName\": \"张三\",                    \"PaymentType\": \"20\",                    \"customerBank\": \"\",                    \"customerAccount\":\"\",                    \"amount\":\"1002\",                    \"orderStatus\":\"DL\",                    \"remark\":\"\"                }            ],            \"requestId\": \"3423425\"        },        {            \"details\": [                {                   \"parentOrderNum\": \"115040100013\",                    \"orderNum\": \"115040100013\",     \"orderChannel\": \"1000\",                    \"orderDate\": \"2015-04-02 16:08:58\",                    \"customerName\": \"张三\",                    \"PaymentType\": \"10\",                    \"customerBank\": \"工商银行\",                    \"customerAccount\":\"2205065445512545555\",                    \"amount\":\"1002\",                    \"orderStatus\":\"DL\",     \"remark\":\"\"                }            ],            \"requestId\": \"34234\"        }    ],    \"transactionId\": \"10000802323432352\"}", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
