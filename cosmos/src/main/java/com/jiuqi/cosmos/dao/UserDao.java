package com.jiuqi.cosmos.dao;

import java.util.List;

import com.jiuqi.cosmos.entity.FoodRecipe;
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
	
	int modifyPwd(User user);

	int updateByPrimaryKey(User user);

	/**
	 * 登录时根据手机号和密码查询
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	User quaryByPhoneAndPassword(String phone, String password);
	/**
	 *  用于忘记密码的查询
	 * 
	 * @param phone
	 * @param answer
	 * @return
	 */
	User queryByPhoneAndQuestion(String phone, String answer);
	 

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
	User getById(int userId);

	/**
	 * 返回所有用户信息
	 * 
	 * @return
	 */
	List<User> getAll();

	/**
	 * 查询所关注的用户发送的食谱集合
	 * 
	 * @param userId
	 * @return
	 */
	List<FoodRecipe> selectFoodRecipeByUser(Integer focusPostId);

	List<FoodRecipe> selectRecipeFromLike(Integer userId);

	List<FoodRecipe> selectRecipeFromCollect(Integer userId);
}
