package com.jiuqi.cosmos.dao;

import java.util.List;

import com.jiuqi.cosmos.entity.FoodStep;

public interface FoodStepMapper {
	int insert(FoodStep record);
	 
	List<FoodStep> selectByRecipeId(Integer recipeId);
}