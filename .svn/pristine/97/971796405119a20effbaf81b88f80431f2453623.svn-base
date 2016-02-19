package com.multigold.mdm.service.rest.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.multigold.common.util.LogTracking;
import com.multigold.mdm.dto.tsix.E74.RecoveryReqDetail;
import com.multigold.mdm.service.rest.Hello;

public class HttpRequestClient {
	
	public <T> T post(String url,Object request,Class<T> response) throws Exception{
		ObjectMapper mapper = new ObjectMapper(); 
		String requestJSON = StringUtils.EMPTY;
		String result = StringUtils.EMPTY;
		
		requestJSON = mapper.writeValueAsString(request);
//		requestJSON = "{\"transactionId\":\"93624d7d7318426ab783a8f1d3303227\",\"trans\":[{\"requestId\":\"1440662439649\",\"details\":[{\"recoveryNum\":\"B15080400017\",\"orderStatus\":\"9\",\"traceNum\":\"757444444\",\"operator\":\"DRG_ADMIN\",\"operationTime\":\"2015-08-25 00:00:00\",\"remark\":\"\"}]}]}";
		try {
			System.out.println(url);
			result = this.requestPost(url, requestJSON, null);
			System.out.println(result);
		} catch (Exception e) {
			throw e;
		}
		return mapper.readValue(result,response);
	}
	
	/**
	 * 发送数据
	 * @param    requestJSON 
	 *            -JSON格式
	 * @return   String
	 *            -JSON格式
	 * @throws Exception
	 */
	private String requestPost(String url,String requestJSON,String authorization) throws Exception{
		LogTracking.debugLog(this.getClass(), "<<<请求URL>>>", url);
		LogTracking.debugLog(this.getClass(), "<<<请求消息>>>", requestJSON);
		HostnameVerifier hv = new HostnameVerifier() {
	        public boolean verify(String urlHostName, SSLSession session) {
	            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
	                               + session.getPeerHost());
//	            urlHostName="多变金都";
	            return true;
	        }
	    };
	    HttpsURLConnection.setDefaultHostnameVerifier(hv);
		URL postURL =null;
		HttpURLConnection httpUrl=null;
		postURL = new URL(url);
		
		httpUrl = (HttpURLConnection) postURL.openConnection();
		String atpUserAndPassword = null;
		if(StringUtils.isNotEmpty(authorization)){
			atpUserAndPassword = authorization;
		}else{
			atpUserAndPassword = "Basic dXNlcjpwYXNzd29yZA==";
		}
		httpUrl.setRequestProperty("User-Agent","Mozilla/5.0");
		httpUrl.addRequestProperty("content-type", "application/json");
		httpUrl.addRequestProperty("Authorization",atpUserAndPassword);
		httpUrl.setRequestProperty("Charset", "UTF-8"); 
		
//		httpUrl.addRequestProperty("requires-channel", "https");
		httpUrl.setDoInput(true);
	    httpUrl.setDoOutput(true);
		httpUrl.setUseCaches(false);
	    httpUrl.setRequestMethod("POST");
	    
		OutputStream os =  httpUrl.getOutputStream();			
		os.write(requestJSON.getBytes());
		os.flush();
		os.close();
		
		String resultStr = StringUtils.EMPTY;
		InputStream inputStr = httpUrl.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(inputStr));
		String line=null; 
		while((line=br.readLine())!=null){ 
			resultStr=line;
		}
		inputStr.close();
		LogTracking.debugLog(this.getClass(), "<<<返回结果>>>", resultStr);

		return resultStr;
		
	}
}
