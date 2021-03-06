package com.jiuqi.cosmos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuqi.cosmos.dao.UserDao;
import com.jiuqi.cosmos.entity.User;
import com.jiuqi.cosmos.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public int createUser(User user) {
		return userDao.insert(user);
	}

	@Override
	public User quaryByPhoneAndPassword(String phone, String password) {
		return userDao.quaryByPhoneAndPassword(phone, password);
	}

	@Override
	public User getByPhone(String phone) {
		return userDao.quaryByPhone(phone);
	}

	@Override
	public User getById(int userid) {
		return userDao.getById(userid);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public Integer deleteById(Integer userid) {
		return userDao.deleteByPrimaryKey(userid);
	}

	@Override
	public int updateUserinfo(User user) {
		return userDao.updateByPrimaryKey(user);
	}

	@Override
	public int modifyPwd(User user) {
		return userDao.modifyPwd(user);
	}
 

}
