package com.jiuqi.cosmos.dao;

import java.util.List;

import com.jiuqi.cosmos.entity.FoodRecipe;
import com.jiuqi.cosmos.pojo.AidInfo;

public interface FoodRecipeMapper {
    int insert(FoodRecipe record);
    
    /**
     * 查询数据库中有限的食谱集合
     *  按类别分组，按时间倒序，查询每一类别的第一条数据
     * @return
     */
    List<FoodRecipe> selectAll();
    
    int deleteByPrimaryKey(Integer recipeId);
    
    FoodRecipe selectByPrimaryKey(Integer recipeId);
    
    List<FoodRecipe> selectByClassifyId(Integer classifyId);
    List<FoodRecipe> selectByUserId(Integer userId);
    /**
	 * 据食谱recipeId查询其类别下的其他食谱
	 * @param recipeId
	 * @return
	 */
    List<FoodRecipe> selectRecipeListByOtherRecipe(Integer recipeId);//嵌套
    /**
	 * 查询当前用户（focusPostId）所关注的用户发表的食谱
	 * @param focusPostId
	 * @return
	 */
    List<FoodRecipe> selectRecipeByPostUserId(Integer focusPostId);
    /**
	 * 查询与用户userId有关的食谱主键
	 * @param userId
	 * @return
	 */
	List<AidInfo> selectReciIdByUid(Integer userId);
    
}