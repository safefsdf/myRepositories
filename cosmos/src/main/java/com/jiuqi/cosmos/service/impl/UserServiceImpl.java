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
	public boolean createUser(User user) {
		return userDao.createUser(user);
	}

	@Override
	public int updateUserinfo(User user) {
		return userDao.updateUserinfo(user);
	}

	@Override
	public User quaryByPhoneAndPassword(String phone, String password) {
		return userDao.quaryByPhoneAndPassword(phone, password);
	}

	@Override
	public User getByPhone(String phone) {
		return userDao.getByPhone(phone);
	}

	@Override
	public User getById(int userid) {
		return userDao.getById(userid);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

}
