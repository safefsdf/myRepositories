package com.jiuqi.cosmos.dao;

import java.util.List;

import com.jiuqi.cosmos.entity.FoodRecipe;

public interface FoodRecipeMapper {
    int insert(FoodRecipe record);
    
    List<FoodRecipe> selectAll();
    
    int deleteByPrimaryKey(Integer recipeId);
    
    FoodRecipe selectByPrimaryKey(Integer recipeId);
    
    List<FoodRecipe> selectByClassifyId(Integer classifyId);
    List<FoodRecipe> selectByUserId(Integer userId);
}