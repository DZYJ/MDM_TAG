package com.multigold.mdm.service.ws.api.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.multigold.mdm.service.ws.WebServicesBase;
import com.multigold.mdm.service.ws.api.TntWebService;

@WebService(endpointInterface = "com.multigold.mdm.service.ws.api.TntWebService")
@Component
public class TntWebServiceImpl extends WebServicesBase implements TntWebService {


	public void setTntInformationsByXmlStr(String xmlStr) throws Exception {
		String username = this.getUserName();
		System.out.println(username);
		System.out.println(xmlStr);
	}

}
