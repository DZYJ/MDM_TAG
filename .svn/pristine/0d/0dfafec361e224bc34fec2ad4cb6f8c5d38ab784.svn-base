/**
 * 权限验证
 */
package com.multigold.mdm.service.ws.auth;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multigold.upms.entity.security.User;
import com.multigold.upms.service.api.security.UserService;

@Component
public class AuthenticationCallback implements CallbackHandler {
	
	@Autowired
	private UserService<User, Long> userService;

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
		//用户名
		String identify = pc.getIdentifier();
		//根据用户名检索用户密码
		User param = new User();
		param.setAccount(identify);
		User user = userService.queryByEntity(param);
		if (user != null) {
			//设置用户密码
			pc.setPassword(user.getPassword());
		}
	}
}