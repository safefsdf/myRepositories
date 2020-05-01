package com.jiuqi.cosmos.service;

import java.util.List;

import com.jiuqi.cosmos.entity.User;

public interface UserService {

	int createUser(User user);

	 int updateUserinfo(User user);
	 
	 int modifyPwd(User user);

	User quaryByPhoneAndPassword(String phone, String password);

	User getByPhone(String phone);

	User getById(int userid);

	Integer deleteById(Integer userid);

	List<User> getAll();

}
