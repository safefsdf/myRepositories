package com.jiuqi.cosmos.dao;

import java.util.List;

import com.jiuqi.cosmos.entity.User;

public interface UserDao {
	/**
	 * 根据用户名，密码，手机号注册
	 * 
	 * @param user
	 * @return
	 */
	int insert(User user);

	
	
	/**
	 * 注销
	 * 
	 * @param user
	 * @return
	 */
	int deleteByPrimaryKey(Integer userId);

	/**
	 * 更新数据库中的粉丝量
	 * 
	 * @param user
	 * @return
	 */
	int updateFocusCount(User user);
	
	int updateByPrimaryKey(User user);

	/**
	 * 修改信息
	 * 
	 * @param userid
	 * @return
	 */
//	 int updateUserinfo(User user);
	/**
	 * 登录时根据手机号和密码查询
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	User quaryByPhoneAndPassword(String phone, String password);

	/**
	 * 根据手机查用户
	 * 
	 * @param phone
	 * @return
	 */
	User quaryByPhone(String phone);

	/**
	 * 根据id查询
	 * 
	 * @param userid
	 * @return
	 */
	User getById(int userid);

	/**
	 * 返回所有用户信息
	 * 
	 * @return
	 */
	List<User> getAll();

}
