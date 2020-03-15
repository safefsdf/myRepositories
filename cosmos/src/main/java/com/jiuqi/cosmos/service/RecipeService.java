package com.jiuqi.cosmos.service;

import java.util.List;

import com.jiuqi.cosmos.entity.ClassifyInfo;
import com.jiuqi.cosmos.entity.FoodRecipe;

public interface RecipeService {
	/**
	 * 	创建博客
	 * 	前端传入的食谱信息中，有步骤的List集合，获取该集合然后存入step表中，初始化recipe的steps信息
	 * @param recipe
	 * @return
	 */
	int insert(FoodRecipe recipe);
	
	/**
	 * 	根据ID删除博客
	 * @param recipeId
	 * @return
	 */
	String delete(Integer recipeId);
	/**
	 * 	根据recipeId查询FoodRecipe的信息
	 * @param recipeId
	 * @return
	 */
	FoodRecipe selectByPrimaryKey(Integer recipeId);
	
	/**
	 *  根据类别查询食谱集合
	 * @param classifyId
	 * @return
	 */
	List<FoodRecipe> selectByClassifyId(Integer classifyId);
	
	/**
	 * 	根据用户查询食谱集合
	 * @param userid
	 * @return
	 */
	List<FoodRecipe> selectByUserId(Integer userId);
	
	List<ClassifyInfo> selectClassifies();
	
	
}
