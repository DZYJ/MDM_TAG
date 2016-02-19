package com.multigold.mdm.service.rest.post;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.multigold.mdm.service.rest.client.HttpRequestClient;
import com.multigold.t6.tsix.E74.RecoveryResp;
import com.multigold.t6.tsix.E74.RecoveryRespDetail;
import com.multigold.t6.tsix.E74.RecoveryRespTran;

public class HttpPostDemo {
	public static void main(String[] args) throws Exception {
		HttpRequestClient client = new HttpRequestClient();
//	    String url ="https://10.129.161.79:8443/api/buyBackInfoResponse";
//		String url ="https://10.145.3.60:9003/mdm-interface/hjbGLService/hjbGL";
		String url ="http://127.0.0.1:8088/mdm-interface/hjbGLService/hjbGL";
		//String request = "{\"hello\":\"hello\",\"values\":{\"key4\":\"value4\",\"key3\":\"value3\",\"key2\":\"value2\",\"key1\":\"value1\"}}";
	    RecoveryRespDetail recoveryRespDetail 	= new RecoveryRespDetail();
		List<RecoveryRespDetail> details 		= new ArrayList<RecoveryRespDetail>();
		RecoveryRespTran reqTran 				= new RecoveryRespTran();
		List<RecoveryRespTran> reqTrans 		= new ArrayList<RecoveryRespTran>();
		RecoveryResp resp 						= new RecoveryResp();
		SimpleDateFormat sdf 					= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			recoveryRespDetail.setRecoveryNum("44444");
////			recoveryRespDetail.setRecoveryNum("111");
//			recoveryRespDetail.setOrderStatus("9");//打款状态 9.已打款
//			recoveryRespDetail.setTraceNum("51115");
////			recoveryRespDetail.setOperator(recordConfirmVO.getOperator());
////			recoveryRespDetail.setOperationTime(sdf.format(recordConfirmVO.getOperationTime()));
//			recoveryRespDetail.setOperator("5151515");
//			recoveryRespDetail.setOperationTime(sdf.format(new Date()));
//			recoveryRespDetail.setRemark("441544552");
//			details.add(recoveryRespDetail);
//			reqTran.setDetails(details);
////			now.getTimeInMillis();
//			reqTran.setRequestId(new Date().getTime()+"");
//			reqTrans.add(reqTran);
//		resp.setTrans(reqTrans);
		
	    //1-前处理： 组装请求报文
//		RecoveryReqDetail reqDetail = new RecoveryReqDetail();
//		reqDetail.setRecoveryNum("115201533655");
//		reqDetail.setRecoveryDate("2015-07-09");
//		reqDetail.setTradeType("2");
//		reqDetail.setOrderChannel("1");
//		
//		RecoveryReqDetail reqDetail2 = new RecoveryReqDetail();
//		reqDetail2.setRecoveryNum("115201533655");
//		reqDetail2.setRecoveryDate("2015-07-09");
//		reqDetail2.setTradeType("2");
//		reqDetail2.setOrderChannel("1");
//		
//		List<RecoveryReqDetail> details = new ArrayList<RecoveryReqDetail>();
//		details.add(reqDetail);
//		details.add(reqDetail2);
//		RecoveryReqTran reqTran = new RecoveryReqTran();
//		reqTran.setDetails(details);
//		reqTran.setRequestId("20150709001");
//		
//		List<RecoveryReqTran> reqTrans = new ArrayList<RecoveryReqTran>();
//		reqTrans.add(reqTran);
//////		
//		RecoveryReq req = new RecoveryReq();
//		req.setTrans(reqTrans);
//		req.setTransactionId("abc123344");
		HjbGlList glGlList = new HjbGlList();
		glGlList.setBizCode("FM01114");
		glGlList.setAtomCode("2222");
		glGlList.setMemo("");
		glGlList.setCnyAmount(new BigDecimal("30"));
		glGlList.setGram(new BigDecimal("9.999"));
		
		
//		HjbGlList glGlList2 = new HjbGlList();
//		glGlList2.setBizCode("FM01101");
//		glGlList2.setAtomCode("11001");
//		glGlList2.setMemo("1111");
//		glGlList2.setCnyAmount(new BigDecimal("13"));
//		glGlList2.setGram(new BigDecimal("12.001"));
		List<HjbGlList> list = new ArrayList<HjbGlList>();
		list.add(glGlList);
//		list.add(glGlList2);
		HjbGLReq req = new HjbGLReq();
		req.setTransactionId("D11_20150915_1442246400000_000008");
		req.setGlDate("20150915");
		req.setRecordTotalCount("1");
		req.setGlList(list);
		//2-主处理：发送报文
		HjbGLResp reps = client.post(url, req, HjbGLResp.class);
		//3-后处理：处理反映\
		System.out.println(reps.getTransactionId());
		System.out.println(reps.getResultCode());
		System.out.println(reps.getErrorMsg());
	}
}
