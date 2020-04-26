package com.jiuqi.cosmos.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiuqi.cosmos.entity.FoodRecipe;
import com.jiuqi.cosmos.service.RecipeService;
@Component
public class RecipeUtil {
	@Autowired
	RecipeService recipeService;
	@Autowired
	private RedisUtil<FoodRecipe> redisService;

	public static void bianli(List<FoodRecipe> recipeList, List<FoodRecipe> newList) {
		for (FoodRecipe rec : recipeList) {
			if (newList.contains(rec)) {
				continue;
			}
			newList.add(rec);
		}
	}

	public boolean initRecipeAllToRedis() {
		try {
			List<FoodRecipe> selectAll = recipeService.selectAll();
			return redisService.pushToList("notoken", selectAll);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
