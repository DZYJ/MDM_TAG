package com.multigold.mdm.rest.request;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.multigold.common.rest.response.GenericResponse;
import com.multigold.common.util.JsonUtil;
import com.multigold.mdm.dto.sale.SaleReturnStatusReq;
import com.multigold.mdm.dto.sale.SalesReturnStatusReqDetails;
import com.multigold.mdm.dto.sale.SalesReturnStatusReqTran;

public class HttpPostDemoRefundStatus {
	public static void main(String[] args) throws Exception {
		HttpRequestClient client = new HttpRequestClient();
//	    String url ="https://10.129.161.79:8443/api/buyBackInfoResponse";
		String url ="http://10.129.61.216:8085/mdm-interface/tsixservice/refundStatus";
		//String request = "{\"hello\":\"hello\",\"values\":{\"key4\":\"value4\",\"key3\":\"value3\",\"key2\":\"value2\",\"key1\":\"value1\"}}";
		SaleReturnStatusReq req = new SaleReturnStatusReq();
		req.setTransactionId("tr9525546");
		
		List<SalesReturnStatusReqTran> trans = new ArrayList<SalesReturnStatusReqTran>();
		SalesReturnStatusReqTran tran = new SalesReturnStatusReqTran();
		tran.setRequestId("r956254513");
		List<SalesReturnStatusReqDetails> detailsList = new ArrayList<SalesReturnStatusReqDetails>();
		SalesReturnStatusReqDetails details = new SalesReturnStatusReqDetails();
		details.setRefundStatus("2");
		details.setOrderNum("T545123517");
		details.setRemark("");
		details.setOperationTime("2015-10-06 12:12:12");
		
		detailsList.add(details);
		tran.setDetails(detailsList);
		trans.add(tran);
		req.setTrans(trans);
		String requestJSON = JsonUtil.objectToJSON(req);
		System.out.println("-----请求报文-----："+requestJSON);
		//2-主处理：发送报文
		Object o = client.post(url, req, GenericResponse.class);
		System.out.println("Object:--------------"+o);
		//3-后处理：处理反映\
//		System.out.println(reps.getTransactionId());
//		System.out.println(reps.getResultCode());
//		System.out.println(reps.getErrorMsg());
	}
}
