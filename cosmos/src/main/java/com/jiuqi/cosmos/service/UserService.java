package com.jiuqi.cosmos.service;

import java.util.List;

import com.jiuqi.cosmos.entity.User;

public interface UserService {

	boolean createUser(User user);

//	 int updateUserinfo(User user);

	User quaryByPhoneAndPassword(String phone, String password);

	User getByPhone(String phone);

	User getById(int userid);

	Integer deleteById(Integer userid);

	List<User> getAll();

	/**
	 * 更新数据库中的粉丝量
	 * 
	 * @param user
	 * @return
	 */
	int updateFocusCount(User user);
}
