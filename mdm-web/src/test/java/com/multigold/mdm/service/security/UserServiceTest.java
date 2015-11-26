package com.multigold.mdm.service.security;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.multigold.upms.entity.security.User;
import com.multigold.upms.service.api.security.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
public class UserServiceTest {

	@Autowired
	private UserService<User, Long> userService;

	@Test
	public void getPageList() {
		User user = new User();
		Map<String, Object> users = userService.pageQueryEntity(10l, 10, user);
		System.out.println(users.size() + ":asdfasdfasdfasd################:"
				+ users);
	}

	@Test
	public void getList() {
		System.out.println("UserService:" + userService);
		List<User> users = userService.queryList();
		System.out.println("users:" + users);
	}

}
