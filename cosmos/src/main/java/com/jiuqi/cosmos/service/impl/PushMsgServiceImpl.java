package com.jiuqi.cosmos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuqi.cosmos.dao.UserDao;
import com.jiuqi.cosmos.entity.FoodRecipe;

/**
 *	 用户的消息推送模块
 * @author hp
 *
 */
@Service
public class PushMsgServiceImpl {
	@Autowired
	UserDao userDao;
	
	/**
	 *  	根据用户主键，查询其所关注的用户列表
	 *  	所关注用户最近发送的食谱
	 * @param userId
	 */
	public List<FoodRecipe> getFocusUser(Integer userId) {
		List<FoodRecipe> recipeList = userDao.selectFoodRecipeByUser(userId);
		List<FoodRecipe> recipeList2 = userDao.selectRecipeFromLike(userId);
		List<FoodRecipe> recipeList3 = userDao.selectRecipeFromCollect(userId);
		recipeList.addAll(recipeList2);
		recipeList.addAll(recipeList3);
		return recipeList;
		
	}
	
	
	//写了30多分钟，调用了这么多方法，我仿佛不会数据库多表连接查询了？？？？？
	//多表连接，食谱表，点赞表，收藏表，关注表
}
