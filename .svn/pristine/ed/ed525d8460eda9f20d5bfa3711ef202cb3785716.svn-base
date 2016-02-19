/**
 * 
 */
package com.multigold.mdm.service.ws.auth;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.cxf.helpers.CastUtils;
import org.apache.ws.security.WSSecurityEngine;

public class WSS4JInInterceptorExt extends org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor {

	private WSSecurityEngine secEngineOverride;
	public WSS4JInInterceptorExt()
	{
		super();
		Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("action","UsernameToken");
        properties.put("passwordType","PasswordText");
        properties.put("passwordCallbackClass","com.dell.atpservice.ws.ServerPasswordCallback");       
        
		setProperties(properties);
		
        final Map<QName, Object> map = CastUtils.cast(
            (Map)properties.get(PROCESSOR_MAP));
        if (map != null) {
            secEngineOverride = createSecurityEngine(map);
        }
	}
	
	@Override
	protected WSSecurityEngine getSecurityEngine(boolean arg0) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
        if (secEngineOverride != null) {
            return secEngineOverride;
        }
        return secEngine;
	}

}
