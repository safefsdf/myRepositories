package com.jiuqi.cosmos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiuqi.cosmos.entity.User;
import com.jiuqi.cosmos.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 使用手机号和密码登录
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	public User login(String phone, String password) {

		User user = userService.getByPhone(phone); // 根据电话查询用户
		if (user != null) {
			user = userService.quaryByPhoneAndPassword(phone, password); // 根据手机号和密码查询用户
			if (user != null) {
				System.out.println("该用户存在");
				return user;
			} else {
				System.out.println("用户不存在");
			}
		} else {
		}
		return null;
	}
	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}
}
