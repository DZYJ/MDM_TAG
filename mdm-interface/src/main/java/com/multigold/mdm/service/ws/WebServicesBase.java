package com.multigold.mdm.service.ws;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.ws.security.WSUsernameTokenPrincipal;

import com.multigold.mdm.service.ws.exception.BusinessException;

public class WebServicesBase {

	@Resource
	private WebServiceContext wsContext;
	
	private String xmlMsg = "";
	
	public String getXmlMsg() {
		return xmlMsg;
	}

	public void setXmlMsg(String xmlMsg) {
		this.xmlMsg = xmlMsg;
	}

	@Resource
	public final void setWebContext(final WebServiceContext argResource) {
		this.wsContext = argResource;
	}

	public String getUserName() throws BusinessException {

		String userName = "LSP_MORTW";

		try {
			WSUsernameTokenPrincipal p = (WSUsernameTokenPrincipal) wsContext.getUserPrincipal();
			userName = p.getName();
			// String password = p.getPassword();
			// String passwordType = p.getPasswordType();
			
		} catch (Exception e) {
			
			String errMsg = ErrorStatus.ERROR_00005 +"Exception in:"+e.getMessage();
			
			BusinessException ae = new BusinessException(errMsg);
			//TracingLog.errorLog(WebServicesBase.class, "getUserName", ae);
			throw ae;
		}
		if (userName == null || userName.equals(StringUtils.EMPTY)) {
			userName = "LSP_MORTW";
		}
		return userName;
	}


	public void insermdmgToDB() {
		//dto:id,3plcode,xmlmsg,starttime,endtime,excetionmsg
	}
	
}
